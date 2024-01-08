package vtys.group.serverhealth.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vtys.group.serverhealth.adapter.DailyReportDataModel
import vtys.group.serverhealth.adapter.MonthlyReportDataModel
import vtys.group.serverhealth.data.InterruptDataModel
import vtys.group.serverhealth.model.HealthDataModel
import vtys.group.serverhealth.service.HealthDataAPIService
import vtys.group.serverhealth.service.InterruptDataAPIService

private val Any.isSuccessful: Boolean
    get() {
        return true
    }

class ServerDetailViewModel : ViewModel() {

    val healthData = MutableLiveData<ArrayList<HealthDataModel>>()
    val healthDataError = MutableLiveData<Boolean>()
    val healthDataLoading = MutableLiveData<Boolean>()

    val interruptData = MutableLiveData<ArrayList<InterruptDataModel>>()
    val interruptDataError = MutableLiveData<Boolean>()
    val interruptDataLoading = MutableLiveData<Boolean>()

    val monthlyReportData = MutableLiveData<ArrayList<MonthlyReportDataModel>>()
    val monthlyReportError = MutableLiveData<Boolean>()
    val monthlyReportLoading = MutableLiveData<Boolean>()

    val dailyReportData = MutableLiveData<ArrayList<DailyReportDataModel>>()
    val dailyReportError = MutableLiveData<Boolean>()
    val dailyReportLoading = MutableLiveData<Boolean>()


    private val healthDataAPIService = HealthDataAPIService()
    private val interruptDataAPIService = InterruptDataAPIService()


    fun refreshDataMonth(serverId: Int) {
        healthDataLoading.value = true
        healthDataAPIService.healthDataMonth(serverId)
            .enqueue(object : Callback<ArrayList<HealthDataModel>> {
                override fun onResponse(
                    call: Call<ArrayList<HealthDataModel>>,
                    response: Response<ArrayList<HealthDataModel>>
                ) {
                    if (response.isSuccessful) {
                        healthData.value = response.body()
                        healthDataError.value = false
                        healthDataLoading.value = false
                    } else {
                        healthDataError.value = true
                        healthDataLoading.value = false
                    }
                }

                override fun onFailure(call: Call<ArrayList<HealthDataModel>>, t: Throwable) {
                    Log.e("ServerDetailViewModel", t.message.toString())
                    healthDataError.value = true
                    healthDataLoading.value = false
                }
            })
    }

    fun refreshDataWeek(serverId: Int) {
        healthDataLoading.value = true
        healthDataAPIService.healthDataWeek(serverId)
            .enqueue(object : Callback<ArrayList<HealthDataModel>> {
                override fun onResponse(
                    call: Call<ArrayList<HealthDataModel>>,
                    response: Response<ArrayList<HealthDataModel>>
                ) {
                    if (response.isSuccessful) {
                        healthData.value = response.body()
                        healthDataError.value = false
                        healthDataLoading.value = false
                    } else {
                        healthDataError.value = true
                        healthDataLoading.value = false
                    }
                }

                override fun onFailure(call: Call<ArrayList<HealthDataModel>>, t: Throwable) {
                    Log.e("ServerDetailViewModel", t.message.toString())
                    healthDataError.value = true
                    healthDataLoading.value = false
                }
            })
    }

    fun refreshDataYear(serverId: Int) {
        healthDataLoading.value = true
        healthDataAPIService.healthDataYear(serverId)
            .enqueue(object : Callback<ArrayList<HealthDataModel>> {
                override fun onResponse(
                    call: Call<ArrayList<HealthDataModel>>,
                    response: Response<ArrayList<HealthDataModel>>
                ) {
                    if (response.isSuccessful) {
                        healthData.value = response.body()
                        healthDataError.value = false
                        healthDataLoading.value = false
                    } else {
                        healthDataError.value = true
                        healthDataLoading.value = false
                    }
                }

                override fun onFailure(call: Call<ArrayList<HealthDataModel>>, t: Throwable) {
                    Log.e("ServerDetailViewModel", t.message.toString())
                    healthDataError.value = true
                    healthDataLoading.value = false
                }
            })
    }

