package com.tfandkusu.ga913kmp.analytics

/**
 * 画面内操作イベントクラス群
 */
sealed class KmpAnalyticsEventAction(
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
        val description: String,
        val screenName: String,
    )

    data object LandmarkList : Screen(
        description = "ランドマーク一覧画面",
        screenName = "LandmarkList",
    ) {
        data class FavoritesOnlySwitch(
            val favoritesOnly: Boolean,
        ) : KmpAnalyticsEventAction(
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
        Screen(description = "ランドマーク詳細画面", screenName = "LandmarkDetail") {
        data class FavoriteOn(
            val id: Long,
            val name: String,
        ) : KmpAnalyticsEventAction(
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

    data object Info : Screen(description = "情報画面", screenName = "Info") {
        data object PrivacyPolicy : KmpAnalyticsEventAction(
            description = "プライバシーポリシーを表示する",
            eventName = screenName + "PrivacyPolicy",
            eventParameterDescriptions = emptyMap(),
            eventParameters = emptyMap(),
            isConversionEvent = false,
        )
    }
}
