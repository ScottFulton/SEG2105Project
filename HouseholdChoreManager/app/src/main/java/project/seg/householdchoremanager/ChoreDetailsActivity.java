package project.seg.householdchoremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ben on 2017-11-29.
 */

public class ChoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_details);

        //getting intents from YourChoresActivity and adding them to variables for ease of use
        Bundle intents = getIntent().getExtras();
        String choreName = intents.getString("name");
        String choreDescriptionString = intents.getString("description");
        String[] choreResourcesArray = intents.getStringArray("resources");
        StringBuilder choreResourcesBuilder = new StringBuilder("What You Need:");

        //Building the nice-looking list for the what you need section
        for(String s : choreResourcesArray) {
            choreResourcesBuilder.append("\n - " + s);
        }

        String choreResources = choreResourcesBuilder.toString();
        //Appending appropriate values to the text view in the detail view
        TextView choreTitle = (TextView)findViewById(R.id.choreTitle);
        choreTitle.append(choreName);
        TextView resourceItems = (TextView)findViewById(R.id.resourceItems);
        resourceItems.append(choreResources);
        TextView choreDescription = (TextView)findViewById(R.id.choreDescription);
        choreDescription.append(choreDescriptionString);

        String groups = intents.getString("group");

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

    }
}
