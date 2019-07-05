package com.example.wordpuzzlegame;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LeavePageDialog extends BottomSheetDialogFragment {

    public interface LeaveAccepted{
        void onAccepted();
    }

    public LeaveAccepted leaveAccepted;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_leave_page, container, false);

        final EditText et_dialog_leave_page = rootView.findViewById(R.id.et_dialog_leave_page);
        Button button_dialog_leave_page = rootView.findViewById(R.id.button_dialog_leave_page);

        button_dialog_leave_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = et_dialog_leave_page.getText().toString().trim();

                if(password.length() == 0) {
                    Toast.makeText(getContext(), "Please, fill your password", Toast.LENGTH_SHORT).show();
                } else {
                    if(checkIfCorrect(password)) {
                        leaveAccepted.onAccepted();
                    } else {
                        Toast.makeText(getContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return rootView;
    }

    private boolean checkIfCorrect(String password) {

        Parent parent = new Parent(getContext()).singleParent();

        String newPassword = parent.getParent_password();

        return newPassword.equals(password);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        leaveAccepted = (LeaveAccepted) context;
    }
}
