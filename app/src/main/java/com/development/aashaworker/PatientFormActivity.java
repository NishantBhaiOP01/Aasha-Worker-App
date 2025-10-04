package com.development.aashaworker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.development.aashaworker.custom.BeneficiaryDetails;
import com.development.aashaworker.custom.LoadingDialog;
import com.development.aashaworker.custom.PrefManager;
import com.development.aashaworker.custom.WorkerStorage;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Map;

public class PatientFormActivity extends AppCompatActivity {
    private TextInputEditText etName, etId, etAge, etAddress, etFather, etHusband, etMonth;

    private TextInputLayout etMonthLayout, etFatherLayout, etHusbandLayout;
    private SwitchMaterial switchMarried, switchPregnant ;
    private ChipGroup chipGroupGender;
    private Chip chipMale, chipFemale, chipOther;
    private Button btnSubmit;

    private String selectedSex = null;
    private Calendar selectedDate = null;
    private float selectedRating = 3f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);
        initViews();
        setupListeners();
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etId = findViewById(R.id.etId);
        etAge = findViewById(R.id.etAge);
        etAddress = findViewById(R.id.etAddress);
        etFather = findViewById(R.id.etFather);
        etHusband = findViewById(R.id.etHusband);
        etMonth = findViewById(R.id.etMonth);
        switchMarried = findViewById(R.id.switchMarried);
        switchPregnant = findViewById(R.id.switchPregnant);
        chipGroupGender = findViewById(R.id.chipGroupGender);
        chipMale = findViewById(R.id.chipMale);
        chipFemale = findViewById(R.id.chipFemale);
        chipOther = findViewById(R.id.chipOther);
        btnSubmit = findViewById(R.id.btnSubmit);

        etMonthLayout = findViewById(R.id.etMonthLayout);
        etFatherLayout = findViewById(R.id.etFatherLayout);
        etHusbandLayout = findViewById(R.id.etHusbandLayout);
        ImageView backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupListeners() {

        // Initially hide pregnancy months field
        etMonthLayout.setVisibility(View.GONE);

        // Marriage switch listener
        switchMarried.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateFieldsVisibility();
        });

        // Pregnancy switch listener (only shows "months" field if pregnant)
        switchPregnant.setOnCheckedChangeListener((buttonView, isChecked) -> {
            etMonthLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        // Gender chip listener
        chipGroupGender.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.chipMale) {
                selectedSex = "Male";
            } else if (checkedId == R.id.chipFemale) {
                selectedSex = "Female";
            } else if (checkedId == R.id.chipOther) {
                selectedSex = "Other";
            } else {
                selectedSex = null;
            }
            updateFieldsVisibility();
        });



        // Submit button
        btnSubmit.setOnClickListener(v -> {

            LoadingDialog loadingDialog = new LoadingDialog();

// Show
            loadingDialog.show(this, "Please wait...");

// Dismiss



            String name = etName.getText().toString().trim();
            String id = etId.getText().toString().trim();
            String age = etAge.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String father = etFather.getText().toString().trim();
            String husband = etHusband.getText().toString().trim();

            if (id.length() != 12) {
                etId.setError("ID must be exactly 12 digits");
                etId.requestFocus();

                loadingDialog.dismiss();
                return;
            }

            WorkerStorage workerStorage = new WorkerStorage(this);

// Generate random 12-digit workerId
            String workerId = id.toString().trim();

            BeneficiaryDetails worker = new BeneficiaryDetails(address, age, father, husband, id, false, switchMarried.isChecked(), switchPregnant.isChecked(), etMonth.getText().toString(), name, "", selectedSex,"Today");


            workerStorage.addWorker(worker);

            Toast.makeText(this, "Worker saved with ID: " + workerId, Toast.LENGTH_SHORT).show();

            Log.e("Nishant","Worker saved with ID: " + workerId);


//
//            PrefManager prefManager = new PrefManager(this);
//            BeneficiaryDetails account = new BeneficiaryDetails(address, age, father, husband, id, switchComplication.isChecked(), switchMarried.isChecked(), switchPregnant.isChecked(), etMonth.getText().toString(), name, notes, selectedSex);
//            String accountId = id.toString(); // must be 12 digits
//            prefManager.saveAccount(accountId, account);

            new Handler( ).postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingDialog.dismiss();
                    finish();
                }
            },2500);
        });






/*

// Retrieve by ID
        BeneficiaryDetails saved = prefManager.getAccount("635672879123");
        if (saved != null) {
            Log.d("SharedPrefs", "Email: " + */
/*saved.getEmail()*//*
);
        }

// Get all accounts
        Map<String, BeneficiaryDetails> all = prefManager.getAllAccounts();
        for (String id : all.keySet()) {
           // Log.d("SharedPrefs", "ID: " + id + " Email: " + all.get(id).getEmail());
        }

*/

    }
    private void updateFieldsVisibility() {
        boolean isMarried = switchMarried.isChecked();

        if ("Female".equals(selectedSex)) {
            if (isMarried) {
                // married female → only husband
                etHusbandLayout.setVisibility(View.VISIBLE);
                etFatherLayout.setVisibility(View.GONE);
            } else {
                // not married female → only father
                etHusbandLayout.setVisibility(View.GONE);
                etFatherLayout.setVisibility(View.VISIBLE);
            }

            // pregnancy fields only for female
            switchPregnant.setVisibility(View.VISIBLE);
            if (switchPregnant.isChecked()) {
                etMonthLayout.setVisibility(View.VISIBLE);
            } else {
                etMonthLayout.setVisibility(View.GONE);
            }

        } else if ("Male".equals(selectedSex) || "Other".equals(selectedSex)) {
            // male or other → only father
            etHusbandLayout.setVisibility(View.GONE);
            etFatherLayout.setVisibility(View.VISIBLE);

            // hide pregnancy fields
            switchPregnant.setVisibility(View.GONE);
            etMonthLayout.setVisibility(View.GONE);

        } else {
            // no gender selected → hide all
            etHusbandLayout.setVisibility(View.GONE);
            etFatherLayout.setVisibility(View.GONE);
            switchPregnant.setVisibility(View.GONE);
            etMonthLayout.setVisibility(View.GONE);
        }
    }






}
