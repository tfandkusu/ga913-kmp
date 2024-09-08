package com.tfandkusu.ga913kmp.analytics

import java.io.File
import kotlin.reflect.KClass

object MarkdownGenerator {
    fun generate() {
        generateScreensMarkdown(
            createScreenInstances(),
        )
        generateActionsMarkdown(
            createScreensInActions(),
        )
    }

    private fun generateScreensMarkdown(screens: List<KmpAnalyticsEventScreen>) {
        val builder = StringBuilder()
        builder.append("# 画面遷移イベント一覧\n\n")
        builder.append("| 画面名 | Analytics イベント名 | コンバージョンイベント |\n")
        builder.append("| -- | -- | -- |\n")
        for (screen in screens) {
            builder.append("| ${screen.description} | ${screen.eventName} | ${screen.isConversionEvent.toCircle()} |\n")
        }
        File("screens.md").writeText(builder.toString())
    }

    @Suppress("UNCHECKED_CAST")
    private fun generateActionsMarkdown(screensInActions: List<KmpAnalyticsEventAction.Screen>) {
        val builder = StringBuilder()
        builder.append("# 画面内操作イベント一覧\n\n")
        for (screen in screensInActions) {
            builder.append("## ${screen.description}\n\n")
            val actions =
                screen.javaClass.kotlin.nestedClasses.map {
                    if (it.objectInstance != null) {
                        it.objectInstance as KmpAnalyticsEventAction
                    } else {
                        createInstance(it as KClass<KmpAnalyticsEventAction>)
                    }
                }
            builder.append(generateActionsMarkdown(actions))
            builder.append("\n")
        }
        File("actions.md").writeText(builder.toString())
    }

    private fun generateActionsMarkdown(actions: List<KmpAnalyticsEventAction>): String {
        val builder = StringBuilder()
        builder.append("| 操作内容 | Analytics イベント名 | パラメータ | コンバージョンイベント |\n")
        builder.append("| -- | -- | -- | -- |\n")
        for (action in actions) {
            builder.append(
                "| ${action.description} | ${action.eventName} | ${
                    generateParameterListString(
                        action.eventParameterDescriptions,
                        action.eventParameters,
                    )
                } | ${action.isConversionEvent.toCircle()} |\n",
            )
        }
        return builder.toString()
    }

    private fun generateParameterListString(
        eventParameterDescriptions: Map<String, String>,
        eventParameters: Map<String, Any>,
    ): String {
        val builder = StringBuilder()
        for (key in eventParameters.keys.sorted()) {
            val description = eventParameterDescriptions[key]
            val typeName = getTypeName(eventParameters[key]?.javaClass?.kotlin)
            builder.append("$description $key: $typeName<br>")
        }
        return builder.toString()
    }

    private fun Boolean.toCircle(): String =
        if (this) {
            "○"
        } else {
            ""
        }

    private fun getTypeName(type: KClass<*>?): String {
        return when (type) {
            String::class -> "string"
            Int::class -> "int"
            Long::class -> "long"
            Float::class -> "float"
            Double::class -> "double"
            Boolean::class -> "boolean"
            else -> throw RuntimeException("未対応の型です。")
        }
    }
}
