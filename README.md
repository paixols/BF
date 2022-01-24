# BF

> BF Android Clean Architecture Code Test

## Requirements:
- Insert Google Cloud Maps API Key in local.properties file (using mapsplatform.secrets-gradle-plugin):
```
MAPS_API_KEY=<API KEY>
```
- Emulator or Device to run the application require Android SDK 26, and Google Play Services enabled.


## Specifications:
- Project Built with Clean Architecture principles, MVVM and Android Jetpack Tools for Architecture Scalability in mind.
- Build Source Gradle is built using Kotlin DSL, and can be found in:
  - buildSrc
    - AppConfig
    - Deps.kt
- Use of Dagger2 with submodules for DI management and Architecture Scalability in mind.
- Google Maps rendered using Google Cloud Maps (Modified - Atlas) or Default as Satellite.
- Emulator or Device to run the application require Android SDK 26, and Google Play Services enabled.

## Functionality:
- **Map**: Can be changed with menu options between Satellite(Default), Terrain or Atlas(Custom render from GC).
  - Map initial camera position is set over a parking lot in Tampa, FL for demonstration purposes.
- **Order**: The user can request fuel and select time window (morning or afternoon), and default payment method.
- **Confirmation**: The user can view the fuel request confirmation, or cancel to start over.

## Notes:
- Project built with Android Studio - Artic Fox 2020.3.1 Patch 4
