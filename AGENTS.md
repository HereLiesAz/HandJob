# Agent Instructions

This document provides instructions for AI agents working on this project.

## Project Overview

This project is an Android application that uses CameraX, MediaPipe, and SceneView to create an auto-posing 3D hand model for artists.

## Development Environment

The project is built using Gradle. The following are the key files for the build system:

- `settings.gradle.kts`: Defines the project modules.
- `build.gradle.kts`: The root build file.
- `app/build.gradle.kts`: The build file for the `app` module.
- `gradle/libs.versions.toml`: Defines the project's dependencies.

To build the project, run the following command:

```bash
./gradlew build
```

## Coding Style

The project uses the standard Kotlin coding style. Please ensure that your code is well-formatted and documented.

## Testing

The project does not yet have a comprehensive test suite. When adding new features, please also add corresponding unit and instrumented tests.

## Submitting Changes

Before submitting any changes, please ensure that the project builds successfully and that all tests pass.
