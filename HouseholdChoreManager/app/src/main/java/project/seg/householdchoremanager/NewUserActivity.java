package project.seg.householdchoremanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    public void enterOnClick(View view){
        UserDatabase users = new UserDatabase(this);    //Whaaaaaaat!?

        EditText textName = (EditText)findViewById(R.id.name);
        EditText textPassword = (EditText)findViewById(R.id.password);
        EditText textConfirm = (EditText)findViewById(R.id.confirmPassword);
        TextView textError = (TextView)findViewById(R.id.confirmError);

        String name = textName.toString();
        String password = textPassword.toString();
        String confirm = textConfirm.toString();

        if(!confirm.equals(password)){
            textError.setVisibility(View.VISIBLE);
            return;
        } else {
            if(){
                //If the username already exists, no
            } else {
                User newUser = new User(name, password, true, 0);
                users.addUser(newUser);

            }
        }

    }

}
