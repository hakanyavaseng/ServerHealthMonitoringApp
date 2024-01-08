package vtys.group.serverhealth.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vtys.group.serverhealth.R

class MonthlyReportAdapter(private var monthlyReportList: List<MonthlyReportDataModel>) :
    RecyclerView.Adapter<MonthlyReportAdapter.MonthlyReportViewHolder>() {

    class MonthlyReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val monthName: TextView = itemView.findViewById(R.id.monthNameTextView)
        val interruptCountMonthly: TextView = itemView.findViewById(R.id.interruptCountTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthlyReportViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_monthly_report, parent, false)
        return MonthlyReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: MonthlyReportViewHolder, position: Int) {
        val report = monthlyReportList[position]
        holder.monthName.text = report.month
        holder.interruptCountMonthly.text = report.interruptCount.toString()

        Log.d(
            "MonthlyReportAdapter",
            "Month: ${report.month}, Interrupt Count: ${report.interruptCount}"
        )
    }

    override fun getItemCount(): Int {
        return monthlyReportList.size
    }

    // Use this method to update the dataset
    fun setData(newMonthlyReportList: List<MonthlyReportDataModel>) {
        monthlyReportList = newMonthlyReportList
        notifyDataSetChanged()
    }
}


data class MonthlyReportDataModel(
    val month: String,
    val interruptCount: Int
)
