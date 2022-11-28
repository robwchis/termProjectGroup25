package com.jetbrains.handson.mpp.termproject25;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import com.jetbrains.handson.mpp.termproject25.databinding.FragmentFinalBinding;


public class FinalFragment extends Fragment {

    //xml stuff

    private FragmentFinalBinding binding;
    String username, password;
    String[] names, codes, days, hours, capacities, descs,inst, tinst, tnames, tcodes, tdays, thours, tcapacities, tdescs;
    RecyclerView recyclerView;
    FinalAdapter adapter;
    Button addCourse;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event


//                getParentFragmentManager().setFragmentResult("beepboop",bundle);

                NavHostFragment.findNavController(FinalFragment.this)
                        .navigate(R.id.action_finalFragment_to_FirstFragment);

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

        binding = FragmentFinalBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    void onButtonClick(int pos) {

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView nameText = view.findViewById(R.id.txt_displayUsernameInst);
        adminDBHandler db = new adminDBHandler(FinalFragment.this.getContext());
        userDBHandler uDB = new userDBHandler((FinalFragment.this.getContext()));



        getParentFragmentManager().setFragmentResultListener("beepboop", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                username = result.getString("beepboop");
                password = result.getString("beepboop2");

                nameText.setText("Username: "+username);
            }
        });

        context = this.getContext();

        recyclerView = view.findViewById(R.id.recyclerView2);

        binding.loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                names = new String[5];
                codes = new String[5];
                days = new String[5];
                hours = new String[5];
                capacities = new String[5];
                descs = new String[5];
                inst = new String[5];

                Cursor uc = uDB.getData();
                Cursor c = db.getData();
                int i = 0;

                while(uc.moveToNext()){
                    if(uc.getString(0).equals(username)){



                        if (!(uc.getString(2).toString().equals(""))) {
                            System.out.println("HAS ONE AL:READY");

                            String[] day1 = uc.getString(3).split("@");
                            String[] day2 = uc.getString(4).split("@");

                            names[0] = uc.getString(2);
                            days[0] = day1[0]+"/"+day2[0];
                            hours[0] = day1[1]+"/"+day2[1];

                            while(c.moveToNext()){
                                if(c.getString(0).equals(names[0] )&& days[0].equals(c.getString(2)+"/"+c.getString(3)) && hours[0].equals(c.getString(4)+"/"+c.getString(5)) ){
                                    codes[i] = c.getString(1);
                                    inst[i] = c.getString(6);
                                    descs[i] = c.getString(7);
                                    capacities[i] = c.getString(8);
                                }
                            }


                            i++;
                        }
                        if (!(uc.getString(5).toString().equals(""))) {

                            String[] day1 = uc.getString(6).split("@");
                            String[] day2 = uc.getString(7).split("@");

                            names[1] = uc.getString(5);
                            days[1] = day1[0]+"/"+day2[0];
                            hours[1] = day1[1]+"/"+day2[1];

                            while(c.moveToNext()){
                                if(c.getString(0).equals(names[1] )&& days[1].equals(c.getString(2)+"/"+c.getString(3)) && hours[1].equals(c.getString(4)+"/"+c.getString(5)) ){
                                    codes[i] = c.getString(1);
                                    inst[i] = c.getString(6);
                                    descs[i] = c.getString(7);
                                    capacities[i] = c.getString(8);
                                }
                            }

                            i++;
                        }
                        if (!(uc.getString(8).toString().equals(""))) {

                            String[] day1 = uc.getString(9).split("@");
                            String[] day2 = uc.getString(10).split("@");

                            names[2] = uc.getString(8);
                            days[2] = day1[0]+"/"+day2[0];
                            hours[2] = day1[1]+"/"+day2[1];

                            while(c.moveToNext()){
                                if(c.getString(0).equals(names[2] )&& days[2].equals(c.getString(2)+"/"+c.getString(3)) && hours[2].equals(c.getString(4)+"/"+c.getString(5)) ){
                                    codes[i] = c.getString(1);
                                    inst[i] = c.getString(6);
                                    descs[i] = c.getString(7);
                                    capacities[i] = c.getString(8);
                                }
                            }

                            i++;
                        }
                        if (!(uc.getString(11).toString().equals(""))) {
                            String[] day1 = uc.getString(12).split("@");
                            String[] day2 = uc.getString(13).split("@");

                            names[3] = uc.getString(11);
                            days[3] = day1[0]+"/"+day2[0];
                            hours[3] = day1[1]+"/"+day2[1];

                            while(c.moveToNext()){
                                if(c.getString(0).equals(names[3] )&& days[3].equals(c.getString(2)+"/"+c.getString(3)) && hours[3].equals(c.getString(4)+"/"+c.getString(5)) ){
                                    codes[i] = c.getString(1);
                                    inst[i] = c.getString(6);
                                    descs[i] = c.getString(7);
                                    capacities[i] = c.getString(8);
                                }
                            }

                            i++;
                        }
                        if (!(uc.getString(14).toString().equals(""))) {

                            String[] day1 = uc.getString(15).split("@");
                            String[] day2 = uc.getString(16).split("@");

                            names[4] = uc.getString(14);
                            days[4] = day1[0]+"/"+day2[0];
                            hours[4] = day1[1]+"/"+day2[1];

                            while(c.moveToNext()){
                                if(c.getString(0).equals(names[4] )&& days[4].equals(c.getString(2)+"/"+c.getString(3)) && hours[4].equals(c.getString(4)+"/"+c.getString(5)) ){
                                    codes[i] = c.getString(1);
                                    inst[i] = c.getString(6);
                                    descs[i] = c.getString(7);
                                    capacities[i] = c.getString(8);
                                }
                            }

                            i++;
                        }


                    }
                }



                //I love arrays :)
                tnames = new String[i];
                tcodes = new String[i];
                tdays = new String[i];
                thours = new String[i];
                tcapacities = new String[i];
                tdescs = new String[i];
                tinst = new String[i];

                for (int j = 0; j < i; j++){
                    tnames[j] = names[j];
                    tcodes[j] =  codes[j];
                    tdays[j] = days[j];
                    thours[j] = hours[j];
                    tdescs[j] = descs[j];
                    tcapacities[j] = capacities[j];
                    tinst[j] = inst[j];
                }


                adapter = new FinalAdapter(context, tnames, tcodes, tdays, thours, tcapacities, tdescs, tinst, new FinalAdapter.OnButtonClickListener() {
                    @Override
                    public void onButtonClick(int pos) {
                        // DROP COURSE HERE @JAMES

                        User u = new User(username, password);
                        Cursor cursor = uDB.getData();

                        String course1= "",course2= "",course3= "",course4= "",course5 = "";

                        while(cursor.moveToNext()) {

                            if (cursor.getString(0).equals(username)) {
                                System.out.println("SUPERNATURAL TESTIUNG");
                                if (!(cursor.getString(2).toString().equals(""))) {
                                    System.out.println("HAS ONE AL:READY");
                                    course1 = cursor.getString(2) + "_" + cursor.getString(3) + "_" + cursor.getString(4) + "_";

                                    if (!(cursor.getString(5).toString().equals(""))) {

                                        course2 = cursor.getString(5) + "_" + cursor.getString(6) + "_" + cursor.getString(7) + "_";

                                        if (!(cursor.getString(8).toString().equals(""))) {

                                            course3 = cursor.getString(8) + "_" + cursor.getString(9) + "_" + cursor.getString(10) + "_";

                                            if (!(cursor.getString(11).toString().equals(""))) {

                                                course4 = cursor.getString(11) + "_" + cursor.getString(12) + "_" + cursor.getString(13) + "_";

                                                if (!(cursor.getString(14).toString().equals(""))) {

                                                    course5 = cursor.getString(14) + "_" + cursor.getString(15) + "_" + cursor.getString(16) + "_";

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }




                        uDB.removeUser(u);

                        if(!course1.equals("")){

                            String[] temp = course1.split("_");
                            System.out.println(course1);
                            String[] day1 = temp[1].split("@");
                            String[] day2 = temp[2].split("@");

                            u.setCourse(temp[0], day1[0]+"/"+day2[0], day1[1]+"/"+day2[1]);

                        }

                        u.setCourse(tcodes[pos], tdays[pos], thours[pos]);

                        uDB.addUser(u);

                    }
                    });

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

            }
        });



//        pickCourse = view.findViewById(R.id.addCourseBtn);

        binding.addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newText = nameText.getText().toString().substring(9);
                System.out.println("SAME" +  newText);

                Bundle bundle = new Bundle();
                bundle.putString("beepboop", username); // Put anything what you want
                bundle.putString("beepboop :)", password);


                getParentFragmentManager().setFragmentResult("beepboopBundle",bundle);

                NavHostFragment.findNavController(FinalFragment.this)
                        .navigate(R.id.action_finalFragment_to_finalFragment2);
            }
        });
        System.out.println("nameText isssss" + nameText.getText());

    }

    public void updateStuff() {

        System.out.println("GETS TO THIS POINT");

        adapter = new FinalAdapter(this.getContext(), tnames, tcodes, tdays, thours, tcapacities, tdescs, tinst,new FinalAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(int pos) {
                // DROP COURSE HERE @JAMES
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