package com.development.aashaworker.custom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.development.aashaworker.DashboardActivity;
import com.development.aashaworker.R;

public class LoadingDialog {
    private Dialog dialog;

    public void show(Context context, String message) {

                 dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_loading);
                dialog.setCancelable(true);

                TextView tvLoading = dialog.findViewById(R.id.tvLoading);
                tvLoading.setText(message);


                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

        dialog.show();
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}


