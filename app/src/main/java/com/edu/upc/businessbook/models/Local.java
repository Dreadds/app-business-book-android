package com.edu.upc.businessbook.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Local {
    private String localId;
    private String name;
    private String direction;
    private String state;
    private int thumbnail;

    public Local() {
    }

    public Local(String name, String direction, String state ,int thumbnail) {
        this.name = name;
        this.direction = direction;
        this.state = state;
        this.thumbnail = thumbnail;
    }

    public Local(String name, String direction, String state) {
        this.name = name;
        this.direction = direction;
        this.state = state;
    }


    public String getLocalId() {
        return localId;
    }

    public Local setLocalId(String localId) {
        this.localId = localId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Local setName(String name) {
        this.name = name;
        return this;
    }

    public String getDirection() {
        return direction;
    }

    public Local setDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public Local setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public String getState() {
        return state;
    }

    public Local setState(String state) {
        this.state = state;
        return this;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("localId", getLocalId());
        bundle.putString("name", getName());
        bundle.putString("direction", getDirection());
        bundle.putString("state", getState());
        return bundle;
    }



    public static  class Builder {
        private Local local;
        private List<Local> locals;

        public Builder() {
            this.local = new Local();
            this.locals = new ArrayList<>();
        }

        public Builder(Local local) {
            this.local = local;
        }

        public Builder(List<Local> locals) {
            this.locals = locals;
        }

        public Local build() {
            return local;
        }

        public List<Local> buildAll() {
            return locals;
        }

        public static Builder from(Bundle bundle) {
            return new Builder(new Local(
                    bundle.getString("name"),
                    bundle.getString("direction"),
                    bundle.getString("state")
                    //bundle.getInt("thumbnail")

            ));
        }

        public static Builder from(JSONObject jsonLocal) {
            try {
                return new Builder(new Local(
                        jsonLocal.getString("name"),
                        jsonLocal.getString("direction"),
                        jsonLocal.getString("state")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static Builder from(JSONArray jsonLocals){
            List<Local> locals = new ArrayList<>();
            int length = jsonLocals.length();
            for (int i = 0; i < length; i++){
                try {
                    locals.add(Builder.from(jsonLocals.getJSONObject(i)).build());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(locals);
        }
    }
}
