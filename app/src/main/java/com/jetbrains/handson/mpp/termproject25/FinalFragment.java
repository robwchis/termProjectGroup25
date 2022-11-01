package com.jetbrains.handson.mpp.termproject25;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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

        Bundle bundle = this.getArguments();

        if(bundle != null){
            // handle your code here.
            System.out.println("WORKED");
        }


        roleText = view.findViewById(R.id.txt_displayRole);
        nameText = view.findViewById(R.id.txt_displayUsername);

        userDBHandler userDB = new userDBHandler(FinalFragment.this.getContext());

        Cursor cursor = userDB.getData();

        System.out.println(cursor.getCount());

        while (cursor.moveToNext()) {
            System.out.println("OVER HERE: "+cursor.getString(0));
            nameText.setText(cursor.getString(0));
            break;
        }


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}