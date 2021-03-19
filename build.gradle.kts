import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	base
	id("org.springframework.boot") version "2.3.9.RELEASE"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.31" apply true
	kotlin("plugin.spring") version "1.4.31" apply true
	kotlin("plugin.allopen") version "1.4.31" apply true

    application
	java
}

group = "com.rpires.projects"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.mockk:mockk:1.10.6")
}

tasks {

	withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}

	withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
		archiveFileName.set("app.jar")
		mainClassName = "com.rpires.projects.base.TbdApplicationKt"
	}

    withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    	project.properties["profiles"]?.let { systemProperty("spring.profiles.active", it) }
	}

	withType<Test> {
		useJUnitPlatform()
	}
}
