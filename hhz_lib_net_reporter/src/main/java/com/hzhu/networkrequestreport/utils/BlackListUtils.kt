package com.hzhu.networkrequestreport.utils

import java.util.*

object BlackListUtils {
    private val urlBlackList = ArrayList<String>()

    init {
        urlBlackList.add("resshow")
    }

    fun contain(url: String): Boolean {
        for (keywords in urlBlackList) {
            if (url.toLowerCase(Locale.ROOT).contains(keywords)) {
                return true
            }

        }
        return false
    }

    fun add(keywords: String) {
        urlBlackList.add(keywords)
    }

    fun remove(keywords: String) {
        urlBlackList.remove(keywords)
    }

}