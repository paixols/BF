object Versions {
    /*Class Paths*/
    const val gradle = "7.0.4"
    const val kotlinGradle = "1.6.10"
    const val mapsPlatform = "2.0.0"

    /*Kotlin*/
    const val ktx = "1.7.0"
    const val appCompat = "1.4.1"
    const val constraintLayout = "2.1.3"
    const val navFragment = "2.3.5"
    const val navUi = "2.3.5"
    const val kotlin = "1.5.31"

    /* Co-routines */
    const val ktx_coroutines = "2.4.0"
    const val coroutines = "1.5.2"

    /*Google*/
    const val materialDesign = "1.5.0"
    const val maps = "18.0.2"

    /* DI */
    const val dagger2 = "2.40"

    /* Database */
    const val room = "2.4.1"

    /*Testing*/
    const val junit4 = "4.+"
    const val androidxTesting = "1.1.3"
    const val espresso = "3.4.0"
}

object Google {
    private const val materialDesign =
        "com.google.android.material:material:${Versions.materialDesign}"
    private const val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"
    val google = listOf(materialDesign, maps)
}

object Android {
    private const val androidxKtx = "androidx.core:core-ktx:${Versions.ktx}"
    private const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    private const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private const val navFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navFragment}"
    private const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navUi}"
    private const val room = "androidx.room:room-runtime:${Versions.room}"
    private const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    private const val roomCoroutines = "androidx.room:room-ktx:${Versions.room}"
    private const val livedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.ktx_coroutines}"

    val androidDependencies = listOf(
        androidxKtx,
        appCompat,
        constraintLayout,
        navFragment,
        navUi,
        room,
        coroutines,
        roomCoroutines,
        livedata
    )
}

object KotlinLibs {
    private const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    private const val kotlin_coroutines_extension =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ktx_coroutines}"
    private const val roomKapt = "androidx.room:room-compiler:${Versions.room}"

    val implementations = listOf(
        kotlin,
        kotlin_coroutines_extension
    )
    val annotationProcessing = listOf(roomKapt)
}

object AppClassPaths {
    private const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    private const val kotlinGradle =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradle}"
    private const val mapsPlatform =
        "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:${Versions.mapsPlatform}"

    val classPaths = listOf(gradle, kotlinGradle, mapsPlatform)
}

object DependencyInjection {
    private const val dagger2 = "com.google.dagger:dagger:${Versions.dagger2}"
    private const val dagger2Kapt = "com.google.dagger:dagger-compiler:${Versions.dagger2}"

    val implementations = listOf(dagger2)
    val kaptImplementations = listOf(dagger2Kapt)
}

object Testing {
    const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    private const val jUnit4 = "junit:junit:${Versions.junit4}"
    val testImplementation = listOf(jUnit4)

    private const val androidX = "androidx.test.ext:junit:${Versions.androidxTesting}"
    private const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val androidTestImplementation = listOf(androidX, espresso)
}
