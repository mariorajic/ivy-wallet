package com.ivy.buildsrc

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

abstract class IvyPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.apply {
            plugin("android-library")
            plugin("kotlin-android")
            plugin("kotlin-kapt")
            plugin("dagger.hilt.android.plugin")
        }

        project.allprojects {
            allprojects {
                tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
                    kotlinOptions.freeCompilerArgs += listOf("-Xcontext-receivers")
//                    kotlinOptions.freeCompilerArgs += listOf(
//                        "-P",
//                        "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
//                    )
                    kotlinOptions.freeCompilerArgs += listOf("-Xskip-prerelease-check")
                }
            }
        }

        val library = project.extensions.getByType(LibraryExtension::class.java)
        library.compileSdk = com.ivy.buildsrc.Project.compileSdkVersion
        library.defaultConfig {
            minSdk = com.ivy.buildsrc.Project.minSdk
            targetSdk = com.ivy.buildsrc.Project.targetSdk
        }
    }
}