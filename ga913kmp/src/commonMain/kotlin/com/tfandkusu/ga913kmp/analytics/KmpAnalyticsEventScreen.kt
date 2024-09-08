package com.tfandkusu.ga913kmp.analytics

/**
 * 画面遷移イベントクラス群
 */
sealed class KmpAnalyticsEventScreen(
    /**
     * イベント仕様書上の順番
     */
    val order: Int,
    /**
     * イベント仕様書の説明文
     */
    val description: String,
    /**
     * Analytics イベント名
     */
    val eventName: String,
    /**
     * コンバージョンイベントフラグ
     */
    val isConversionEvent: Boolean,
) {
    data object LandmarkList : KmpAnalyticsEventScreen(
        order = 1,
        description = "ランドマーク一覧画面",
        eventName = "LandmarkList",
        isConversionEvent = false,
    )

    data object LandmarkDetail :
        KmpAnalyticsEventScreen(
            order = 2,
            description = "ランドマーク詳細画面",
            eventName = "LandmarkDetail",
            isConversionEvent = false,
        )

    data object Setting : KmpAnalyticsEventScreen(
        order = 3,
        description = "設定画面",
        eventName = "Setting",
        isConversionEvent = true,
    )

    data object Info : KmpAnalyticsEventScreen(
        order = 4,
        description = "情報画面",
        eventName = "Info",
        isConversionEvent = false,
    )
}
