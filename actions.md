# 画面内操作イベント一覧

## ランドマーク一覧画面

| 操作内容 | Analytics イベント名 | パラメータ | コンバージョンイベント |
| -- | -- | -- | -- |
| いいねを付けたランドマークのみを表示するスイッチ | LandmarkListFavoritesOnlySwitch | スイッチの ON/OFF favorites_only: boolean<br> |  |

## ランドマーク詳細画面

| 操作内容 | Analytics イベント名 | パラメータ | コンバージョンイベント |
| -- | -- | -- | -- |
| いいねを付ける | LandmarkDetailFavoriteOn | ランドマークの ID id: long<br>ランドマークの名前 name: string<br> | ○ |
| いいねを解除する | LandmarkDetailFavoriteOff | ランドマークの ID id: long<br>ランドマークの名前 name: string<br> |  |

## 情報画面

| 操作内容 | Analytics イベント名 | パラメータ | コンバージョンイベント |
| -- | -- | -- | -- |
| プライバシーポリシーを表示する | InfoPrivacyPolicy |  |  |

