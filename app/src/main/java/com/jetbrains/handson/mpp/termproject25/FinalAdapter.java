package com.jetbrains.handson.mpp.termproject25;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FinalAdapter extends RecyclerView.Adapter<FinalAdapter.finalViewHolder> {
    interface OnButtonClickListener {
        void onButtonClick(int pos);
    }

    interface OnButton2ClickListener {
        void onButton2Click(int pos, String d, String h, String cap, String disc);
    }

    String[] names,codes, days, hours, capacites, descriptions, instructor;
    Context context;
    OnButtonClickListener listener;
    OnButton2ClickListener listener2;



    public FinalAdapter(Context ct, String[] n, String[] c, String[] d, String[] h, String[] cap, String[] desc, String[] i, OnButtonClickListener l) {
        this.context = ct;
        this.names = n;
        this.codes = c;
        this.days = d;
        this.hours = h;
        this.capacites = cap;
        this.descriptions = desc;
        this.instructor = i;
        this.listener = l;
    }

    @NonNull
    @Override
    public finalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_course_final, parent, false);
        return new finalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull finalViewHolder holder, int position) {
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

    public class finalViewHolder extends RecyclerView.ViewHolder {

        TextView nameView, codeView, posView, dayView, hourView, capView, descView;
        Button button, button2;

        public finalViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
            codeView = itemView.findViewById(R.id.code);
            posView = itemView.findViewById(R.id.pos);
            dayView = itemView.findViewById(R.id.txtDays);
            hourView = itemView.findViewById(R.id.txtHours);
            capView = itemView.findViewById(R.id.txtCap);
            descView = itemView.findViewById(R.id.txtDesc);
            button = itemView.findViewById(R.id.drop);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { listener.onButtonClick(Integer.parseInt((String) posView.getText())); }
            });

//            button2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int posPut = Integer.parseInt((String) posView.getText());
//                    String dPut = dayView.getText().toString();
//                    String hPut = hourView.getText().toString();
//                    String capPut = capView.getText().toString();
//                    String descPut = descView.getText().toString();
//
//                    listener2.onButton2Click(posPut, dPut, hPut, capPut, descPut);
//                }
//            });

        }


    }
}
