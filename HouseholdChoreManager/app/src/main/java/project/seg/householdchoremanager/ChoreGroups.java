package project.seg.householdchoremanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ChoreGroups extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_groups);


        String[] choreList = {"Chore 1", "Chore 2", "Chore 3", "Chore 4", "Chore 5"};
        ListView listView = (ListView) findViewById(R.id.choreList);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this,choreList);
        listView.setAdapter(adapter);
    };
}
