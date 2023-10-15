rootProject.name = "kboot-gradleservice"

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            name = "releases"
            url = uri("https://pkgs.dev.azure.com/helsana/_packaging/releases/maven/v1")
            credentials {
                username = System.getenv("AZURE_USERNAME")
                password = System.getenv("AZURE_PAT")
            }
        }
        maven {
            name = "myhelsanafeed"
            url = uri("https://helsana.pkgs.visualstudio.com/MYHELSANA/_packaging/myhelsanafeed/maven/v1")
            credentials {
                username = System.getenv("AZURE_USERNAME")
                password = System.getenv("AZURE_PAT")
            }
        }
        maven {
            name = "myhelsanafeed-snapshots"
            url = uri("https://helsana.pkgs.visualstudio.com/MYHELSANA/_packaging/myhelsanafeed-snapshots/maven/v1")
            credentials {
                username = System.getenv("AZURE_USERNAME")
                password = System.getenv("AZURE_PAT")
            }
        }
    }
}
