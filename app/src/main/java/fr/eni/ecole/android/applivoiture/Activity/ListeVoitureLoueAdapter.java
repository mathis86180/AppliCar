package fr.eni.ecole.android.applivoiture.Activity;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.eni.ecole.android.applivoiture.R;
import fr.eni.ecole.android.applivoiture.model.Voiture;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class ListeVoitureLoueAdapter extends ArrayAdapter<Voiture> {
    private LayoutInflater inflater;
    private Integer resId;

    public ListeVoitureLoueAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Voiture> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        resId = resource;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null)
        {
            convertView = inflater.inflate(resId, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewMarque = (TextView) convertView.findViewById(R.id.marque);
            viewHolder.textViewModele = (TextView) convertView.findViewById(R.id.modele);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Voiture item = getItem(position);

        viewHolder.textViewMarque.setText(item.getMarque());
        viewHolder.textViewModele.setText(item.getModele());
        return convertView;
    }

    private class ViewHolder {
        TextView textViewMarque;
        TextView textViewModele;
    }

}
