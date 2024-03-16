import org.ajoberstar.grgit.Grgit
import org.jetbrains.dokka.DokkaConfiguration.Visibility
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import java.net.URI
import java.net.URL
import java.time.LocalDate

/*
 * This file was generated by the Gradle 'init' task.
 */


group = "ski.gagar.vertigram"

val catalogs = extensions
    .getByType<VersionCatalogsExtension>()

val libs = catalogs.named("libs")
val libsInternal = catalogs.named("libsInternal")

plugins {
    id("org.jetbrains.dokka")
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    dokkaPlugin(libsInternal.findLibrary("dokka-versioning-plugin").get().get().toString())
}

tasks.withType<DokkaTaskPartial>().configureEach {
    pluginConfiguration<org.jetbrains.dokka.base.DokkaBase, org.jetbrains.dokka.base.DokkaBaseConfiguration> {
        footerMessage = "© ${LocalDate.now().year} <a href=\"https://github.com/gagarski/\">Kirill Gagarski</a>"
    }
    dokkaSourceSets {
        named("main") {
            sourceRoots.from(file("src/main/"), file("build/generated/source/kaptKotlin/main"))
            includes.from("README.md")
            suppressGeneratedFiles = false

            jdkVersion = 21

            platform.set(org.jetbrains.dokka.Platform.jvm)

            documentedVisibilities.set(setOf(
                Visibility.PUBLIC,
                Visibility.PROTECTED
            ))
            perPackageOption {
                matchingRegex.set(".*internal.*")
                suppress.set(true)
            }

            perPackageOption {
                matchingRegex.set(".*samples.*")
                suppress.set(true)
            }
            samples.from(files("src/main/kotlin/ski/gagar/vertigram/samples/Samples.kt"))
            sourceLink {
                val isSnapshot = version.toString().split("-").let { it[it.lastIndex] } == "SNAPSHOT"
                val urlVersion = if (isSnapshot) {
                    val git = Grgit.open(rootDir)
                    git.head().id
                } else {
                    "v${version.toString()}"
                }
                // Unix based directory relative path to the root of the project (where you execute gradle respectively).
                localDirectory.set(rootDir)

                // URL showing where the source code can be accessed through the web browser
                remoteUrl.set(URI("https://github.com/gagarski/vertigram/tree/${urlVersion}").toURL())
                // Suffix which is used to append the line number to the URL. Use #L for GitHub
                remoteLineSuffix.set("#L")
            }

            externalDocumentationLink {
                url.set(URI("https://kotlinlang.org/api/kotlinx.coroutines/").toURL())
            }
            externalDocumentationLink {
                url.set(URI("https://vertx.io/docs/${libs.findVersion("vertx").get()}/apidocs/").toURL())
            }
            val jacksonVersion = libs.findVersion("jackson").get()
            externalDocumentationLink {
                url.set(URI("https://javadoc.io/doc/com.fasterxml.jackson.core/jackson-annotations/${jacksonVersion}/").toURL())
            }
            externalDocumentationLink {
                url.set(URI("https://javadoc.io/doc/com.fasterxml.jackson.core/jackson-core/${jacksonVersion}/").toURL())
                packageListUrl.set(URI("https://javadoc.io/doc/com.fasterxml.jackson.core/jackson-core/${jacksonVersion}/element-list").toURL())
            }
            externalDocumentationLink {
                url.set(URI("https://javadoc.io/doc/com.fasterxml.jackson.core/jackson-databind/${jacksonVersion}/").toURL())
            }
            externalDocumentationLink {
                url.set(URI("https://javadoc.io/doc/org.jooq/jooq/${libs.findVersion("jooq").get()}").toURL())
                packageListUrl.set(URI("https://javadoc.io/doc/org.jooq/jooq/${libs.findVersion("jooq").get()}/element-list").toURL())
            }
        }
    }
}
