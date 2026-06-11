plugins {
    id("java-library")
    id("chirp.spring-boot-service")
    kotlin("plugin.jpa")
}

group = "com.plcoding"
version = "unspecified"

dependencies {
    implementation(projects.common)

    implementation(libs.firebase.admin.sdk)

    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.mail)
    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.spring.boot.starter.thymeleaf)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.data.jpa)

    runtimeOnly(libs.postgresql)

    testImplementation(kotlin("test"))
}