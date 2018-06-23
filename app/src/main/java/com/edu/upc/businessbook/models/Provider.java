package com.edu.upc.businessbook.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Provider {

    private String providerId;
    private String name;
    private String state;
    private String phone;
    private String email;
    private int thumbnail;

    public Provider() {
    }

    public Provider(String providerId, String name, String state, String phone, String email) {
        this.providerId = providerId;
        this.name = name;
        this.state = state;
        this.phone = phone;
        this.email = email;
    }

    public Provider(String providerId, String name, String state, String phone, String email, int thumbnail) {
        this.providerId = providerId;
        this.name = name;
        this.state = state;
        this.phone = phone;
        this.email = email;
        this.thumbnail = thumbnail;
    }

    public String getProviderId() {
        return providerId;
    }

    public Provider setProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Provider setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public Provider setState(String state) {
        this.state = state;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Provider setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Provider setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public Provider setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("providerId", getProviderId());
        bundle.putString("name", getName());
        bundle.putString("state", getState());
        bundle.putString("phone", getPhone());
        bundle.putString("email", getEmail());
        return bundle;
    }

    public static class Builder{
        private Provider provider;
        private List<Provider> providers;

        public Builder(){
            this.provider = new Provider();
            this.providers = new ArrayList<>();
        }

        public Builder(Provider provider){
            this.provider = provider;
        }
        public Builder(List<Provider> providers){
            this.providers = providers;
        }

        public Provider build(){
            return provider;
        }
        public List<Provider> buildAll(){
            return providers;
        }
        public static Builder from(Bundle bundle){
            return new Builder(new Provider(
                    bundle.getString("providerId"),
                    bundle.getString("name"),
                    bundle.getString("state"),
                    bundle.getString("phone"),
                    bundle.getString("email")));
        }
        public static Builder from(JSONObject jsonProvider){
            try {
                return new Builder(new Provider(
                        jsonProvider.getString("providerId"),
                        jsonProvider.getString("name"),
                        jsonProvider.getString("state"),
                        jsonProvider.getString("phone"),
                        jsonProvider.getString("email")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        public static Builder from(JSONArray jsonProviders){
            int length = jsonProviders.length();
            List<Provider> providers = new ArrayList<>();
            for (int i = 0; i < length; i++){
                try {
                    providers.add(Builder.from(jsonProviders.getJSONObject(i)).build());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(providers);
        }
    }
}
