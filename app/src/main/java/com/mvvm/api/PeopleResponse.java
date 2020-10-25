package com.mvvm.api;

import com.mvvm.model.People;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PeopleResponse {

    @SerializedName("results")
    private List<People> peopleList;

    public List<People> getPeopleList() {
        return peopleList;
    }
}
