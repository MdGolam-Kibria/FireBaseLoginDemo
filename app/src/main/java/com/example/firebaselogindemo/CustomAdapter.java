package com.example.firebaselogindemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class CustomAdapter extends ArrayAdapter<ModelClass> {
    List<ModelClass> list;
    Activity context;

    public CustomAdapter(Activity context, List<ModelClass> list) {
        super(context, R.layout.simple_view, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.simple_view, null, true);
        ModelClass modelClass = list.get(position);
        EditText name = view.findViewById(R.id.name);
        EditText age = view.findViewById(R.id.age);
        name.setText("name = " + modelClass.getName());
        age.setText("age = " + modelClass.getAge());

        return view;
    }
}
