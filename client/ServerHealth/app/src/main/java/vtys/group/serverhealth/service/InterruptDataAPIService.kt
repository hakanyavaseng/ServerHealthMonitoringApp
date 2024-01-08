package vtys.group.serverhealth.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vtys.group.serverhealth.adapter.DailyReportDataModel
import vtys.group.serverhealth.adapter.MonthlyReportDataModel
import vtys.group.serverhealth.data.InterruptDataModel
import vtys.group.serverhealth.model.HealthDataModel

class InterruptDataAPIService {

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://serverhealth.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val interruptDataAPI = retrofit.create(InterruptDataAPI::class.java)

    fun interruptDataYear(serverId: Int): retrofit2.Call<ArrayList<InterruptDataModel>> {
        return interruptDataAPI.interruptDataYear(serverId)

    }
    fun interruptDataMonth(serverId: Int): retrofit2.Call<ArrayList<InterruptDataModel>> {
        return  interruptDataAPI.interruptDataMonth(serverId)

    }
    fun interruptDataWeek(serverId: Int): retrofit2.Call<ArrayList<InterruptDataModel>> {
        return  interruptDataAPI.interruptDataWeek(serverId)
    }

    fun interruptDataMonthlyReport(serverId: Int): retrofit2.Call<ArrayList<MonthlyReportDataModel>> {
        return  interruptDataAPI.interruptDataMonthlyReport(serverId)
    }

    fun interruptDataDailyReport(serverId: Int): retrofit2.Call<ArrayList<DailyReportDataModel>> {
        return  interruptDataAPI.interruptDataDailyReport(serverId)
    }

}