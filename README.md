# Android MVVM Networking Sample

A historical Kotlin Android practice project exploring a repository-based data layer, ViewModels, coroutines and network response handling. The repository retains its original name; consult the implementation before using it as a dependency-injection reference.

## Source map

- `ui/main/MainViewModel.kt`: screen state and request coordination.
- `ui/main/data/repository/PostRepository.kt`: data access boundary.
- `ui/main/data/service/PostsServices.kt`: remote service interface.
- `core/network/Resource.kt`: network result representation.
- `core/ServiceGenerator.kt` and `CoroutineCallAdapterFactory.kt`: networking setup.
- `src/test`: unit and network test examples.

## Working with the sample

Open the project in Android Studio, inspect the Gradle versions and install the matching Android SDK. The Gradle wrapper is included:

```sh
./gradlew testDebugUnitTest
./gradlew assembleDebug
```

The historical Android toolchain has not been revalidated as part of the portfolio documentation update. This project demonstrates mobile architecture practice; current NestJS backend examples are [Catalog Sync](https://github.com/ihashimz/nestjs-catalog-sync) and [Order Workflow](https://github.com/ihashimz/nestjs-order-workflow).
