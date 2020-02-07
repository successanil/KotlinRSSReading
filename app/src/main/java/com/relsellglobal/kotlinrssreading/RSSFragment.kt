/*
 * Copyright (c) 2020. Relsell Global
 */

package com.relsellglobal.kotlinrssreading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.relsellglobal.kotlinrssreading.viewmodels.RssFragmentViewModel


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [RSSFragment.OnListFragmentInteractionListener] interface.
 */
class RSSFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    val RSS_FEED_LINK = "https://www.relsellglobal.in/feed/";

    var adapter: MyItemRecyclerViewAdapter? = null
    var rssItems = ArrayList<RssItem>()

    var listV : RecyclerView ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        listV = view.findViewById(R.id.listV)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = MyItemRecyclerViewAdapter(rssItems, listener,activity)
        listV?.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        listV?.adapter = adapter

        val model = ViewModelProviders.of(this).get(RssFragmentViewModel::class.java)

        model.fetchRssItems().observe(this, Observer {
            if (it != null && !it.isEmpty()) {
                rssItems.addAll(it)
                adapter?.notifyDataSetChanged()
            }
        })


    }




    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: RssItem?)
    }

}
