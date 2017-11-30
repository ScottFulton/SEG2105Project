package project.seg.householdchoremanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EditChore extends AppCompatActivity {
    ImageView groupImg;
    EditText nameBox;
    EditText pointsBox;
    EditText dateBox;
    EditText resourcesBox;
    EditText descriptionBox;
    TextView groupView;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_chore);
        dbHandler = new DatabaseHandler(this);
        groupView = (TextView) findViewById(R.id.groupTxt);
        nameBox = (EditText) findViewById(R.id.nameText);
        pointsBox = (EditText) findViewById(R.id.pointsText);
        dateBox = (EditText) findViewById(R.id.dueDateText);
        resourcesBox = (EditText) findViewById(R.id.resourcesText);
        groupImg = (ImageView) findViewById(R.id.groupImg);
        descriptionBox = (EditText) findViewById(R.id.descriptionText);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String group = intent.getStringExtra("group");
        String resources = intent.getStringExtra("resources");
        String description = intent.getStringExtra("description");
        int reward = intent.getIntExtra("reward", 0);
        int duedate = intent.getIntExtra("duedate", 0);
        nameBox.setText(name);
        descriptionBox.setText(description);
        if (group != null) {
            groupView.setText(group);
            String drawableName = whatGroup(groupView.getText().toString());
            int resID = getResources().getIdentifier(drawableName, "drawable",
                    getPackageName());
            groupImg.setImageResource(resID);
        }
        resourcesBox.setText(resources);
        if (reward != 0) {
            pointsBox.setText(Integer.toString(reward));
        }
        if (duedate != 0){
            dateBox.setText(Integer.toString(duedate));
        }
    }

    private String whatGroup(String group){
        String drawableName = "ic_launcher";
        if (group.equals("Bathroom")){
            drawableName = "bathroom";
        }
        else if (group.equals("Kitchen")){
            drawableName = "kitchen";
        }
        else if (group.equals("Bedroom")){
            drawableName = "bedroom";
        }
        else if (group.equals("Full House")){
            drawableName = "fullhouse";
        }
        else if (group.equals("Outdoors")){
            drawableName = "outdoors";
        }

        return drawableName;
    }

    public void newChore (View view) {

        if(!allFieldsFilled(view)) {
            groupView.setText("Please make all fields valid entries");
        } else if(dateBox.getText().toString().length() != 8){
            groupView.setText("Please enter an 8 due date");
        } else {
            Chore chore = new Chore(nameBox.getText().toString(), descriptionBox.getText().toString(), resourcesBox.getText().toString()
                    , groupView.getText().toString(), Integer.parseInt(pointsBox.getText().toString()), Integer.parseInt(dateBox.getText().toString()));
            dbHandler.addChore(chore);

            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    }

    //returns true if every field in the activity has a valid entry
    //Avert your eyes if you don't like ugly return statements.
    private boolean allFieldsFilled(View view){
        return !(nameBox.getText().toString().equals("") || pointsBox.getText().toString().equals("")||
                dateBox.getText().toString().equals("") || resourcesBox.getText().toString().equals("")
                || descriptionBox.getText().toString().equals(""));
    }

    public void OnSetAvatarButton(View view) {
//Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), SelectGroup.class);
        startActivityForResult (intent,0);
    }

    public void cancel(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) return;
//Getting the Avatar Image we show to our users
        ImageView groupImg = (ImageView) findViewById(R.id.groupImg);
        TextView groupTxt = (TextView) findViewById(R.id.groupTxt);
//Figuring out the correct image
        String drawableName = "ic_launcher";
        String groupName = "Click on image to select group";
        switch (data.getIntExtra("imageID",R.id.bedroomImg)) {
            case R.id.bedroomImg:
                drawableName = "bedroom";
                groupName = "Bedroom";
                break;
            case R.id.kitchenImg:
                drawableName = "kitchen";
                groupName = "Kitchen";
                break;
            case R.id.bathroomImg:
                drawableName = "bathroom";
                groupName = "Bathroom";
                break;
            case R.id.outdoorImg:
                drawableName = "outdoor";
                groupName = "Outdoor";
                break;
            case R.id.fullhouseImg:
                drawableName = "fullhouse";
                groupName = "Full House";
                break;
            default:
                drawableName = "ic_launcher";
                groupName = "Click on image to select group";
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable",
                getPackageName());
        groupImg.setImageResource(resID);
        groupTxt.setText(groupName);
    }
}
