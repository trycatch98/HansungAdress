package trycatch.hs.hansungadress.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import trycatch.hs.hansungadress.R
import trycatch.hs.hansungadress.data.remote.model.AddressModel
import trycatch.hs.hansungadress.databinding.SearchItemBinding
import java.util.*

class SearchAdapter(private val inflater: LayoutInflater, private var items: Vector<AddressModel>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(i: Int): Any {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    fun setItems(items: Vector<AddressModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View {
        lateinit var binding: SearchItemBinding
        lateinit var view: View

        if (convertView == null) {
            binding = SearchItemBinding.inflate(inflater, viewGroup, false)
            view = binding.root
            view.tag = binding
        } else {
            view = convertView
            binding = convertView.tag as SearchItemBinding
        }

        binding.data = items?.get(i)

        return view
    }
}
