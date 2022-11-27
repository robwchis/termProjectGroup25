package com.jetbrains.handson.mpp.termproject25;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jetbrains.handson.mpp.termproject25.databinding.FragmentFinal2Binding;
import com.jetbrains.handson.mpp.termproject25.databinding.FragmentInstructor2Binding;

public class FinalFragment2 extends Fragment {

    //xml stuff

    private @NonNull
    FragmentFinal2Binding binding;

    String[] names, codes, days, hours, capacities, inst, descs, tdescs, tnames, tcodes, tdays, thours, tcapacities, tinst;
    String data, data2;
    RecyclerView recyclerView;
    String instructor;
    FinalAdapter2 adapter;
    TextView txtUsernameIF2;
    adminDBHandler db;
    userDBHandler uDB;
    EditText n, c, d;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                System.out.println("!!!!gets here fo sure!!!!");
                Bundle bundle = new Bundle();
                bundle.putString("beepboop", String.valueOf(txtUsernameIF2.getText())); // Put anything what you want


                getParentFragmentManager().setFragmentResult("beepboop",bundle);

                NavHostFragment.findNavController(FinalFragment2.this)
                        .navigate(R.id.action_finalFragment2_to_finalFragment);

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFinal2Binding.inflate(inflater, container, false);
        return binding.getRoot();



    }

    void onButtonClick(int pos) {

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView2);
        TextView nameText = view.findViewById(R.id.txt_displayUsernameInst);
        txtUsernameIF2 = view.findViewById(R.id.txtUsernameIF2);
        n = (EditText) view.findViewById(R.id.etSearchName);
        c = (EditText) view.findViewById(R.id.etSearchCode);
        d = (EditText) view.findViewById(R.id.etSearchDays);
        context = this.getContext();

        db = new adminDBHandler(FinalFragment2.this.getContext());
        uDB = new userDBHandler(FinalFragment2.this.getContext());
        getParentFragmentManager().setFragmentResultListener("beepboopBundle", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                data = result.getString("beepboop").trim();
                data2 = result.getString("beepboop :)");


                instructor = String.valueOf(data);
                txtUsernameIF2.setText(data +" : "+ data2);

            }
        });




        names = new String[100];
        codes = new String[100];
        days = new String[100];
        hours = new String[100];
        capacities = new String[100];
        descs = new String[100];
        inst = new String[100];

        Cursor cursor = db.getData();

        int i = 0;
        while(cursor.moveToNext()){
            names[i] = cursor.getString(0);
            codes[i] = cursor.getString(1);
            days[i] = cursor.getString(2) + "/" + cursor.getString(3);
            hours[i] = cursor.getString(4) + "/" + cursor.getString(5);
            inst[i] = cursor.getString(6);
            descs[i] = cursor.getString(7);
            capacities[i] = cursor.getString(8);
            i++;
        }

        //I love arrays :)
        tnames = new String[i];
        tcodes = new String[i];
        tdays = new String[i];
        thours = new String[i];
        tcapacities = new String[i];
        tdescs = new String[i];
        tinst = new String[i];


        for (int j = 0; j <i; j++){
            tnames[j] = names[j];
            tcodes[j] =  codes[j];
            tdays[j] = days[j];
            thours[j] = hours[j];
            tdescs[j] = descs[j];
            tcapacities[j] = capacities[j];
            tinst[j] = inst[j];
        }


        FinalAdapter2 adapter = new FinalAdapter2(this.getContext(), tnames, tcodes, tdays, thours, tcapacities, tdescs, tinst, new FinalAdapter2.OnButtonClickListener() {
            @Override
            public void onButtonClick(int pos) {

                User u = new User(data, data2);
                uDB.removeUser(u);

                u.setCourse(tcodes[pos], tdays[pos], thours[pos]);
                uDB.addUser(u);
                System.out.println(u.getCourseCode(1) + " " + u.getCourseDay1(1) + " " +u.getCourseDay2(1) );

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //Search Functionality
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ENROLL HERE @JAMES
                String[] sNames = new String[100];
                String[] sCodes = new String[100];

                String ns = n.getText().toString();
                String cs = c.getText().toString();
                String[] ds = d.getText().toString().split("/");

                Cursor cursor = db.getData();

                int i = 0;
                while(cursor.moveToNext()){

                    if((cursor.getString(6).equals(""))) {
                        if(cursor.getString(0).equals(ns) || ( (cursor.getString(2).equals(ds[0])) && (cursor.getString(3).equals(ds[1])) ) ||cursor.getString(1).equals(cs) ){
                            names[i] = cursor.getString(0);
                            codes[i] = cursor.getString(1);
                            //System.out.println("name" + names[i]+ " code " + codes[i]);
                            i++;
                        }

                    }
                }

                //I love arrays :)
                tnames = new String[i];
                tcodes = new String[i];


                for (int j = 0; j <i; j++){
                    tnames[j] = names[j];
                    tcodes[j] =  codes[j];
                    System.out.println("aname" + tnames[j]+ " acode " + tcodes[j]);
                }

                updateStuff();

            }
        });


    }

    public void updateStuff() {

        System.out.println("GETS TO THIS POINT");

        adapter = new FinalAdapter2(this.getContext(), tnames, tcodes, tdays, thours, tcapacities, tdescs, tinst, new FinalAdapter2.OnButtonClickListener() {
            @Override
            public void onButtonClick(int pos) {
                // ENROLL HERE @JAMES
                User u = new User(data, data2);
                Cursor cursor = uDB.getData();

                while(!cursor.getString(0).equals(data)){
                    cursor.moveToNext();
                }

                System.out.println("XD" + cursor.getString(0));
                String course1= "",course2= "",course3= "",course4= "",course5 = "";


                if(!(cursor.getString(2).toString().equals(""))){
                    System.out.println("HAS ONE AL:READY");
                    course1 = cursor.getString(2)+ "_" + cursor.getString(3)+ "_" + cursor.getString(4)+ "_";

                    if(!(cursor.getString(5).toString().equals(""))){

                        course2 = cursor.getString(5)+ "_" + cursor.getString(6)+ "_" + cursor.getString(7)+ "_";

                        if(!(cursor.getString(8).toString().equals(""))){

                            course3 = cursor.getString(8)+ "_" + cursor.getString(9)+ "_" + cursor.getString(10)+ "_";

                            if(!(cursor.getString(11).toString().equals(""))){

                                course4 = cursor.getString(11)+ "_" + cursor.getString(12)+ "_" + cursor.getString(13)+ "_";

                                if(!(cursor.getString(14).toString().equals(""))){

                                    course5 = cursor.getString(14)+ "_" + cursor.getString(15)+ "_" + cursor.getString(16)+ "_";

                                }
                            }
                        }
                    }
                }


                uDB.removeUser(u);

                if(!course1.equals("")){

                    String[] temp = course1.split("_");
                    u.setCourse(temp[1], temp[1], temp[2]);

                }

                u.setCourse(tcodes[pos], tdays[pos], thours[pos]);

                uDB.addUser(u);
                System.out.println(u.getCourseCode(1) + " " + u.getCourseDay1(1) + " " +u.getCourseDay2(1) );

            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}