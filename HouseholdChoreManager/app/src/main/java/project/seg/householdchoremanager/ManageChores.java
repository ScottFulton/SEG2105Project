package project.seg.householdchoremanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ManageChores extends AppCompatActivity {

    DatabaseHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_chores);
        dbHandler = new DatabaseHandler(this);
        final Chore[] choreList = dbHandler.getAllChores();
        ListView listView = (ListView) findViewById(R.id.list);
        ChoreCustomAdapter3 adapter = new ChoreCustomAdapter3(this, choreList,  new BtnClickListener() {

            @Override
            public void onBtnClick(int position) {
                // TODO Auto-generated method stub
                // Call your function which creates and shows the dialog here
                dbHandler.deleteChore(choreList[position].getName());
                refresh();
            }

        }, new BtnClickListener() {

            @Override
            public void onBtnClick(int position) {
                // TODO Auto-generated method stub
                // Call your function which creates and shows the dialog here
                Intent editorLaunchInterest = new Intent(getApplicationContext(), EditChore.class);
                editorLaunchInterest.putExtra("position",position);
                editorLaunchInterest.putExtra("name",choreList[position].getName());
                editorLaunchInterest.putExtra("description",choreList[position].getDescription());
                editorLaunchInterest.putExtra("resources",choreList[position].getResources());
                editorLaunchInterest.putExtra("group",choreList[position].getGroup());
                editorLaunchInterest.putExtra("reward",choreList[position].getReward());
                editorLaunchInterest.putExtra("duedate",choreList[position].getDueDate());
                startActivityForResult(editorLaunchInterest, 0);
                dbHandler.deleteChore(choreList[position].getName());
            }

        });
        listView.setAdapter(adapter);
    }
    public void addChore(View view) {
//Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), EditChore.class);
        startActivityForResult (intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== RESULT_CANCELED) return;
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Intent refresh = new Intent(this, ManageChores.class);
            startActivity(refresh);
            this.finish();
        }
    }
    public void refresh(){
        Intent refresh = new Intent(this, ManageChores.class);
        startActivity(refresh);
        this.finish();
    }
}
