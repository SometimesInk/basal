plugins {
  `application`
}

repositories {
  mavenCentral()
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(25)
  }
}

dependencies {
  implementation("com.google.code.gson:gson:2.14.0")
}

application {
  mainClass = "ink.basal.Main"
}

tasks.named<JavaExec>("run") {
  standardInput = System.`in`
}

version = "0.0.1"
