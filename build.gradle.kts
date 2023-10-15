import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`maven-publish`
	alias(libs.plugins.kotlin.spring)
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependencymanager)
}

group = "ch.keepcalm.demo"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

dependencyManagement {
	imports {
		mavenBom(libs.springCloud.bom.get().toString())
	}
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["java"])
		}
	}
	repositories {
		mavenLocal()
		maven {
			name = "myhelsanafeed"
			url = uri("https://helsana.pkgs.visualstudio.com/MYHELSANA/_packaging/myhelsanafeed/maven/v1")
		}
		maven {
			name = "myhelsanafeed-snapshots"
			url = uri("https://pkgs.dev.azure.com/helsana/MYHELSANA/_packaging/myhelsanafeed-snapshots/maven/v1")
		}
	}
}

dependencies {
	// Spring
	implementation(libs.springBoot.starter.actuator)
	implementation(libs.springBoot.starter.hateoas){
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-web")
	}
	implementation(libs.springBoot.starter.security)
	implementation(libs.springBoot.starter.validation)
	implementation(libs.springBoot.starter.webflux)
	implementation(libs.springCloud.starter)

	// Others
	implementation(libs.githubMicroutils.kotlin.logging.jvm) {
		exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
	}

	implementation(libs.fasterxmlJackson.jackson.module.kotlin)
	implementation(libs.projectReactor.reactor.kotlin.extensions)
	implementation(libs.jetBrains.kotlin.reflect)
	implementation(libs.jetBrains.kotlinx.coroutines.reactor)

	// Test
	testImplementation(libs.springBoot.starter.test)
	testImplementation(libs.projectReactor.test)
	testImplementation(libs.restassured.spring.web.test.client)
	testImplementation(libs.springFramework.spring.security.test)

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
