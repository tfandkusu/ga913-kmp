// swift-tools-version:5.5
import PackageDescription

let package = Package(
    name: "ga913kmp",
    products: [
        .library(
            name: "ga913kmp",
            targets: ["ga913kmp"]
        ),
    ],
    dependencies: [],
    targets: [
        .binaryTarget(
            name: "ga913kmp",
            url: "https://github.com/tfandkusu/ga913-kmp/releases/download/0.0.8/ga913kmp.xcframework.zip",
            checksum: "e4a1a75d242aece50be08e7aff823cf72d84a8fdeed5677ab699e5e1a18c395b"
        ),
    ]
)
