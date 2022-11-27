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
    String username;
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



        getParentFragmentManager().setFragmentResultListener("beepboop", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                username = result.getString("beepboop");
                Boolean data2 = result.getBoolean("beepboop2");

                System.out.println("data2: "+data2);
                nameText.setText("Username: "+username);
            }
        });

        context = this.getContext();

        recyclerView = view.findViewById(R.id.recyclerView2);

        binding.loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                    if(cursor.getString(6) != null) {
                        System.out.println("HELLO" + username + "aa" +cursor.getString(6) );
                        if (cursor.getString(6).equals(username)) {
                            names[i] = cursor.getString(0);
                            codes[i] = cursor.getString(1);
                            days[i] = cursor.getString(2) + "/" + cursor.getString(3);
                            hours[i] = cursor.getString(4) + "/" + cursor.getString(5);
                            inst[i] = cursor.getString(6);
                            descs[i] = cursor.getString(7);
                            capacities[i] = cursor.getString(8);
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
                bundle.putString("beepboop", newText); // Put anything what you want


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