
package project.seg.householdchoremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
        String s = getIntent().getStringExtra("USERNAME");
        Session session = new Session(s);
        String user = session.getUser().getName();
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
                editorLaunchInterest.putExtra("resources", choreList[position].getResourcesArray());
                editorLaunchInterest.putExtra("name",choreList[position].getName());
                editorLaunchInterest.putExtra("description", choreList[position].getDescription());
                startActivityForResult(editorLaunchInterest, 0);
            }
        });
    }
}