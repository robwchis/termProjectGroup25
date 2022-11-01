package com.jetbrains.handson.mpp.termproject25;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.jetbrains.handson.mpp.termproject25.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    //xml stuff

    private FragmentSecondBinding binding;

    EditText etUsername, etPassword;
    Switch switchInst;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        switchInst = view.findViewById(R.id.switchInst);
        etUsername = view.findViewById(R.id.txt_userName);
        etPassword = view.findViewById(R.id.txt_password);

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(switchInst.isActivated());


                boolean switchState = switchInst.isChecked();
                if (switchState) {
                    System.out.println("what");
                } else {
                    System.out.println("what2");
                }


                if (!switchState) {

                    System.out.println("is a user");

                    userDBHandler userDB = new userDBHandler(SecondFragment.this.getContext());

                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    User nUser = new User(username, password, 0);
                    userDB.addUser(nUser);

                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);

                } else {

                    System.out.println("is a inst");

                    instructorDBHandler instDB = new instructorDBHandler(SecondFragment.this.getContext());

                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    User nUser = new User(username, password, 0);
                    instDB.addUser(nUser);

                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                }


            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}