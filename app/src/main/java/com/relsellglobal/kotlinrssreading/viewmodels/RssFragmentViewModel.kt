/*
 * Copyright (c) 2020. Relsell Global
 */

package com.relsellglobal.kotlinrssreading.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.relsellglobal.kotlinrssreading.RssItem
import com.relsellglobal.kotlinrssreading.repository.RssRepository

class RssFragmentViewModel : ViewModel() {
    private lateinit var rssItems : MutableLiveData<List<RssItem>>


    fun fetchRssItems():LiveData<List<RssItem>> {
        if(!::rssItems.isInitialized) {
            rssItems = MutableLiveData()
            rssItems = loadRssItems()
        }
        return rssItems
    }
    private fun loadRssItems() : MutableLiveData<List<RssItem>>{
        rssItems = RssRepository.instance.fetchRSSData()
        return rssItems
    }

}