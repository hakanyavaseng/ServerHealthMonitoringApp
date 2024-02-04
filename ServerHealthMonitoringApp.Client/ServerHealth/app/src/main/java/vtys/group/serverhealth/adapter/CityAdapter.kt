package vtys.group.serverhealth.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import vtys.group.serverhealth.model.CityDataModel

class CityAdapter(
    context: Context,
    resource: Int,
    objects: List<CityDataModel>
) : ArrayAdapter<CityDataModel>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        (view as TextView).text = getItem(position)?.cityname ?: ""
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        (view as TextView).text = getItem(position)?.cityname ?: ""
        return view
    }
}