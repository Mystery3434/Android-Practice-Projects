package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.*;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    int quantity = 0;



    public String createOrderSummary(int price, String customer, boolean addWhippedCream, boolean addChocolate)
    {
        int total = price*quantity;
        String message = getString(R.string.order_summary_name, customer);
        message += "\n"+getString(R.string.add_whipped_cream);
        message+=addWhippedCream;
        message+="\n"+getString(R.string.add_chocolate);
        message+=addChocolate;
        message+="\n"+getString(R.string.quantity);
        message+=quantity;
        message+="\n"+getString(R.string.thank_you);

        return message;
    }

    public void submitOrder(View view) {

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = checkBox1.isChecked();
        CheckBox checkBox2 = (CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = checkBox2.isChecked();
        EditText customerName = (EditText) findViewById(R.id.name_edit_box);
        String customer = customerName.getText().toString();
        int price = 5;
        if(hasWhippedCream)
            price+=1;
        if(hasChocolate)
            price+=2;
        String message = createOrderSummary(price, customer, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "romario.vaz@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void increment(View view) {

        if(quantity<100)
            display(++quantity);
        else
        {
            Toast.makeText(this, "You cannot order more than 100 coffees.",Toast.LENGTH_SHORT).show();
        }

    }
    public void decrement(View view) {

        if(quantity==0)
        {
            Toast.makeText(this, "You cannot order a negative number of coffees.",Toast.LENGTH_SHORT).show();
            display(0);
        }
        else
            display(--quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}