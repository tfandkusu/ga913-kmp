# DroidKaigi 2024「 データに基づく意思決定を支える、Google Analytics for Firebase のイベント送信」の Kotlin Multiplatform による Analytics イベント定義の実装例

[tfandkusu/ga913-yaml](https://github.com/tfandkusu/ga913-yaml) と同じ目的を達成するための別ソリューションです。解決できる課題は [tfandkusu/ga913-yaml](https://github.com/tfandkusu/ga913-yaml) の方でご確認ください。

アプリ開発者は Kotlin で [Google Analytics for Firebase](https://firebase.google.com/docs/analytics) のイベントの仕様を定義します。
本リポジトリは Kotlin Multiplatform ライブラリなので iOS / Android アプリから取り込むことが出来ます。

## ライブラリの使用方法

### iOS 向け

[Swift Package Manager](https://developer.apple.com/documentation/xcode/adding-package-dependencies-to-your-app) で、このリポジトリを追加します。

### Android 向け

[JitPack](https://jitpack.io/) を使用しています。

settings.gradle に JitPack を追加します。

```settings.gradle.kts
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // 追加
        maven { url = uri("https://jitpack.io") }
    }
}
```

モジュールの build.gradle.kts でライブラリ依存を追加します。

```app/build.gradle.kts
dependencies {
    implementation("com.github.tfandkusu:ga913-kmp:0.0.7")
}
```
