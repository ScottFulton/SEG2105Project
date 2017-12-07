package project.seg.householdchoremanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cfran on 2017-11-09.
 */

public class ChoreCustomAdapter extends ArrayAdapter {
    private final Context context;
    private final Chore[] myChores;

    public ChoreCustomAdapter(Context context, Chore[] choreList) {
        super(context, R.layout.chore_item_layout, choreList);
        this.context = context;
        this.myChores = choreList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //putting group value in variable for readability 
        String groups = myChores[position].getGroup();
        //populating the chore view
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chore_item_layout, parent, false);
        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.itemName);
        TextView choreDescriptionTextField = (TextView) rowView.findViewById(R.id.itemDescription);
        ImageView choreImage = (ImageView) rowView.findViewById(R.id.groupIcon);

        //applying appropriate group values
        if(myChores[position]!=null) {
            choreNameTextField.setText(myChores[position].getName());
            choreDescriptionTextField.setText(myChores[position].getDescription());
            if(groups.equals("Bedroom")){
                choreImage.setImageResource(R.drawable.bedroom);
            }else if(groups.equals("Kitchen")){
                choreImage.setImageResource(R.drawable.kitchen);
            }else if(groups.equals("Bathroom")){
                choreImage.setImageResource(R.drawable.bathroom);
            }else if(groups.equals("Outdoor")){
                choreImage.setImageResource(R.drawable.outdoor);
            }else if(groups.equals("Full House")){
                choreImage.setImageResource(R.drawable.fullhouse);
            }
        }

        return rowView;

    }
}
