package project.seg.householdchoremanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.graphics.Color;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ChoreGroups extends AppCompatActivity {
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_groups);



        //all added for dynamic chores
        DatabaseHandler db = new DatabaseHandler(this);

        Chore[] allChores = db.getAllChores();

        Log.d("myTag", ""+allChores.length);

        ArrayList<Chore> bedList = new ArrayList<Chore>();
        ArrayList<Chore> kitList = new ArrayList<Chore>();
        ArrayList<Chore> bathList = new ArrayList<Chore>();
        ArrayList<Chore> outList = new ArrayList<Chore>();
        ArrayList<Chore> fullList = new ArrayList<Chore>();

        //these are all used for the resource lists
        Set<String> rListBed = new LinkedHashSet<>();
        Set<String> rListKit = new LinkedHashSet<>();
        Set<String> rListBath = new LinkedHashSet<>();
        Set<String> rListOut = new LinkedHashSet<>();
        Set<String> rListFull = new LinkedHashSet<>();
        List<String> list;

//        this is created to get the list of chores to then be added to a string array thus creating each chore dynamically
        for(Chore chore1 : allChores){

            list = new ArrayList<String>(Arrays.asList(chore1.getResources().split(",")));
            Log.d("secondTag","count: "+Arrays.asList(chore1.getResources().split(",")) + "size: " + list.size());

            //each of these if statements will add the chore the the right group list and put said shores resources in the correct list
            if(chore1.getGroup().equals("Bedroom")){
                bedList.add(chore1);
                rListBed.addAll(list);
            }else if(chore1.getGroup().equals("Kitchen")){
                kitList.add(chore1);
                rListKit.addAll(list);
            }else if(chore1.getGroup().equals("Bathroom")){
                bathList.add(chore1);
                rListBath.addAll(list);
            }else if(chore1.getGroup().equals("Outdoor")){
                outList.add(chore1);
                rListOut.addAll(list);
            }else{
                fullList.add(chore1);
                for(String list1 : list) {
                    rListFull.add(list1);
                }
            }
        }

        //create strings of the resources using stringbuilders of the resources.
        final String bedRes = rListBed.toString().replace("[","").replace("]","");
        final String kitRes = rListKit.toString().replace("[","").replace("]","");
        final String bathRes = rListBath.toString().replace("[","").replace("]","");
        final String outRes = rListOut.toString().replace("[","").replace("]","");
        final String fullRes = rListFull.toString().replace("[","").replace("]","");


        //initialize chorelists for each group
        String[] choreList = new String[bedList.size()];
        String[] choreList2 = new String[kitList.size()];
        String[] choreList3 = new String[bathList.size()];
        String[] choreList4 = new String[outList.size()];
        String[] choreList5 = new String[fullList.size()];



        String choreString;
        //creating each chore string for list
        for(int i = 0; i < bedList.size(); i++){
            Chore addingChore = bedList.get(i);
            String assigned = addingChore.getAssigned();
            if(addingChore.getAssigned() == null) {
                assigned = "No one";
            }
            choreString = addingChore.getName() + "\nDue: " + addingChore.getDueDate() + "\nAssigned to: "+assigned;
            choreList[i] = choreString;
        }
        for(int i = 0; i < kitList.size(); i++){
            Chore addingChore = kitList.get(i);
            String assigned = addingChore.getAssigned();
            if(addingChore.getAssigned() == null) {
                assigned = "No one";
            }
            choreString = addingChore.getName() + "\nDue: " + addingChore.getDueDate() + "\nAssigned to: "+assigned;
            choreList2[i] = choreString;
        }
        for(int i = 0; i < bathList.size(); i++){
            Chore addingChore = bathList.get(i);
            String assigned = addingChore.getAssigned();
            if(addingChore.getAssigned() == null) {
                assigned = "No one";
            }
            choreString = addingChore.getName() + "\nDue: " + addingChore.getDueDate() + "\nAssigned to: "+assigned;
            choreList3[i] = choreString;
        }
        for(int i = 0; i < outList.size(); i++){
            Chore addingChore = outList.get(i);
            String assigned = addingChore.getAssigned();
            if(addingChore.getAssigned() == null) {
                assigned = "No one";
            }
            choreString = addingChore.getName() + "\nDue: " + addingChore.getDueDate() + "\nAssigned to: "+assigned;
            choreList4[i] = choreString;
        }
        for(int i = 0; i < fullList.size(); i++){
            Chore addingChore = fullList.get(i);
            String assigned = addingChore.getAssigned();
            if(addingChore.getAssigned() == null) {
                assigned = "No one";
            }
            choreString = addingChore.getName() + "\nDue: " + addingChore.getDueDate() + "\nAssigned to: "+assigned;
            choreList5[i] = choreString;
        }

        //add all chores to each listview to be displayed on screen
        final ListView listView = (ListView) findViewById(R.id.choreList);
        ChoreCustomAdapter2 adapter = new ChoreCustomAdapter2(this, choreList, "Bedroom");
        listView.setAdapter(adapter);

        final ListView listView2 = (ListView) findViewById(R.id.choreList2);
        ChoreCustomAdapter2 adapter2 = new ChoreCustomAdapter2(this, choreList2, "Kitchen");
        listView2.setAdapter(adapter2);

        final ListView listView3 = (ListView) findViewById(R.id.choreList3);
        ChoreCustomAdapter2 adapter3 = new ChoreCustomAdapter2(this, choreList3, "Bathroom");
        listView3.setAdapter(adapter3);

        final ListView listView4 = (ListView) findViewById(R.id.choreList4);
        ChoreCustomAdapter2 adapter4 = new ChoreCustomAdapter2(this, choreList4, "Outdoor");
        listView4.setAdapter(adapter4);

        final ListView listView5 = (ListView) findViewById(R.id.choreList5);
        ChoreCustomAdapter2 adapter5 = new ChoreCustomAdapter2(this, choreList5, "Full House");
        listView5.setAdapter(adapter5);


        final TextView resList = (TextView)findViewById(R.id.resourcesTextView);

        //hide all group lists other than the first one
        listView.setVisibility(View.VISIBLE);
        listView2.setVisibility(View.INVISIBLE);
        listView3.setVisibility(View.INVISIBLE);
        listView4.setVisibility(View.INVISIBLE);
        listView5.setVisibility(View.INVISIBLE);
        resList.setText("Resources: " + bedRes);


        //Group Buttons
        final Button Bedroom = (Button) findViewById(R.id.BedroomBtn);
        final Button Kitchen = (Button) findViewById(R.id.KitchenBtn);
        final Button Bathroom = (Button) findViewById(R.id.BathroomBtn);
        final Button Outdoor = (Button) findViewById(R.id.OutdoorBtn);
        final Button FullHouse = (Button) findViewById(R.id.FullHouseBtn);
        //initialize group buttons as correct colour
        Bedroom.setBackgroundColor(Color.parseColor("#1c739d"));
        Kitchen.setBackgroundColor(Color.parseColor("#59a2ce"));
        Bathroom.setBackgroundColor(Color.parseColor("#59a2ce"));
        Outdoor.setBackgroundColor(Color.parseColor("#59a2ce"));
        FullHouse.setBackgroundColor(Color.parseColor("#59a2ce"));

        //onclick hide different lists and sets colour to dark if it is the one clicked
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
                resList.setText("Resources: " + bedRes);

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
                resList.setText("Resources: " + kitRes);

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
                resList.setText("Resources: " + bathRes);


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
                resList.setText("Resources: " + outRes);

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
                resList.setText("Resources: " + fullRes);

            }
        });


        //this is all nav bar from here on
