package vtys.group.serverhealth.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vtys.group.serverhealth.R

class DailyReportAdapter(private var dailyReportList: List<DailyReportDataModel>) :
    RecyclerView.Adapter<DailyReportAdapter.DailyReportViewHolder>() {

    class DailyReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dayName: TextView = itemView.findViewById(R.id.dayNameTextView)
        val interruptCountDaily: TextView = itemView.findViewById(R.id.interruptCountTextViewDay)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyReportViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_daily_report, parent, false)
        return DailyReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyReportViewHolder, position: Int) {
        val report = dailyReportList[position]
        holder.dayName.text = report.day
        holder.interruptCountDaily.text = report.interruptCount.toString()
        Log.d("DailyReportAdapter", "Day: ${report.day}, Interrupt Count: ${report.interruptCount}")

    }

    override fun getItemCount(): Int {
        return dailyReportList.size
    }

    // Use this method to update the dataset
    fun setData(newDailyReportList: List<DailyReportDataModel>) {
        dailyReportList = newDailyReportList
        notifyDataSetChanged()

    }
}

data class DailyReportDataModel(
    val day: String,
    val interruptCount: Int
)