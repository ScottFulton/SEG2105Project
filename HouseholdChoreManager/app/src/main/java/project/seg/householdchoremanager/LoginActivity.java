package project.seg.householdchoremanager;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
            //Intent intent = new Intent(getApplicationContext(), ChoreGroups.class);
            //startActivity(intent);
            Intent intent = new Intent(getBaseContext(), YourChoresActivity.class);
            intent.putExtra("USERNAME", name);
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

