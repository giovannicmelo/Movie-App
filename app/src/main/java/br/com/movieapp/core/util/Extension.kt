package br.com.movieapp.core.util

import br.com.movieapp.BuildConfig

fun String?.toPostUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"

fun String?.toBackdropUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"

fun String?.orEmpty() = this ?: ""

fun Double?.orZero() = this ?: 0.0

fun Float?.orZero() = this ?: 0f

fun Int?.orZero() = this ?: 0

fun Any?.isNull() = this == null