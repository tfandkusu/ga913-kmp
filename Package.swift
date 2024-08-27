// swift-tools-version:5.5
import PackageDescription

let package = Package(
    name: "ga913-kmp",
    products: [
        .library(
            name: "analytics",
            targets: ["analytics"]
        ),
    ],
    dependencies: [],
    targets: [
        .binaryTarget(
            name: "analytics",
            url: "https://github.com/tfandkusu/ga913-kmp/releases/download/0.0.1/analytics.zip",
            checksum: "05929602c559c8ce6fe0dd9488417b64e793c1c3ec758b7a40077898454bd18a"
        ),
    ]
)
