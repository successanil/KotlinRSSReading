/*
 * Copyright (c) 2020. Relsell Global
 */

package com.relsellglobal.kotlinrssreading


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.relsellglobal.kotlinrssreading.RSSFragment.OnListFragmentInteractionListener


class MyItemRecyclerViewAdapter(
    private val mValues: List<RssItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            //val item = v.tag as DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            //mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_rss_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.titleTV?.text = item.title
        holder.pubDateTV?.text = item.pubDate
        holder.contentTV?.loadData(item.description,"text/html","UTF-8")

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val titleTV : TextView? = mView.findViewById(R.id.txtTitle)
        val pubDateTV : TextView? = mView.findViewById(R.id.txtPubdate)
        val contentTV : WebView? = mView.findViewById(R.id.webContent)


    }
}
