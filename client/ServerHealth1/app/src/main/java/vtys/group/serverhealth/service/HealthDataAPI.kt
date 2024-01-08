package vtys.group.serverhealth.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import vtys.group.serverhealth.model.HealthDataModel

interface HealthDataAPI {

    @GET("/api/healthdata/getoneyear/{serverid}")
    fun healthDataYear(@Path("serverid") serverId: Int): Call<ArrayList<HealthDataModel>>

    @GET("/api/healthdata/getonemonth/{serverid}")
    fun healthDataMonth(@Path("serverid") serverId: Int): Call<ArrayList<HealthDataModel>>

    @GET("/api/healthdata/getoneweek/{serverid}")
    fun healthDataWeek(@Path("serverid") serverId: Int): Call<ArrayList<HealthDataModel>>

    @GET("/api/healthdata/getbyid/{serverid}")
    fun healthDataById(@Path("serverid") serverId: Int): Call<ArrayList<HealthDataModel>>
}