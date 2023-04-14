@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id("com.android.application") version libs.versions.androidGradlePlugin.get() apply false
    id("com.android.library") version libs.versions.androidGradlePlugin.get() apply false
    id("org.jetbrains.kotlin.android") version libs.versions.kotlin.get() apply false
    id("com.google.dagger.hilt.android") version libs.versions.hilt.get() apply false
}
