package com.imallan.gankmvp.api

import com.imallan.playground.APIResponse
import com.imallan.playground.Post
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface GankService {

    @GET("data/{type}/{limit}/{page}")
    fun getPost(@Path("type") type: String,
                @Path("limit") limit: Int = 20,
                @Path("page") page: Int = 1): Observable<APIResponse<Post>>

}
