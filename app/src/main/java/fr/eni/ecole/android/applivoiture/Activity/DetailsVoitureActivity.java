package fr.eni.ecole.android.applivoiture.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

import fr.eni.ecole.android.applivoiture.R;
import fr.eni.ecole.android.applivoiture.dao.VoitureDAO;
import fr.eni.ecole.android.applivoiture.model.Voiture;

public class DetailsVoitureActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 18;
    private ImageView imageVoiture;
    private TextView modeleMarque;
    private TextView prix;
    private TextView immatriculation;
    private TextView terrain;
    private Button buttonLocation;
    Voiture v;
    Integer loue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_voiture);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageVoiture = (ImageView) findViewById(R.id.imageVoiture);
        modeleMarque = (TextView) findViewById(R.id.modele_marque);
        prix = (TextView) findViewById(R.id.prix);
        immatriculation = (TextView) findViewById(R.id.immatriculation);
        terrain = (TextView) findViewById(R.id.terrain);
        buttonLocation = (Button) findViewById(R.id.buttonLocation);

        Intent intent = getIntent();
        String immatriculationQuery = intent.getStringExtra("immatriculation");
        loue = intent.getIntExtra("loue",2);
        v = VoitureDAO.findOneById(immatriculationQuery,DetailsVoitureActivity.this);
        String campagne = "-campagne";
        String ville = "-ville";
        modeleMarque.setText(v.getMarque() + " " + v.getModele());
        prix.setText(v.getPrix_par_jour().toString() + " " + "€");
        immatriculation.setText(v.getImmatriculation());
        if(v.getVille() == 1 && v.getCampagne() == 1){
            terrain.setText(ville + " " + campagne);
        }else if(v.getVille() == 0 && v.getCampagne() == 1) {
            terrain.setText(campagne);
        }else if(v.getVille() == 1 && v.getCampagne() == 0){
            terrain.setText(ville);
        }

        File imgFile = new  File(v.getImage());

        /*if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageVoiture.setImageBitmap(myBitmap);
        }*/

        Picasso.with(DetailsVoitureActivity.this)
            .load(imgFile)
            .into(imageVoiture);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        Intent intent;

        switch (id){

            case R.id.action_Modifier:
                String idAModif = getId();
                intent = new Intent(DetailsVoitureActivity.this, ModifierActivity.class);
                intent.putExtra("immatriculation", idAModif);
                startActivityForResult(intent, REQUEST_CODE);
                break;

            case R.id.action_Supprimer:
                // TODO : Gerer suppression lors d'une location
                Toast.makeText(DetailsVoitureActivity.this, "Suppression", Toast.LENGTH_LONG).show();
                String voitureAsuppr = getId();
                VoitureDAO.delete(voitureAsuppr);
                Intent homepage = new Intent(DetailsVoitureActivity.this, ListeVoitureLoueActivity.class);
                startActivity(homepage);
                break;

            case android.R.id.home:
                onBackPressed();
                break;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    private String getId(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("immatriculation");
        return id;
    }

    public void openLocation(View view){
        Intent intent = new Intent(DetailsVoitureActivity.this,LocationActivity.class);
        intent.putExtra("immatriculation", v.getImmatriculation());
        intent.putExtra("loue",loue);
        startActivity(intent);
    }
}
