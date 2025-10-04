plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.development.aashaworker"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.development.aashaworker"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


    implementation("com.tbuonomo:dotsindicator:5.0")

    // Room (Java)
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation( "androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")

    // Lifecycle / ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.6.2")

    // WorkManager
    implementation ("androidx.work:work-runtime:2.8.1")

    // Retrofit (network placeholder)
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Encrypted SharedPreferences (security)
    implementation ("androidx.security:security-crypto:1.1.0")
}