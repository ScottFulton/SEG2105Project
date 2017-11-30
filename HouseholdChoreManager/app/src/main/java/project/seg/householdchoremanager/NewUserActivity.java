package project.seg.householdchoremanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.ToggleButton;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    /*
    public void enterOnClick(View view){
        UserDatabase users = new UserDatabase(this);

        EditText textName = (EditText)findViewById(R.id.name);
        EditText textPassword = (EditText)findViewById(R.id.password);
        EditText textConfirm = (EditText)findViewById(R.id.confirmPassword);
        TextView textError = (TextView)findViewById(R.id.confirmError);
        ToggleButton isAdult  = (ToggleButton)findViewById(R.id.isAdult);

        String name = textName.getText().toString();
        String password = textPassword.getText().toString();
        String confirm = textConfirm.getText().toString();

        textError.setVisibility(View.VISIBLE);

        if(!confirm.equals(password)){
            displayError(textError, "Both password entries must be the same");
        } else {
            boolean taken = users.checkUser(name);
            if(taken){
                displayError(textError, "That username is already taken");
            } else {
                User retUser = new User(name, password, isAdult.isChecked(), 0);
                users.addUser(retUser);
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        }
    }

    public void displayError(TextView textError, String error){
        textError.setText(error);
    }

*/
}

