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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chore_item_layout, parent, false);

        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.itemName);
        TextView choreDescriptionTextField = (TextView) rowView.findViewById(R.id.itemDescription);
        ImageView choreImage = (ImageView) rowView.findViewById(R.id.icon);

        Log.d("CHORENAME",""+"top");
//        Log.d("CHORENAME",""+myChores[position].getName());
        Log.d("CHORENAME",""+"bottom");
        if(myChores[position]!=null) {
            choreNameTextField.setText(myChores[position].getName());
            choreDescriptionTextField.setText(myChores[position].getDescription());
            //choreImage.setImageDrawable();
        }

        return rowView;

    }
}
