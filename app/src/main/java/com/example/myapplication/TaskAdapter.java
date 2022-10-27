package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private  int resourceID;
    public TaskAdapter(
            Context context, int textViewResourcID, List<Task> object){
        super(context,textViewResourcID,object);
        resourceID = textViewResourcID;
    };

    public View getView(int position, View coverView, ViewGroup parent){
        Task task = getItem(position); //获取task实力
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView TaskTitle = view.findViewById(R.id.Item_textView_title);
        TaskTitle.setText(task.getTitle());
        return view;
    }
}
