package fr.eni.ecole.android.applivoiture.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.android.applivoiture.R;
import fr.eni.ecole.android.applivoiture.dao.Database;
import fr.eni.ecole.android.applivoiture.dao.LocationDAO;
import fr.eni.ecole.android.applivoiture.dao.VoitureDAO;
import fr.eni.ecole.android.applivoiture.model.Voiture;

public class ListeVoitureLoueActivity extends AppCompatActivity {
    private ListView listViewVoiture;
    private List<Voiture> listVoiture = new ArrayList<>();
    private Integer loue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_voiture_loue);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listViewVoiture = (ListView) findViewById(R.id.listViewVoiture);
        Database.getInstance(ListeVoitureLoueActivity.this);
        Integer intentReceive = getIntent().getIntExtra("location",2);

        if(intentReceive == 1){
            listVoiture = LocationDAO.findVoitureLouees(ListeVoitureLoueActivity.this,LocationDAO.getQueryFindLocationNonTerminees());
            loue = 1;
        }else if(intentReceive == 0){
            listVoiture = LocationDAO.findVoitureLouees(ListeVoitureLoueActivity.this,LocationDAO.getQueryFindVoituresDisponibles());
            loue = 0;
            listVoiture = VoitureDAO.findVoitures(ListeVoitureLoueActivity.this,VoitureDAO.getQuerySelectNonLouees());
        }else if(intentReceive == 3){

            int villeRecherche = getIntent().getIntExtra("villeRecherche",1);
            int campagneRecherche = getIntent().getIntExtra("campagneRecherche",1);
            int minPrix = getIntent().getIntExtra("minPrix",1);
            int maxPrix = getIntent().getIntExtra("maxPrix",1);

            listVoiture = VoitureDAO.findRechercheVoitures(ListeVoitureLoueActivity.this, villeRecherche,
                    campagneRecherche, minPrix, maxPrix);
        }
            listViewVoiture.setAdapter(new ListeVoitureLoueAdapter(ListeVoitureLoueActivity.this,R.layout.ma_liste, listVoiture));
                listViewVoiture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Voiture v = listVoiture.get(position);
                        Intent intent = new Intent(ListeVoitureLoueActivity.this, DetailsVoitureActivity.class);
                        intent.putExtra("immatriculation",v.getImmatriculation());
                        intent.putExtra("loue",loue);
                        startActivity(intent);
                    }
                });

        listViewVoiture.setAdapter(new ListeVoitureLoueAdapter(ListeVoitureLoueActivity.this,R.layout.ma_liste, listVoiture));
        listViewVoiture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Voiture v = listVoiture.get(position);
                Intent intent = new Intent(ListeVoitureLoueActivity.this, DetailsVoitureActivity.class);
                intent.putExtra("immatriculation",v.getImmatriculation());
                startActivity(intent);
            }
        });
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
