
package project.seg.householdchoremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 2017-11-29.
*/

public class YourChoresActivity extends AppCompatActivity{
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_chores);

        DatabaseHandler db = new DatabaseHandler(this);
        UserDatabase udb = new UserDatabase(this);

        Chore[] DBchoreList = db.getAllChores();
        final Chore[] choreList = new Chore[DBchoreList.length];

        //fetching relevant user info
        String s = getIntent().getStringExtra("USERNAME");
        Session session = new Session(s);
        String user = session.getUser().getName();
        Boolean isParent = session.getUser().isAdult();


        final Button manageChores = (Button)findViewById(R.id.manageChoresButton);
        if(!isParent) {
            /*
            * if the current user isn't a parent, the manage chores button is removed from the chore
            * view.
            */
            manageChores.setVisibility(View.GONE);
        }

        manageChores.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent managerLaunchInterest = new Intent(getApplicationContext(), ManageChores.class);
                startActivityForResult(managerLaunchInterest, 0);
            }
        });

        int count = 0;
        for(Chore c : DBchoreList){
            try {
                if(c.getAssigned().equals(user)) {
                    choreList[count] = c;
                    count++;
                }
            } catch (Exception e) {}
        }

        ListView listView = (ListView) findViewById(R.id.list);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this,choreList);
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
                editorLaunchInterest.putExtra("name",choreList[position].getName());
                editorLaunchInterest.putExtra("description", choreList[position].getDescription());
                startActivityForResult(editorLaunchInterest, 0);
            }
        });
    }
}