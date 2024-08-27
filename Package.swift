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
            url: "https://github.com/tfandkusu/ga913-kmp/releases/download/0.0.3/analytics.xcframework.zip",
            checksum: "05929602c559c8ce6fe0dd9488417b64e793c1c3ec758b7a40077898454bd18a"
        ),
    ]
)
