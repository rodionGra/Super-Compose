// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val compose_version by extra("1.5.0-beta03")
    val compose_compiler_version by extra("1.5.0")
    val kotlin_version by extra("1.9.0")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")

        //hilt
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.47")

        //serialization
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")
    }
}

/*tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}*/

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