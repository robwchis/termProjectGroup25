package com.jetbrains.handson.mpp.termproject25;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FinalAdapter2 extends RecyclerView.Adapter<FinalAdapter2.finalViewHolder> {
    interface OnButtonClickListener {
        void onButtonClick(int pos);
    }

    String[] names,codes, days, hours, capacites, descriptions, instructor;
    Context context;
    OnButtonClickListener listener;



    public FinalAdapter2(Context ct, String[] n, String[] c, String[] d, String[] h, String[] cap, String[] desc, String[] i, OnButtonClickListener l) {
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
        View view = inflater.inflate(R.layout.item_course_final2, parent, false);
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
        holder.instView.setText(instructor[position]);
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

        TextView nameView, codeView, posView, dayView, hourView, capView, descView, instView;
        Button button;

        public finalViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
            codeView = itemView.findViewById(R.id.code);
            posView = itemView.findViewById(R.id.pos);
            dayView = itemView.findViewById(R.id.txtDays);
            hourView = itemView.findViewById(R.id.txtHours);
            capView = itemView.findViewById(R.id.txtCap);
            descView = itemView.findViewById(R.id.txtDesc);
            instView = itemView.findViewById(R.id.inst);
            button = itemView.findViewById(R.id.enroll);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { listener.onButtonClick(Integer.parseInt((String) posView.getText())); }
            });

        }


    }
}
