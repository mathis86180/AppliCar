package fr.eni.ecole.android.applivoiture.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.eni.ecole.android.applivoiture.R;
import fr.eni.ecole.android.applivoiture.dao.AgenceDAO;
import fr.eni.ecole.android.applivoiture.dao.GerantDAO;
import fr.eni.ecole.android.applivoiture.model.Agence;
import fr.eni.ecole.android.applivoiture.model.Gerant;

public class InscriptionActivity extends AppCompatActivity {

    private EditText editNom;
    private EditText editPrenom;
    private EditText editMail;
    private EditText editMdp;
    private EditText editMdpRepeat;

    private Agence a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        editNom = (EditText) findViewById(R.id.editNom);
        editPrenom = (EditText) findViewById(R.id.editPrenom);
        editMail = (EditText) findViewById(R.id.editMail);
        editMdp = (EditText) findViewById(R.id.editMdp);
        editMdpRepeat = (EditText) findViewById(R.id.editMdpRepeat);
    }

    public void inscription(View view){
        String nom = editNom.getText().toString();
        String prenom = editPrenom.getText().toString();
        String mail = editMail.getText().toString();
        String mdp = editMdp.getText().toString();
        String mdpRepeat = editMdpRepeat.getText().toString();
        if(!nom.equals("") && !prenom.equals("") && !mail.equals("") && !mdp.equals("") && !mdpRepeat.equals("")){
            if(mdp.equals(mdpRepeat)) {
                Agence agence = new Agence(1,"test");
                AgenceDAO.insert(InscriptionActivity.this, agence);
                a = AgenceDAO.findOneById(1,InscriptionActivity.this);
                Gerant gTest = GerantDAO.findByMail(mail,InscriptionActivity.this);
                Gerant g = new Gerant(nom, prenom, mail, mdp, a);
                if(gTest != null) {
                        Toast.makeText(this, "Le mail est déjà pris par un autre utilisateur", Toast.LENGTH_SHORT).show();
                    } else {
                        GerantDAO.insert(InscriptionActivity.this, g);
                        editNom.setText("");
                        editPrenom.setText("");
                        editMail.setText("");
                        editMdp.setText("");
                        editMdpRepeat.setText("");
                        Toast.makeText(this, "Inscription validée", Toast.LENGTH_SHORT).show();
                    }
            }else{
                Toast.makeText(this, "Les mots de passe doivent être identiques", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Un des champs est vide !", Toast.LENGTH_SHORT).show();
        }
    }
}
