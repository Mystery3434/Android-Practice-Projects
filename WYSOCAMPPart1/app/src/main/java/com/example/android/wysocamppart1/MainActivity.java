package com.example.android.wysocamppart1;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Integer> img_type_A;
    public ArrayList<Integer> img_type_B;
    public ArrayList<Integer> img_type_C;
    public List<Item> items;

    public void createProbabilityList(int probA, int probB, int probC)
    {
        items.clear();
        items.add(new Item("A", probA));
        items.add(new Item("B", probB));
        items.add(new Item("C", probC));
    }
    public void populate_type_A()
    {
        img_type_A = new ArrayList<>();
        img_type_A.add(R.drawable.energy_drink_a);
        img_type_A.add(R.drawable.pistol_1_a);
        img_type_A.add(R.drawable.pistol_2_a);
        img_type_A.add(R.drawable.red_dot_a);

    }

    public void populate_type_B()
    {
        img_type_B = new ArrayList<>();
        img_type_B.add(R.drawable.first_aid_kit_b);
        img_type_B.add(R.drawable.helmet_b);
        img_type_B.add(R.drawable.scope_4x_b);
        img_type_B.add(R.drawable.uzi_b);
    }

    public void populate_type_C()
    {
        img_type_C = new ArrayList<>();
        img_type_C.add(R.drawable.akm_c);
        img_type_C.add(R.drawable.awm_c);
        img_type_C.add(R.drawable.med_kit_c);
        img_type_C.add(R.drawable.scope_15x_c);
        img_type_C.add(R.drawable.vest_c);
    }

    public char randomSelect(List<Item> items)
    {
        Random rand = new Random();
        int totalSum = 0;
        for(Item item : items) {
            totalSum = totalSum + item.getRelativeProbability();
        }
        int index = rand.nextInt(totalSum);
        int sum = 0;
        int i=0;
        while(sum < index ) {
            sum = sum + items.get(i++).getRelativeProbability();
        }
        return items.get(Math.max(0,i-1)).getTypeOfItem().charAt(0);

    }
    public void clickTypeA(View view)
    {

        createProbabilityList(80, 15, 5);
        char typeToSelect = randomSelect(items);
        displayRandomItemOfType(typeToSelect);

    }

    public void clickTypeB(View view)
    {

        createProbabilityList(70, 20, 10);
        char typeToSelect = randomSelect(items);
        displayRandomItemOfType(typeToSelect);
    }

    public void clickTypeC(View view)
    {

        createProbabilityList(50, 30, 20);
        char typeToSelect = randomSelect(items);
        displayRandomItemOfType(typeToSelect);
    }

    public void displayRandomItemOfType(char c)
    {
        Random random = new Random();
        int index;
        ImageView imageView = (ImageView) findViewById(R.id.item_image);




        switch (c)
        {
            case 'A':
                index = random.nextInt(4);
                imageView.setImageResource(img_type_A.get(index));

                break;

            case 'B':
                index = random.nextInt(4);
                imageView.setImageResource(img_type_B.get(index));
                break;

            case 'C':
                index = random.nextInt(5);
                imageView.setImageResource(img_type_C.get(index));
                break;

            default:
                index = 0;
                imageView.setImageResource(img_type_A.get(index));
                Log.e("Randomizing Error", "This should never happen.");
                break;

        }

        ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populate_type_A();
        populate_type_B();
        populate_type_C();
        Log.v("Items: ", img_type_A.toString());
        items = new ArrayList<>();

        Button restaurantButton = (Button) findViewById(R.id.restaurant_button);
        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restaurantIntent = new Intent(MainActivity.this, RestaurantActivity.class);
                startActivity(restaurantIntent);
            }
        });
        Button missionButton = (Button) findViewById(R.id.mission_button);
        missionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent missionIntent = new Intent(MainActivity.this, MissionActivity.class);
                startActivity(missionIntent);
            }
        });
        Button battleButton = (Button) findViewById(R.id.battle_button);
        battleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent battleIntent = new Intent(MainActivity.this, BattleActivity.class);
                startActivity(battleIntent);
            }
        });
//        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.image_container);
//
//        linearLayout.setBackgroundColor(Color.parseColor("#93A2A5"));
//        imageView.setBackgroundColor(Color.parseColor("#0AD0FB"));
//        imageView.setImageResource(img_type_A.get(3));
    }
}
