plugins {
    application
    checkstyle
    jacoco
}

group = "hexlet.code"
version = "1.0.3-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)
    }
}

application {
    mainClass.set("hexlet.code.App")
}

dependencies {
    implementation(
        "org.apache.commons:commons-lang3:3.12.0",
        "com.fasterxml.jackson.core:jackson-databind:2.15.2",
        "com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.2",
        "info.picocli:picocli:4.7.4"
    )

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    testImplementation(
        "org.junit.jupiter:junit-jupiter-params:5.8.2",
        "org.assertj:assertj-core:3.24.2"
    )
}

tasks.test {
    useJUnitPlatform()
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.withType<JacocoReport>())
}

tasks.withType<JacocoReport> {
    dependsOn(tasks.test) // Зависит от задачи test
    reports {
        xml.required.set(true)
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/html"))
    }
}

// Правильный способ установки стандартного ввода в Kotlin DSL
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

private fun TaskContainer.jacocoTestReport(function: Any) {}
