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

import com.jetbrains.handson.mpp.termproject25.databinding.FragmentInstructorBinding;

public class InstructorFragment extends Fragment {

    //xml stuff

    private FragmentInstructorBinding binding;
    String username;
    String[] names, codes, days, hours, capacities, descs, tnames, tcodes, tdays, thours, tcapacities, tdescs;
    RecyclerView recyclerView;
    InstructorAdapter adapter;
    Button pickCourse;
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

                NavHostFragment.findNavController(InstructorFragment.this)
                        .navigate(R.id.action_instructorFragment_to_FirstFragment);

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

        binding = FragmentInstructorBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    void onButtonClick(int pos) {

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView nameText = view.findViewById(R.id.txt_displayUsernameInst);
        adminDBHandler db = new adminDBHandler(InstructorFragment.this.getContext());



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

                for (int j = 0; j < i; j++){
                    tnames[j] = names[j];
                    tcodes[j] =  codes[j];
                    tdays[j] = days[j];
                    thours[j] = hours[j];
                    tdescs[j] = descs[j];
                    tcapacities[j] = capacities[j];
                }


                adapter = new InstructorAdapter(context, tnames, tcodes, tdays, thours, tcapacities, tdescs, new InstructorAdapter.OnButtonClickListener() {
                    @Override
                    public void onButtonClick(int pos) {
                        System.out.println("pos: "+pos);

                        // DOUGLASS -> right here set the instructor for the course in position 'pos' to unassigned.
                        // then get a list of all courses that are assigned to the instructor and get the data for each one in an array (again).

                        // here

                        course re = new course(tnames[pos], tcodes[pos]);
                        db.removeCourse(re);
                        re.setInstructor("");

                        db.addCourse(re);


//                capacities = new String[]{"cap1h","cap2h","cap3h"};
                        updateStuff();


                    }
                }, new InstructorAdapter.OnButton2ClickListener() {
                    @Override
                    public void onButton2Click(int pos, String d, String h, String cap, String desc) {
                        System.out.println("pos: "+pos);
                        boolean good = true;
                        Toast toast;
                        course reOrig = new course(tnames[pos], tcodes[pos]);
                        course re = reOrig;
                        db.removeCourse(re);
                        re.setCourseDesc(desc);


                        if (!d.contains("/")) {
                            toast = Toast.makeText(context, "ERROR: Days formatted incorrectly", Toast.LENGTH_LONG);
                            toast.show();
                            good = false;
                            System.out.println("ERR DAY");
                        } else {
                            String[] dumb = d.split("/", 2);
                            re.setCourseDays(dumb[0], dumb[1]);
                        }



                        if (!h.contains("/")) {
                            toast = Toast.makeText(context, "ERROR: Hours formatted incorrectly", Toast.LENGTH_LONG);
                            toast.show();
                            good = false;
                            System.out.println("ERR DAY");
                        } else {
                            String[] dumb2 = h.split("/", 2);
                            re.setCourseTimes(dumb2[0], dumb2[1]);
                        }


                        try {
                            final int i1 = Integer.parseInt(cap) + 10;
                            re.setStudentCapacity(cap);
                        } catch(Exception e) {
                            System.out.println("ERR CAP");
                            toast = Toast.makeText(context, "ERROR: Capacity formatted incorrectly", Toast.LENGTH_LONG);
                            toast.show();
                            good = false;
                        }

                        re.setInstructor(username);
                        if (good) {
                            db.addCourse(re);
                            updateStuff();
                            toast = Toast.makeText(context, "Changes saved.", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            db.addCourse(reOrig);
                            updateStuff();
                        }



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

                NavHostFragment.findNavController(InstructorFragment.this)
                        .navigate(R.id.action_instructorFragment_to_instructorFragment2);
            }
        });
        System.out.println("nameText isssss" + nameText.getText());

    }

    public void updateStuff() {

        System.out.println("GETS TO THIS POINT");

        adapter = new InstructorAdapter(this.getContext(), tnames, tcodes, tdays, thours, tcapacities, tdescs, new InstructorAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(int pos) {
                System.out.println("pos: "+pos);

                // DOUGLASS -> right here set the instructor for the course in position 'pos' to unassigned.
                // then get a list of all courses that are assigned to the instructor and get the data for each one in an array (again).

                // here


//                capacities = new String[]{"cap1h","cap2h","cap3h"};
                updateStuff();


            }
        }, new InstructorAdapter.OnButton2ClickListener() {
            @Override
            public void onButton2Click(int pos, String d, String h, String cap, String desc) {
                System.out.println("pos: "+pos);

                // DOUGLASS -> right here set every value for the item in position "pos" to the values put here in here
                // then get a list of all courses that are assigned to the instructor and get the data for each one in an array (again).

                // here


//                capacities = new String[]{"cap1h","cap2h","cap3h"};
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