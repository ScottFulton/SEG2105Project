package project.seg.householdchoremanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
/*
Accompanying code to the activity_manage_code.xml file
Access to assigning chores, editing chores, creating chores, and deleting chores is held here
 */
public class ManageChores extends AppCompatActivity {
    int counter;
    Chore[] newChoreList;

    DatabaseHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_chores);
        dbHandler = new DatabaseHandler(this);
        final Chore[] choreList = dbHandler.getAllChores();
        newChoreList = choreList;
        ListView listView = (ListView) findViewById(R.id.list);
        //adding functionality to the "Go Back" button
        Button yourChoresButton = (Button) findViewById(R.id.goBackButton);
        yourChoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent managerLaunchInterest = new Intent(getApplicationContext(), YourChoresActivity.class);
                startActivityForResult(managerLaunchInterest, 0);
            }
        });
        //Creates the list of chores already made using ChoreCustomAdapter3
        ChoreCustomAdapter3 adapter = new ChoreCustomAdapter3(this, choreList, new BtnClickListener() {
            //If delete button is pressed it removes that chore from the database
            @Override
            public void onBtnClick(int position) {
                // TODO Auto-generated method stub
                // Call your function which creates and shows the dialog here
                dbHandler.deleteChore(choreList[position].getName());
                refresh();
            }

        }, new BtnClickListener() {
            //If Edit button is pressed passes the information the chore to be edited to edit chore through an intent
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
                editorLaunchInterest.putExtra("edit", 1);
                startActivityForResult(editorLaunchInterest, 0);
            }

        }, new BtnClickListener() {
            //If assignTo button is pressed, launches the assignChore activity
            @Override
            public void onBtnClick(int position) {
                // TODO Auto-generated method stub
                // Call your function which creates and shows the dialog here
                Intent editorLaunchInterest = new Intent(getApplicationContext(), AssignChore.class);
                startActivityForResult(editorLaunchInterest, 0);
                counter = position;

            }
        }
        , new BtnClickListener() {
            //Deallocates the chore from the user its assigned to if the Deallocate button is pressed
                @Override
                public void onBtnClick(int position) {
                    // TODO Auto-generated method stub
                    choreList[position].setAssigned("");
                    final  Chore chore = choreList[position];
                    dbHandler.updateChore(chore);

                }
            });
        listView.setAdapter(adapter);
    }

    //On Click method for Add Chore button, if pressed launches AddChore class
    public void addChore(View view) {
//Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), EditChore.class);
        startActivityForResult (intent,0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== RESULT_CANCELED) return;
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==-2){
            try {
                newChoreList[counter].setAssigned(data.getStringExtra("name"));
                dbHandler.updateChore(newChoreList[counter]);
            }
            catch (Exception e){
                System.out.println("");
            }
            refresh();
        }
        if(resultCode == RESULT_OK) {
            refresh();
        }
    }
    //Code to automatically refresh activity when returning from a other class in case something was changed
    //in the chore list
    public void refresh(){
        Intent refresh = new Intent(this, ManageChores.class);
        startActivity(refresh);
        this.finish();
    }
}
