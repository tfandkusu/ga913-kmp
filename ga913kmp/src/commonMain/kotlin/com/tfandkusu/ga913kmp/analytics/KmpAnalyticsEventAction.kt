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
    /**
     * ランドマーク一覧画面
     */
    object LandmarkList {
        data class FavoritesOnlySwitch(
            /**
             * スイッチの ON/OFF
             */
            val favoritesOnly: Boolean,
        ) : KmpAnalyticsEventAction(
                description = "いいねを付けたランドマークのみを表示するスイッチ",
                eventName = "LandmarkListFavoritesOnlySwitch",
                eventParameters =
                    mapOf(
                        "favorites_only" to favoritesOnly,
                    ),
                isConversionEvent = false,
            )
    }

    /**
     * ランドマーク詳細画面
     */
    object LandmarkDetail {
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
                eventName = "LandmarkDetailFavoriteOn",
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

    /**
     * 設定画面
     */
    object Setting

    /**
     * 情報画面
     */
    object Info {
        data object PrivacyPolicy : KmpAnalyticsEventAction(
            description = "プライバシーポリシーを表示する",
            eventName = "InfoPrivacyPolicy",
            eventParameters = emptyMap(),
            isConversionEvent = false,
        )
    }
}
