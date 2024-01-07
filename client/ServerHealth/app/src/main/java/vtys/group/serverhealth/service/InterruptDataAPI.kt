package vtys.group.serverhealth.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import vtys.group.serverhealth.data.InterruptDataModel
import vtys.group.serverhealth.model.HealthDataModel

interface InterruptDataAPI {

    @GET("/api/interrupts/getoneyear/{serverid}")
    fun healthDataYear(@Path("serverid") serverId: Int): Call<ArrayList<InterruptDataModel>>

    @GET("/api/interrupts/getonemonth/{serverid}")
    fun healthDataMonth(@Path("serverid") serverId: Int): Call<ArrayList<InterruptDataModel>>

    @GET("/api/interrupts/getoneweek/{serverid}")
    fun healthDataWeek(@Path("serverid") serverId: Int): Call<ArrayList<InterruptDataModel>>



}