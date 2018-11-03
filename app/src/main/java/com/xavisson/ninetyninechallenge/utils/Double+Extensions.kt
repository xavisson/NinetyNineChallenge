package com.xavisson.ninetyninechallenge.utils

import java.text.NumberFormat
import java.util.*

fun Double.formatPrice(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    return format.format(this)
}