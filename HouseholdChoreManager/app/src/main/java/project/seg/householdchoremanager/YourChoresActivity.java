
package project.seg.householdchoremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        DatabaseHandler db = new DatabaseHandler(this);
        UserDatabase udb = new UserDatabase(this);

        Chore[] DBchoreList = db.getAllChores();
        final ArrayList<Chore> choreArrayList = new ArrayList<>();

        //fetching relevant user info
        s = getIntent().getStringExtra("USERNAME");
        final User onlineUser = udb.getUserByName(s);
        Boolean isParent = onlineUser.isAdult();
        Log.d("ISPARENT", "" + isParent);


//        Chore chore5 = new Chore("chorename","descname","resname, test 1, test 1","Full House",212,20171129,"chris");
//        db.addChore(chore5);

        final Button manageChores = (Button) findViewById(R.id.manageChoresButton);
        if (!isParent) {
            /*
            * if the current user isn't a parent, the manage chores button is removed from the chore
            * view.
            */
            manageChores.setVisibility(View.GONE);
        }

        manageChores.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent managerLaunchInterest = new Intent(getApplicationContext(), ManageChores.class);
                startActivityForResult(managerLaunchInterest, 0);
            }
        });


        for (Chore c : DBchoreList) {
//            Log.d("CHORELIST",""+c.getName());
            try {
                if (c.getAssigned().equals(onlineUser.getName())) {
                    Log.d("ASSIGNED", "" + c.getAssigned());
                    choreArrayList.add(c);

                }
            } catch (Exception e) {
            }
        }

        final Chore[] choreList = choreArrayList.toArray(new Chore[choreArrayList.size()]);
        ListView listView = (ListView) findViewById(R.id.list);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this, choreList);
        listView.setAdapter(adapter);

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
                startActivityForResult(editorLaunchInterest, 0);
            }
        });

        //this is all nav bar from here on
        Button groupsButton = (Button) findViewById(R.id.toGroupButton);
//        Button homeButton = (Button) findViewById(R.id.toHomeButton);
        Button logoutButton = (Button) findViewById(R.id.logoutButton);



        groupsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent managerLaunchInterest = new Intent(getApplicationContext(), ChoreGroups.class);
                managerLaunchInterest.putExtra("USERNAME", s);
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

        UserDatabase userDB = new UserDatabase(this);
//        User onlineUser = userDB.getOnlineUser();


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



    }

    //Debug method to bring us to the manageChores activity.
    //Should be removed when we've implemented ChoreView
//    public void manageChoresOnClick(View view){
//        Intent newIntent = new Intent(YourChoresActivity.this, YourChoresActivity.class);
//        newIntent.putExtra("USERNAME", s);
//        startActivity(newIntent);
//    }

//}
