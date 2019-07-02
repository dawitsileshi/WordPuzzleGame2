package com.example.wordpuzzlegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MatchImageDialog extends DialogFragment {

    private TextView tv_dialog_correct;
    private TextView tv_dialog_inCorrect;
    private Button button_dialog;
    private ImageView iv_dialog;

    private LinearLayout ll_dialog_correct, ll_dialog_inCorrect, ll_dialog_separator;

    public static final String IMAGE_SENT = "image_sent";
    public static final String CORRECT_ANSWER_SENT = "correct_answer_sent";
    public static final String INCORRECT_ANSWER_SENT = "incorrect_answer_sent";

    public MatchImageDialog newInstance(byte[] image, String correctAnswer, String inCorrectAnswer) {

        MatchImageDialog matchImageDialog = new MatchImageDialog();
        Bundle bundle = new Bundle();
        bundle.putByteArray(IMAGE_SENT, image);
        bundle.putString(CORRECT_ANSWER_SENT, correctAnswer);
        bundle.putString(INCORRECT_ANSWER_SENT, inCorrectAnswer);
        matchImageDialog.setArguments(bundle);
        return matchImageDialog;

    }

    public interface OKClicked{
        void okClicked();
    }

    public OKClicked okClicked;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_image_word, container, false);

//        assert getArguments() != null;
        tv_dialog_correct = rootView.findViewById(R.id.tv_dialog_correct);
        tv_dialog_inCorrect = rootView.findViewById(R.id.tv_dialog_incorrect);
        iv_dialog = rootView.findViewById(R.id.iv_dialog);
        button_dialog = rootView.findViewById(R.id.button_dialog);

        ll_dialog_correct = rootView.findViewById(R.id.ll_dialog_correct_answer);
        ll_dialog_inCorrect = rootView.findViewById(R.id.ll_dialog_incorrect_answer);
        ll_dialog_separator = rootView.findViewById(R.id.ll_dialog_separator);

        if(getArguments() != null) {
            byte[] image = getArguments().getByteArray(IMAGE_SENT);
            String correctAnswer = getArguments().getString(CORRECT_ANSWER_SENT);
            String inCorrectAnswer = getArguments().getString(INCORRECT_ANSWER_SENT);
            if(inCorrectAnswer.equals("")) {
                ll_dialog_inCorrect.setVisibility(View.GONE);
                ll_dialog_separator.setVisibility(View.GONE);
            }
//            Log.i("passed data", image + " " + correctAnswer + " " + inCorrectAnswer);
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            iv_dialog.setImageBitmap(bitmap);
            tv_dialog_correct.setText(correctAnswer);
            tv_dialog_inCorrect.setText(inCorrectAnswer);
//            Toast.makeText(getContext(), "something wrong", Toast.LENGTH_SHORT).show();
        }



        button_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okClicked.okClicked();
                dismiss();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        okClicked = (OKClicked) context;

    }
}
