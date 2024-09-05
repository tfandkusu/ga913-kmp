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
            checksum: "ba20be1807613c6755d93b712b31b0d669842a09cf07acc61a10dddbf1e14562"
        ),
    ]
)
