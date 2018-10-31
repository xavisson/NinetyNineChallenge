package com.xavisson.ninetyninechallenge.presentation.companylist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xavisson.ninetyninechallenge.R
import com.xavisson.ninetyninechallenge.presentation.companylist.CompanyUI
import kotlinx.android.synthetic.main.companylist_item.view.*
import kotlin.properties.Delegates

class CompanyAdapter : RecyclerView.Adapter<CompanyAdapter.ViewHolder>(), AutoUpdatableAdapter {

    var items: List<CompanyUI> by Delegates.observable(emptyList())
    { prop, oldList, newList ->
        autoNotify(oldList, newList) { oldItem, newItem -> oldItem.id == newItem.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.companylist_item, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(company: CompanyUI) = with(itemView) {
            companyName.text = company.name
            sharePrice.text = company.sharePrice.toString()
            ric.text = company.ric
        }
    }
}