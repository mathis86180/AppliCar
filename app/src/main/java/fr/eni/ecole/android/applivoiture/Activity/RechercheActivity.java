package fr.eni.ecole.android.applivoiture.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.android.applivoiture.MainActivity;
import fr.eni.ecole.android.applivoiture.R;
import fr.eni.ecole.android.applivoiture.dao.VoitureDAO;
import fr.eni.ecole.android.applivoiture.model.Voiture;

public class RechercheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RangeBar echellePrix = (RangeBar)  findViewById(R.id.rangebar);

        float min = VoitureDAO.selectMin(RechercheActivity.this);
        float max = VoitureDAO.selectMax(RechercheActivity.this);

        int minInt = Math.round(min);
        int dizaineMin = ((minInt/10) % 10) * 10;
        if (min == dizaineMin)
        {
            echellePrix.setTickStart(min);
        } else {
            echellePrix.setTickStart(dizaineMin);
        }

        int maxInt = Math.round(max);
        int dizaineMax = ((maxInt/10) % 10) * 10;
        if (max == dizaineMax)
        {
            echellePrix.setTickEnd(max);
        } else {
            echellePrix.setTickEnd(dizaineMax);
        }

        echellePrix.setTickInterval(5F);
    }

    public void onRechercheVoitureClick(View view){

        CheckBox rechercheVille = (CheckBox) findViewById(R.id.checkVille);
        CheckBox rechercheCampagne = (CheckBox) findViewById(R.id.checkCampagne);
        RangeBar echellePrix = (RangeBar)  findViewById(R.id.rangebar);

        int villeRecherche = -1;
        int campagneRecherche = -1;
        int minPrix = -1;
        int maxPrix = -1;

        if(rechercheVille.isChecked())
        {
            villeRecherche = 1;
        } else {
            villeRecherche = 0;
        }

        if(rechercheCampagne.isChecked())
        {
            campagneRecherche = 1;
        } else {
            campagneRecherche = 0;
        }

        minPrix =  Integer.parseInt(echellePrix.getLeftPinValue());
        maxPrix = Integer.parseInt(echellePrix.getRightPinValue());

        Intent intent = new Intent(RechercheActivity.this,ListeVoitureLoueActivity.class);

        intent.putExtra("location",3);

        intent.putExtra("villeRecherche",villeRecherche);
        intent.putExtra("campagneRecherche",campagneRecherche);
        intent.putExtra("minPrix",minPrix);
        intent.putExtra("maxPrix",maxPrix);

        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
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
    }
}
