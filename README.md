# obj

# gradle
```cmd
git submodule add https://github.com/harleylizard/obj obj
```
```kts
// within settings.gradle.kts
include(":obj")

// within build.gradle.kts
dependencies { 
	implementation(project(":obj"))
}
```