    fun refreshInterruptMonth(serverId: Int) {
        interruptDataLoading.value = true
        interruptDataAPIService.interruptDataMonth(serverId)
            .enqueue(object : Callback<ArrayList<InterruptDataModel>> {
                override fun onResponse(
                    call: Call<ArrayList<InterruptDataModel>>,
                    response: Response<ArrayList<InterruptDataModel>>
                ) {
                    if (response.isSuccessful) {
                        interruptData.value = response.body()
                        interruptDataError.value = false
                        interruptDataLoading.value = false
                    } else {
                        interruptDataError.value = true
                        interruptDataLoading.value = false
                    }
                }

                override fun onFailure(call: Call<ArrayList<InterruptDataModel>>, t: Throwable) {
                    Log.e("ServerDetailViewModel", t.message.toString())
                    interruptDataError.value = true
                    interruptDataLoading.value = false
                }
            })
    }

    fun refreshInterruptWeek(serverId: Int) {
        interruptDataLoading.value = true
        interruptDataAPIService.interruptDataWeek(serverId)
            .enqueue(object : Callback<ArrayList<InterruptDataModel>> {
                override fun onResponse(
                    call: Call<ArrayList<InterruptDataModel>>,
                    response: Response<ArrayList<InterruptDataModel>>
                ) {
                    if (response.isSuccessful) {
                        interruptData.value = response.body()
                        interruptDataError.value = false
                        interruptDataLoading.value = false
                    } else {
                        interruptDataError.value = true
                        interruptDataLoading.value = false
                    }
                }

                override fun onFailure(call: Call<ArrayList<InterruptDataModel>>, t: Throwable) {
                    Log.e("ServerDetailViewModel", t.message.toString())
                    interruptDataError.value = true
                    interruptDataLoading.value = false
                }
            })
    }

    fun refreshInterruptYear(serverId: Int) {
        interruptDataLoading.value = true
        interruptDataAPIService.interruptDataYear(serverId)
            .enqueue(object : Callback<ArrayList<InterruptDataModel>> {
                override fun onResponse(
                    call: Call<ArrayList<InterruptDataModel>>,
                    response: Response<ArrayList<InterruptDataModel>>
                ) {
                    if (response.isSuccessful) {
                        interruptData.value = response.body()
                        interruptDataError.value = false
                        interruptDataLoading.value = false
                    } else {
                        interruptDataError.value = true
                        interruptDataLoading.value = false
                    }
                }

                override fun onFailure(call: Call<ArrayList<InterruptDataModel>>, t: Throwable) {
                    Log.e("ServerDetailViewModel", t.message.toString())
                    interruptDataError.value = true
                    interruptDataLoading.value = false
                }
            })
    }

    fun refreshMonthlyReport(serverId: Int) {
        monthlyReportLoading.value = true
        interruptDataAPIService.interruptDataMonthlyReport(serverId)
            .enqueue(object : Callback<ArrayList<MonthlyReportDataModel>> {
                override fun onResponse(
                    call: Call<ArrayList<MonthlyReportDataModel>>,
                    response: Response<ArrayList<MonthlyReportDataModel>>
                ) {
                    if (response.isSuccessful) {
                        monthlyReportData.value = response.body()
                        monthlyReportError.value = false
                        monthlyReportLoading.value = false
                    } else {
                        monthlyReportError.value = true
                        monthlyReportLoading.value = false
                    }
                }

                override fun onFailure(
                    call: Call<ArrayList<MonthlyReportDataModel>>,
                    t: Throwable
                ) {
                    Log.e("ServerDetailViewModel", t.message.toString())
                    monthlyReportError.value = true
                    monthlyReportLoading.value = false
                }
            })
    }

    fun refreshDailyReport(serverId: Int) {
        dailyReportLoading.value = true
        interruptDataAPIService.interruptDataDailyReport(serverId)
            .enqueue(object : Callback<ArrayList<DailyReportDataModel>> {
                override fun onResponse(
                    call: Call<ArrayList<DailyReportDataModel>>,
                    response: Response<ArrayList<DailyReportDataModel>>
                ) {
                    if (response.isSuccessful) {
                        dailyReportData.value = response.body()
                        dailyReportError.value = false
                        dailyReportLoading.value = false
                    } else {
                        dailyReportError.value = true
                        dailyReportLoading.value = false
                    }
                }

                override fun onFailure(call: Call<ArrayList<DailyReportDataModel>>, t: Throwable) {
                    Log.e("ServerDetailViewModel", t.message.toString())
                    dailyReportError.value = true
                    dailyReportLoading.value = false
                }
            })
    }


}

