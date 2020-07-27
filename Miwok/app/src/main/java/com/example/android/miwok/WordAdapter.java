package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.*;


/**
 * Created by Romario on 31-Aug-17.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private int colorResourceID;
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word currentWord = getItem(position);
        TextView miwokTextView = (TextView)listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());



        TextView defaultTextView = (TextView)listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getEnglishTranslation());


        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);

        //boolean x =  currentWord.hasImage();
        //String s = String.valueOf(x);
        //defaultTextView.setText(s);


        if(currentWord.hasImage())
        {

            iconView.setImageResource(currentWord.getImageResourceID());
          iconView.setVisibility(View.VISIBLE);

       }
        else
        {

          iconView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), colorResourceID);
        textContainer.setBackgroundColor(color);


         return listItemView;



    }

    public WordAdapter(Activity context, ArrayList<Word> words, int color)
    {
        super(context, 0, words);
        colorResourceID = color;
    }
}
