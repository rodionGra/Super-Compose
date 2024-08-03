// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
}

buildscript {
    val compose_version by extra("1.5.0-beta03")
    val compose_compiler_version by extra("1.9.0")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
    }
}

subprojects {
    tasks
        .withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>()
        .configureEach {
            kotlinOptions {

                val enableMetricsProvider =
                    project.providers.gradleProperty("composeCompilerMetrics")
                val enableMetrics = (enableMetricsProvider.orNull == "true")

                val enableRepostsProvider =
                    project.providers.gradleProperty("composeCompilerReports")
                val enableReports = (enableRepostsProvider.orNull == "true")

                if (enableMetrics || enableReports) {
                    //jvmTarget = "1.8"
                    freeCompilerArgs = freeCompilerArgs + listOf(
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                                project.buildDir.absolutePath + "/compose_metrics",
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                                project.buildDir.absolutePath + "/compose_metrics",
                    )
                }
            }
        }
}