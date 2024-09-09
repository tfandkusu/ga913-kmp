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
            url: "https://github.com/tfandkusu/ga913-kmp/releases/download/0.0.9/ga913kmp.xcframework.zip",
            checksum: "cd5b43ba02ea964440b0316e158c8e5c2ec3cf7ca6f9dc79a7d0d0da6f9f767e"
        ),
    ]
)
