package vtys.group.serverhealth.service.impl

import vtys.group.serverhealth.adapter.DailyReportDataModel
import vtys.group.serverhealth.adapter.MonthlyReportDataModel
import vtys.group.serverhealth.model.InterruptDataModel
import vtys.group.serverhealth.service.InterruptDataAPI

class InterruptDataAPIService {

    private val retrofitService: RetrofitService = RetrofitService()
    val retrofit = retrofitService.getRetrofit()

    private val interruptDataAPI = retrofit.create(InterruptDataAPI::class.java)

    fun interruptDataYear(serverId: Int): retrofit2.Call<ArrayList<InterruptDataModel>> {
        return interruptDataAPI.interruptDataYear(serverId)

    }

    fun interruptDataMonth(serverId: Int): retrofit2.Call<ArrayList<InterruptDataModel>> {
        return interruptDataAPI.interruptDataMonth(serverId)

    }

    fun interruptDataWeek(serverId: Int): retrofit2.Call<ArrayList<InterruptDataModel>> {
        return interruptDataAPI.interruptDataWeek(serverId)
    }

    fun interruptDataMonthlyReport(serverId: Int): retrofit2.Call<ArrayList<MonthlyReportDataModel>> {
        return interruptDataAPI.interruptDataMonthlyReport(serverId)
    }

    fun interruptDataDailyReport(serverId: Int): retrofit2.Call<ArrayList<DailyReportDataModel>> {
        return interruptDataAPI.interruptDataDailyReport(serverId)
    }

}