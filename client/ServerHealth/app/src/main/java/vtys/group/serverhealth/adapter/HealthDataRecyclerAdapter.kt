package vtys.group.serverhealth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vtys.group.serverhealth.R
import vtys.group.serverhealth.model.HealthDataModel

class HealthDataRecyclerAdapter(private var healthDataList: List<HealthDataModel>) :
    RecyclerView.Adapter<HealthDataRecyclerAdapter.ServerDetailViewHolder>() {

    class ServerDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dataDateTimeTextView: TextView = itemView.findViewById(R.id.dataDateTimeTextView)
        val heartbeatStatusTextView: TextView = itemView.findViewById(R.id.heartbeatStatusTextView)
        val cpuUsageTextView: TextView = itemView.findViewById(R.id.cpuUsageTextView)
        val temperatureStatusTextView: TextView =
            itemView.findViewById(R.id.temperatureStatusTextView)
        val ambientTemperatureStatusTextView: TextView =
            itemView.findViewById(R.id.ambientTemperatureStatusTextView)
        val ramUsageTextView: TextView = itemView.findViewById(R.id.ramUsageTextView)
        val storageUsageTextView: TextView = itemView.findViewById(R.id.StorageUsageTextView)
        val energyUsageTextView: TextView = itemView.findViewById(R.id.energyUsageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_server_detail, parent, false)
        return ServerDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServerDetailViewHolder, position: Int) {

        val healthData = healthDataList[position]
        holder.dataDateTimeTextView.text = healthData.dataDateTime.toString()
        holder.heartbeatStatusTextView.text = healthData.dataHeartBeat.toString()
        holder.cpuUsageTextView.text = healthData.dataCpuUsage.toString()
        holder.temperatureStatusTextView.text = healthData.dataServerTemp.toString()
        holder.ambientTemperatureStatusTextView.text = healthData.dataAmbientTemp.toString()
        holder.ramUsageTextView.text = healthData.dataRamUsage.toString()
        holder.storageUsageTextView.text = healthData.dataStorageUsage.toString()
        holder.energyUsageTextView.text = healthData.dataEnergyUsage.toString()
    }

    override fun getItemCount(): Int {
        return healthDataList.size
    }

    // Use this method to update the dataset
    fun setData(newHealthDataList: ArrayList<HealthDataModel>) {
        healthDataList = newHealthDataList
        notifyDataSetChanged()
    }

    fun getHealthdataList(): List<HealthDataModel> {
        return healthDataList
    }

    fun clearData() {
        healthDataList = emptyList()
        notifyDataSetChanged()
    }
}