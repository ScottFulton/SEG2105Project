package project.seg.householdchoremanager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ChoreCustomAdapter3 extends ArrayAdapter {
    private final Context context;
    private final Chore[] myChores;
    private BtnClickListener mClickListener = null;
    private BtnClickListener mClickListener2 = null;
    private BtnClickListener mClickListener3 = null;
    private BtnClickListener mClickListener4 = null;


    public ChoreCustomAdapter3(Context context, Chore[] choreList, BtnClickListener listener, BtnClickListener listener2, BtnClickListener listener3, BtnClickListener listener4) {
        super(context, R.layout.chore_item_layout2, choreList);
        this.context = context;
        this.myChores = choreList;
        this.mClickListener = listener;
        this.mClickListener2 = listener2;
        this.mClickListener3 = listener3;
        this.mClickListener4 = listener4;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chore_item_layout2, parent, false);

        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.itemName);
        TextView choreDescriptionTextField = (TextView) rowView.findViewById(R.id.itemDescription);
        ImageView choreImage = (ImageView) rowView.findViewById(R.id.icon);

        String drawableText = whatGroup(myChores[position].getGroup());
        int resID = context.getResources().getIdentifier(drawableText, "drawable",
                context.getPackageName());

        choreNameTextField.setText(myChores[position].getName());
        choreDescriptionTextField.setText(myChores[position].getDescription());
        choreImage.setImageResource(resID);

        Button deleteButton = (Button) rowView.findViewById(R.id.deleteBtn);
        deleteButton.setTag(position); //For passing the list item index
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mClickListener != null)
                    mClickListener.onBtnClick((Integer) v.getTag());
            }
        });

        Button editButton = (Button) rowView.findViewById(R.id.editBtn);
        editButton.setTag(position); //For passing the list item index
        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mClickListener2 != null)
                    mClickListener2.onBtnClick((Integer) v.getTag());
            }
        });

        Button assignToButton = (Button) rowView.findViewById(R.id.assignmentBtn);
        assignToButton.setTag(position); //For passing the list item index
        assignToButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mClickListener3 != null)
                    mClickListener3.onBtnClick((Integer) v.getTag());
            }
        });

        Button deallocateButton = (Button) rowView.findViewById(R.id.deallocateBtn);
        deallocateButton.setTag(position); //For passing the list item index
        deallocateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mClickListener4 != null)
                    mClickListener4.onBtnClick((Integer) v.getTag());
            }
        });
        return rowView;
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
        else if (group.equals("Outdoor")){
            drawableName = "outdoor";
        }
        return drawableName;
    }
}