package project.seg.householdchoremanager;

/**
 * Created by Scott Fulton on 2017-11-29.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserAdapterClass extends ArrayAdapter {
    private final Context context;
    private final User[] myUsers;

    public UserAdapterClass(Context context, User[] userList) {
        super(context, R.layout.user_item_layout, userList);
        this.context = context;
        this.myUsers = userList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.user_item_layout, parent, false);

        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.userName);
        ImageView choreImage = (ImageView) rowView.findViewById(R.id.profileIcon);

        choreNameTextField.setText(myUsers[position].getName());
        //choreImage.setImageDrawable();

        return rowView;
    }
}