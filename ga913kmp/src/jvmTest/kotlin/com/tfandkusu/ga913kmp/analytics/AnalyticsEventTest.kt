package com.tfandkusu.ga913kmp.analytics

import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.full.primaryConstructor
import kotlin.test.Test
import kotlin.test.assertTrue

class AnalyticsEventTest {
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

    private fun createEvents(): List<Event> {
        return createScreenInstances().map { screen ->
            Event(
                eventName = screen.eventName,
                isConversionEvent = screen.isConversionEvent,
            )
        } +
            createActionInstances().map { action ->
                Event(
                    eventName = action.eventName,
                    isConversionEvent = action.isConversionEvent,
                )
            }
    }

    private fun createScreenInstances(): List<KmpAnalyticsEventScreen> {
        return KmpAnalyticsEventScreen::class.sealedSubclasses.map {
            it.objectInstance
                ?: throw RuntimeException("画面遷移イベントクラス作成に失敗しました。")
        }
    }

    private fun createActionInstances(): List<KmpAnalyticsEventAction> {
        return KmpAnalyticsEventAction::class.sealedSubclasses.map {
            it.objectInstance ?: createInstance(it)
                ?: throw RuntimeException("画面内操作イベントクラス作成に失敗しました。")
        }
    }

    private fun createInstance(klass: KClass<out KmpAnalyticsEventAction>): KmpAnalyticsEventAction? {
        val constructor = klass.primaryConstructor
        return if (constructor != null) {
            val parameters =
                constructor.parameters.map { param ->
                    defaultParameterValue(param.type.classifier)
                }
            try {
                constructor.call(*parameters.toTypedArray())
            } catch (e: Exception) {
                throw RuntimeException("Analytics イベントクラス作成に失敗しました。")
            }
        } else {
            throw RuntimeException("Analytics イベントクラス作成に失敗しました。")
        }
    }

    private fun defaultParameterValue(type: KClassifier?): Any? {
        val kClass = type as KClass<*>
        return when (kClass) {
            String::class -> ""
            Int::class -> 0
            Long::class -> 0L
            Float::class -> 0.0f
            Double::class -> 0.0
            Boolean::class -> false
            else -> null
        }
    }
}
