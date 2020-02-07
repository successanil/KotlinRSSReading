/*
 * Copyright (c) 2020. Relsell Global
 */

package com.relsellglobal.kotlinrssreading.repository

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.relsellglobal.kotlinrssreading.RssItem
import com.relsellglobal.kotlinrssreading.RssParser
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class RssRepository private constructor(){

    private val RSS_FEED_LINK = "https://www.relsellglobal.in/feed/";

    private object HOLDER {
        val INSTANCE = RssRepository()
    }

    companion object {
        val instance: RssRepository by lazy { HOLDER.INSTANCE }
    }

    fun fetchRSSData():MutableLiveData<List<RssItem>> {
        val url = URL(RSS_FEED_LINK)
        val data = MutableLiveData<List<RssItem>>()
        RssFeedFetcher(data).execute(url)
        return data
    }

    class RssFeedFetcher(data: MutableLiveData<List<RssItem>>) : AsyncTask<URL, Void, List<RssItem>>() {
        private var stream: InputStream? = null;
        val dataObj:MutableLiveData<List<RssItem>> = data
        override fun doInBackground(vararg params: URL?): List<RssItem>? {
            val connect = params[0]?.openConnection() as HttpsURLConnection
            connect.readTimeout = 8000
            connect.connectTimeout = 8000
            connect.requestMethod = "GET"
            connect.connect();

            val responseCode: Int = connect.responseCode;
            var rssItems: List<RssItem>? = null
            if (responseCode == 200) {
                stream = connect.inputStream;


                try {
                    val parser = RssParser()
                    rssItems = parser.parse(stream!!)

                } catch (e: IOException) {
                    e.printStackTrace()
                }


            }

            return rssItems

        }

        override fun onPostExecute(result: List<RssItem>?) {
            super.onPostExecute(result)
            if (result != null && !result.isEmpty()) {
                dataObj.value = result
            }

        }

    }
}