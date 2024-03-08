import org.jetbrains.dokka.gradle.DokkaTaskPartial

/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("buildlogic.vertigram-module")
    kotlin("kapt")
}

dependencies {
    api(libs.bundles.commons.lang3)
    api(libs.bundles.jackson)
    api(libs.bundles.vertx.core)
    api(libs.bundles.vertx.web)
    api(libs.bundles.vertx.web.client)
    api(libs.bundles.kotlin.std)
    api(libs.bundles.kotlinx.html)
    api(libs.bundles.reflections)
    api(libs.bundles.slf4j.api)
    api(project(":vertigram-util"))
    api(project(":vertigram-annotations"))
    kapt(project(":vertigram-codegen"))

    testImplementation(libs.junit.api)
    testRuntimeOnly (libs.junit.engine)

    dokkaPlugin(libs.dokka.versioning.plugin)
}

description = "Vertigram Client"

kapt {
    annotationProcessor("ski.gagar.vertigram.codegen.VertigramClientGenerator")
}


sourceSets {
    create("withGenerated") {
        kotlin {
//            srcDir(file("src/main/kotlin"))
            srcDir("${buildDir}/generated/source/kaptKotlin/main")
        }
    }
}

tasks.named<Jar>("sourcesJar").configure {
    dependsOn("kaptKotlin")
    archiveClassifier = "sources"
    from(sourceSets.named("withGenerated").get().allSource)
}


tasks.withType<DokkaTaskPartial>().configureEach {
    dependsOn("kaptKotlin")
}