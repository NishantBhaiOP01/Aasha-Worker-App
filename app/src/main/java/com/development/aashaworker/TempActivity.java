package com.development.aashaworker;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TempActivity extends AppCompatActivity {

    // Example data
    private final String beneficiaryName = "Laxmi Kumari";
    private final String husbandName = "Manoj Kumar";
    private final String category = "Pregnant Woman (ANC)";
    private final String beneficiaryId = "BNF/ANC/033";
    private final int age = 26;
    private final String village = "Khera Gram (Cluster 4)";
    private final String phone = "+91 91234 56789";

    // Clinical
    private final String lmp = "2025-05-10";
    private final String edd = "2026-02-15";
    private final String riskStatus = "Low Risk";
    private final String assignedAsha = "Sunita Devi (ASHA/12/45)";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temp);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Beneficiary Health Profile");
            getSupportActionBar().setBackgroundDrawable(
                    getResources().getDrawable(R.color.pink_700, null));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Header
        TextView tvName = findViewById(R.id.beneficiaryName);
        TextView tvCategory = findViewById(R.id.beneficiaryCategory);
        tvName.setText(beneficiaryName);
        tvCategory.setText(category);

        // Demographics
        bindDetail(R.id.detailBeneficiaryId, R.drawable.ic_badge, "Beneficiary ID", beneficiaryId);
        bindDetail(R.id.detailHusbandName, R.drawable.ic_family, "Husband Name", husbandName);
        bindDetail(R.id.detailAge, R.drawable.ic_cake, "Age (Years)", String.valueOf(age));

        // Clinical
        bindDetail(R.id.detailLmp, R.drawable.ic_date_range, "LMP (Last Menstrual Period)", lmp);
        bindDetail(R.id.detailEdd, R.drawable.ic_calendar, "EDD (Estimated Delivery Date)", edd);

        int riskIcon = R.drawable.ic_health;
        String riskLabel = "Current Risk Status";
        bindDetail(R.id.detailRisk, riskIcon, riskLabel, riskStatus);

        // Contact
        bindDetail(R.id.detailVillage, R.drawable.ic_location, "Residential Village", village);
        bindDetail(R.id.detailPhone, R.drawable.ic_phone, "Contact Number", phone);
        bindDetail(R.id.detailAsha, R.drawable.ic_support, "Assigned ASHA Worker", assignedAsha);
    }

    private void bindDetail(int layoutId, int iconRes, String label, String value) {
        android.view.View row = findViewById(layoutId);
        ImageView icon = row.findViewById(R.id.icon);
        TextView tvLabel = row.findViewById(R.id.label);
        TextView tvValue = row.findViewById(R.id.value);

        icon.setImageResource(iconRes);
        tvLabel.setText(label);
        tvValue.setText(value);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}