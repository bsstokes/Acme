plugins {
    kotlin("jvm")
}

dependencies {
    implementation(libs.coroutines.core)
    testImplementation(projects.testUtils)
    testImplementation(libs.junit)
}
