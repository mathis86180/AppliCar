package fr.eni.ecole.android.applivoiture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.eni.ecole.android.applivoiture.dao.Database;
import fr.eni.ecole.android.applivoiture.dao.GerantDAO;
import fr.eni.ecole.android.applivoiture.model.Gerant;

public class LoginActivity extends AppCompatActivity {

    private EditText editMail;
    private EditText editMdp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editMail = (EditText) findViewById(R.id.editMail);
        editMdp = (EditText) findViewById(R.id.editMdp);
    }

    public void seConnecter(View view){

        Gerant g;
        String mail = editMail.getText().toString();
        String mdp = editMdp.getText().toString();
        Database.getInstance(LoginActivity.this);
        if(mail != null && mdp != null) {
            g = GerantDAO.findByMailMdp(mail, mdp, LoginActivity.this);
            if(g != null)
            {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Le gérant n'existe pas", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void sinscrire(View view){
        Intent intent = new Intent(LoginActivity.this,InscriptionActivity.class);
        startActivity(intent);
    }
}
