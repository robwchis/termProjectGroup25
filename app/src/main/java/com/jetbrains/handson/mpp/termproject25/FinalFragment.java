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

import com.jetbrains.handson.mpp.termproject25.databinding.FragmentAdminBinding;
import com.jetbrains.handson.mpp.termproject25.databinding.FragmentFinalBinding;
import com.jetbrains.handson.mpp.termproject25.databinding.FragmentFirstBinding;

public class FinalFragment extends Fragment {

    private FragmentFinalBinding binding;

    TextView roleText, nameText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFinalBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        roleText = view.findViewById(R.id.txt_displayRole);
        nameText = view.findViewById(R.id.txt_displayUsername);


        getParentFragmentManager().setFragmentResultListener("beepboop", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data = result.getString("beepboop");
                Boolean data2 = result.getBoolean("beepboop2");
                System.out.println(data);
                System.out.println(data2);
                nameText.setText("Username: "+data);
                if (!data2) {
                    roleText.setText("Role: User");
                } else {
                    roleText.setText("Role: Instructor");
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