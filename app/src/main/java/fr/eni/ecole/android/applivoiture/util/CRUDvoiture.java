package fr.eni.ecole.android.applivoiture.util;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fr.eni.ecole.android.applivoiture.Activity.AjoutVehiculeActivity;
import fr.eni.ecole.android.applivoiture.R;
import fr.eni.ecole.android.applivoiture.dao.AgenceDAO;
import fr.eni.ecole.android.applivoiture.dao.VoitureDAO;
import fr.eni.ecole.android.applivoiture.model.Agence;
import fr.eni.ecole.android.applivoiture.model.Voiture;

/**
 * Created by afillon2016 on 28/06/2017.
 */

public class CRUDvoiture extends AppCompatActivity {

    String cheminDefault = "defaut";

    public void insertOrUpdateVoiture(ImageView image, TextView cheminPhoto, CheckBox ville, CheckBox campagne, EditText prix,
                                      EditText marque, EditText immatriculation, EditText modele,
                                      EditText etat, Context context){
        Float prixAjout = null;
        int villeAjout = -1;
        int campagneAjout = -1;

        String ajoutChemin = cheminPhoto.getText().toString();

        if(ville.isChecked())
        {
            villeAjout = 1;
        } else {
            villeAjout = 0;
        }

        if(campagne.isChecked())
        {
            campagneAjout = 1;
        } else {
            campagneAjout = 0;
        }

        try {
            prixAjout = Float.parseFloat(prix.getText().toString());
        }
        catch (NumberFormatException e)
        {
            prixAjout = 0F;
        }

        String ajoutMarque = marque.getText().toString();
        String ajoutImmatriculation = immatriculation.getText().toString();
        String ajoutModele = modele.getText().toString();
        String ajoutEtat = etat.getText().toString();
        int loueAjout = 0;

        // TODO : A remplacer avec utilisation de la BDD

        Agence agenceAjout = AgenceDAO.findOneById(1,context);
        if(agenceAjout == null)
        {
            agenceAjout=new Agence(1,"dkhpsdfkfhl");
            AgenceDAO.insert(context, agenceAjout);
        }

        if (!cheminPhoto.getText().equals(cheminDefault)) {
            if (villeAjout == 1 || campagneAjout == 1) {
                if (!ajoutChemin.isEmpty() && !prixAjout.isNaN() && !ajoutMarque.isEmpty() && !ajoutImmatriculation.isEmpty()
                        && !ajoutModele.isEmpty() && !ajoutEtat.isEmpty()) {

                    if(context.getClass().equals(AjoutVehiculeActivity.class)){
                        Voiture newVoiture = new Voiture(loueAjout, villeAjout, campagneAjout, prixAjout, ajoutImmatriculation, ajoutEtat,
                                ajoutMarque, ajoutModele, agenceAjout, ajoutChemin);

                        VoitureDAO.insert(newVoiture, context);
                        Toast.makeText(context, "La voiture a bien été ajouté :)", Toast.LENGTH_LONG).show();
                        videAjout(image, cheminPhoto, ville, campagne, prix, marque, immatriculation, modele, etat);
                    }
                    else {
                        Voiture newVoiture = new Voiture(loueAjout, villeAjout, campagneAjout, prixAjout, ajoutImmatriculation, ajoutEtat,
                                ajoutMarque, ajoutModele, agenceAjout, ajoutChemin);

                        VoitureDAO.update(newVoiture, context);
                        Toast.makeText(context, "La voiture a bien été modifié :)", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Tout les champs textes sont obligatoires !", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(context, "Vous devez au moins valider la Ville ou la Campagne !", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(context, "Vous devez changer la photo !", Toast.LENGTH_LONG).show();
        }
    }

    private void videAjout(ImageView image, TextView cheminPhoto, CheckBox ville, CheckBox campagne, EditText prix,
                           EditText marque, EditText immatriculation, EditText modele,
                           EditText etat){
        image.setImageResource(R.drawable.renault_megane);
        cheminPhoto.setText(cheminDefault);
        ville.setChecked(false);
        campagne.setChecked(false);
        prix.setText("");
        marque.setText("");
        immatriculation.setText("");
        modele.setText("");
        etat.setText("");
    }

}
