package fr.eni.ecole.android.applivoiture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.android.applivoiture.dao.Database;
import fr.eni.ecole.android.applivoiture.dao.VoitureDAO;
import fr.eni.ecole.android.applivoiture.model.Voiture;

public class ListeVoitureLoueActivity extends AppCompatActivity {
    private ListView listViewVoiture;
    private List<Voiture> listVoiture = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_voiture_loue);
        listViewVoiture = (ListView) findViewById(R.id.listViewVoiture);
        Database.getInstance(ListeVoitureLoueActivity.this);
        Integer intentReceive = getIntent().getIntExtra("location",2);
        if(intentReceive == 1){
            listVoiture = VoitureDAO.findVoitures(ListeVoitureLoueActivity.this,VoitureDAO.getQuerySelectLouees());
        }else if(intentReceive == 0){
            listVoiture = VoitureDAO.findVoitures(ListeVoitureLoueActivity.this,VoitureDAO.getQuerySelectNonLouees());
        }
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
    }
