package com.tfandkusu.ga913kmp.analytics

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AnalyticsEventTest {
    @Test
    fun checkEventNameLength() {
        val events = createEvents()
        events.forEach {
            assertTrue(it.eventName.length <= 40, "${it.eventName} は40文字を超えています。")
        }
    }

    @Test
    fun checkEventParameterKeyLength() {
        val actions = createActionInstances()
        actions.flatMap { action ->
            action.eventParameters.keys
        }.forEach { key ->
            assertTrue(key.length <= 40, "イベントパラメータ $key は40文字を超えています。")
        }
    }

    @Test
    fun checkEventCount() {
        val events = createEvents()
        val eventCount = events.count { it.isConversionEvent }
        if (eventCount > 500) {
            throw IllegalArgumentException("イベント種類数が500を超えています。")
        }
    }

    @Test
    fun checkDuplicateEventName() {
        val events = createEvents()
        val eventNames = mutableSetOf<String>()
        events.forEach {
            if (eventNames.contains(it.eventName)) {
                throw IllegalArgumentException("イベント名 ${it.eventName} が重複しています。")
            }
            eventNames.add(it.eventName)
        }
    }

    @Test
    fun checkEventParameterDescriptions() {
        val actions = createActionInstances()
        actions.forEach { action ->
            assertEquals(
                action.eventParameterDescriptions.keys,
                action.eventParameters.keys,
                "イベントパラメータと説明文が一致しません",
            )
        }
    }
}
