package vtys.group.serverhealth.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import vtys.group.serverhealth.model.HospitalDataModel

class HospitalAdapter(
    context: Context,
    resource: Int,
    private val objects: List<HospitalDataModel>
) : ArrayAdapter<HospitalDataModel>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        (view as TextView).text = getItem(position)?.hospitalname ?: ""
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        (view as TextView).text = getItem(position)?.hospitalname ?: ""
        return view
    }

    override fun getCount(): Int {
        return objects.size
    }

    override fun getItem(position: Int): HospitalDataModel? {
        return objects[position]
    }

    fun getHospitalId(position: Int): Int {
        return objects[position].hospitalid
    }
}