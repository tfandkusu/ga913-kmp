package com.tfandkusu.ga913kmp.analytics

import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.full.primaryConstructor

fun createEvents(): List<Event> {
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

fun createScreenInstances(): List<KmpAnalyticsEventScreen> {
    return KmpAnalyticsEventScreen::class.sealedSubclasses.map {
        it.objectInstance
            ?: throw RuntimeException("画面遷移イベントクラス作成に失敗しました。")
    }.sortedBy { it.order }
}

fun createActionInstances(): List<KmpAnalyticsEventAction> {
    return KmpAnalyticsEventAction::class.sealedSubclasses.map {
        it.objectInstance ?: createInstance(it)
    }
}

fun createScreensInActions(): List<KmpAnalyticsEventAction.Screen> {
    return KmpAnalyticsEventAction.Screen::class.sealedSubclasses.map {
        it.objectInstance
            ?: throw RuntimeException(
                "KmpAnalyticsEventAction.Screen を継承したクラスが object で実装されていません。",
            )
    }
}

fun createInstance(klass: KClass<out KmpAnalyticsEventAction>): KmpAnalyticsEventAction {
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
