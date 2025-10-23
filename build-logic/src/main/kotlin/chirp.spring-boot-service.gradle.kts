plugins {
    id("chirp.kotlin-common")
    id("io.spring.dependency-management")
}

dependencies {
    "implementation"(libraries.findLibrary("kotlin-reflect").get())
    "implementation"(libraries.findLibrary("kotlin-stdlib").get())
    "implementation"(libraries.findLibrary("spring-boot-starter-web").get())
    "implementation"(libraries.findLibrary("jackson-module-kotlin").get())

    "testImplementation"(libraries.findLibrary("spring-boot-starter-test").get())
    "testImplementation"(libraries.findLibrary("kotlin-test-junit5").get())
    "testRuntimeOnly"(libraries.findLibrary("junit-platform-launcher").get())
}