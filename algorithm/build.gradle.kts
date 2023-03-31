plugins {
    kotlin("jvm")
}

dependencies {
    implementation(projects.appDomain)
    implementation(libs.coroutines.core)
    implementation(libs.kevinsternHungarianAlgorithm)

    testImplementation(projects.testUtils)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.junit)
}
