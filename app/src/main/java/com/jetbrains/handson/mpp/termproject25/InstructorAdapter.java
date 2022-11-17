package com.jetbrains.handson.mpp.termproject25;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.instructorViewHolder> {
    interface OnButtonClickListener {
        void onButtonClick(int pos);
    }

    interface OnButton2ClickListener {
        void onButton2Click(int pos, String d, String h, String cap, String disc);
    }

    String[] names,codes, days, hours, capacites, descriptions;
    Context context;
    OnButtonClickListener listener;
    OnButton2ClickListener listener2;



    public InstructorAdapter(Context ct, String[] n, String[] c, String[] d, String[] h, String[] cap, String[] desc, OnButtonClickListener l, OnButton2ClickListener l2) {
        this.context = ct;
        this.names = n;
        this.codes = c;
        this.days = d;
        this.hours = h;
        this.capacites = cap;
        this.descriptions = desc;
        this.listener = l;
        this.listener2 = l2;
    }

    @NonNull
    @Override
    public instructorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_course, parent, false);
        return new instructorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull instructorViewHolder holder, int position) {
        holder.nameView.setText(names[position]);
        holder.codeView.setText(codes[position]);
        holder.dayView.setText(days[position]);
        holder.hourView.setText(hours[position]);
        holder.capView.setText(capacites[position]);
        holder.descView.setText(descriptions[position]);
        holder.posView.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        if(names == null){
            return 0;
        } else {
            return names.length;
        }

    }

    public class instructorViewHolder extends RecyclerView.ViewHolder {

        TextView nameView, codeView, posView, dayView, hourView, capView, descView;
        Button button, button2;

        public instructorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
            codeView = itemView.findViewById(R.id.code);
            posView = itemView.findViewById(R.id.pos);
            dayView = itemView.findViewById(R.id.txtDays);
            hourView = itemView.findViewById(R.id.txtHours);
            capView = itemView.findViewById(R.id.txtCap);
            descView = itemView.findViewById(R.id.txtDesc);
            button = itemView.findViewById(R.id.remove_btn);
            button2 = itemView.findViewById(R.id.buttonUpdate);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { listener.onButtonClick(Integer.parseInt((String) posView.getText())); }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posPut = Integer.parseInt((String) posView.getText());
                    String dPut = dayView.getText().toString();
                    String hPut = hourView.getText().toString();
                    String capPut = capView.getText().toString();
                    String descPut = descView.getText().toString();

                    listener2.onButton2Click(posPut, dPut, hPut, capPut, descPut);
                }
            });

        }


    }
}
