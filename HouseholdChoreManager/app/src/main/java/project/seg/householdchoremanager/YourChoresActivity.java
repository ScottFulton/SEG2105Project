/*
package project.seg.householdchoremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Ben on 2017-11-29.
 */
/*
public class yourChoresActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);


        final String[] choreList = {"Walk Dog", "Do the Dishes", "Clean Room", "Make Bed", "Take Trash Out", "memes"};
        ListView listView = (ListView) findViewById(R.id.list);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this,choreList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent editorLaunchInterest = new Intent(getApplicationContext(), ChoreDetailsActivity.class);
                editorLaunchInterest.putExtra("position",position);
                editorLaunchInterest.putExtra("name",choreList[position]);
                startActivityForResult(editorLaunchInterest, 0);
            }
        });
    }
}
*/