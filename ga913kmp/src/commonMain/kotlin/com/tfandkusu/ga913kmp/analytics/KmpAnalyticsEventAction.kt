package com.tfandkusu.ga913kmp.analytics

/**
 * 画面内操作イベントクラス群
 */
sealed class KmpAnalyticsEventAction(
    /**
     * 説明文
     */
    val description: String,
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
            /**
             * スイッチの ON/OFF
             */
            val favoritesOnly: Boolean,
        ) : KmpAnalyticsEventAction(
                description = "いいねを付けたランドマークのみを表示するスイッチ",
                eventName = screenName + "FavoritesOnlySwitch",
                eventParameters =
                    mapOf(
                        "favorites_only" to favoritesOnly,
                    ),
                isConversionEvent = false,
            )
    }

    data object LandmarkDetail : Screen(description = "ランドマーク詳細画面", screenName = "LandmarkDetail") {
        data class FavoriteOn(
            /**
             * ランドマークの ID
             */
            val id: Long,
            /**
             * ランドマークの名前
             */
            val name: String,
        ) : KmpAnalyticsEventAction(
                description = "いいねを付ける",
                eventName = screenName + "FavoriteOn",
                eventParameters =
                    mapOf(
                        "id" to id,
                        "name" to name,
                    ),
                isConversionEvent = true,
            )

        data class FavoriteOff(
            /**
             * ランドマークの ID
             */
            val id: Long,
            /**
             * ランドマークの名前
             */
            val name: String,
        ) : KmpAnalyticsEventAction(
                description = "いいねを解除する",
                eventName = "LandmarkDetailFavoriteOff",
                eventParameters =
                    mapOf(
                        "id" to id,
                        "name" to name,
                    ),
                isConversionEvent = false,
            )
    }

    data object Setting : Screen(description = "設定画面", screenName = "Setting")

    data object Info : Screen(description = "情報画面", screenName = "Info") {
        data object PrivacyPolicy : KmpAnalyticsEventAction(
            description = "プライバシーポリシーを表示する",
            eventName = screenName + "PrivacyPolicy",
            eventParameters = emptyMap(),
            isConversionEvent = false,
        )
    }
}
