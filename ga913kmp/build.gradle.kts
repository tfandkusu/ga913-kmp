import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.ktlint)
    `maven-publish`
    application
}

kotlin {
    jvm {
        withJava()
    }
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
        }
        jvmMain.dependencies {
            implementation(libs.kotlin.reflect)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmTest {
            dependencies {
            }
        }
    }
}

application {
    mainClass.set("com.tfandkusu.ga913kmp.analytics.MainKt")
}
