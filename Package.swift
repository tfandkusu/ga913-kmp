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
            url: "https://github.com/tfandkusu/ga913-kmp/releases/download/0.0.3/ga913kmp.xcframework.zip",
            checksum: "3d178b067acff553f3c64eefb385eb2b49a7573b23de8bc763644b712660ffe9"
        ),
    ]
)
