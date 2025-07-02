# VoidBreaker Launcher

**VoidBreaker Launcher** is a minimal and adaptive launcher built with Jetpack Compose and Material 3, optimized for performance and clarity. Designed for custom workflows and experimental interface deployments.

> _“Form defines function — yet shadows define intent.”_

---

## 🚀 Features

- ✨ Clean splash screen with shimmer animation
- 🧩 Modular architecture powered by Firebase (RealtimeDB & Config-ready)
- 🎯 Native Jetpack Compose UI
- 🔐 Built-in obfuscation compatibility and build separation
- ⚡ Terminal-only build workflow supported (`./gradlew assembleDebug`)

---

## 📦 Tech Stack

| Tech              | Description                            |
|------------------|----------------------------------------|
| Kotlin            | v2.0.21 (Stable)                       |
| Android Gradle    | Plugin v8.4.0                          |
| Jetpack Compose   | v1.5.4                                 |
| Material3         | v1.1.2                                 |
| Firebase Support  | (RealtimeDB, Installations, Analytics) |

---

## 📂 Module Structure
VoidBreakerLauncher/ ├── app/ │ ├── src/ │ ├── build.gradle.kts ├── build.gradle.kts (root - no Android plugin!) ├── settings.gradle.kts

---

## 🛠️ Build Instructions

```bash
# From project root:
./gradlew assembleDebug
adb install -r app/build/outputs/apk/debug/VoidBreakerLauncher.apk

🧙‍♂️ Disclaimer
This launcher is intended for educational and experimental use only. All trademarks and app IDs used in testing belong to their respective owners.

“The interface may look pure, but the intent lies beneath the surface.”

