package fr.eni.ecole.android.applivoiture.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RatingBar;

import fr.eni.ecole.android.applivoiture.R;
import fr.eni.ecole.android.applivoiture.model.Voiture;

/**
 * Created by afillon2016 on 28/06/2017.
 */

public class ModifierActivity extends AppCompatActivity {

    int idModif = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_vehicule);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        idModif = intent.getIntExtra("id", 1);
        //Voiture voiture = getVoiture(idModif);

        /*EditText nom = (EditText) findViewById(R.id.textNom);
        EditText prix = (EditText) findViewById(R.id.textPrix);
        EditText description = (EditText) findViewById(R.id.textDescription);
        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar);
        EditText lienUrl = (EditText) findViewById(R.id.textUrl);

        nom.setText(a.getNom());
        prix.setText(a.getPrix().toString());
        description.setText(a.getDescription());
        rating.setRating(a.getNote());
        lienUrl.setText(a.getLienUrl());*/

    }

    /*private Voiture getVoiture(int id){
        Intent intent = getIntent();
        Voiture voiture = articleDAO.FindOneById(id);
        return a;
    }*/

}































