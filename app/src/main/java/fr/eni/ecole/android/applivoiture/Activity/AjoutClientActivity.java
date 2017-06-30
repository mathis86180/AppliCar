package fr.eni.ecole.android.applivoiture.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.eni.ecole.android.applivoiture.MainActivity;
import fr.eni.ecole.android.applivoiture.R;
import fr.eni.ecole.android.applivoiture.dao.ClientDAO;
import fr.eni.ecole.android.applivoiture.model.Client;

public class AjoutClientActivity extends AppCompatActivity {

    private EditText editNom;
    private EditText editPrenom;
    private EditText editAdresse;
    private EditText editVille;
    private EditText editMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_client);

        editNom = (EditText) findViewById(R.id.editNom);
        editPrenom = (EditText) findViewById(R.id.editPrenom);
        editAdresse = (EditText) findViewById(R.id.editAdresse);
        editVille = (EditText) findViewById(R.id.editVille);
        editMail = (EditText) findViewById(R.id.editMail);
    }

    public void enregistrerClient(View view){

        String nom = editNom.getText().toString();
        String prenom = editPrenom.getText().toString();
        String adresse = editAdresse.getText().toString();
        String ville = editVille.getText().toString();
        String mail = editMail.getText().toString();

        Client client = new Client(nom,prenom,adresse,ville,mail);
        ClientDAO.insert(client,AjoutClientActivity.this);
        Toast.makeText(this, "Client ajouté", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AjoutClientActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
