 package project.seg.householdchoremanager;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.support.v4.widget.SwipeRefreshLayout;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.w3c.dom.Text;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Ben on 2017-11-29.
 */

public class YourChoresActivity extends AppCompatActivity {
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_chores);
        final Button manageChores = (Button) findViewById(R.id.manageChoresButton);
        TextView flavourText = (TextView) findViewById(R.id.flavourText);
        ListView listView = (ListView) findViewById(R.id.list);

        //Getting sharedPreferences data
        SharedPreferences sessionDetails = getSharedPreferences("sessionDetails", MODE_PRIVATE);
        s = sessionDetails.getString("sessionUsername", null);

        //fetching relevant user info and adding values to the flavour text
        DatabaseHandler db = new DatabaseHandler(this);
        UserDatabase udb = new UserDatabase(this);
        User onlineUser = udb.getUserByName(s);
        flavourText.append(s + "'s Chores");

        /*
        * if the current user isn't a parent, the manage chores button is removed from the chore
        * view.
        */
        Boolean isParent = onlineUser.isAdult();
        if (!isParent) {
            manageChores.setVisibility(View.GONE);
        }

        Chore[] DBchoreList = db.getAllChores();
        ArrayList<Chore> choreArrayList = new ArrayList<>();

        /*
        * uses the chore database above to build an array list of chores, all chores that have their
        * assigning matching the username are added to the array list
         */
        for (Chore c : DBchoreList) {
            try {
                if (c.getAssigned().equals(onlineUser.getName())) {
                    choreArrayList.add(c);
                }
            } catch (Exception e) {}
        }
        /*
        * since the choreArrayList adapter uses arrays instead of array list it is better to convert
        * the array list to a static array
        */
        final Chore[] choreList = choreArrayList.toArray(new Chore[choreArrayList.size()]);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this, choreList);
        listView.setAdapter(adapter);

        //sets action for when user clicks manage chores button
        manageChores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent managerLaunchInterest = new Intent(getApplicationContext(), ManageChores.class);
                startActivityForResult(managerLaunchInterest, 0);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            /*
            Launches ChoreDetailsActivity when a list item is clicked, also sends important
            text items like the description via intent
             */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editorLaunchInterest = new Intent(getApplicationContext(), ChoreDetailsActivity.class);
                editorLaunchInterest.putExtra("group", choreList[position].getGroup());
                editorLaunchInterest.putExtra("resources", choreList[position].getResourcesArray());
                editorLaunchInterest.putExtra("name", choreList[position].getName());
                editorLaunchInterest.putExtra("description", choreList[position].getDescription());
                editorLaunchInterest.putExtra("points", choreList[position].getReward());
                startActivityForResult(editorLaunchInterest, 1);
            }
        });

        /*
        SWIPE TO REFRESH CODE
         */
        SwipeRefreshLayout swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        recreate();
                    }
                }
        );


        /*
        NAV BAR CODE
         */
        Button groupsButton = (Button) findViewById(R.id.toGroupButton);
//      Button homeButton = (Button) findViewById(R.id.toHomeButton); <- home button unneeded for this nav bar
        Button logoutButton = (Button) findViewById(R.id.logoutButton);



        groupsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent managerLaunchInterest = new Intent(getApplicationContext(), ChoreGroups.class);
                startActivityForResult(managerLaunchInterest, 0);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(YourChoresActivity.this, TitleActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        //this is to change the name to the correct user
        TextView newName = (TextView)findViewById(R.id.nameNavBar);
        newName.setText(onlineUser.getName());
        //this is to show how many points the user has
        TextView newPoints = (TextView)findViewById(R.id.pointsTextView);
        newPoints.setText("Points: " + onlineUser.getPoints());
        ImageView profileIcon = (ImageView)findViewById(R.id.memberAvatar);
        //still need a display pic thing
        int resID = getResources().getIdentifier(onlineUser.getDrawableIcon(), "drawable",
                getPackageName());
        profileIcon.setImageResource(resID);
    }
}

//Debug method to bring us to the manageChores activity.
//Should be removed when we've implemented ChoreView
//    public void manageChoresOnClick(View view){
//        Intent newIntent = new Intent(YourChoresActivity.this, YourChoresActivity.class);
//        newIntent.putExtra("USERNAME", s);
//        startActivity(newIntent);
//    }

//}