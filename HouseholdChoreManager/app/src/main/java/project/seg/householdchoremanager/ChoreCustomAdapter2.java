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

public class ChoreCustomAdapter2 extends ArrayAdapter {

    private final Context context;
    private final String[] myChores;
    private final String groups;

    public ChoreCustomAdapter2(Context context, String[] choreList, String group){
        super(context, R.layout.chore_item_layout, choreList);
        this.context = context;
        this.myChores = choreList;
        this.groups = group;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chore_item_layout, parent, false);
        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.itemName);
        TextView choreDescriptionTextField = (TextView) rowView.findViewById(R.id.itemDescription);
        //TODO - if in certain group set as certain picture.
        Log.d("CHORELOG",""+myChores[position]);
        ImageView choreImage = (ImageView) rowView.findViewById(R.id.groupIcon);
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
        choreNameTextField.setText(myChores[position]);
        choreDescriptionTextField.setText(null);


        return rowView;
    }
}
