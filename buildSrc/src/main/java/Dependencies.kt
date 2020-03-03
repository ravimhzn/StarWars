object Android {
    const val compile_sdk_version = 28
    const val min_sdk_version = 21
    const val target_sdk_version = 29
    const val version_code = 1
    const val version_name = "0.1"
}

object Modules {
    const val core_network = ":core-network"
    const val core_utils = ":core-utils"
    const val feature_login = ":feature-login"
    const val feature_reward = ":feature-reward"
}

object Versions {
    const val appcompat = "1.1.0"
    const val constraint_layout = "1.1.3"
    const val core_testing = "1.1.1"
    const val coroutines = "1.3.3"
    const val espresso_core = "3.2.0"
    const val fragment_testing = "1.1.0"
    const val gradle = "3.5.3"
    const val junit = "4.12"
    const val kakao = "2.1.0"
    const val koin = "2.0.1"
    const val kotlin = "1.3.61"
    const val ktor = "1.3.1"
    const val ktx = "1.2.0"
    const val lifecycle = "2.2.0"
    const val lottie = "3.4.0"
    const val mockk = "1.9.3"
    const val navigation = "1.0.0"
    const val serialization = "0.14.0"
    const val test_core = "1.2.0"
    const val test_ext = "1.1.1"
    const val test_rules = "1.2.0"
    const val test_runner = "1.2.0"
}

object Libs {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val koin_android = "org.koin:koin-android:${Versions.koin}"
    const val koin_core = "org.koin:koin-core:${Versions.koin}"
    const val koin_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val kotlin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinx_serialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.serialization}"
    const val ktor_android = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ktor_logging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
    const val ktor_serialization = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycle_ext = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val navigation = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
}

object TestLibs {
    const val core_testing = "android.arch.core:core-testing:${Versions.core_testing}"
    const val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso_core}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    const val fragment_testing = "androidx.fragment:fragment-testing:${Versions.fragment_testing}"
    const val junit = "junit:junit:${Versions.junit}"
    const val kakao = "com.agoda.kakao:kakao:${Versions.kakao}"
    const val koin_test = "org.koin:koin-test:${Versions.koin}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockk_android = "io.mockk:mockk-android:${Versions.mockk}"
    const val test_core = "androidx.test:core:${Versions.test_core}"
    const val test_ext = "androidx.test.ext:junit:${Versions.test_ext}"
    const val test_rules = "androidx.test:rules:${Versions.test_rules}"
    const val test_runner = "androidx.test:runner:${Versions.test_runner}"
}
