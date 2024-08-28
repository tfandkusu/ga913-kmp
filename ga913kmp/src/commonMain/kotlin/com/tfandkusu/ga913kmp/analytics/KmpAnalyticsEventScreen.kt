package com.tfandkusu.ga913kmp.analytics

/**
 * 画面遷移イベントクラス群
 */
sealed class KmpAnalyticsEventScreen(
    /**
     * Analytics イベント名
     */
    val eventName: String,
    /**
     * コンバージョンイベントフラグ
     */
    val isConversionEvent: Boolean,
) {
    /**
     * ランドマーク一覧画面
     */
    data object LandmarkList : KmpAnalyticsEventScreen("LandmarkList", false)

    /**
     * ランドマーク詳細画面
     */
    data object LandmarkDetail : KmpAnalyticsEventScreen("LandmarkDetail", false)

    /**
     * 設定画面
     */
    data object Setting : KmpAnalyticsEventScreen("Setting", true)

    /**
     * 情報画面
     */
    data object Info : KmpAnalyticsEventScreen("Info", false)
}
