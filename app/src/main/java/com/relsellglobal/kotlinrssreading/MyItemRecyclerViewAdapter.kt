/*
 * Copyright (c) 2020. Relsell Global
 */

package com.relsellglobal.kotlinrssreading


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.relsellglobal.kotlinrssreading.RSSFragment.OnListFragmentInteractionListener
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException


class MyItemRecyclerViewAdapter(
    private val mValues: List<RssItem>,
    private val mListener: OnListFragmentInteractionListener?,
    private val context : FragmentActivity?
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
        var link = getFeaturedImageLink(item.description)
        if(link != null) {
            context?.let {
                GlideApp.with(it)
                    .load(link)
                    .into(holder.featuredImg)
            }
        }
        holder.contentTV?.text  =item.description

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val titleTV: TextView? = mView.findViewById(R.id.txtTitle)
        val pubDateTV: TextView? = mView.findViewById(R.id.txtPubdate)
        val contentTV: TextView? = mView.findViewById(R.id.txtContent)
        val featuredImg: ImageView = mView.findViewById(R.id.featuredImg);


    }


    private fun getFeaturedImageLink(htmlText: String): String? {
        var result: String? = null

        val stringBuilder = StringBuilder()
        try {
            val doc: Document = Jsoup.parse(htmlText)
            val imgs: Elements = doc.select("img")
            for (img in imgs) {
                var src = img.attr("src")
                result = src

            }

        } catch (e: IOException) {

        }
        return result

    }


}
