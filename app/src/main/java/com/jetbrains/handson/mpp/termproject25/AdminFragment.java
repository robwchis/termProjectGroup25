package com.jetbrains.handson.mpp.termproject25;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

    TextView nameCode, nameText, confText, addName, addCode;
    LinearLayout bar2, bar3;
    EditText searchBar, delBar;



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
        searchBar = view.findViewById(R.id.searchBar);
        nameCode = view.findViewById(R.id.nameCode);
        delBar = view.findViewById(R.id.delBar);


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

                String fCode = searchBar.getText().toString();

                while(cursor.moveToNext()){
                    System.out.println(fCode + " =? " + cursor.getString(1));

                    String currName = cursor.getString(0);
                    String currCode = cursor.getString(1);

                    if(fCode.equals(cursor.getString(1))){
                        bar2.setVisibility(View.VISIBLE);
                        nameCode.setText(currName + " | " + currCode);

                        binding.editName.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {

                                bar3.setVisibility(View.VISIBLE);

                                binding.confirm.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {

                                        course c = new course(cursor.getString(0).toString(), cursor.getString(1).toString());
                                        course nC = new course(confText.getText().toString(), cursor.getString(1).toString());

                                        db.removeCourse(c);
                                        db.addCourse(nC);

                                        bar2.setVisibility(View.INVISIBLE);
                                        bar3.setVisibility(View.INVISIBLE);

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

                                        course c = new course(cursor.getString(0).toString(), cursor.getString(1).toString());
                                        course nC = new course(cursor.getString(0).toString(), confText.getText().toString());

                                        db.removeCourse(c);
                                        db.addCourse(nC);

                                        bar2.setVisibility(View.INVISIBLE);
                                        bar3.setVisibility(View.INVISIBLE);

                                    }
                                });



                            }
                        });

                        binding.delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                course nC = new course(currName, currCode);
                                db.removeCourse(nC);
                                bar2.setVisibility(View.INVISIBLE);
                                bar3.setVisibility(View.INVISIBLE);
                                searchBar.setText("Code");
                            }
                        });

                        break;

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

        binding.delButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userDBHandler uDB = new userDBHandler(AdminFragment.this.getContext());
                instructorDBHandler iDB = new instructorDBHandler(AdminFragment.this.getContext());
                Cursor cursor = uDB.getData();
                Cursor icursor = iDB.getData();

                String bar = delBar.getText().toString();

                while(cursor.moveToNext()){
                    if(cursor.getString(0).equals(bar)){

                        User dC = new User(cursor.getString(0), cursor.getString(1), 0 );
                        uDB.removeUser(dC);

                        delBar.setText("Username");

                        Toast toast = Toast.makeText(AdminFragment.this.getContext(), "A user has been removed", Toast.LENGTH_LONG);

                        break;
                    }
                }
                while(icursor.moveToNext()){
                    if(icursor.getString(0).equals(bar)){

                        User dC = new User(icursor.getString(0), icursor.getString(1), 0 );
                        iDB.removeUser(dC);

                        delBar.setText("Username");

                        Toast toast = Toast.makeText(AdminFragment.this.getContext(), "A user has been removed", Toast.LENGTH_LONG);

                        break;
                    }
                }

                Toast toast = Toast.makeText(AdminFragment.this.getContext(), "No user has been removed", Toast.LENGTH_LONG);

            }
        }));


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}