package fr.eni.ecole.android.applivoiture.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.eni.ecole.android.applivoiture.MainActivity;
import fr.eni.ecole.android.applivoiture.R;
import fr.eni.ecole.android.applivoiture.dao.ClientDAO;
import fr.eni.ecole.android.applivoiture.dao.LocationDAO;
import fr.eni.ecole.android.applivoiture.dao.VoitureDAO;
import fr.eni.ecole.android.applivoiture.model.Client;
import fr.eni.ecole.android.applivoiture.model.Location;
import fr.eni.ecole.android.applivoiture.model.Voiture;


public class LocationActivity extends AppCompatActivity {
    Calendar c = Calendar.getInstance();
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);
    int startDay = c.get(Calendar.DAY_OF_MONTH);
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date dateChoisie;

    private TextView textImmatriculation;
    private EditText editClient;
    private EditText editDateDebut;
    private ImageButton buttonDateDebut;
    private EditText editDateFin;
    private ImageButton buttonDateFin;
    private Button buttonValider;
    private ImageView imageVoiture;
    private TextView textVoitureLouee;
    Voiture v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textImmatriculation = (TextView) findViewById(R.id.textImmatriculation);
        editClient = (EditText) findViewById(R.id.editClient);
        editDateDebut = (EditText) findViewById(R.id.editDateDebut);
        buttonDateDebut = (ImageButton) findViewById(R.id.buttonDateDebut);
        editDateFin = (EditText) findViewById(R.id.editDateFin);
        buttonDateFin = (ImageButton) findViewById(R.id.buttonDateFin);
        buttonValider = (Button) findViewById(R.id.buttonValider);
        imageVoiture = (ImageView) findViewById(R.id.imageVoiture);
        textVoitureLouee = (TextView) findViewById(R.id.textVoitureLouee);

        Intent intent = getIntent();
        Integer loue = intent.getIntExtra("loue",2);
        String immatriculation = intent.getStringExtra("immatriculation");
        v = VoitureDAO.findOneById(immatriculation, LocationActivity.this);
        textImmatriculation.setText(v.getImmatriculation() + " " + " " + v.getMarque() + " " +  v.getModele());
        if(loue == 1){
            editClient.setVisibility(View.INVISIBLE);
            editDateDebut.setVisibility(View.INVISIBLE);
            editDateFin.setVisibility(View.INVISIBLE);
            buttonDateDebut.setVisibility(View.INVISIBLE);
            buttonDateFin.setVisibility(View.INVISIBLE);
            buttonValider.setVisibility(View.INVISIBLE);
            textVoitureLouee.setVisibility(View.VISIBLE);
            textVoitureLouee.setText("Cette voiture est déjà louée par un autre client !");
        }

        File imgFile = new  File(v.getImage());

        Picasso.with(LocationActivity.this)
                .load(imgFile)
                .into(imageVoiture);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void AjouterDate(View view){
        DialogFragment dialogFragment = new StartDatePicker(view);
        dialogFragment.show(getFragmentManager(), "start_date_picker");
    }

    public void enregistrerLocation(View view) throws ParseException {
        Client client = ClientDAO.findOneByMail(editClient.getText().toString(),LocationActivity.this);
        Date dateDebut = df.parse(editDateDebut.getText().toString());
        Date dateFin = df.parse(editDateFin.getText().toString());
        Location location = new Location(v,client,dateDebut,dateFin);
        LocationDAO.insert(location,LocationActivity.this);

        Intent intent = new Intent(LocationActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public class StartDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        View v;

        public StartDatePicker(View view) {
            v = view;

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            // Use the current date as the default date in the picker
            DatePickerDialog dialog = new DatePickerDialog(LocationActivity.this, this, startYear, startMonth, startDay);
            return dialog;

        }
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            // Do something with the date chosen by the user
            startYear = year;
            startMonth = monthOfYear + 1;
            startDay = dayOfMonth;
            try {
               dateChoisie = df.parse(String.valueOf(startYear + "-" + startMonth + "-" + startDay));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.e("DATE CHOISIE",df.format(dateChoisie));
            switch (v.getId()){
                case R.id.buttonDateDebut:
                    editDateDebut.setText(df.format(dateChoisie));
                    break;
                case R.id.buttonDateFin:
                    editDateFin.setText(df.format(dateChoisie));
                    break;
            }
        }

    }
}
