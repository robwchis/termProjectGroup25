package com.jetbrains.handson.mpp.termproject25;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jetbrains.handson.mpp.termproject25.databinding.FragmentInstructor2Binding;
import com.jetbrains.handson.mpp.termproject25.databinding.FragmentInstructorBinding;

public class InstructorFragment2 extends Fragment {

    //xml stuff

    private @NonNull FragmentInstructor2Binding binding;

    String[] names, codes, tnames, tcodes;
    String username;
    RecyclerView recyclerView;
    String instructor;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentInstructor2Binding.inflate(inflater, container, false);
        return binding.getRoot();



    }

    void onButtonClick(int pos) {

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView nameText = view.findViewById(R.id.txt_displayUsernameInst);
        adminDBHandler db = new adminDBHandler(InstructorFragment2.this.getContext());
        getParentFragmentManager().setFragmentResultListener("beepboop", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                username = result.getString("beepboop");
                Boolean data2 = result.getBoolean("beepboop2");
                System.out.println("data: "+username);
                System.out.println("data2: "+data2);
                instructor = String.valueOf(data2);
            }
        });


        recyclerView = view.findViewById(R.id.recyclerView2);

        names = new String[100];
        codes = new String[100];

        Cursor cursor = db.getData();

        int i = 0;
        while(cursor.moveToNext()){

            if(cursor.getString(6) == null) {
                    names[i] = cursor.getString(0);
                    codes[i] = cursor.getString(1);
                    i++;
            }
        }

        //I love arrays :)
        tnames = new String[i];
        tcodes = new String[i];


        for (int j = 0; j <i; j++){
            tnames[j] = names[j];
            tcodes[j] =  codes[j];
        }
        System.out.println(tnames[0]);

        InstructorAdapter2 adapter = new InstructorAdapter2(this.getContext(), tnames, tcodes, new InstructorAdapter2.OnButtonClickListener() {
            @Override
            public void onButtonClick(int pos) {
                System.out.println("pos: "+pos);

                // DOUGLASS -> right here set the instructor for the course in position 'pos' to the instructor.

                // here


                // DOGULASS -> this is still not working
                String newText = instructor;

                Bundle bundle = new Bundle();
                bundle.putString("beepboop", newText); // Put anything what you want
                bundle.putBoolean("beepboop2",true);

                getParentFragmentManager().setFragmentResult("beepboop",bundle);

                NavHostFragment.findNavController(InstructorFragment2.this)
                        .navigate(R.id.action_instructorFragment2_self);


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