//        Button groupsButton = (Button) findViewById(R.id.toGroupButton);
        Button homeButton = (Button) findViewById(R.id.toHomeButton);
        homeButton.setText("My Chores");
        Button logoutButton = (Button) findViewById(R.id.logoutButton);

        //when home is clicked go to the yourchoresactivity
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoreGroups.this, YourChoresActivity.class));
            }
        });
        //when logout is clicked log user out and proceed to login screen with no history so the user cannot press back
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(ChoreGroups.this, TitleActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        UserDatabase userDB = new UserDatabase(this);
//        User onlineUser = userDB.getOnlineUser();

        SharedPreferences sessionDetails = getSharedPreferences("sessionDetails", MODE_PRIVATE);
        s = sessionDetails.getString("sessionUsername", null);

        User onlineUser = userDB.getUserByName(s);
        //this is to change the name to the correct user
        TextView newName = (TextView)findViewById(R.id.nameNavBar);
        newName.setText(onlineUser.getName());
        //this is to show how many points the user has
        TextView newPoints = (TextView)findViewById(R.id.pointsTextView);
        newPoints.setText("Points: " + onlineUser.getPoints());
        ImageView profileIcon = (ImageView)findViewById(R.id.memberAvatar);
        //still need a display pic thing
        int resID = getResources().getIdentifier(onlineUser.getDrawableIcon().toString(), "drawable",
                getPackageName());
        profileIcon.setImageResource(resID);
    }

    //Debug method to bring us to the manageChores activity.
    //Should be removed when we've implemented ChoreView
    public void manageChoresOnClick(View view){
        Intent newIntent = new Intent(getApplicationContext(), YourChoresActivity.class);
        startActivity(newIntent);
    }
}
