#!/bin/bash

# Carfs Android App Release Script
# This script builds and prepares the app for release

set -e

VERSION_CODE=${1:-1}
VERSION_NAME=${2:-"1.0"}

echo "🚀 Preparing Carfs Android App for Release..."
echo "📦 Version Code: $VERSION_CODE"
echo "📦 Version Name: $VERSION_NAME"

# Check if Android SDK is available
if [ -z "$ANDROID_HOME" ]; then
    echo "❌ Error: ANDROID_HOME environment variable is not set"
    exit 1
fi

# Update version in build.gradle
echo "📝 Updating version information..."
sed -i "s/versionCode [0-9]*/versionCode $VERSION_CODE/" app/build.gradle
sed -i "s/versionName \".*\"/versionName \"$VERSION_NAME\"/" app/build.gradle

# Clean previous builds
echo "🧹 Cleaning previous builds..."
./gradlew clean

# Build release APK
echo "🔨 Building release APK..."
./gradlew assembleRelease

# Run tests
echo "🧪 Running tests..."
./gradlew test

# Create release directory
RELEASE_DIR="releases/v$VERSION_NAME"
mkdir -p "$RELEASE_DIR"

# Copy APK to release directory
echo "📦 Copying release artifacts..."
cp app/build/outputs/apk/release/app-release.apk "$RELEASE_DIR/carfs-v$VERSION_NAME.apk"

# Generate release notes template
cat > "$RELEASE_DIR/RELEASE_NOTES.md" << EOF
# Carfs v$VERSION_NAME Release Notes

## What's New
- Language learning flashcard functionality
- Flip cards to see translations
- Navigate between cards
- Add new cards

## Installation
1. Download carfs-v$VERSION_NAME.apk
2. Enable "Install from unknown sources" in Android settings
3. Install the APK

## Build Information
- Version Code: $VERSION_CODE
- Version Name: $VERSION_NAME
- Built on: $(date)
- Commit: $(git rev-parse --short HEAD 2>/dev/null || echo "N/A")
EOF

echo "📊 Release Summary:"
echo "✅ APK: $RELEASE_DIR/carfs-v$VERSION_NAME.apk"
echo "✅ Release Notes: $RELEASE_DIR/RELEASE_NOTES.md"
echo "✅ Release prepared successfully!"

echo ""
echo "🚀 Next steps:"
echo "1. Test the APK on a device"
echo "2. Update RELEASE_NOTES.md with actual changes"
echo "3. Create a git tag: git tag v$VERSION_NAME"
echo "4. Upload to distribution platform"