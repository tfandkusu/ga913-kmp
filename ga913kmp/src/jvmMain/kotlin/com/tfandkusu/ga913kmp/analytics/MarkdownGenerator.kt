package com.tfandkusu.ga913kmp.analytics

import java.io.File

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

    private fun generateActionsMarkdown(screensInActions: List<KmpAnalyticsEventAction.Screen>) {
        val builder = StringBuilder()
        builder.append("# 画面内操作イベント一覧\n\n")
        for (screen in screensInActions) {
            builder.append("## ${screen.description}\n\n")
            // builder.append(generateActionsMarkdown(screen))
            builder.append("\n")
        }
        File("actions.md").writeText(builder.toString())
    }

    private fun Boolean.toCircle(): String =
        if (this) {
            "○"
        } else {
            ""
        }
}
