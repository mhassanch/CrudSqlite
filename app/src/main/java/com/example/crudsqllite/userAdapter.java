package com.example.crudsqllite;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class userAdapter extends ArrayAdapter {
    private android.app.Activity Context;
    private ArrayList<userModel> users;

    public userAdapter(Activity context, ArrayList<userModel> u) {
        super(context,R.layout.data_item,u);
        Context = context;
        this.users = u;
    }

    public userAdapter(@NonNull android.content.Context context, int resource, Activity context1) {
        super(context, resource);
        Context = context1;
    }

    public userAdapter(@NonNull android.content.Context context, int resource, int textViewResourceId, Activity context1) {
        super(context, resource, textViewResourceId);
        Context = context1;
    }

    public userAdapter(@NonNull android.content.Context context, int resource, @NonNull Object[] objects, Activity context1) {
        super(context, resource, objects);
        Context = context1;
    }

    public userAdapter(@NonNull android.content.Context context, int resource, int textViewResourceId, @NonNull Object[] objects, Activity context1) {
        super(context, resource, textViewResourceId, objects);
        Context = context1;
    }

    public userAdapter(@NonNull android.content.Context context, int resource, @NonNull List objects, Activity context1) {
        super(context, resource, objects);
        Context = context1;
    }

    public userAdapter(@NonNull android.content.Context context, int resource, int textViewResourceId, @NonNull List objects, Activity context1) {
        super(context, resource, textViewResourceId, objects);
        Context = context1;
    }

    public userAdapter(MainActivity context, int simple_list_item_1, ArrayList<userModel> list) {
        super(context, simple_list_item_1, list);
        Context = context;
        users = list;

    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflator = Context.getLayoutInflater();
        View Single = inflator.inflate(R.layout.data_item,null,true);
        TextView uE = Single.findViewById(R.id.userE);
        TextView uP = Single.findViewById(R.id.userP);
        uE.setText(users.get(position).getEmail());
        uP.setText(users.get(position).getPassword());
        ImageButton del = Single.findViewById(R.id.delete);
        ImageButton edit = Single.findViewById(R.id.edit);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(Context);
                dbHelper.deleteUser(users.get(position));
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(Context);
                dbHelper.updateUser(users.get(position));
            }
        });
        return Single;
    }
}
