plugins {
    kotlin("jvm")
}

group = "io.luxus.adofai.lib"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")

    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
    testImplementation("io.kotest:kotest-assertions-core:5.6.2")
    testImplementation("io.mockk:mockk:1.13.5")
}

tasks.test {
    useJUnitPlatform()
}
