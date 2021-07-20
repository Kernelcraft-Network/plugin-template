import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.plugin.metadata.PluginDependency

plugins {
    `java-library`
    id("org.spongepowered.gradle.plugin") version "1.1.1"
}

group = "net.kernelcraft.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

sponge {
    apiVersion("9.0.0")
    plugin("example") {
        loader(PluginLoaders.JAVA_PLAIN)
        displayName("Example")
        mainClass("net.kernelcraft.example.Example")
        description("Just testing things...")
        links {
            homepage("https://kernelcraft.net")
            source("https://github.com/Kernelcraft-Network/plugin-template")
            issues("https://github.com/Kernelcraft-Network/plugin-template/issues")
        }
        contributor("KernelFreeze") {
            description("Lead Developer")
        }
        dependency("spongeapi") {
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(false)
        }
    }
}

val javaTarget = 16 // Sponge targets a minimum of Java 8
java {
    sourceCompatibility = JavaVersion.toVersion(javaTarget)
    targetCompatibility = JavaVersion.toVersion(javaTarget)
    if (JavaVersion.current() < JavaVersion.toVersion(javaTarget)) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(javaTarget))
    }
}

tasks.withType(JavaCompile::class).configureEach {
    options.apply {
        encoding = "utf-8" // Consistent source file encoding
        release.set(javaTarget)
    }
}

// Make sure all tasks which produce archives (jar, sources jar, javadoc jar, etc) produce more consistent output
tasks.withType(AbstractArchiveTask::class).configureEach {
    isReproducibleFileOrder = true
    isPreserveFileTimestamps = false
}