package com.hzhu.networkrequestreport.utils

import java.util.*

object BlackListUtils {
    private val urlBlackList = arrayOf<String>("resshow")

    fun contain(url: String): Boolean {
        for (keywords in urlBlackList) {
            if (url.toLowerCase(Locale.ROOT).contains(keywords)){
                return true
            }

        }
        return false
    }

}