plugins {
    java
    id("org.sonarqube") version "6.0.1.5171"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Hier in der Kotlin-DSL: "sonarqube", nicht "sonar"
sonarqube {
    properties {
        property("sonar.projectKey", "AAU-MANKOMANIA-SE2-2025_Mankomania-Client")
        property("sonar.organization", "aau-mankomania-se2-2025")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
