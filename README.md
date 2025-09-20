# gdx-sqlite
![CI](https://github.com/karnotxo/gdx-sqlite/actions/workflows/ci.yml/badge.svg)
Modernized fork of https://github.com/mrafayaleem/gdx-sqlite.
`gdx-sqlite` is a cross‑platform libGDX extension that provides a unified API for SQLite database access. Desktop uses the bundled SQLite JDBC driver; Android uses the platform SQLite API. (Historic RoboVM/iOS backend is present but not actively verified.)
Supported / maintained backends:
* Desktop (sqlite-jdbc)
* Android (platform API)
* Core (shared API)
* RoboVM (legacy – build may require environment setup)
This fork removes legacy Ant and Maven/JitPack setup in favor of a single Gradle build with automated CI & release artifacts.
## Getting the JARs (CI Artifacts)

On every release, GitHub Actions attaches three ready‑to‑use jars to the release page:

| Jar | Purpose |
|-----|---------|
| `gdx-sqlite.jar` | Core API (add to all platforms) |
| `gdx-sqlite-desktop.jar` | Desktop implementation (requires also `sqlite-jdbc` at runtime – already on classpath through this module) |
| `gdx-sqlite-android.jar` | Android implementation |
If you prefer to build locally:
```bash
./gradlew prepareReleaseJars
ls build/release
```
Without an Android SDK configured locally only the core & desktop jars are produced (CI builds all three).
### Integrating into your libGDX project

Gradle (Kotlin DSL example):
```kotlin
dependencies {
	implementation(files("libs/gdx-sqlite.jar"))          // core shared
	// Desktop project:
	implementation(files("libs/gdx-sqlite-desktop.jar"))  // includes sqlite-jdbc
	// Android project:
	implementation(files("libs/gdx-sqlite-android.jar"))
}

RoboVM (optional, legacy): add the RoboVM backend classes and ensure you link the implementation class if using tree‑shaking.
## Changes vs Original
* Added column-name getters on `DatabaseCursor`.
* Updated dependencies (libGDX 1.13.5, sqlite-jdbc 3.50.3.0, AndroidX, RoboVM 2.3.23).
* Removed Ant & Maven builds; single Gradle build.
* Added CI workflow producing release artifacts.
* Added sources & javadoc jars for core/desktop/robovm.
* Modernized Gradle configurations (`implementation`, Java 8 baseline).
## Building From Source
Minimum steps:
```bash
git clone https://github.com/karnotxo/gdx-sqlite.git
cd gdx-sqlite
./gradlew prepareReleaseJars
```
Artifacts: `build/release/`.
Individual module build:
```bash
./gradlew :gdx-sqlite:build :gdx-sqlite-desktop:build
```

Sources & Javadoc jars (automatically attached to `assemble` for Java modules):
```bash
./gradlew :gdx-sqlite:sourcesJar :gdx-sqlite:javadocJar
```

Android jar locally (requires `local.properties` or ANDROID_HOME):
```bash
./gradlew :gdx-sqlite-android:androidJar
```

Release aggregation (skips android if SDK absent):
```bash
./gradlew prepareReleaseJars
```
Git tagging helpers:
```bash
./gradlew createGitTag   # creates v<version> (checks clean tree)
./gradlew pushGitTag     # pushes that tag
./gradlew tagRelease     # both steps
```
Set the version in root `build.gradle` before tagging.
## Continuous Integration & Releases
GitHub Actions workflow (`.github/workflows/ci.yml`) runs on pushes & PRs and publishes jars on release events:
1. Installs JDK 17 and Android SDK (for release event).
2. Builds all modules and runs `prepareReleaseJars`.
3. Uploads `gdx-sqlite.jar`, `gdx-sqlite-desktop.jar`, `gdx-sqlite-android.jar` (if Android SDK) to the GitHub Release.
## Versioning
Project version is defined in the root `build.gradle`. Update it, commit, then:
```bash
./gradlew tagRelease
```
Create a GitHub Release using the generated tag to trigger asset upload.

## Consuming via Maven (Local / Custom Repo)

Local development consumption (after running `publishToMavenLocal`):
```xml
<dependency>
	<groupId>io.github.karnotxo</groupId>
	<artifactId>gdx-sqlite</artifactId>
	<version>1.1.0</version>
</dependency>
<dependency>
	<groupId>io.github.karnotxo</groupId>
	<artifactId>gdx-sqlite-desktop</artifactId>
	<version>1.1.0</version>
</dependency>
```
Gradle (Kotlin DSL):
```kotlin
dependencies {
		implementation("io.github.karnotxo:gdx-sqlite:1.1.0")
		implementation("io.github.karnotxo:gdx-sqlite-desktop:1.1.0")
}
```
Android artifact is not published automatically unless Android SDK present; RoboVM publication currently disabled.
To publish to a custom Maven repo:
```bash
./gradlew -PpublishUrl=https://your.repo.url/repository/releases \
					-PpublishUsername=USER -PpublishPassword=PASS publish
```
Signing / Maven Central: not yet wired (next steps would add `signing` plugin & OSSRH credentials).
## Dependency Versions (current)
| Component | Version |
|-----------|---------|
| libGDX | 1.13.5 |
| sqlite-jdbc | 3.50.3.0 |
| RoboVM plugin | 2.3.23 |
| AndroidX AppCompat | 1.7.0 |
| Java baseline | 8 |
## Roadmap / Ideas
* Optional publication to Maven Central (needs groupId coordination).
* Kotlin multiplatform wrapper (experimental).
* Expand test coverage (current samples are manual integration tests).
* Verify / modernize RoboVM backend or remove if unmaintained.
## License
Apache License 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)

Original author: Mohammad Rafay Aleem. Fork improvements © contributors.
# gdx-sqlite  
![CI](https://github.com/karnotxo/gdx-sqlite/actions/workflows/ci.yml/badge.svg)

This is a fork of https://github.com/mrafayaleem/gdx-sqlite project.

gdx-sqlite is a cross-platform Libgdx extension for SQLite database handling. The extension abstracts databse handling to provide a unified method to handle database transacitons across multiple platforms while also adding SQLite support for desktop version of Libgdx application.

Currently supported platforms:
- Android (Implemented using Android SQLite API)
- Desktop (SQLite JDBC from https://bitbucket.org/xerial/sqlite-jdbc/wiki/Home)

A small portion of code has been adapted from the tutorial located at:  http://www.vogella.com/articles/AndroidSQLite/article.html 

Latest build of this extension can be downloaded from: http://bit.ly/gdx-sqlite

## Extension setup in a Libgdx application:

Note: This setup assumes that you have properly setup your project as follows (or similar to the following):
 - App
 - AppDesktop
 - AppAndroid

#### For App project:
 - Copy gdx-sqlite.jar into your App project libs folder
 - In the Libraries tab of your Java Build Path, add the gdx-sqlite.jar

#### For AppDesktop project:
 - Copy gdx-sqlite-desktop.jar and sqlite-jdbc-3.7.2.jar into your AppDesktop project libs folder
 - In the Libraries tab of your Java Build Path, add the gdx-sqlite-desktop.jar and sqlite-jdbc-3.7.2.jar

#### For AppAndroid project:
 - Copy gdx-sqlite-android.jar into your AppAndroid project libs folder
 - In the Libraries tab of your Java Build Path, add the gdx-sqlite-android.jar and gdx-sqlite.jar
 - In the Order and Export tab of your Java Build Path, make sure that gdx-sqlite-android.jar and gdx-sqlite.jar are checked

#### For AppRoboVM project:
- Add following lines to robovm.xml
```
<forceLinkClasses>
	<pattern>com.badlogic.gdx.sqlite.robovm.RobovmDatabaseManager</pattern>
	<pattern>SQLite.**</pattern>
```

## Extensions done to original project
- com.badlogic.gdx.sql.DatabaseCursor interface now has methods to get a value from database given a column name.
- Updated DatabaseCursor implementations in Desktop, Android and Robovm implementations.

## Example Code:
```java
package com.mrafayaleem.gdxsqlitetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

public class DatabaseTest {

	Database dbHandler;

	public static final String TABLE_COMMENTS = "comments";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_COMMENT = "comment";

	private static final String DATABASE_NAME = "comments.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table if not exists "
			+ TABLE_COMMENTS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_COMMENT
			+ " text not null);";

	public DatabaseTest() {
		Gdx.app.log("DatabaseTest", "creation started");
		dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME,
				DATABASE_VERSION, DATABASE_CREATE, null);

		dbHandler.setupDatabase();
		try {
			dbHandler.openOrCreateDatabase();
			dbHandler.execSQL(DATABASE_CREATE);
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}

		Gdx.app.log("DatabaseTest", "created successfully");

		try {
			dbHandler
					.execSQL("INSERT INTO comments ('comment') VALUES ('This is a test comment')");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}

		DatabaseCursor cursor = null;

		try {
			cursor = dbHandler.rawQuery("SELECT * FROM comments");
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		while (cursor.next()) {
			Gdx.app.log("FromDb", String.valueOf(cursor.getString(1)));
		}

		try {
			dbHandler.closeDatabase();
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
		dbHandler = null;
		Gdx.app.log("DatabaseTest", "dispose");
	}
}
```

## Compiling the Code:
The repository contains all the necessary libraries and files to generate jar files. Three test projects are also added which depend on the respective gdx-sqlite projects to run. To generate jar files from the source code, clone the repository and execute ```ant clean build``` command from the root directory. This will generate jars in their respective folders (in jar directory of each gdx-sqlite project). Note that the gdx-sqlite projects are independent of the test projects.

## License:
This extension follows the Apache License version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)

See License FAQ http://www.apache.org/foundation/licence-FAQ.html for more details.

## Reporting Bugs:
Please email any bugs or feature requests at: mrafayaleem at gmail.com

## Author:
Mohammad Rafay Aleem

---

## Maven / JitPack Usage (Modernized Build)

The project now provides a Maven multi-module build (core, desktop, robovm) to allow consumption through JitPack. The historical instructions above (manual JAR copying, Ant build) are retained for legacy reference.

Android support currently still uses the original Gradle setup and is not yet migrated to Maven (future work: evaluate android-maven-plugin, or keep Gradle for Android only).

### 1. Add JitPack repository

Maven:
```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

Gradle (Kotlin DSL):
```kotlin
repositories { maven("https://jitpack.io") }
```

### 2. Add Dependencies

Choose a released git tag (recommended) or a commit hash you trust after the Maven conversion is merged.

Maven:
```xml
<dependency>
	<groupId>com.github.karnotxo</groupId>
	<artifactId>gdx-sqlite-core</artifactId>
	<version>TAG_OR_COMMIT</version>
</dependency>
<dependency>
	<groupId>com.github.karnotxo</groupId>
	<artifactId>gdx-sqlite-desktop</artifactId>
	<version>TAG_OR_COMMIT</version>
</dependency>
<dependency>
	<groupId>com.github.karnotxo</groupId>
	<artifactId>gdx-sqlite-robovm</artifactId>
	<version>TAG_OR_COMMIT</version>
</dependency>
```

Gradle (Kotlin DSL):
```kotlin
dependencies {
		implementation("com.github.karnotxo:gdx-sqlite-core:TAG_OR_COMMIT")
		implementation("com.github.karnotxo:gdx-sqlite-desktop:TAG_OR_COMMIT") // desktop (sqlite-jdbc)
		implementation("com.github.karnotxo:gdx-sqlite-robovm:TAG_OR_COMMIT")  // iOS (RoboVM)
}
```

### 3. Modules Overview

| Module | Purpose |
|--------|---------|
| gdx-sqlite-core | Platform-neutral API & shared code |
| gdx-sqlite-desktop | Desktop implementation via sqlite-jdbc |
| gdx-sqlite-robovm | iOS RoboVM implementation |
| gdx-sqlite-android (Gradle) | Android implementation (pending migration) |
| gdx-sqlite-android (Maven profile) | Experimental Maven AAR (activate with -Pandroid) |

### Current Build (Gradle Only)

Run desktop & core compilation:
```bash
./gradlew :gdx-sqlite-desktop:build
```

Android build (may require aligning code to newer SDK / AGP constraints):
```bash
./gradlew :gdx-sqlite-android:assembleRelease
```

Key versions:
- libGDX: 1.13.5
- sqlite-jdbc: 3.50.3.0 (desktop)
- RoboVM plugin: 2.3.23
- AndroidX AppCompat: 1.7.0

Notes:
- Source compatibility raised to Java 8 across modules.
- Repositories migrated from jcenter() to mavenCentral() / google().
- Maven/JitPack integration removed per project decision; previous release tag history retained in Git.
Legacy build (Ant / manual JAR copy) is considered deprecated in favor of dependency management via Maven/Gradle + JitPack.
