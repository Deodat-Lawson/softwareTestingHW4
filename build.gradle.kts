plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5 dependencies
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")

    // RestAssured dependency
    testImplementation("io.rest-assured:rest-assured:5.4.0")

    // Optional: If you need JSON support explicitly (RestAssured includes it, but adding for clarity)
    testImplementation("org.json:json:20230227")

}

tasks.test {
    useJUnitPlatform()

    // Configure test logging
    testLogging {
        events("passed", "skipped", "failed")
    }
}
