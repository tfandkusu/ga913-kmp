#!/bin/sh
VERSION=$(cat VERSION)
CHECKSUM=$(swift package compute-checksum ga913kmp/build/XCFrameworks/release/ga913kmp.xcframework.zip)
npx ejs Package.swift.ejs -i '{"version": "'$VERSION'", "checksum": "'$CHECKSUM'"}' -o Package.swift
