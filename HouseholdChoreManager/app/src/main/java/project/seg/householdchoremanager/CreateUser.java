package project.seg.householdchoremanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;


public class CreateUser extends AppCompatActivity {
    ToggleButton parentAccount;
    EditText username;
    EditText password;
    EditText birthday;
    TextView textError;
    UserDatabase dbHandler;

    ImageView iconImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        parentAccount = (ToggleButton) findViewById(R.id.parentButton);
        username = (EditText) findViewById(R.id.nameText);
        password = (EditText) findViewById(R.id.passwordText);
        birthday = (EditText) findViewById(R.id.birthdayText);
        textError = (TextView) findViewById(R.id.iconTxt);

        iconImg = (ImageView) findViewById(R.id.groupImg);
        dbHandler = new UserDatabase(this);
    }


    public void newUser (View view) {
        if(dbHandler.checkUser(username.getText().toString())) {
            textError.setText("Username is already taken");
        } else if(!allFieldsFilled(view)) {
            textError.setText("Please make sure all entries valid");
        } else {
            User user = new User(username.getText().toString(), password.getText().toString(), parentAccount.isChecked(), 0, iconImg.getDrawable().toString());
            dbHandler.addUser(user);

            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    }

    //Identical to editChore activity's implementation, will return false if all the
    //form fields are not filled
    private boolean allFieldsFilled(View view){
        return !(username.getText().toString().equals("") || password.getText().toString().equals("")
            || birthday.getText().toString().equals(""));
    }

    public void OnSetAvatarButton(View view) {
//Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), SelectIcon.class);
        startActivityForResult (intent,0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) return;
//Getting the Avatar Image we show to our users
        ImageView iconImg = (ImageView) findViewById(R.id.groupImg);
//Figuring out the correct image
        String drawableName = "ic_launcher";
        switch (data.getIntExtra("imageID",R.id.bedroomImg)) {
            case R.id.bedroomImg:
                drawableName = "bedroom";
                break;
            case R.id.kitchenImg:
                drawableName = "kitchen";
                break;
            case R.id.bathroomImg:
                drawableName = "bathroom";
                break;
            case R.id.outdoorImg:
                drawableName = "outdoor";
                break;
            case R.id.fullhouseImg:
                drawableName = "fullhouse";
                break;

            case R.id.starImg:
                drawableName = "fullhouse";
                break;
            default:
                drawableName = "ic_launcher";
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable",
                getPackageName());
        iconImg.setImageResource(resID);
    }

    public void cancel(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }


}
