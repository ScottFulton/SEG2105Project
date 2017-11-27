package project.seg.householdchoremanager;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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

        listView.setVisibility(View.INVISIBLE);
        listView2.setVisibility(View.INVISIBLE);
        listView3.setVisibility(View.INVISIBLE);
        listView4.setVisibility(View.INVISIBLE);

        //Group Buttons
        Button group1 = (Button) findViewById(R.id.button4);
        Button group2 = (Button) findViewById(R.id.button3);
        Button group3 = (Button) findViewById(R.id.button2);
        Button group4 = (Button) findViewById(R.id.button);

        //onclick hide different lists
        group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView2.setVisibility(View.INVISIBLE);
                listView4.setVisibility(View.INVISIBLE);
                listView3.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.VISIBLE);
            }
        });
        group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setVisibility(View.INVISIBLE);
                listView4.setVisibility(View.INVISIBLE);
                listView3.setVisibility(View.INVISIBLE);
                listView2.setVisibility(View.VISIBLE);
            }
        });
        group3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setVisibility(View.INVISIBLE);
                listView4.setVisibility(View.INVISIBLE);
                listView2.setVisibility(View.INVISIBLE);
                listView3.setVisibility(View.VISIBLE);
            }
        });
        group4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setVisibility(View.INVISIBLE);
                listView2.setVisibility(View.INVISIBLE);
                listView3.setVisibility(View.INVISIBLE);
                listView4.setVisibility(View.VISIBLE);
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
