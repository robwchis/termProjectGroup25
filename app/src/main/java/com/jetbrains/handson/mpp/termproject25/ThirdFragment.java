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

        binding.btnConfirmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                userDBHandler userDB = new userDBHandler(ThirdFragment.this.getContext());

                Cursor cursor = userDB.getData();
                if (cursor.getCount() == 0) {
                    Toast.makeText(ThirdFragment.this.getContext(), "Nothing to show", Toast.LENGTH_SHORT).show();
                } else {
                    while (cursor.moveToNext()) {

//                        System.out.println(cursor.getString(0) + " =? " + etUsername.getText().toString());
//                        System.out.println(cursor.getString(1) + " =? " + etPassword.getText().toString());


                        if ((cursor.getString(1).equals(etUsername.getText().toString()) && (cursor.getString(2).equals(etPassword.getText().toString())))) {

                            Bundle bundle = new Bundle();
                            bundle.putString("key","abc"); // Put anything what you want

                            FinalFragment fragment2 = new FinalFragment();
                            fragment2.setArguments(bundle);

                            getFragmentManager()
                                    .beginTransaction()
                                    .commit();

                            NavHostFragment.findNavController(ThirdFragment.this)
                                    .navigate(R.id.action_thirdFragment_to_finalFragment);
                            break;
                        } else {
                            System.out.println("nope.");
                        }
                    }

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