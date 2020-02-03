/*
 * Copyright (c) 2020. Relsell Global
 */

package com.relsellglobal.kotlinrssreading

class RssItem {
    var title = ""
    var link = ""
    var pubDate = ""
    var description = ""
    var category = ""

    override fun toString(): String {
        return "RssItem(title='$title', link='$link', pubDate='$pubDate', description='$description', category='$category')"
    }

}