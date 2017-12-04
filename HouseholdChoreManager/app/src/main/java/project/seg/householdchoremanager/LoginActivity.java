package project.seg.householdchoremanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.preference.PreferenceManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void enterOnClick(View view){
        UserDatabase users = new UserDatabase(this);

        EditText textName = (EditText)findViewById(R.id.name);
        EditText textPassword = (EditText)findViewById(R.id.password);
        TextView textError = (TextView)findViewById(R.id.confirmError);

        String name = textName.getText().toString();
        String password = textPassword.getText().toString();


        if(users.authUser(name, password)){
            /*

            SharedPreferences allows you to store, retrieve, modify, and fetch data from any class.
            It works the same way intents do with a keyname and value, but it only works with primitive
            types unless you want to go into serializations or parcelables.

            To add something to a shared preferences you launch the editor like so:
            SharedPreferences.Editor [editorVarName] = getSharedPreferences("nameOfPrefs", MODE_PRIVATE).edit();
            Then you use the put function:
            editorVarName.put[type of your object]("objectKey", objectValue);
            Then you apply it to the map:
            editorVarName.apply();

            To receive you would for example do:
            SharedPreferences nameOfPrefs = getSharedPreferences("nameOfPrefs", MODE_PRIVATE);
            SomeType s = nameOfPrefs.getSomeType("objectKey", null); <-- the second value here
            is what is applied to s instead if it can't find the key
            -Ben
            */
            SharedPreferences.Editor editor = getSharedPreferences("sessionDetails", MODE_PRIVATE).edit();
            editor.putString("sessionUsername", name); //keyName, value
            editor.apply(); //writes the key to the map sessionDetails

            /*

            To get user data from anywhere:
            SharedPreferences sessionDetails = getSharedPreferences("sessionDetails", MODE_PRIVATE);
            String usr = sessionDetails.getString("sessionUsername", null);
            UserDatabase udb = new UserDatabase(this);
            final User onlineUser = udb.getUserByName(usr);
            -Ben
             */

            //Intent intent = new Intent(getApplicationContext(), ChoreGroups.class);
            //startActivity(intent);
            Intent intent = new Intent(getBaseContext(), YourChoresActivity.class);
            startActivity(intent);

            /*
            Access above intent with
            String s = getIntent().getStringExtra("USERNAME");

            Then create:
            Session session = new Session(s);
            session.getUser();
             */
        } else if(users.checkUser(name)){
            textError.setText("Incorrect password");
            textError.setVisibility(View.VISIBLE);
        }
        else {
            textError.setText("Unrecognized user");
            textError.setVisibility(View.VISIBLE);
        }



    }
}

