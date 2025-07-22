# Carfs - Language Learning Cards

Android app with flashcards for language learning. Study vocabulary by flipping between words and their translations.

## Features

- 📚 Flashcard-based learning
- 🔄 Flip cards to reveal translations
- ➡️ Navigate between cards
- ➕ Add new vocabulary cards
- 🎨 Clean, intuitive interface

## Getting Started

### Prerequisites

- Android SDK (API level 24+)
- Java 8 or higher
- Gradle 8.14.3

### Building the App

1. Clone the repository:
   ```bash
   git clone https://github.com/jolfzverb/carfs.git
   cd carfs
   ```

2. Build the app:
   ```bash
   ./build.sh
   ```

3. Install on device:
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

### Release Build

To create a release build:

```bash
./release.sh [version_code] [version_name]
```

Example:
```bash
./release.sh 2 "1.1"
```

## Usage

1. Launch the app
2. View the current flashcard
3. Tap the card or "Flip" button to see the translation
4. Use "Next" and "Previous" buttons to navigate
5. Tap the "+" button to add new cards

## Project Structure

```
carfs/
├── app/                          # Android application module
│   ├── src/main/
│   │   ├── java/com/carfs/       # Kotlin source code
│   │   ├── res/                  # Resources (layouts, strings, etc.)
│   │   └── AndroidManifest.xml   # App manifest
│   └── build.gradle              # App-level build configuration
├── build.gradle                  # Project-level build configuration
├── settings.gradle               # Gradle settings
├── build.sh                      # Build script
├── release.sh                    # Release script
└── README.md                     # This file
```

## Scripts

### build.sh
Builds debug and release APKs, runs unit tests.

### release.sh
Creates a versioned release with:
- Release APK
- Version updates
- Release notes template
- Build artifacts

## Development

The app is built with:
- **Kotlin** - Primary language
- **Android SDK** - Platform
- **Material Design** - UI components
- **View Binding** - UI binding

### Adding Features

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
