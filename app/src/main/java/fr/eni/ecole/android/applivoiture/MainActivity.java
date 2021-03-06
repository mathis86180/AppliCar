package fr.eni.ecole.android.applivoiture;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import fr.eni.ecole.android.applivoiture.Activity.AjoutClientActivity;
import fr.eni.ecole.android.applivoiture.Activity.AjoutVehiculeActivity;
import fr.eni.ecole.android.applivoiture.Activity.DetailsVoitureActivity;
import fr.eni.ecole.android.applivoiture.Activity.ListeVoitureLoueActivity;
import fr.eni.ecole.android.applivoiture.Activity.LoginActivity;
import fr.eni.ecole.android.applivoiture.Activity.RechercheActivity;
import fr.eni.ecole.android.applivoiture.dao.AgenceDAO;
import fr.eni.ecole.android.applivoiture.dao.ClientDAO;
import fr.eni.ecole.android.applivoiture.dao.Database;
import fr.eni.ecole.android.applivoiture.dao.GerantDAO;
import fr.eni.ecole.android.applivoiture.dao.LocationDAO;
import fr.eni.ecole.android.applivoiture.dao.VoitureDAO;
import fr.eni.ecole.android.applivoiture.model.Agence;
import fr.eni.ecole.android.applivoiture.model.Client;
import fr.eni.ecole.android.applivoiture.model.Gerant;
import fr.eni.ecole.android.applivoiture.model.Location;
import fr.eni.ecole.android.applivoiture.model.Voiture;

import static fr.eni.ecole.android.applivoiture.R.id.textView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_CODE = 1;

    private Integer location = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*TextView adressMail = (TextView) findViewById(textView);
        Intent intent = getIntent();
        String mail = intent.getStringExtra("Mail");
        adressMail.setText(mail);*/

        Database.getInstance(MainActivity.this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.Ajout) {
           Intent intent = new Intent(MainActivity.this, AjoutVehiculeActivity.class);
           startActivityForResult(intent, REQUEST_CODE);
        } else if (id == R.id.Recherche) {
           Intent intent = new Intent(MainActivity.this, RechercheActivity.class);
           startActivityForResult(intent, REQUEST_CODE);
        } else if (id == R.id.Parametre) {
           Toast.makeText(MainActivity.this, "En Travaux !", Toast.LENGTH_LONG).show();
        } else if (id == R.id.AjoutClient) {
           Intent intent = new Intent(MainActivity.this, AjoutClientActivity.class);
           startActivity(intent);
       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void chiffre_affaire(View view){
        Toast.makeText(MainActivity.this, "En Travaux !", Toast.LENGTH_LONG).show();
    }

    public void getListVoiture(View view){
        switch (view.getId()){
            case R.id.listLoueeButton:
                location = 1;
                break;
            case R.id.listNonLoueeButton:
                location = 0;
                break;
        }
        Intent intent = new Intent(MainActivity.this,ListeVoitureLoueActivity.class);
        intent.putExtra("location",location);
        startActivity(intent);
    }

    public void openPageConnexion(View view){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
