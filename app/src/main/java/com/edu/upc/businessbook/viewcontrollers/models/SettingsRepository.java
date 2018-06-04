package com.edu.upc.businessbook.viewcontrollers.models;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsRepository {
    private Context context;
    private SharedPreferences settings;

    public SettingsRepository(Context context) {
        this.context = context;
        settings = context.getSharedPreferences("BusinessBook",
                Context.MODE_PRIVATE);
    }

    public boolean isOnboardingPending() {
        return settings.getBoolean("onboarding_pending", false);
    }

    public SettingsRepository setOnboardingPending(boolean isPending) {
        settings.edit().putBoolean("onboarding_pending", isPending).apply();
        return this;
    }

    public SettingsRepository setOnboardingCompleted() {
        return setOnboardingPending(false);
    }
}
