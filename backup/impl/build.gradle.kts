import com.ivy.buildsrc.Hilt
import com.ivy.buildsrc.Testing

apply<com.ivy.buildsrc.IvyPlugin>()

plugins {
    `android-library`
    `kotlin-android`
}

dependencies {
    Hilt()
    implementation(project(":common:main"))
    implementation(project(":core:domain"))
    implementation(project(":core:data-model"))
    implementation(project(":core:persistence"))
    api(project(":backup:base"))
    implementation(project(":android:file-system"))
    implementation(project(":drive:google-drive"))
    Testing()
}