import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources") version "0.23.0"
}

group = "io.luxus"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

multiplatformResources {
    multiplatformResourcesPackage = "io.luxus.adofai.helper" // required
//    multiplatformResourcesClassName = "" // optional, default MR
//    multiplatformResourcesVisibility = MRVisibility.Internal // optional, default Public
//    iosBaseLocalizationRegion = "en" // optional, default "en"
//    multiplatformResourcesSourceSet = "jvmMain"  // optional, default "commonMain"
}

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("dev.icerock.moko:resources:0.23.0")
                implementation("dev.icerock.moko:resources-compose:0.23.0")
                implementation("com.darkrockstudios:mpfilepicker:1.1.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation("dev.icerock.moko:resources-test:0.23.0")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":ADOFAI-Lib"))
            }
        }
        val jvmTest by getting {
            dependencies {
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ADOFAI-Helper"
            packageVersion = "1.0.0"
        }
    }
}
