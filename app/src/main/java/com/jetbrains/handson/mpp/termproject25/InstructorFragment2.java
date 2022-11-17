package com.jetbrains.handson.mpp.termproject25;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    String data;
    RecyclerView recyclerView;
    String instructor;
    InstructorAdapter2 adapter;
    TextView txtUsernameIF2;
    adminDBHandler db;

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

                NavHostFragment.findNavController(InstructorFragment2.this)
                        .navigate(R.id.action_instructorFragment2_to_instructorFragment);

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

        binding = FragmentInstructor2Binding.inflate(inflater, container, false);
        return binding.getRoot();



    }

    void onButtonClick(int pos) {

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView2);
        TextView nameText = view.findViewById(R.id.txt_displayUsernameInst);
        txtUsernameIF2 = view.findViewById(R.id.txtUsernameIF2);
        db = new adminDBHandler(InstructorFragment2.this.getContext());
        getParentFragmentManager().setFragmentResultListener("beepboopBundle", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                data = result.getString("beepboop").trim();


                System.out.println("datasdafsfsfdsfs: "+data);
                instructor = String.valueOf(data);
                txtUsernameIF2.setText(data);
                System.out.println("XD Brother " + txtUsernameIF2.getText());
            }
        });

        System.out.println("XD Brotherdd " + txtUsernameIF2.getText());


        names = new String[100];
        codes = new String[100];

        Cursor cursor = db.getData();

        int i = 0;
        while(cursor.moveToNext()){

            if((cursor.getString(6).equals(""))) {
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


        InstructorAdapter2 adapter = new InstructorAdapter2(this.getContext(), tnames, tcodes, new InstructorAdapter2.OnButtonClickListener() {
            @Override
            public void onButtonClick(int pos) {
                System.out.println("pos: "+pos);

                // DOUGLASS -> right here set the instructor for the course in position 'pos' to the instructor.
                course re = new course(tnames[pos], tcodes[pos]);
                db.removeCourse(re);
                re.setInstructor(txtUsernameIF2.getText().toString());

                db.addCourse(re);

                // here


                // DOGULASS -> this is still not working
                updateStuff();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

    }

    public void updateStuff() {

        System.out.println("GETS TO THIS POINT");

        adapter = new InstructorAdapter2(this.getContext(), names, codes, new InstructorAdapter2.OnButtonClickListener() {
            @Override
            public void onButtonClick(int pos) {
                System.out.println("pos: "+pos);

                // DOUGLASS -> right here set the instructor for the course in position 'pos' to the instructor.
                course re = new course(tnames[pos], tcodes[pos]);
                db.removeCourse(re);
                re.setInstructor(txtUsernameIF2.getText().toString());

                db.addCourse(re);

                // here


                // DOGULASS -> this is still not working
                updateStuff();
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