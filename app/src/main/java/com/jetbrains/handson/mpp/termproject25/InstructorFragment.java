package com.jetbrains.handson.mpp.termproject25;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jetbrains.handson.mpp.termproject25.databinding.FragmentInstructorBinding;

public class InstructorFragment extends Fragment {

    //xml stuff

    private FragmentInstructorBinding binding;

    String[] names, codes, days, hours, capacities, descs;
    RecyclerView recyclerView;
    InstructorAdapter adapter;
    Button pickCourse;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentInstructorBinding.inflate(inflater, container, false);
        return binding.getRoot();



    }

    void onButtonClick(int pos) {

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView nameText = view.findViewById(R.id.txt_displayUsernameInst);

        getParentFragmentManager().setFragmentResultListener("beepboop", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data = result.getString("beepboop");
                Boolean data2 = result.getBoolean("beepboop2");
                System.out.println("data: "+data);
                System.out.println("data2: "+data2);
                nameText.setText("Username: "+data);
            }
        });


        recyclerView = view.findViewById(R.id.recyclerView2);

        // DOUGLASS -> right here create two String arrays,
        // one with all names that are assigned to the current instructor,
        // and one with all codes that are assigned to the current instructor.
        // i.e get a list of all courses that are assigned to the instructor and get the names and codes.


        // DOUGLASS -> Remove this when the bit above is implemented
        // v just for testing v //
        names = new String[]{"name1", "name2", "name3"};
        codes = new String[]{"code1","code2","code3"};
        days = new String[]{"days1","days2","days3"};
        hours = new String[]{"hours1","hours2","hours3"};
        capacities = new String[]{"cap1","cap2","cap3"};
        descs = new String[]{"desc1","desc2","desc3"};
        // ^ just for testing ^ //

//        InstructorFragment storage = this;



        adapter = new InstructorAdapter(this.getContext(), names, codes, days, hours, capacities, descs, new InstructorAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(int pos) {
                System.out.println("pos: "+pos);

                // DOUGLASS -> right here set the instructor for the course in position 'pos' to unassigned.

                // here

                // DOUGLASS -> also right here is where we refresh the app, but for some reason the name of the instructor isn't saved...

                capacities = new String[]{"cap1h","cap2h","cap3h"};
                updateStuff();
//                String newText = nameText.getText().toString().substring(9);
//
//                Bundle bundle = new Bundle();
//                bundle.putString("beepboop", newText+"gamertest"); // Put anything what you want
//                bundle.putBoolean("beepboop2",true);
//
//                getParentFragmentManager().setFragmentResult("beepboop",bundle);
//
//                NavHostFragment.findNavController(InstructorFragment.this)
//                        .navigate(R.id.action_instructorFragment_self);


            }
        });



        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

//        pickCourse = view.findViewById(R.id.addCourseBtn);

        binding.addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newText = nameText.getText().toString().substring(9);

                Bundle bundle = new Bundle();
                bundle.putString("beepboop", newText); // Put anything what you want
                bundle.putBoolean("beepboop2",true);

                getParentFragmentManager().setFragmentResult("beepboop",bundle);

                NavHostFragment.findNavController(InstructorFragment.this)
                        .navigate(R.id.action_instructorFragment_to_instructorFragment2);
            }
        });


    }

    public void updateStuff() {
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}