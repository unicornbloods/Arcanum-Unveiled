plugins {
    id("net.neoforged.moddev") version "2.0.143"
    java
}

fun prop(key: String): String = providers.gradleProperty(key).get()

val modId = prop("mod_id")
val modName = prop("mod_name")
val modVersion = prop("mod_version")
val modGroupId = prop("mod_group_id")
val modAuthors = prop("mod_authors")
val modDescription = prop("mod_description")

val minecraftVersionRange = prop("minecraft_version_range")
val neoforgeVersion = prop("neoforge_version")
val neoforgeLoaderVersionRange = prop("neoforge_loader_version_range")

group = modGroupId
version = modVersion
base.archivesName.set(modId)

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
    maven("https://maven.neoforged.net/releases")
    maven("https://maven.theillusivec4.top/")
    maven("https://maven.blamejared.com/")
    maven("https://www.cursemaven.com")
}

neoForge {
    version = neoforgeVersion

    // Uncomment to use Parchment human-readable parameter names:
    // parchment {
    //     mappingsVersion.set("2024.07.28")
    //     minecraftVersion.set("1.21.1")
    // }

    runs {
        create("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
        create("server") {
            server()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
            programArgument("--nogui")
        }
        configureEach {
            gameDirectory.set(project.file("run"))
        }
    }

    mods {
        create(modId) {
            sourceSet(sourceSets.main.get())
        }
    }
}

// Expand mod metadata into neoforge.mods.toml at build time.
tasks.named<ProcessResources>("processResources") {
    val replacements = mapOf(
        "modId" to modId,
        "modName" to modName,
        "modVersion" to modVersion,
        "modAuthors" to modAuthors,
        "modDescription" to modDescription,
        "minecraftVersionRange" to minecraftVersionRange,
        "neoforgeVersion" to neoforgeVersion,
        "neoforgeLoaderVersionRange" to neoforgeLoaderVersionRange,
    )
    inputs.properties(replacements)
    filesMatching("META-INF/neoforge.mods.toml") {
        expand(replacements)
    }
}

dependencies {
    // Curios API - modern accessory/trinket slot system replacing Baubles
    compileOnly("top.theillusivec4.curios:curios-neoforge:9.5.1+1.21.1")
    
    // JEI API
    compileOnly("mezz.jei:jei-1.21.1-neoforge-api:19.39.0.369")

    // TerraBlender API for Biome Spawning (via CurseMaven)
    "implementation"("curse.maven:terrablender-neoforge-940057:6054947")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(21)
}
