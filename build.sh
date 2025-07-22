#!/bin/bash

# Carfs Android App Build Script
# This script builds the Android app in debug and release modes

set -e

echo "🚀 Building Carfs Android App..."

# Check if Android SDK is available
if [ -z "$ANDROID_HOME" ]; then
    echo "❌ Error: ANDROID_HOME environment variable is not set"
    echo "Please set ANDROID_HOME to point to your Android SDK installation"
    exit 1
fi

echo "✅ Android SDK found at: $ANDROID_HOME"

# Check if Gradle wrapper exists, create if not
if [ ! -f "./gradlew" ]; then
    echo "📦 Creating Gradle wrapper..."
    gradle wrapper --gradle-version 8.14.3
fi

# Make gradlew executable
chmod +x ./gradlew

echo "🔨 Building debug APK..."
./gradlew assembleDebug

echo "🔨 Building release APK..."
./gradlew assembleRelease

echo "🧪 Running unit tests..."
./gradlew test

echo "📊 Build report:"
echo "Debug APK: app/build/outputs/apk/debug/app-debug.apk"
echo "Release APK: app/build/outputs/apk/release/app-release.apk"

echo "✅ Build completed successfully!"