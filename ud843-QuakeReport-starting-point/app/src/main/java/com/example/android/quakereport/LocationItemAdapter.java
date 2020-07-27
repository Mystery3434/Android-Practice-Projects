package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LocationItemAdapter extends ArrayAdapter<LocationItem> {
    /**
     * @param context        The current context. Used to inflate the layout file.
     * @param locationItem A List of LocationItem objects to display in a list
     */

    public LocationItemAdapter(Context context, List<LocationItem> locationItem)
    {
        super(context,0,locationItem);

    }

    private String formatDate(Date d)
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String formattedDate = dateFormatter.format(d);
        return formattedDate;
    }

    private String formatTime(Date d)
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm a");
        String formattedTime = dateFormatter.format(d);
        return formattedTime;
    }

    private String[] findLocationComponents(String loc)
    {
        if(loc.contains("of"))
        {
            String locationData[] = loc.split("of");
            locationData[0] += " of";
            return locationData;
        }
        else
        {
            String locationData[] = {"Near",loc};
            return locationData;
        }
    }
    private int getMagnitudeColor(double magnitude)
    {
        int magnitudeColor=0;
        if(magnitude>=10)
            magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        else if (magnitude>=9&&magnitude<10)
            magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
        else if (magnitude>=8&&magnitude<9)
            magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
        else if (magnitude>=7&&magnitude<8)
            magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
        else if (magnitude>=6&&magnitude<7)
            magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
        else if (magnitude>=5&&magnitude<6)
            magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
        else if (magnitude>=4&&magnitude<5)
            magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
        else if (magnitude>=3&&magnitude<4)
            magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
        else if (magnitude>=2&&magnitude<3)
            magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
        else if (magnitude>=1&&magnitude<2)
            magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude1);

        return magnitudeColor;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        LocationItem currentLocationItem = getItem(position);

        double magnitude = currentLocationItem.getMagnitude();
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedMagnitude = decimalFormat.format(magnitude);



        TextView magnitudeView = (TextView)listItemView.findViewById(R.id.magnitude);
        magnitudeView.setText(formattedMagnitude);


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentLocationItem.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        //Splits the location text obtained from the JSON file, to generate two components: The first component (index 0) stores the location offset (distance from, or near/close to) while the second component (index 1) stores the name of the location/city.
        String locationComponents[] = findLocationComponents(currentLocationItem.getCity());

        TextView locationOffsetView = (TextView)listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationComponents[0]);

        TextView cityView = (TextView)listItemView.findViewById(R.id.primary_location);
        cityView.setText(locationComponents[1]);

        Date date = new Date(currentLocationItem.getDate());

        TextView dateView = (TextView)listItemView.findViewById(R.id.date);
        String dateToDisplay = formatDate(date);
        dateView.setText(dateToDisplay);

        TextView timeView = (TextView)listItemView.findViewById(R.id.time);
        String timeToDisplay = formatTime(date);
        timeView.setText(timeToDisplay);

        return listItemView;
    }
}
