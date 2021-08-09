import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    `maven-publish`
    kotlin("jvm") version "1.5.21"
    jacoco
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jsoup:jsoup:1.13.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.12.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
    testImplementation("junit:junit:4.12")
    testImplementation(kotlin("test-junit"))
}

group = "io.github.kokorins"
version = "0.14.0"
description = "essence"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    withSourcesJar()
    withJavadocJar()
}

jacoco {
    toolVersion = "0.8.7"
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
    withType<Test> {
        testLogging {
            showStandardStreams = true
        }
        useJUnitPlatform()
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
