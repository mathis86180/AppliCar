package fr.eni.ecole.android.applivoiture;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.eni.ecole.android.applivoiture.dao.Database;
import fr.eni.ecole.android.applivoiture.dao.VoitureDAO;
import fr.eni.ecole.android.applivoiture.model.Voiture;

public class DetailsVoitureActivity extends AppCompatActivity {

    private ImageView imageVoiture;
    private TextView modeleMarque;
    private TextView prix;
    private TextView immatriculation;
    private TextView terrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_voiture);
        imageVoiture = (ImageView) findViewById(R.id.imageVoiture);
        modeleMarque = (TextView) findViewById(R.id.modele_marque);
        prix = (TextView) findViewById(R.id.prix);
        immatriculation = (TextView) findViewById(R.id.immatriculation);
        terrain = (TextView) findViewById(R.id.terrain);

        Intent intent = getIntent();
        String immatriculationQuery = intent.getStringExtra("immatriculation");
        Voiture v = VoitureDAO.findOneById(immatriculationQuery,DetailsVoitureActivity.this);
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

            Picasso.with(DetailsVoitureActivity.this)
                    .load(v.getImage())
                    .into(imageVoiture);
    }
}
