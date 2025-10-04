package com.development.aashaworker.custom;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PrefManager {
    private static final String PREF_NAME = "MyAppPrefs";
    private static final String KEY_ACCOUNTS = "accounts_data";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Gson gson;

    public PrefManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
        gson = new Gson();
    }

    // Save or Update an Account with 12-digit ID
    public void saveAccount(String id, BeneficiaryDetails account) {
        Map<String, BeneficiaryDetails> accounts = getAllAccounts();
        accounts.put(id, account); // insert or update
        String json = gson.toJson(accounts);
        editor.putString(KEY_ACCOUNTS, json);
        editor.apply();
    }

    // Get a specific account by ID
    public BeneficiaryDetails getAccount(String id) {
        Map<String, BeneficiaryDetails> accounts = getAllAccounts();
        return accounts.get(id);
    }

    // Get all accounts
    public Map<String, BeneficiaryDetails> getAllAccounts() {
        String json = prefs.getString(KEY_ACCOUNTS, null);
        if (json != null) {
            Type type = new TypeToken<Map<String, BeneficiaryDetails>>(){}.getType();
            return gson.fromJson(json, type);
        }
        return new HashMap<>();
    }

    // Delete one account
    public void deleteAccount(String id) {
        Map<String, BeneficiaryDetails> accounts = getAllAccounts();
        accounts.remove(id);
        editor.putString(KEY_ACCOUNTS, gson.toJson(accounts));
        editor.apply();
    }

    // Clear all accounts
    public void clearAllAccounts() {
        editor.remove(KEY_ACCOUNTS);
        editor.apply();
    }
}

