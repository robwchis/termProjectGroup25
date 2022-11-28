package com.jetbrains.handson.mpp.termproject25;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.jetbrains.handson.mpp.termproject25.databinding.FragmentSecondBinding;
import com.jetbrains.handson.mpp.termproject25.databinding.FragmentThirdBinding;

public class ThirdFragment extends Fragment {

    //XML stuff again
    EditText etUsername, etPassword;

    private FragmentThirdBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUsername = view.findViewById(R.id.txt_userName_login);
        etPassword = view.findViewById(R.id.txt_password_login);

        userDBHandler a = new userDBHandler(ThirdFragment.this.getContext());
        User u = new User("admin", "admin123");
        a.removeUser(u);



        binding.btnConfirmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (etUsername.getText().toString().equals("admin") && etPassword.getText().toString().equals("admin123")) {

                    Bundle bundle = new Bundle();
                    bundle.putString("beepboop",etUsername.getText().toString()); // Put anything what you want
                    getParentFragmentManager().setFragmentResult("beepboop",bundle);

                    NavHostFragment.findNavController(ThirdFragment.this)
                            .navigate(R.id.action_thirdFragment_to_adminFragment);
                }

                userDBHandler userDB = new userDBHandler(ThirdFragment.this.getContext());
                instructorDBHandler instDB = new instructorDBHandler(ThirdFragment.this.getContext());

                Cursor cursor = userDB.getData();
                Cursor cursor2 = instDB.getData();

                if (cursor.getCount() == 0) {
                    Toast.makeText(ThirdFragment.this.getContext(), "Nothing to show", Toast.LENGTH_SHORT).show();
                } else {

                    while (cursor.moveToNext()) {

//                        System.out.println(cursor.getString(0) + " =? " + etUsername.getText().toString());
//                        System.out.println(cursor.getString(1) + " =? " + etPassword.getText().toString());


                        if ((cursor.getString(0).equals(etUsername.getText().toString()) && (cursor.getString(1).equals(etPassword.getText().toString())))) {

                            Bundle bundle = new Bundle();
                            bundle.putString("beepboop", etUsername.getText().toString()); // Put anything what you want
                            bundle.putString("beepboop2", etPassword.getText().toString());

                            getParentFragmentManager().setFragmentResult("beepboop",bundle);


                            NavHostFragment.findNavController(ThirdFragment.this)
                                    .navigate(R.id.action_thirdFragment_to_finalFragment);
                            break;
                        } else {
                            System.out.println("nope.");
                        }
                    }

                }

                if (cursor2.getCount() == 0) {
                    Toast.makeText(ThirdFragment.this.getContext(), "Nothing to show2", Toast.LENGTH_SHORT).show();
                } else {
                    while (cursor2.moveToNext()) {

//                        System.out.println(cursor.getString(0) + " =? " + etUsername.getText().toString());
//                        System.out.println(cursor.getString(1) + " =? " + etPassword.getText().toString());


                        if ((cursor2.getString(0).equals(etUsername.getText().toString()) && (cursor2.getString(1).equals(etPassword.getText().toString())))) {

                            Bundle bundle = new Bundle();
                            bundle.putString("beepboop", etUsername.getText().toString()); // Put anything what you want
                            bundle.putString("beepboop :)", etPassword.getText().toString());

                            getParentFragmentManager().setFragmentResult("beepboop",bundle);


                            NavHostFragment.findNavController(ThirdFragment.this)
                                    .navigate(R.id.action_thirdFragment_to_instructorFragment);
                            break;
                        } else {
                            System.out.println("nope2.");
                        }
                    }

                }
                cursor.close();
                cursor2.close();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}