# Copilot Instructions

Be concise. Observe existing style choices.

## Build & Versions
- Gradle project with version catalog at `gradle/libs.versions.toml`.
- All dependency versions must come from the catalog (no hardcoded version strings in scripts or sources).
- Android compile/target SDK kept via root `android_compile_version` variable (not in catalog).
- No Maven publication in this repo (publishing logic removed). Do not reintroduce POMs.

## Code Style & Quality
- Use Java 8 source/target.
- Run `./gradlew format` (Spotless) before committing; formatting applies to core + platform modules (samples excluded).
- `./gradlew codeQuality` runs Checkstyle (warnings only). Don't “fix” style by disabling rules unless explicitly requested.
- Avoid adding license headers; current Spotless config does not enforce them.

## Libraries & Patterns
- Lombok is available (compileOnly + annotationProcessor). Prefer Lombok for boilerplate (getters/setters) only when it improves clarity.
- libGDX backends: desktop (LWJGL2), android, robovm. Keep API surface stable; avoid backend-specific code in core.
- SQLite abstraction lives in `gdx-sqlite` (interfaces) with per-platform implementations in backend modules.

## Do / Don't
**Do**
- Use catalog aliases: `libs.libgdx.core`, `libs.sqlite.jdbc`, etc.
- Keep interfaces minimal; prefer adding default methods over breaking changes.
- Add Javadoc for new public APIs (doclint is relaxed but we aim for completeness).

**Don’t**
- Don’t commit new `pom.xml` files.
- Don’t bump toolchain beyond Java 8 without approval.
- Don’t introduce breaking API changes or change package names.

## Tasks Summary
- Format: `./gradlew format`
- Check style: `./gradlew codeQuality`
- Build core & desktop: `./gradlew :gdx-sqlite:build :gdx-sqlite-desktop:build`

Keep PRs small, focused, and with a brief rationale in the description.
