package com.jetbrains.handson.mpp.termproject25;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InstructorAdapter2 extends RecyclerView.Adapter<InstructorAdapter2.instructorViewHolder> {
    interface OnButtonClickListener {
        void onButtonClick(int pos);
    }

    String[] names,codes, days, hours, capacites, descriptions, instructor;
    Context context;
    OnButtonClickListener listener;



    public InstructorAdapter2(Context ct, String[] n, String[] c, String[] d, String[] h, String[] cap, String[] desc, String[] i, OnButtonClickListener l) {
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
    public instructorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_course2, parent, false);
        return new instructorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull instructorViewHolder holder, int position) {
        holder.nameView.setText(names[position]);
        holder.codeView.setText(codes[position]);
        holder.posView.setText(String.valueOf(position));

        System.out.println("|"+instructor[position]+"|");
        System.out.println("||"+instructor[position].equals("")+"||");
        if (instructor[position].equals("")) {
            holder.instView.setText("Current inst: None");
        } else {
            holder.instView.setText("Current inst: "+instructor[position]);
        }
        holder.dayView.setText(days[position]);
        holder.hourView.setText(hours[position]);
        holder.capView.setText(capacites[position]);
        holder.descView.setText(descriptions[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class instructorViewHolder extends RecyclerView.ViewHolder {

        TextView nameView, codeView, posView, instView, dayView, hourView, capView, descView;
        Button button;

        public instructorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
            codeView = itemView.findViewById(R.id.code);
            posView = itemView.findViewById(R.id.pos);
            instView = itemView.findViewById(R.id.inst);
            dayView = itemView.findViewById(R.id.txtDays);
            hourView = itemView.findViewById(R.id.txtHours);
            capView = itemView.findViewById(R.id.txtCap);
            descView = itemView.findViewById(R.id.txtDesc);
            button = itemView.findViewById(R.id.add);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    System.out.println("test: " + posView.getText());

                    listener.onButtonClick(Integer.parseInt((String) posView.getText()));


                }
            });
        }
    }
}
