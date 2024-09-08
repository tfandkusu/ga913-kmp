package com.tfandkusu.ga913kmp.analytics

/**
 * イベント情報
 *
 * @param eventName イベント名
 * @param isConversionEvent 500種類制限のあるイベント名にするフラグ
 */
class Event(
    val eventName: String,
    val isConversionEvent: Boolean,
)
