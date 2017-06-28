package fr.eni.ecole.android.applivoiture;

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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import fr.eni.ecole.android.applivoiture.dao.VoitureDAO;
import fr.eni.ecole.android.applivoiture.model.Agence;
import fr.eni.ecole.android.applivoiture.model.Gerant;
import fr.eni.ecole.android.applivoiture.model.Voiture;

public class AjoutVehiculeActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final int PICK_IMAGE_REQUEST = 2;
    private static final String TAG = "Ajout";
    private Uri mImageCaptureUri;
    private ImageButton action_Photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_vehicule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

                Float prixAjout = null;
                int villeAjout = -1;
                int campagneAjout = -1;

                TextView cheminPhoto = (TextView) findViewById(R.id.cheminPhoto);
                CheckBox ville = (CheckBox) findViewById(R.id.checkVille);
                CheckBox campagne = (CheckBox) findViewById(R.id.checkCampagne);
                EditText prix = (EditText) findViewById(R.id.textPrix);
                EditText marque = (EditText) findViewById(R.id.textMarque);
                EditText immatriculation = (EditText) findViewById(R.id.textImmatriculation);
                EditText modele = (EditText) findViewById(R.id.textModele);
                EditText etat = (EditText) findViewById(R.id.textEtat);

                String ajoutChemin = cheminPhoto.getText().toString();

                if(ville.isChecked())
                {
                    villeAjout = 1;
                } else {
                    villeAjout = 0;
                }

                if(campagne.isChecked())
                {
                    campagneAjout = 1;
                } else {
                    campagneAjout = 0;
                }

                try {
                    prixAjout = Float.parseFloat(prix.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    prixAjout = 0F;
                }

                String ajoutMarque = marque.getText().toString();
                String ajoutImmatriculation = immatriculation.getText().toString();
                String ajoutModele = modele.getText().toString();
                String ajoutEtat = etat.getText().toString();
                int loueAjout = 0;

                // TODO : A remplacer avec utilisation de la BDD
                Gerant g = new Gerant("Fillon","Aurelien","fillonau@hotmail.fr", "af091294");
                Agence agenceAjout = new Agence("Fictive SA",g);

                if(villeAjout==1 || campagneAjout==1){
                    if(!ajoutChemin.isEmpty() && !prixAjout.isNaN() && !ajoutMarque.isEmpty() && !ajoutImmatriculation.isEmpty()
                            && !ajoutModele.isEmpty() && !ajoutEtat.isEmpty()){
                        Voiture newVoiture = new Voiture(loueAjout, villeAjout, campagneAjout, prixAjout, ajoutImmatriculation, ajoutEtat,
                                ajoutMarque, ajoutModele, agenceAjout, ajoutChemin);

                        VoitureDAO.insert(newVoiture, AjoutVehiculeActivity.this);
                        Toast.makeText(AjoutVehiculeActivity.this, "La voiture a bien été ajouté :)", Toast.LENGTH_LONG).show();
                        videAjout();
                    }
                    else
                    {
                        Toast.makeText(AjoutVehiculeActivity.this, "Tout les champs textes sont obligatoires !", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(AjoutVehiculeActivity.this, "Vous devez au moins valider la Ville ou la Campagne !", Toast.LENGTH_LONG).show();
                }
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

    @Override
    public void onBackPressed(){
        super.onBackPressed();

        TextView cheminPhoto = (TextView) findViewById(R.id.cheminPhoto);
        String ajoutChemin = cheminPhoto.getText().toString();
        deletePhoto(ajoutChemin);

    }

    public void onClicBoutonPhoto(View view){

        TextView cheminPhoto = (TextView) findViewById(R.id.cheminPhoto);
        String cheminTestPhoto = cheminPhoto.getText().toString();

        deletePhoto(cheminTestPhoto);

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

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
            image.setImageBitmap(bitmap);

            cheminPhoto.setText(imagePath.toString());

            cursor.close();
        }

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Récupération de la données
            Bitmap bit = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream dataImage = new ByteArrayOutputStream();
            bit.compress(Bitmap.CompressFormat.JPEG, 80, dataImage);
            ByteArrayOutputStream bos = (ByteArrayOutputStream)dataImage;

            // Affichage  de l'image
            image.setImageBitmap(bit);

            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    "picture"+ ts + ".jpg");
            OutputStream os = null;
            try {
                Log.e(TAG, "Writing ... "+file.getAbsolutePath());

                cheminPhoto.setText(file.getAbsolutePath());

                os = new FileOutputStream(file);
                os.write(bos.toByteArray());
                os.close();
            } catch (IOException e) {
                Log.e(TAG, "Cannot write to " + file, e);
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        // Ignore
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE && permissions[0].equals(Manifest.permission.CAMERA) | requestCode == REQUEST_CODE && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                action_Photo.performClick();
            }
            else {
                Toast.makeText(AjoutVehiculeActivity.this, "Permission refusée, modifier les paramétres", Toast.LENGTH_SHORT).show();
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    private void videAjout(){
        ImageView image = (ImageView) findViewById(R.id.imageViewPhoto);
        TextView cheminPhoto = (TextView) findViewById(R.id.cheminPhoto);
        CheckBox ville = (CheckBox) findViewById(R.id.checkVille);
        CheckBox campagne = (CheckBox) findViewById(R.id.checkCampagne);
        EditText prix = (EditText) findViewById(R.id.textPrix);
        EditText marque = (EditText) findViewById(R.id.textMarque);
        EditText immatriculation = (EditText) findViewById(R.id.textImmatriculation);
        EditText modele = (EditText) findViewById(R.id.textModele);
        EditText etat = (EditText) findViewById(R.id.textEtat);

        image.setImageResource(R.drawable.renault_megane);
        cheminPhoto.setText("");
        ville.setChecked(false);
        campagne.setChecked(false);
        prix.setText("");
        marque.setText("");
        immatriculation.setText("");
        modele.setText("");
        etat.setText("");
    }

    private void deletePhoto(String cheminPhoto){

        if(!cheminPhoto.equals("default")) {
            File file = new File(cheminPhoto);
            boolean deleted = file.delete();

            if (deleted == false) {
                Toast.makeText(AjoutVehiculeActivity.this, "Erreur suppression", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
