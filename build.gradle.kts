// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("org.sonarqube") version "4.4.1.3373"

}

sonarqube {
    properties {
        property("sonar.projectKey", "AAU-MANKOMANIA-SE2-2025_Mankomania-Client")
        property("sonar.organization", "aau-mankomania-se2-2025")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.login", System.getenv("SONAR_TOKEN"))
        property("sonar.coverage.jacoco.xmlReportPaths", "app/build/reports/jacoco/testDebugUnitTestReport/jacocoTestDebugUnitTestReport.xml")
        property("sonar.gradle.skipCompile", "true")
    }
}

subprojects {
    apply(plugin = "org.sonarqube")
}
