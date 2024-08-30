import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ktlint)
    id("maven-publish")
}

kotlin {
    androidTarget()
    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "ga913kmp"
            xcf.add(this)
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.tfandkusu.ga913kmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/tfandkusu/ga913-kmp")
            credentials {
                username = "tfandkusu"
                password = System.getenv("GITHUB_PACKAGES_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.tfandkusu"
            artifactId = "ga913kmp"
            version = "0.0.3"
            from(components["kotlin"])
        }
    }
}
