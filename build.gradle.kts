plugins {
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.ktlint) apply false
    `maven-publish`
}
