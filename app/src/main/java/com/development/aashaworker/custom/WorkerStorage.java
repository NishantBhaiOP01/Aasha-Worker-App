package com.development.aashaworker.custom;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WorkerStorage {
    private static final String PREF_NAME = "worker_prefs";
    private static final String KEY_WORKERS = "workers";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public WorkerStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // Save a worker
    public void addWorker(BeneficiaryDetails worker) {
        List<BeneficiaryDetails> workers = getWorkers();
        workers.add(worker);
        saveWorkers(workers);
    }

    // Get all workers
    public List<BeneficiaryDetails> getWorkers() {
        String json = sharedPreferences.getString(KEY_WORKERS, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<List<BeneficiaryDetails>>(){}.getType();
        return gson.fromJson(json, type);
    }
    public BeneficiaryDetails getWorkerById(String workerId) {
        List<BeneficiaryDetails> workers = getWorkers();
        for (BeneficiaryDetails w : workers) {
            if (w.getId().equals(workerId)) {
                return w;
            }
        }
        return null; // Not found
    }

    // Save back into SharedPreferences
    private void saveWorkers(List<BeneficiaryDetails> workers) {
        String json = gson.toJson(workers);
        sharedPreferences.edit().putString(KEY_WORKERS, json).apply();
    }
}

