package com.zybooks.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zybooks.myapplication.model.FileEntry;
import com.zybooks.myapplication.repo.FileRepo;

import java.util.ArrayList;
import java.util.List;

public class FileActivity extends AppCompatActivity {

    private EditText mId;
    private EditText mName;
    private EditText mEmail;
    private EditText mAccessPassword;
    private EditText mConfirmPassword;

    private TextView mIdView;
    private TextView mNameView;
    private TextView mEmailView;
    private TextView mAccessPasswordView;
    private TextView mConfirmPasswordView;
    private TextView mSpinnerView;
    private Spinner mSpinner;
    private CheckBox mNeutered;
    private RadioGroup mMaleOrFemale;
    private RadioGroup mMale;
    private RadioGroup mFemale;
    private String dogBreed;
    private String mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);
        mId                     = findViewById(R.id.microchip_edit_text);
        mIdView                 = findViewById(R.id.microchip_text_view);
        mName                   = findViewById(R.id.Name_edit_text);
        mNameView               = findViewById(R.id.Name_text_view);
        mEmail                  = findViewById(R.id.email_edit_text);
        mEmailView              = findViewById(R.id.email_text_view);
        mAccessPassword         = findViewById(R.id.Access_edit_text);
        mAccessPasswordView     = findViewById(R.id.Access_text_view);
        mConfirmPassword        = findViewById(R.id.confirm_edit_text);
        mConfirmPasswordView    = findViewById(R.id.confirm_text_view);
        mMaleOrFemale           = findViewById(R.id.Gender_Radio_Group);
        mNeutered               = (CheckBox) findViewById(R.id.checkbox_Neutered);
        mSpinner                = findViewById(R.id.breed_spinner);
        mSpinnerView            = findViewById(R.id.breed_text_view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Dog_Type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dogBreed = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//
//        mAccessPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//            }
//            // Add the missing code here
//        });
//
//        mConfirmPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//            }
//            // Add the missing code here
//        });

//        mSpinner.setSelection(0);
//        mMale.setSelected(false);
//        mFemale.setSelected(true);
//        mNeutered.setChecked(true);
    }

    public void onGenderSelected(View view) {
    }

    public void onCheckboxClicked(View view) {
    }

    public void Reset(View view) {
        RadioButton male = findViewById(R.id.male_button);
        RadioButton female = findViewById(R.id.female_button);
        female.setChecked(true);
        male.setChecked(false);
        String reset = "ID";
        mId.setText(reset);
        reset = "";
        mName.setText(reset);
        mAccessPassword.setText(reset);
        mConfirmPassword.setText(reset);
        reset = "none@none.com";
        mEmail.setText(reset);
        mSpinner.setSelection(0);
    }

    public void Submit(View view) {
        FileRepo mFileRepo = FileRepo.getInstance(this);
        StringBuilder message   = new StringBuilder();
        boolean empty           = false;
        int strLen;

        String id               = mId.getText().toString();
        String name             = mName.getText().toString();
        String email            = mEmail.getText().toString();
        String access           = mAccessPassword.getText().toString();
        String confirm          = mConfirmPassword.getText().toString();
        boolean neutered        = mNeutered.isChecked();
        int buttonId            = mMaleOrFemale.getCheckedRadioButtonId();
        String gender;

        if(R.id.male_button == buttonId)
        {
            gender = "Male";
        } else {
            gender = "Female";
        }

        System.out.println(gender);



        if(id.length() == 0) {
            empty = true;
            mIdView.setTextColor(Color.RED);
        } else {
            strLen = id.length();

            boolean error = false;

            if(strLen < 5 || strLen > 15) {
                error = true;
                message.append("ID must be 5-15 characters/digits long\n\n");
            }

            if(!error)
            {
                if(mFileRepo.contains(id))
                {
                    error = true;
                    message.append("ID Already Taken. Please Try Again\n\n");
                }
            }

            if(!error)
            {
                for (int i = 0; i < strLen; i++) {
                    char temp = id.charAt(i);

                    if (!Character.isAlphabetic(temp) && !Character.isDigit(temp)) {
                        message.append("ID must be letters or numbers, NO alphanumeric characters (%,!,&,ect)\n\n");
                        error = true;
                    }
                    if (Character.isAlphabetic(temp) && !Character.isUpperCase(temp)) {
                        message.append("Letters in Id must be capitalized\n\n");
                        error = true;
                    }
                }
            }


            if(error)
            {
                mIdView.setTextColor(Color.RED);
            } else {
                mIdView.setTextColor(Color.GRAY);
            }

        }

        if(name.length() == 0) {
            empty = true;
            mNameView.setTextColor(Color.RED);
        } else {
            //check Name
            strLen = name.length();
            boolean error = false;
            for (int i = 0; i < strLen; i++) {
                char temp = name.charAt(i);


                if (Character.isWhitespace(temp)) continue;
                if ((i == 0 && !Character.isUpperCase(temp))) {
                    message.append("First Letter of each part of your name must be capitalized\n\n");
                    error = true;
                } else if (i > 0 && !Character.isUpperCase(temp) && Character.isWhitespace(name.charAt(i-1))) {
                    message.append("First Letter of each part of your name must be capitalized\n\n");
                    error = true;
                }
                if(!Character.isAlphabetic(temp))
                {
                    error = true;
                    message.append("Name must only contain letters\n\n");
                }
            }
            if(error)
            {
                mNameView.setTextColor(Color.RED);
            } else {
                mNameView.setTextColor(Color.GRAY);
            }
        }
        if(email.length() == 0) {
            empty = true;
            mEmailView.setTextColor(Color.RED);
        } else {
            String domainType = "";
            int count = 0;
            strLen = email.length();
            boolean error = false;

            while(count < strLen && email.charAt(count) != '@') count++;

            if(count < 3)
            {
                error = true;
                message.append("The <prefix> of the email cannot be shorter than 3 characters. \n\n");
            }
            while(count < strLen && email.charAt(count) != '.') count++;
            if(count == strLen){
                message.append("A valid email is of the form <prefix>@<domain>.<domain type>.\n\n");
                error = true;
            }
            else {
                count++;
                while (count < strLen) domainType += email.charAt(count++);
                switch (domainType) {
                    case "edu":
                        break;
                    case "co":
                        break;
                    case "com":
                        break;
                    case "gal":
                        break;
                    default:
                        error = true;
                        message.append("The <domain type> of the email must be one of the following: edu, co, com, or gal.\n\n");
                }
            }

            if(error)
            {
                mEmailView.setTextColor(Color.RED);
            } else {
                mEmailView.setTextColor(Color.GRAY);
            }
        }
        if(access.length() == 0) {
            empty = true;
            mAccessPasswordView.setTextColor(Color.RED);
        }
        if(confirm.length() == 0) {
            empty = true;
            mConfirmPasswordView.setTextColor(Color.RED);
        }
        if(dogBreed.equals("N/A"))
        {
            empty = true;
            mSpinnerView.setTextColor(Color.RED);
        } else {
            mSpinnerView.setTextColor(Color.GRAY);
        }
        if(empty) {
            message.append("Fill in empty or unselected fields\n\n");
        }

        //check Passwords

        if(access.length() > 0 && access.equals(confirm)){
            mAccessPasswordView.setTextColor(Color.GRAY);
            mConfirmPasswordView.setTextColor(Color.GRAY);
        } else if (access.length() > 0) {
            mAccessPasswordView.setTextColor(Color.RED);
            mConfirmPasswordView.setTextColor(Color.RED);
            message.append("Access Code and Confirm Access Code have the same value.");
        }


        if(message.length() == 0) {
            FileEntry newEntry = new FileEntry(id, name, gender, email, access, dogBreed, neutered);
            mFileRepo.addFileEntry(newEntry);
            message.append("Information has successfully been added to the dataBase");
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}