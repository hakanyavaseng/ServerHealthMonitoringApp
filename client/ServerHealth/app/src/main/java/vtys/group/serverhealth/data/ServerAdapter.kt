package vtys.group.serverhealth.data

import ServerDataModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vtys.group.serverhealth.R

class ServerAdapter(private var serverList: List<ServerDataModel>, private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ServerAdapter.ServerViewHolder>() {
    private var originalServerList: List<ServerDataModel> = serverList


    class ServerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serverNameTextView: TextView = itemView.findViewById(R.id.serverNameTextView)
        val serverIpTextView: TextView = itemView.findViewById(R.id.serverIpTextView)
        val hospitalNameTextView: TextView = itemView.findViewById(R.id.hospitalNameTextView)
        val cityTextView: TextView = itemView.findViewById(R.id.cityTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_server, parent, false)
        return ServerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        val server = serverList[position]

        // Bind data to views
        holder.serverNameTextView.text = server.serverName
        holder.serverIpTextView.text = server.serverIp
        holder.hospitalNameTextView.text = server.hospitalId.hospitalName
        holder.cityTextView.text = server.hospitalId.cityId.cityName

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(server)
        }
    }

    override fun getItemCount(): Int {
        return serverList.size
    }

    fun setData(newServerList: List<ServerDataModel>) {
        serverList = newServerList
        originalServerList = newServerList
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        serverList = if (query.isEmpty()) {
            // If the query is empty, restore the original data set
            originalServerList
        } else {
            // Filter the data based on the query
            originalServerList.filter { server ->
                server.serverName.contains(query, ignoreCase = true) ||
                        server.serverIp.contains(query, ignoreCase = true) ||
                        server.hospitalId.hospitalName.contains(query, ignoreCase = true) ||
                        server.hospitalId.cityId.cityName.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(server: ServerDataModel)
    }
}