package com.edu.upc.businessbook.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientItem {
    private String clientId;
    private String name;
    private String lastName;
    private String fullName;
    private String dni;
    private String email;
    private String phone;
    private String location;
    private String dateCreation;
    private String dateUpdate;
    private String state;
    private String sex;

    public ClientItem() {
    }

    public ClientItem(String clientId, String name, String lastName, String fullName, String dni, String email, String phone, String location, String dateCreation, String dateUpdate, String state, String sex) {
        this.clientId = clientId;
        this.name = name;
        this.lastName = lastName;
        this.fullName = fullName;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.state = state;
        this.sex = sex;
    }

    public String getClientId() {
        return clientId;
    }

    public ClientItem setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClientItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ClientItem setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public ClientItem setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getDni() {
        return dni;
    }

    public ClientItem setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ClientItem setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ClientItem setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ClientItem setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public ClientItem setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public ClientItem setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
        return this;
    }

    public String getState() {
        return state;
    }

    public ClientItem setState(String state) {
        this.state = state;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public ClientItem setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("clientId", getClientId());
        bundle.putString("name", getName());
        bundle.putString("lastName", getLastName());
        bundle.putString("fullName", getFullName());
        bundle.putString("dni", getDni());
        bundle.putString("email", getEmail());
        bundle.putString("phone", getPhone());
        bundle.putString("location", getLocation());
        bundle.putString("dateCreation", getDateCreation());
        bundle.putString("dateUpdate", getDateUpdate());
        bundle.putString("state", getState());
        bundle.putString("sex", getSex());
        return bundle;
    }

    public static class Builder{
        private ClientItem clientItem;
        private List<ClientItem> clientItems;

        public Builder(){
            this.clientItem = new ClientItem();
            this.clientItems = new ArrayList<>();
        }

        public Builder(ClientItem clientItem){
            this.clientItem = clientItem;
        }

        public Builder(List<ClientItem> clientItems){
            this.clientItems = clientItems;
        }

        public ClientItem build(){
            return clientItem;
        }

        public List<ClientItem> buildAll(){
            return clientItems;
        }

        public static Builder from(Bundle bundle){
            return new Builder(new ClientItem(
                    bundle.getString("clientId"),
                    bundle.getString("name"),
                    bundle.getString("lastName"),
                    bundle.getString("fullName"),
                    bundle.getString("dni"),
                    bundle.getString("email"),
                    bundle.getString("phone"),
                    bundle.getString("location"),
                    bundle.getString("dateCreation"),
                    bundle.getString("dateUpdate"),
                    bundle.getString("state"),
                    bundle.getString("sex")));
        }

        public static Builder from(JSONObject jsonClientItem){
            try {
                return new Builder(new ClientItem(
                        jsonClientItem.getString("clientId"),
                        jsonClientItem.getString("name"),
                        jsonClientItem.getString("lastName"),
                        jsonClientItem.getString("fullName"),
                        jsonClientItem.getString("dni"),
                        jsonClientItem.getString("email"),
                        jsonClientItem.getString("phone"),
                        jsonClientItem.getString("location"),
                        jsonClientItem.getString("dateCreation"),
                        jsonClientItem.getString("dateUpdate"),
                        jsonClientItem.getString("state"),
                        jsonClientItem.getString("sex")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static Builder from(JSONArray jsonClientItems){
            int length = jsonClientItems.length();
            List<ClientItem> clientItems = new ArrayList<>();
            for (int i= 0; i < length; i++){
                try {
                    clientItems.add(Builder.from(jsonClientItems.getJSONObject(i)).build());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(clientItems);
        }

    }
}
