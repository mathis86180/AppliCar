package fr.eni.ecole.android.applivoiture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.android.applivoiture.model.Agence;
import fr.eni.ecole.android.applivoiture.model.Gerant;
import fr.eni.ecole.android.applivoiture.model.Voiture;

public class ListeVoitureLoueActivity extends AppCompatActivity {
    private ListView listViewVoiture;
    private List<Voiture> listVoiture = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_voiture_loue);
        listViewVoiture = (ListView) findViewById(R.id.listViewVoiture);

            Gerant g = new Gerant(1,"titi","titi","mail@mail.com");

            Agence a = new Agence(1,"agence1",g);
            listVoiture.add(new Voiture(1,1,0,1,20F,"cn-86-rdf","loué","renaud","megane",a));
            listVoiture.add(new Voiture(2,1,0,1,20F,"cn-86-rdf","loué","renaud","clio",a));
            listVoiture.add(new Voiture(3,1,0,1,20F,"cn-86-rdf","loué","renaud","captur",a));

                listViewVoiture.setAdapter(new ListeVoitureLoueAdapter(ListeVoitureLoueActivity.this,R.layout.ma_liste, listVoiture));

                listViewVoiture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Intent intent = new Intent(ListeVoitureLoueActivity.this, DetailsVoitureActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("object", listVoiture.get(position));
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
        }
    }
