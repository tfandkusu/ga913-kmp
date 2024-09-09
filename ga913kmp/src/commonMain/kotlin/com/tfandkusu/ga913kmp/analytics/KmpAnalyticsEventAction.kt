package com.tfandkusu.ga913kmp.analytics

/**
 * 画面内操作イベントクラス群
 */
sealed class KmpAnalyticsEventAction(
    /**
     * イベント仕様書上の順番
     */
    val order: Int,
    /**
     * Analytics イベントの説明文
     */
    val description: String,
    /**
     * Analytics イベントパラメータの説明文
     */
    val eventParameterDescriptions: Map<String, String>,
    /**
     * Analytics イベント名
     */
    val eventName: String,
    /**
     * Analytics イベントパラメータ
     */
    val eventParameters: Map<String, Any>,
    /**
     * コンバージョンイベントフラグ
     */
    val isConversionEvent: Boolean,
) {
    sealed class Screen(
        /**
         * イベント仕様書上の順番
         */
        val order: Int,
        /**
         * イベント仕様書に掲載する画面名
         */
        val description: String,
        /**
         * 画面の名前をベースにした Analytics イベント名のプレフィックス
         */
        val screenName: String,
    )

    data object LandmarkList : Screen(
        order = 1,
        description = "ランドマーク一覧画面",
        screenName = "LandmarkList",
    ) {
        data class FavoritesOnlySwitch(
            val favoritesOnly: Boolean,
        ) : KmpAnalyticsEventAction(
                order = 1,
                description = "いいねを付けたランドマークのみを表示するスイッチ",
                eventParameterDescriptions =
                    mapOf(
                        "favorites_only" to "スイッチの ON/OFF",
                    ),
                eventName = screenName + "FavoritesOnlySwitch",
                eventParameters =
                    mapOf(
                        "favorites_only" to favoritesOnly,
                    ),
                isConversionEvent = false,
            )
    }

    data object LandmarkDetail :
        Screen(
            order = 2,
            description = "ランドマーク詳細画面",
            screenName = "LandmarkDetail",
        ) {
        data class FavoriteOn(
            val id: Long,
            val name: String,
        ) : KmpAnalyticsEventAction(
                order = 1,
                description = "いいねを付ける",
                eventParameterDescriptions =
                    mapOf(
                        "id" to "ランドマークの ID",
                        "name" to "ランドマークの名前",
                    ),
                eventName = screenName + "FavoriteOn",
                eventParameters =
                    mapOf(
                        "id" to id,
                        "name" to name,
                    ),
                isConversionEvent = true,
            )

        data class FavoriteOff(
            val id: Long,
            val name: String,
        ) : KmpAnalyticsEventAction(
                order = 2,
                description = "いいねを解除する",
                eventParameterDescriptions =
                    mapOf(
                        "id" to "ランドマークの ID",
                        "name" to "ランドマークの名前",
                    ),
                eventName = "LandmarkDetailFavoriteOff",
                eventParameters =
                    mapOf(
                        "id" to id,
                        "name" to name,
                    ),
                isConversionEvent = false,
            )
    }

    data object Info : Screen(
        order = 3,
        description = "情報画面",
        screenName = "Info",
    ) {
        data object PrivacyPolicy : KmpAnalyticsEventAction(
            order = 1,
            description = "プライバシーポリシーを表示する",
            eventName = screenName + "PrivacyPolicy",
            eventParameterDescriptions = emptyMap(),
            eventParameters = emptyMap(),
            isConversionEvent = false,
        )
    }
}
