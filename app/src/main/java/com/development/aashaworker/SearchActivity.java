package com.development.aashaworker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Worker;

import com.development.aashaworker.custom.BeneficiaryDetails;
import com.development.aashaworker.custom.PrefManager;
import com.development.aashaworker.custom.WorkerStorage;
import com.google.android.material.button.MaterialButton;

public class SearchActivity extends AppCompatActivity {

    MaterialButton searchButton;
    EditText searchTxt;

    TextView beneficialFatherDetails, beneficialDetails, foundTxt;
    LinearLayout detailsLayout;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchButton = findViewById(R.id.btnSubmit);
        searchTxt = findViewById(R.id.searchInput);

        beneficialFatherDetails = findViewById(R.id.beneficialFatherDetails);
        beneficialDetails = findViewById(R.id.beneficialDetails);
        detailsLayout = findViewById(R.id.detailsLayout);
        foundTxt = findViewById(R.id.foundTxt);

        progressBar = findViewById(R.id.progressIndicator);

        progressBar.setVisibility(View.GONE);
        detailsLayout.setVisibility(View.GONE);
        foundTxt.setVisibility(View.GONE);


        ImageView backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        detailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, PersonDetailsActivity.class);
                intent.putExtra("id",searchTxt.getText().toString().trim() );
                startActivity(intent);
            }
        });
        searchButton.setOnClickListener(v -> {

            progressBar.setVisibility(View.GONE);
            detailsLayout.setVisibility(View.GONE);
            foundTxt.setVisibility(View.GONE);

            progressBar.setVisibility(View.VISIBLE);
/*            PrefManager prefManager = new PrefManager(this);
            BeneficiaryDetails saved = prefManager.getAccount(searchTxt.getText().toString().trim());
            if (saved != null) {
                Toast.makeText(this, String.format("Name: %s", saved.getName()), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
            }*/

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


            WorkerStorage workerStorage = new WorkerStorage(SearchActivity.this);
            String searchId = searchTxt.getText().toString().trim(); // your 12-digit worker ID
            BeneficiaryDetails foundWorker = workerStorage.getWorkerById(searchId);

            if (foundWorker != null) {


                beneficialDetails.setText("Name : " + foundWorker.getName());
                if (foundWorker.getFather()!=null && foundWorker.getHusband()!=null){
                    if (foundWorker.getFather().isEmpty()){
                        beneficialFatherDetails.setText("Husband Name : " + foundWorker.getHusband());
                    }else if (foundWorker.getHusband().isEmpty()){
                        beneficialFatherDetails.setText("Father Name : " + foundWorker.getFather());
                    }else{

                        beneficialFatherDetails.setText("NOT AVIALABLE");
                    }
                }

                detailsLayout.setVisibility(View.VISIBLE);
                foundTxt.setVisibility(View.GONE);


            } else {

                detailsLayout.setVisibility(View.GONE);
                foundTxt.setVisibility(View.VISIBLE);
            }

            progressBar.setVisibility(View.GONE);

                }
            },3000);

        });

    }
}