package project.seg.householdchoremanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Ben on 2017-11-29.
 */

public class ChoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_details);

        final Button setComplete = (Button) findViewById(R.id.setCompleteButton);
        TextView pointsText = (TextView) findViewById(R.id.pointValueText);


        //Getting sharedPreferences data and getting user
        SharedPreferences sessionDetails = getSharedPreferences("sessionDetails", MODE_PRIVATE);
        String usr = sessionDetails.getString("sessionUsername", null);
        UserDatabase udb = new UserDatabase(this);
        final User onlineUser = udb.getUserByName(usr);


        //Getting intents from YourChoresActivity and adding them to variables for ease of use
        Bundle intents = getIntent().getExtras();
        final String choreName = intents.getString("name");
        int pointValue = intents.getInt("points");
        String choreDescriptionString = intents.getString("description");
        String[] choreResourcesArray = intents.getStringArray("resources");
        StringBuilder choreResourcesBuilder = new StringBuilder("What You Need:");
        String groups = intents.getString("group");


        //Building the nice-looking list for the what you need section and also adding point text
        for(String s : choreResourcesArray) {
            choreResourcesBuilder.append("\n - " + s);
        }
        String choreResources = choreResourcesBuilder.toString(); //Formatted chore resources
        pointsText.append("Points: " + pointValue);
        //Appending appropriate values to the text view in the detail view
        TextView choreTitle = (TextView)findViewById(R.id.choreTitle);
        choreTitle.append(choreName);
        TextView resourceItems = (TextView)findViewById(R.id.resourceItems);
        resourceItems.append(choreResources);
        TextView choreDescription = (TextView)findViewById(R.id.choreDescription);
        choreDescription.append(choreDescriptionString);

        //TODO Make the image picker better, seriously there has to be a less shit way
        ImageView choreImage = (ImageView)findViewById(R.id.choreIcon);
        if(groups.equals("Bedroom")){
            choreImage.setImageResource(R.drawable.bedroom);
        }else if(groups.equals("Kitchen")){
            choreImage.setImageResource(R.drawable.kitchen);
        }else if(groups.equals("Bathroom")){
            choreImage.setImageResource(R.drawable.bathroom);
        }else if(groups.equals("Outdoor")){
            choreImage.setImageResource(R.drawable.outdoor);
        }else if(groups.equals("Full House")){
            choreImage.setImageResource(R.drawable.fullhouse);
        }

        //Chore completion code below

        //Getting list of chores
        final DatabaseHandler db = new DatabaseHandler(this);
        Chore[] DBchoreList = db.getAllChores();

        //finding the specific chore and applying it to a variable
        //TODO complain at Scott to make a getChoreByName function for the chore DB
        Chore foundChore = null;
        for(Chore c : DBchoreList) {
            if(c.getName().equals(choreName)) {
                foundChore = c;
            }
        }
        final Chore thisChore = foundChore;
        final int points = thisChore.getReward();

        setComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent managerLaunchInterest = new Intent(getApplicationContext(), YourChoresActivity.class);
                thisChore.complete(); //TODO complain at Kevin to make this process automatic when complete is called
                onlineUser.addPoints(points);
                Toast.makeText(getApplicationContext(), "Chore completed!", Toast.LENGTH_SHORT).show();
                Log.d("POINTS:",""+onlineUser.getPoints());
                db.deleteChore(choreName);
                startActivityForResult(managerLaunchInterest, 0);
            }
        });
    }
}
