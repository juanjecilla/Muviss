# Muviss
This is an example of TheMovieDB API usage made in Kotlin with third party libraries.

## Architecture
The project is structured following Clean architecture principles splitted into three different modules:
- *domain:" Pure kotlin module that contains the model, repository interfaces and app use cases.
- *data:* Android module using frameworks to manage databases and data.
- *app:* Android app to present the information using the MVVM design pattern.


*Used libraries*:
- Dagger + Hilt
- Retrofit + Moshi
- Coroutines + Flow
- Jetpack
    + Navigation
    + ViewModel
- Coil


## TODOs
- Start migration to Compose.
    + Create custom abstract components. These components should be composable and reactive to changes. 
- Increase unit test coverage.
    + Cover most cases before the presentation layer to keep app stability.
- Integrate CI/CD pipeline.
    + Enable GitHub Actions to run unit tests and repetable tasks.
- Research the integration of Jetpack Paging library.
    + Make an MVP with Paging Library and test if could be usable keeping Clean Architecture.
- Jetpack DataStore integration
    + Use DataStore to save simple data from the user. DataStore is the prefered implementation right now and replaces basic SharedPreferences.
- Local Database
    + Integrate Room library to save local data from the user.
- Move API keys to NDK.
    + NDK approach increases the security and prevent API key extraction decompiling the APK.
- Check Splash API
    + Create Splash screen using the new API.
