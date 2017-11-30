package project.seg.householdchoremanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;


public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
    }

    //Start up the login screen "activity_login"
    public void userOnClick(View view){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void newUserOnClick(View view){
        Intent intent = new Intent(getApplicationContext(), CreateUser.class);
        startActivity(intent);
    }
}
