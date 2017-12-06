package project.seg.householdchoremanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/*
Accompanying code to the activity_assign_chore.xml file
 */
public class AssignChore extends AppCompatActivity {
    UserDatabase dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Creates the list of all users that you can click on
        setContentView(R.layout.activity_assign_chore);
        dbHandler = new UserDatabase(this);
        final User[] userList = dbHandler.getAllUsers();
        ListView listView = (ListView) findViewById(R.id.list);
        UserAdapterClass adapter = new UserAdapterClass(this,userList);
        listView.setAdapter(adapter);
        //When a user is clicked on it will return which user and return to the ManageChores class
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("name", userList[position].getName());
                setResult(-2, returnIntent);
                finish();
            }
        });
    }
}
