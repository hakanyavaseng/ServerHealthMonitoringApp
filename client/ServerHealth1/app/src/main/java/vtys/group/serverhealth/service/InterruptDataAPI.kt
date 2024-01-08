package vtys.group.serverhealth.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import vtys.group.serverhealth.adapter.DailyReportDataModel
import vtys.group.serverhealth.adapter.MonthlyReportDataModel
import vtys.group.serverhealth.model.InterruptDataModel

interface InterruptDataAPI {

    @GET("/api/interrupts/getoneyear/{serverid}")
    fun interruptDataYear(@Path("serverid") serverId: Int): Call<ArrayList<InterruptDataModel>>

    @GET("/api/interrupts/getonemonth/{serverid}")
    fun interruptDataMonth(@Path("serverid") serverId: Int): Call<ArrayList<InterruptDataModel>>

    @GET("/api/interrupts/getoneweek/{serverid}")
    fun interruptDataWeek(@Path("serverid") serverId: Int): Call<ArrayList<InterruptDataModel>>

    @GET("/api/interrupts/getmonthlyreport/{serverid}")
    fun interruptDataMonthlyReport(@Path("serverid") serverId: Int): Call<ArrayList<MonthlyReportDataModel>>

    @GET("/api/interrupts/getdailyreport/{serverid}")
    fun interruptDataDailyReport(@Path("serverid") serverId: Int): Call<ArrayList<DailyReportDataModel>>


}