plugins {
    id("java")
    id("jacoco") // Added JaCoCo plugin for code coverage
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5 dependencies
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    // RestAssured dependency
    testImplementation("io.rest-assured:rest-assured:5.4.0")

    // Optional: JSON support
    testImplementation("org.json:json:20230227")

    // Local JAR file
    implementation(files("src/main/resources/guessnumber.jar"))
}

// Configure JAR task to include Main-Class in manifest
tasks.jar {
    manifest {
        attributes("Main-Class" to "Main")
    }
}

// Configure test task
tasks.test {
    useJUnitPlatform()

    // Enhanced test logging configuration
    testLogging {
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        events("started", "passed", "skipped", "failed")
        showStandardStreams = true // Show standard output/error streams
    }
}

// Configure JaCoCo test report
tasks.jacocoTestReport {
    dependsOn(tasks.test) // Ensure tests run before generating report
    reports {
        csv.required.set(true) // Generate CSV report
        xml.required.set(true)  // Optional: Enable XML report if needed
        html.required.set(true) // Optional: Enable HTML report for visual inspection
    }
}