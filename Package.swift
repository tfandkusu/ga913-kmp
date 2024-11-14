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
            url: "https://github.com/tfandkusu/ga913-kmp/releases/download/0.1.0/ga913kmp.xcframework.zip",
            checksum: "54b04d1cad6dce83e100a387e8d54962cdbae7d34e4edb5b3790be84492b81c9"
        ),
    ]
)
