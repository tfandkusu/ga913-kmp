package com.tfankusu.ga913kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform