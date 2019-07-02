package com.example.wordpuzzlegame;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.wordpuzzlegame.model.Languages;

public class AddLanguageDialog extends BottomSheetDialogFragment {

    private EditText et_dialog_add_language;
    private RadioGroup rg_dialog_add_language;
    private RadioButton rb_dialog_add_language_latin,
                        rb_dialog_add_language_geez_amh,
            rb_dialog_add_language_geez_tig;
    private Button button_dialog_add_language;

    public static final String LANGUAGE_NAME = "languageName";
    public static final String ALPHABETS = "alphabets";
    public static final String BUTTON_TEXT = "button_text";

    public interface OnSaveClicked {

        void onSave(Languages languages);

    }

    // we can use this constructor to pass the language name, alphabet and the button text, when we want to edit a language
    public AddLanguageDialog newInstance(String languageName, String alphabet, String buttonText) {
        AddLanguageDialog addLanguageDialog = new AddLanguageDialog();
        Bundle bundle = new Bundle();
        bundle.putString(LANGUAGE_NAME, languageName);
        bundle.putString(ALPHABETS, alphabet);
        bundle.putString(BUTTON_TEXT, buttonText);
        addLanguageDialog.setArguments(bundle);
        return addLanguageDialog;
    }

    public OnSaveClicked onSaveClicked;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_add_language, container, false);

        et_dialog_add_language = rootView.findViewById(R.id.et_dialog_add_language);
        rg_dialog_add_language = rootView.findViewById(R.id.rg_dialog_add_language);
        rb_dialog_add_language_geez_amh = rootView.findViewById(R.id.rb_dialog_add_language_geez_amh);
        rb_dialog_add_language_geez_tig = rootView.findViewById(R.id.rb_dialog_add_language_geez_tig);
        rb_dialog_add_language_latin = rootView.findViewById(R.id.rb_dialog_add_language_latin);
        button_dialog_add_language = rootView.findViewById(R.id.button_dialog_add_language);

        if(getArguments() != null) {

            String languageName = getArguments().getString(LANGUAGE_NAME);
            String alphabet = getArguments().getString(ALPHABETS);
            String buttonText = getArguments().getString(BUTTON_TEXT);

            et_dialog_add_language.setText(languageName);
            button_dialog_add_language.setText(buttonText);

            Log.i("alphabet", rb_dialog_add_language_geez_tig.getText().toString());
            Log.i("alphabet", alphabet);
            if(rb_dialog_add_language_geez_amh.getText().toString().equals(alphabet)) {
                rb_dialog_add_language_geez_amh.setSelected(true);
            } else if(rb_dialog_add_language_latin.getText().toString().equals(alphabet)){
                Log.i("alphabet", "matches");
                rb_dialog_add_language_latin.setSelected(true);
            } else {
                rb_dialog_add_language_geez_tig.setSelected(true);
            }

        }

        button_dialog_add_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String language = et_dialog_add_language.getText().toString().trim();
                int radioButtonId = rg_dialog_add_language.getCheckedRadioButtonId();
                RadioButton radioButton = rg_dialog_add_language.findViewById(radioButtonId);
                if(radioButton == null || language.length() == 0) {

                    Toast.makeText(getContext(), "Please, fill all the data", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String alphabet = radioButton.getText().toString();
                    Languages languages = new Languages(language, alphabet);
                    onSaveClicked.onSave(languages);
                    Toast.makeText(getContext(), language + " " + alphabet, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onSaveClicked = (OnSaveClicked) context;
    }
}
