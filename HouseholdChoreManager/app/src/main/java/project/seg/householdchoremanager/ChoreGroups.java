package project.seg.householdchoremanager;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.graphics.Color;

public class ChoreGroups extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_groups);


        String[] choreList = {"Chore 1", "Chore 2", "Chore 3", "Chore 4", "Chore 5"};
        final ListView listView = (ListView) findViewById(R.id.choreList);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this, choreList);
        listView.setAdapter(adapter);
        String[] choreList2 = {"Chore test", "Chore 2", "Chore 3", "Chore 4", "Chore 5"};
        final ListView listView2 = (ListView) findViewById(R.id.choreList2);
        ChoreCustomAdapter adapter2 = new ChoreCustomAdapter(this, choreList2);
        listView2.setAdapter(adapter2);
        String[] choreList3 = {"Chore test 2", "Chore 2", "Chore 3", "Chore 4", "Chore 5"};
        final ListView listView3 = (ListView) findViewById(R.id.choreList3);
        ChoreCustomAdapter adapter3 = new ChoreCustomAdapter(this, choreList3);
        listView3.setAdapter(adapter3);
        String[] choreList4 = {"Chore test 3", "Chore 2", "Chore 3", "Chore 4", "Chore 5"};
        final ListView listView4 = (ListView) findViewById(R.id.choreList4);
        ChoreCustomAdapter adapter4 = new ChoreCustomAdapter(this, choreList4);
        listView4.setAdapter(adapter4);
        String[] choreList5 = {"Chore test 4", "Chore 2", "Chore 3", "Chore 4", "Chore 5"};
        final ListView listView5 = (ListView) findViewById(R.id.choreList5);
        ChoreCustomAdapter adapter5 = new ChoreCustomAdapter(this, choreList5);
        listView5.setAdapter(adapter5);

        listView.setVisibility(View.VISIBLE);
        listView2.setVisibility(View.INVISIBLE);
        listView3.setVisibility(View.INVISIBLE);
        listView4.setVisibility(View.INVISIBLE);
        listView5.setVisibility(View.INVISIBLE);

        //Group Buttons
        final Button Bedroom = (Button) findViewById(R.id.BedroomBtn);
        final Button Kitchen = (Button) findViewById(R.id.KitchenBtn);
        final Button Bathroom = (Button) findViewById(R.id.BathroomBtn);
        final Button Outdoor = (Button) findViewById(R.id.OutdoorBtn);
        final Button FullHouse = (Button) findViewById(R.id.FullHouseBtn);

        Bedroom.setBackgroundColor(Color.parseColor("#1c739d"));
        Kitchen.setBackgroundColor(Color.parseColor("#59a2ce"));
        Bathroom.setBackgroundColor(Color.parseColor("#59a2ce"));
        Outdoor.setBackgroundColor(Color.parseColor("#59a2ce"));
        FullHouse.setBackgroundColor(Color.parseColor("#59a2ce"));


        //onclick hide different lists
        Bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bedroom.setBackgroundColor(Color.parseColor("#1c739d"));
                Kitchen.setBackgroundColor(Color.parseColor("#59a2ce"));
                Bathroom.setBackgroundColor(Color.parseColor("#59a2ce"));
                Outdoor.setBackgroundColor(Color.parseColor("#59a2ce"));
                FullHouse.setBackgroundColor(Color.parseColor("#59a2ce"));
                listView2.setVisibility(View.INVISIBLE);
                listView4.setVisibility(View.INVISIBLE);
                listView5.setVisibility(View.INVISIBLE);
                listView3.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.VISIBLE);
            }
        });
        Kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bedroom.setBackgroundColor(Color.parseColor("#59a2ce"));
                Kitchen.setBackgroundColor(Color.parseColor("#1c739d"));
                Bathroom.setBackgroundColor(Color.parseColor("#59a2ce"));
                Outdoor.setBackgroundColor(Color.parseColor("#59a2ce"));
                FullHouse.setBackgroundColor(Color.parseColor("#59a2ce"));
                listView.setVisibility(View.INVISIBLE);
                listView4.setVisibility(View.INVISIBLE);
                listView3.setVisibility(View.INVISIBLE);
                listView5.setVisibility(View.INVISIBLE);
                listView2.setVisibility(View.VISIBLE);
            }
        });
        Bathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bedroom.setBackgroundColor(Color.parseColor("#59a2ce"));
                Kitchen.setBackgroundColor(Color.parseColor("#59a2ce"));
                Bathroom.setBackgroundColor(Color.parseColor("#1c739d"));
                Outdoor.setBackgroundColor(Color.parseColor("#59a2ce"));
                FullHouse.setBackgroundColor(Color.parseColor("#59a2ce"));
                listView.setVisibility(View.INVISIBLE);
                listView4.setVisibility(View.INVISIBLE);
                listView2.setVisibility(View.INVISIBLE);
                listView5.setVisibility(View.INVISIBLE);
                listView3.setVisibility(View.VISIBLE);
            }
        });
        Outdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bedroom.setBackgroundColor(Color.parseColor("#59a2ce"));
                Kitchen.setBackgroundColor(Color.parseColor("#59a2ce"));
                Bathroom.setBackgroundColor(Color.parseColor("#59a2ce"));
                Outdoor.setBackgroundColor(Color.parseColor("#1c739d"));
                FullHouse.setBackgroundColor(Color.parseColor("#59a2ce"));
                listView.setVisibility(View.INVISIBLE);
                listView2.setVisibility(View.INVISIBLE);
                listView3.setVisibility(View.INVISIBLE);
                listView5.setVisibility(View.INVISIBLE);
                listView4.setVisibility(View.VISIBLE);
            }
        });
        FullHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bedroom.setBackgroundColor(Color.parseColor("#59a2ce"));
                Kitchen.setBackgroundColor(Color.parseColor("#59a2ce"));
                Bathroom.setBackgroundColor(Color.parseColor("#59a2ce"));
                Outdoor.setBackgroundColor(Color.parseColor("#59a2ce"));
                FullHouse.setBackgroundColor(Color.parseColor("#1c739d"));
                listView.setVisibility(View.INVISIBLE);
                listView2.setVisibility(View.INVISIBLE);
                listView3.setVisibility(View.INVISIBLE);
                listView4.setVisibility(View.INVISIBLE);
                listView5.setVisibility(View.VISIBLE);
            }
        });

        Button groupsButton = (Button) findViewById(R.id.toGroupButton);
        Button homeButton = (Button) findViewById(R.id.toHomeButton);
        Button logoutButton = (Button) findViewById(R.id.logoutButton);


        groupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoreGroups.this, ChoreGroups.class));
            }
        });
//        homeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ChoreGroups.this, yourChores.class));
//            }
//        });
    }
}
