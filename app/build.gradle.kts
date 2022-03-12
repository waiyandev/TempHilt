import org.gradle.internal.impldep.com.jcraft.jsch.ConfigRepository.defaultConfig
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

//plugins {
//    id 'com.android.application'
//    id 'org.jetbrains.kotlin.android'
//}
plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.jetBrainKotlin)
    kotlin(BuildPlugins.kaptAndroid)
    id(BuildPlugins.hiltAndroid)
}

android {
    compileSdkVersion(AndroidSdk.compile)
//    compileSdk = AndroidSdk.compile

    defaultConfig {
        applicationId = "com.showti.temphilt"
//        minSdk = AndroidSdk.min
//        targetSdk = AndroidSdk.target
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

}

dependencies {

    implementation(project(":core"))
//    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.material)
    implementation(Libraries.hilt)
//    kapt(Libraries.hiltCompiler)
    kapt(Libraries.hiltKapt)

    implementation(Libraries.activityKtx)
    implementation(Libraries.fragmentKtx)

    implementation(Libraries.viewModelKtx)
    implementation(Libraries.liveDataKtx)
    implementation(Libraries.runtimeKtx)
    implementation(Libraries.lifeCycleExt)

    implementation(Libraries.coroutineAndroid)
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinePlay)

    implementation(Libraries.glide)
    kapt(Libraries.glideKapt)

    testImplementation (TestLibraries.junit4)
    androidTestImplementation (TestLibraries.testRunner)
    androidTestImplementation (TestLibraries.espresso)
}

kapt {
    correctErrorTypes = true
}