package fr.eni.ecole.android.applivoiture.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import fr.eni.ecole.android.applivoiture.R;
import fr.eni.ecole.android.applivoiture.dao.VoitureDAO;
import fr.eni.ecole.android.applivoiture.model.Voiture;
import fr.eni.ecole.android.applivoiture.util.CRUDphoto;
import fr.eni.ecole.android.applivoiture.util.CRUDvoiture;

/**
 * Created by afillon2016 on 28/06/2017.
 */

public class ModifierActivity extends AppCompatActivity {

    String immatriculationModif = null;
    Boolean checkVille = false;
    Boolean checkCampagne = false;
    String cheminDefault = "defaut";

    private static final int REQUEST_CODE = 1;
    private static final int PICK_IMAGE_REQUEST = 2;
    private static final String TAG = "Ajout";
    private Uri mImageCaptureUri;
    private ImageButton action_Photo;

    private CRUDphoto crudPhoto = new CRUDphoto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_vehicule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        Intent intent = getIntent();
        immatriculationModif = intent.getStringExtra("immatriculation");
        Voiture voiture = getVoiture(immatriculationModif);

        ImageView image = (ImageView) findViewById(R.id.imageViewPhoto);
        TextView cheminPhoto = (TextView) findViewById(R.id.cheminPhoto);
        CheckBox ville = (CheckBox) findViewById(R.id.checkVille);
        CheckBox campagne = (CheckBox) findViewById(R.id.checkCampagne);
        EditText prix = (EditText) findViewById(R.id.textPrix);
        EditText marque = (EditText) findViewById(R.id.textMarque);
        EditText immatriculation = (EditText) findViewById(R.id.textImmatriculation);
        EditText modele = (EditText) findViewById(R.id.textModele);
        EditText etat = (EditText) findViewById(R.id.textEtat);

        File imgFile = new File(voiture.getImage());

        Picasso.with(ModifierActivity.this)
            .load(imgFile)
            .into(image);

        cheminPhoto.setText(voiture.getImage());

        if (voiture.getVille() == 1){
            checkVille = true;
        }
        ville.setChecked(checkVille);

        if (voiture.getCampagne() == 1){
            checkCampagne = true;
        }
        campagne.setChecked(checkCampagne);

        prix.setText(voiture.getPrix_par_jour().toString());
        marque.setText(voiture.getMarque());
        immatriculation.setText(voiture.getImmatriculation());
        modele.setText(voiture.getModele());
        etat.setText(voiture.getEtat());
    }

    private Voiture getVoiture(String immatriculation){
        Voiture voiture = VoitureDAO.findOneById(immatriculation, ModifierActivity.this);
        return voiture;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ajout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_Ajouter:

                ImageView image = (ImageView) findViewById(R.id.imageViewPhoto);
                TextView cheminPhoto = (TextView) findViewById(R.id.cheminPhoto);
                CheckBox ville = (CheckBox) findViewById(R.id.checkVille);
                CheckBox campagne = (CheckBox) findViewById(R.id.checkCampagne);
                EditText prix = (EditText) findViewById(R.id.textPrix);
                EditText marque = (EditText) findViewById(R.id.textMarque);
                EditText immatriculation = (EditText) findViewById(R.id.textImmatriculation);
                EditText modele = (EditText) findViewById(R.id.textModele);
                EditText etat = (EditText) findViewById(R.id.textEtat);

                CRUDvoiture crudVoiture = new CRUDvoiture();
                crudVoiture.insertOrUpdateVoiture(image, cheminPhoto, ville, campagne, prix, marque, immatriculation,
                        modele, etat, ModifierActivity.this);

                final Intent detailsPage = new Intent(ModifierActivity.this, DetailsVoitureActivity.class);

                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000); // As I am using LENGTH_LONG in Toast
                            startActivity(detailsPage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                thread.start();

                break;

            case android.R.id.home:
                onBackPressed();
                break;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClicBoutonPhoto(View view){

        // Lancement de l'action : capture de l'image
        Intent intent_prendrePhoto = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
            }
            return;
        }

        if (intent_prendrePhoto.resolveActivity(getPackageManager()) != null) {                                                                   intent_prendrePhoto.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                mImageCaptureUri);
            startActivityForResult(intent_prendrePhoto, REQUEST_CODE);
        }
    }

    public void onClicBoutonGallery(View view){

        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
            return;
        }

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView image = (ImageView) findViewById(R.id.imageViewPhoto);
        TextView cheminPhoto = (TextView) findViewById(R.id.cheminPhoto);
        String cheminTestPhoto = cheminPhoto.getText().toString();

        crudPhoto.enregistrementPhoto(ModifierActivity.this, requestCode, PICK_IMAGE_REQUEST, resultCode, data,
                image, cheminPhoto, REQUEST_CODE, TAG, cheminTestPhoto);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE && permissions[0].equals(Manifest.permission.CAMERA) | requestCode == REQUEST_CODE && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                action_Photo.performClick();
            }
            else {
                Toast.makeText(ModifierActivity.this, "Permission refusée, modifier les paramétres", Toast.LENGTH_SHORT).show();
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }
}































