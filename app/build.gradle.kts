plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("jacoco")
    id("org.sonarqube") version "4.4.1.3373"
}

android {
    namespace = "com.example.mankomania_client"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mankomania_client"
        minSdk = 24
        targetSdk = 35
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

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    testOptions {
        unitTests.all {
            useJUnitPlatform()
            finalizedBy("jacocoTestReport")
        }
    }
}

// JaCoCo Test-Report-Task
tasks.register("jacocoTestReport", JacocoReport::class) {
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        xml.destination.set(file("${project.projectDir}/build/reports/jacoco/jacocoTestReport/jacocoTestReport.xml"))
    }

    val fileFilter = listOf(
        "**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*", "android/**/*.*"
    )

    val debugTree = fileTree(
        dir = "${project.layout.buildDirectory.get().asFile}/intermediates/javac/debug",
        excludes = fileFilter
    )

    val mainSrc = "${project.projectDir}/src/main/java"

    sourceDirectories.setFrom(files(mainSrc))
    classDirectories.setFrom(files(debugTree))
    executionData.setFrom(files("${project.layout.buildDirectory.get().asFile}/jacoco/testDebugUnitTest.exec"))
}

// SonarQube-Properties
sonar {
    properties {
        property("sonar.projectKey", "AAU-MANKOMANIA-SE2-2025_Mankomania-Client")
        property("sonar.organization", "aau-mankomania-se2-2025")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.coverage.jacoco.xmlReportPaths", "${project.projectDir}/build/reports/jacoco/jacocoTestReport/jacocoTestReport.xml")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
