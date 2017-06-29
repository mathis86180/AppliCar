package fr.eni.ecole.android.applivoiture.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import fr.eni.ecole.android.applivoiture.Activity.ModifierActivity;

import static android.R.attr.data;
import static android.app.Activity.RESULT_OK;

/**
 * Created by afillon2016 on 29/06/2017.
 */

public class CRUDphoto {

    private String cheminDefault = "defaut";

    public void enregistrementPhoto(Context context, int requestCode, int PICK_IMAGE_REQUEST, int resultCode,
                                    Intent data, ImageView image, TextView cheminPhoto, int REQUEST_CODE, String TAG,
                                    String cheminTestPhoto){

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {

            if(context.getClass().equals(ModifierActivity.class)) {
                deletePhoto(cheminTestPhoto, context);
            }

            // Let's read picked image data - its URI
            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = context.getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
            image.setImageBitmap(bitmap);

            cheminPhoto.setText(imagePath.toString());

            cursor.close();
        }

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Récupération de la données
            Bitmap bit = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream dataImage = new ByteArrayOutputStream();
            bit.compress(Bitmap.CompressFormat.JPEG, 100, dataImage);
            ByteArrayOutputStream bos = (ByteArrayOutputStream)dataImage;

            // Affichage  de l'image
            image.setImageBitmap(bit);

            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    "picture"+ ts + ".jpg");
            OutputStream os = null;
            try {
                Log.e(TAG, "Writing ... "+file.getAbsolutePath());

                cheminPhoto.setText(file.getAbsolutePath());

                os = new FileOutputStream(file);
                os.write(bos.toByteArray());
                os.close();
            } catch (IOException e) {
                Log.e(TAG, "Cannot write to " + file, e);
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        // Ignore
                    }
                }
            }
        }
    }

    public void deletePhoto(String cheminPhoto, Context context){

        if(!cheminPhoto.equals(cheminDefault)) {
            File file = new File(cheminPhoto);
            boolean deleted = file.delete();

            if (deleted == false) {
                Toast.makeText(context, "Erreur suppression", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
