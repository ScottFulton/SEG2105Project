package project.seg.householdchoremanager;

import android.content.Intent;
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

        String name = textName.toString();
        String password = textPassword.toString();

        /*
        boolean u = users.checkUser(name);
        textError.setVisibility(View.VISIBLE);
        if(u){
            textError.setText("True");
        } else {
            textError.setText("False");
        }
        */
        String test = users.nameUser();
        textError.setVisibility(View.VISIBLE);
        textError.setText(test);




        /*if(users.checkUser(name)){
            Intent intent = new Intent(getApplicationContext(), ChoreGroups.class);
            startActivity(intent);
        } else {
            textError.setVisibility(View.VISIBLE);
            textError.setText("Unknown user");
        }
        */


    }
}

