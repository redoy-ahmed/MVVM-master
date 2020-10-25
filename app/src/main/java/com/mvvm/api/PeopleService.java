package com.mvvm.api;

import retrofit2.http.GET;
import retrofit2.http.Url;
import io.reactivex.Observable;

public interface PeopleService {

    @GET
    Observable<PeopleResponse> fetchPeople(@Url String url);
}
