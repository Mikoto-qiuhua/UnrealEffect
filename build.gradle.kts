plugins {
    id("java")
}

group = "org.qiuhua.unrealeffect"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://jitpack.io")
    maven {
        name = "spigotmc-repo"
        url = uri ("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }  //SpigotMC仓库
    mavenLocal()  //加载本地仓库
    mavenCentral()  //加载中央仓库
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("org.spigotmc:spigot-api:1.20.2-R0.1-SNAPSHOT")
    compileOnly(fileTree("src/libs"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}


tasks.withType<JavaCompile>{
    options.encoding = "UTF-8"
}

tasks.withType<Jar>().configureEach {
    archiveFileName.set("[U][虚幻机制]UnrealEffect-测试插件.jar")
    destinationDirectory.set(File ("E:\\Server-1.20.2-test\\plugins"))
}