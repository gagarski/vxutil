/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    api(libs.com.fasterxml.jackson.core.jackson.annotations)
    api(libs.com.fasterxml.jackson.core.jackson.core)
    api(libs.com.fasterxml.jackson.core.jackson.databind)
    api(libs.com.fasterxml.jackson.datatype.jackson.datatype.jdk8)
    api(libs.com.fasterxml.jackson.module.jackson.module.kotlin)
    api(libs.com.fasterxml.jackson.module.jackson.module.parameter.names)
    api(libs.io.vertx.vertx.core)
    api(libs.io.vertx.vertx.lang.kotlin)
    api(libs.io.vertx.vertx.lang.kotlin.coroutines)
    api(libs.io.vertx.vertx.web)
    api(libs.io.vertx.vertx.web.client)
    api(libs.org.jetbrains.kotlinx.kotlinx.coroutines.core.jvm)
    api(libs.org.jetbrains.kotlinx.kotlinx.html)
    api(libs.org.jetbrains.kotlinx.kotlinx.html.jvm)
    api(libs.org.jetbrains.kotlin.kotlin.stdlib.jdk8)
    api(libs.org.jetbrains.kotlinx.kotlinx.coroutines.jdk8)
    api(libs.org.reflections.reflections)
    api(libs.org.slf4j.slf4j.api)
    api(project(":vertigram-core"))
    api(project(":vertigram-annotations"))
}

description = "Vertigram Client"
