package fr.eni.ecole.android.applivoiture;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class AjoutVehiculeActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private Uri mImageCaptureUri;
    private ImageView image_maison;
    private String cheminImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_vehicule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ajout, menu);
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    public void onClicBoutonPhoto(View view){

        // Lancement de l'action : capture de l'image
        Intent intent_prendrePhoto = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent_prendrePhoto.resolveActivity(getPackageManager()) != null) {                                                                   intent_prendrePhoto.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                mImageCaptureUri);
            startActivityForResult(intent_prendrePhoto, REQUEST_CODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // Récupération de la données
        Bitmap bit = (Bitmap) data.getExtras().get("data");


        ImageView image = (ImageView) findViewById(R.id.imageViewPhoto);

        // Affichage  de l'image
        image.setImageBitmap(bit);

        /*// Récupération du chemin de la photo
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        // Compression de la photo au format JPEG
        bit.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), bit, "photo", null);

        Uri cheminUri = Uri.parse(path);
        Cursor cursor = getContentResolver().query(cheminUri, null, null, null, null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        cheminImage = cursor.getString(index);*/

    }
}
