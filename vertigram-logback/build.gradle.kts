/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("buildlogic.vertigram-module")
}

dependencies {
    api(libs.bundles.vertx.core)
    api(libs.bundles.kotlin.std)
    api(libs.bundles.logback)

    api(project(":vertigram"))
    api(project(":vertigram-telegram-client"))

    testImplementation(libs.junit.api)
    testRuntimeOnly (libs.junit.engine)

    dokkaPlugin(libsInternal.dokka.versioning.plugin)
}

description = "Vertigram Logback"
