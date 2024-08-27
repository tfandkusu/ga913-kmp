package com.tfandkusu.ga913kmp.analytics

/**
 * 画面内操作イベントクラス群
 */
sealed class AnalyticsEventAction(
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
        /**
         * いいねを付けたランドマークのみを表示するスイッチ
         */
        data class FavoritesOnlySwitch(
            /**
             * スイッチの ON/OFF
             */
            val favoritesOnly: Boolean,
        ) : AnalyticsEventAction(
            "LandmarkListFavoritesOnlySwitch", mapOf(
                "favorites_only" to favoritesOnly,
            ), false
        )
    }

    /**
     * ランドマーク詳細画面
     */
    object LandmarkDetail {
        /**
         * いいねを付ける
         */
        data class FavoriteOn(
            /**
             * ランドマークの ID
             */
            val id: Long,
            /**
             * ランドマークの名前
             */
            val name: String,
        ) : AnalyticsEventAction(
            "LandmarkDetailFavoriteOn", mapOf(
                "id" to id,
                "name" to name,
            ), true
        )

        /**
         * いいねを解除する
         */
        data class FavoriteOff(
            /**
             * ランドマークの ID
             */
            val id: Long,
            /**
             * ランドマークの名前
             */
            val name: String,
        ) : AnalyticsEventAction(
            "LandmarkDetailFavoriteOff", mapOf(
                "id" to id,
                "name" to name,
            ), false
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
        /**
         * プライバシーポリシーを表示する
         */
        object PrivacyPolicy : AnalyticsEventAction("InfoPrivacyPolicy", emptyMap(), false)
    }
}
