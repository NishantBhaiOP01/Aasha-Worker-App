package com.development.aashaworker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.development.aashaworker.custom.BeneficiaryDetails;
import com.development.aashaworker.custom.NoticeAdapter;
import com.development.aashaworker.custom.WorkerAdapter;
import com.development.aashaworker.custom.WorkerStorage;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    LinearLayout action_new_patient,action_search,action_reports;



    Button btnSubmit;
    TextView searchInput;

    ListView listView ;

    private ViewPager2 viewPager;
    private DotsIndicator dotsIndicator;
    private NoticeAdapter noticeAdapter;

    private int currentPage = 0;
    private final Handler handler = new Handler();
    private Runnable runnable;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        action_new_patient = findViewById(R.id.action_new_patient);
        action_search = findViewById(R.id.action_search);
        action_reports = findViewById(R.id.action_reports);




        viewPager = findViewById(R.id.viewPager);
        dotsIndicator = findViewById(R.id.dots_indicator);

        List<Integer> notices = Arrays.asList(
                R.drawable.image_one,
                R.drawable.image_one,
                R.drawable.image_one
        );

        noticeAdapter = new NoticeAdapter(notices);
        viewPager.setAdapter(noticeAdapter);

        // Allow peek of neighbors
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        // Attach dots
        dotsIndicator.setViewPager2(viewPager);

        // Auto-scroll every 4 sec
        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == notices.size()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, 4000);
            }
        };
        handler.postDelayed(runnable, 4000);

        searchInput = findViewById(R.id.searchInput);
        btnSubmit = findViewById(R.id.btnSubmit);

         listView = findViewById(R.id.listViewWorkers);
        WorkerStorage storage = new WorkerStorage(this);
        List<BeneficiaryDetails> workers = storage.getWorkers();

        WorkerAdapter adapter = new WorkerAdapter(this, workers);
        listView.setAdapter(adapter);

        WorkerStorage workerStorage = new WorkerStorage(this);

// Retrieve all workers
        List<BeneficiaryDetails> workerList = workerStorage.getWorkers();

// Example: Loop and log
        for (BeneficiaryDetails w : workerList) {
            Log.d("WorkersList",
                    "ID: " + w.getId() +
                            ", Name: " + w.getName() +
                            ", Age: " + w.getAge() +
                            ", Sex: " + w.getSex() +
                            ", Married: " + w.isMarried());
        }


        ImageView internetConnection = findViewById(R.id.internetConnection);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                internetConnection.setImageResource(R.drawable.ic_cloud_done);

// set tint color from colors.xml
                int tintColor = ContextCompat.getColor(DashboardActivity.this, R.color.white);
                internetConnection.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
            }
        },2300);
// set drawable resource



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, PatientFormActivity.class));
            }
        });

        searchInput .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, SearchActivity.class));
            }
        });




        action_new_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, PatientFormActivity.class));
            }
        });
        action_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, SearchActivity.class));
            }
        });
        action_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DashboardActivity.this, "Working On it..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        WorkerStorage storage = new WorkerStorage(this);
        List<BeneficiaryDetails> workers = storage.getWorkers();

        WorkerAdapter adapter = new WorkerAdapter(this, workers);
        listView.setAdapter(adapter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        WorkerStorage storage = new WorkerStorage(this);
        List<BeneficiaryDetails> workers = storage.getWorkers();

        WorkerAdapter adapter = new WorkerAdapter(this, workers);
        listView.setAdapter(adapter);
        super.onPause();
    }
}