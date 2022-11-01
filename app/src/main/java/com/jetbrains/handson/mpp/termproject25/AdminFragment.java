package com.jetbrains.handson.mpp.termproject25;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.jetbrains.handson.mpp.termproject25.databinding.FragmentAdminBinding;
import com.jetbrains.handson.mpp.termproject25.databinding.FragmentFinalBinding;

public class AdminFragment extends Fragment {

    private FragmentAdminBinding binding;

    TextView roleText, nameText, confText, addName, addCode;
    LinearLayout bar2, bar3;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bar2 = view.findViewById(R.id.foundItem);
        bar3 = view.findViewById(R.id.editStuff);
        nameText = view.findViewById(R.id.txt_displayUsername);
        confText = view.findViewById(R.id.newText);
        addName = view.findViewById(R.id.addName);
        addCode = view.findViewById(R.id.addCode);


        getParentFragmentManager().setFragmentResultListener("beepboop", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data = result.getString("beepboop");
                System.out.println(data);
                nameText.setText("Username: "+data);


            }
        });

        adminDBHandler db = new adminDBHandler(AdminFragment.this.getContext());


        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = db.getData();

                String fCode = nameText.getText().toString();

                while(cursor.moveToNext()){
                    if(fCode.equals(cursor.getString(1))){
                        bar2.setVisibility(View.VISIBLE);

                        binding.editName.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {

                                bar3.setVisibility(View.VISIBLE);

                                binding.confirm.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {

                                        course c = new course(cursor.getString(1).toString(), cursor.getString(2).toString());
                                        course nC = new course(confText.getText().toString(), cursor.getString(2).toString());

                                        db.addCourse(nC);
                                        db.removeCourse(c);

                                    }
                                });



                            }
                        });

                        binding.editCode.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {

                                bar3.setVisibility(View.VISIBLE);

                                binding.confirm.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {

                                        course c = new course(cursor.getString(1).toString(), cursor.getString(2).toString());
                                        course nC = new course(cursor.getString(1).toString(), confText.getText().toString());

                                        db.addCourse(nC);
                                        db.removeCourse(c);

                                    }
                                });



                            }
                        });


                    } else {
                        Toast.makeText(AdminFragment.this.getContext(), "There is not a class with that course code", Toast.LENGTH_LONG).show();
                    }
                }




            }


        });

        binding.confirmAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                course nC = new course(addName.getText().toString(), addCode.getText().toString());
                addName.setText("Name");
                addCode.setText("Code");
                db.addCourse(nC);
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}