/*
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


public class yourChoresActivity extends AppCompatActivity{
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_chores);

        DatabaseHandler db = new DatabaseHandler(this);
        //User usr = new User();
        Chore[] DBchoreList = db.getAllChores();
        List<Chore> choreList = new ArrayList<>();
        choreList.add(DBchoreList[1]);

        for(Chore c : DBchoreList){
            /*
            try {
                if(c.getAssigned().equals(User.getName())) {

                }
            } catch (Exception e) {}

        }
        ListView listView = (ListView) findViewById(R.id.list);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this,choreList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editorLaunchInterest = new Intent(getApplicationContext(), ChoreDetailsActivity.class);
                editorLaunchInterest.putExtra("position",position);
                editorLaunchInterest.putExtra("name",choreList[position]);
                startActivityForResult(editorLaunchInterest, 0);
            }
        });
    }
}
*/