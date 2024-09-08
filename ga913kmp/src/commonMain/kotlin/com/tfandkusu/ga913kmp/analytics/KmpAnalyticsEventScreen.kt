package com.tfandkusu.ga913kmp.analytics

/**
 * 画面遷移イベントクラス群
 */
sealed class KmpAnalyticsEventScreen(
    /**
     * イベント仕様書の説明文
     */
    val description: String,
    /**
     * イベント仕様書上の順番
     */
    val order: Int,
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
        description = "ランドマーク一覧画面",
        order = 1,
        eventName = "LandmarkList",
        isConversionEvent = false,
    )

    data object LandmarkDetail :
        KmpAnalyticsEventScreen(
            description = "ランドマーク詳細画面",
            order = 2,
            eventName = "LandmarkDetail",
            isConversionEvent = false,
        )

    data object Setting : KmpAnalyticsEventScreen(
        description = "設定画面",
        order = 3,
        eventName = "Setting",
        isConversionEvent = true,
    )

    data object Info : KmpAnalyticsEventScreen(
        description = "情報画面",
        order = 4,
        eventName = "Info",
        isConversionEvent = false,
    )
}
