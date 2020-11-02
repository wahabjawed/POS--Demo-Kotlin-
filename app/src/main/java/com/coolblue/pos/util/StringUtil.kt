package com.coolblue.pos.util

class StringUtil {

    companion object {

        fun euroFormat(amount: Double): String {

            return "€ ${amount}"
        }
    }
}