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
        val events = makeEvents()
        events.forEach {
            assertTrue(it.eventName.length <= 40, "${it.eventName} は40文字を超えています。")
        }
    }

    private fun makeEvents(): List<Event> {
        return KmpAnalyticsEventScreen::class.sealedSubclasses.map {
            val screen =
                it.objectInstance
                    ?: throw RuntimeException("画面遷移イベントクラス作成に失敗しました。")
            Event(
                eventName = screen.eventName,
                isConversionEvent = screen.isConversionEvent,
            )
        } +
            KmpAnalyticsEventAction::class.sealedSubclasses.map {
                val action =
                    it.objectInstance ?: createInstance(it)
                        ?: throw RuntimeException("画面内操作イベントクラス作成に失敗しました。")
                Event(
                    eventName = action.eventName,
                    isConversionEvent = action.isConversionEvent,
                )
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