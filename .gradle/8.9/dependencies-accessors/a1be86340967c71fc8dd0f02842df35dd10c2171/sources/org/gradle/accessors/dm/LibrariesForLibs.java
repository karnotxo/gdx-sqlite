package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final AndroidxLibraryAccessors laccForAndroidxLibraryAccessors = new AndroidxLibraryAccessors(owner);
    private final LibgdxLibraryAccessors laccForLibgdxLibraryAccessors = new LibgdxLibraryAccessors(owner);
    private final RobovmLibraryAccessors laccForRobovmLibraryAccessors = new RobovmLibraryAccessors(owner);
    private final SqliteLibraryAccessors laccForSqliteLibraryAccessors = new SqliteLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Dependency provider for <b>junit4</b> with <b>junit:junit</b> coordinates and
     * with version reference <b>junit4</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getJunit4() {
        return create("junit4");
    }

    /**
     * Dependency provider for <b>lombok</b> with <b>org.projectlombok:lombok</b> coordinates and
     * with version reference <b>lombok</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getLombok() {
        return create("lombok");
    }

    /**
     * Group of libraries at <b>androidx</b>
     */
    public AndroidxLibraryAccessors getAndroidx() {
        return laccForAndroidxLibraryAccessors;
    }

    /**
     * Group of libraries at <b>libgdx</b>
     */
    public LibgdxLibraryAccessors getLibgdx() {
        return laccForLibgdxLibraryAccessors;
    }

    /**
     * Group of libraries at <b>robovm</b>
     */
    public RobovmLibraryAccessors getRobovm() {
        return laccForRobovmLibraryAccessors;
    }

    /**
     * Group of libraries at <b>sqlite</b>
     */
    public SqliteLibraryAccessors getSqlite() {
        return laccForSqliteLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class AndroidxLibraryAccessors extends SubDependencyFactory {

        public AndroidxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>appcompat</b> with <b>androidx.appcompat:appcompat</b> coordinates and
         * with version reference <b>androidxAppcompat</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAppcompat() {
            return create("androidx.appcompat");
        }

    }

    public static class LibgdxLibraryAccessors extends SubDependencyFactory {
        private final LibgdxBackendLibraryAccessors laccForLibgdxBackendLibraryAccessors = new LibgdxBackendLibraryAccessors(owner);

        public LibgdxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>com.badlogicgames.gdx:gdx</b> coordinates and
         * with version reference <b>libgdx</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("libgdx.core");
        }

        /**
         * Dependency provider for <b>platform</b> with <b>com.badlogicgames.gdx:gdx-platform</b> coordinates and
         * with version reference <b>libgdx</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPlatform() {
            return create("libgdx.platform");
        }

        /**
         * Group of libraries at <b>libgdx.backend</b>
         */
        public LibgdxBackendLibraryAccessors getBackend() {
            return laccForLibgdxBackendLibraryAccessors;
        }

    }

    public static class LibgdxBackendLibraryAccessors extends SubDependencyFactory {

        public LibgdxBackendLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>android</b> with <b>com.badlogicgames.gdx:gdx-backend-android</b> coordinates and
         * with version reference <b>libgdx</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAndroid() {
            return create("libgdx.backend.android");
        }

        /**
         * Dependency provider for <b>desktop</b> with <b>com.badlogicgames.gdx:gdx-backend-lwjgl</b> coordinates and
         * with version reference <b>libgdx</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getDesktop() {
            return create("libgdx.backend.desktop");
        }

        /**
         * Dependency provider for <b>robovm</b> with <b>com.badlogicgames.gdx:gdx-backend-robovm</b> coordinates and
         * with version reference <b>libgdx</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRobovm() {
            return create("libgdx.backend.robovm");
        }

    }

    public static class RobovmLibraryAccessors extends SubDependencyFactory {

        public RobovmLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>cocoatouch</b> with <b>com.mobidevelop.robovm:robovm-cocoatouch</b> coordinates and
         * with version reference <b>robovm</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCocoatouch() {
            return create("robovm.cocoatouch");
        }

        /**
         * Dependency provider for <b>rt</b> with <b>com.mobidevelop.robovm:robovm-rt</b> coordinates and
         * with version reference <b>robovm</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRt() {
            return create("robovm.rt");
        }

    }

    public static class SqliteLibraryAccessors extends SubDependencyFactory {

        public SqliteLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>jdbc</b> with <b>org.xerial:sqlite-jdbc</b> coordinates and
         * with version reference <b>sqliteJdbc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJdbc() {
            return create("sqlite.jdbc");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>androidGradle</b> with value <b>7.4.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidGradle() { return getVersion("androidGradle"); }

        /**
         * Version alias <b>androidxAppcompat</b> with value <b>1.7.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxAppcompat() { return getVersion("androidxAppcompat"); }

        /**
         * Version alias <b>junit4</b> with value <b>4.13.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getJunit4() { return getVersion("junit4"); }

        /**
         * Version alias <b>libgdx</b> with value <b>1.13.5</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getLibgdx() { return getVersion("libgdx"); }

        /**
         * Version alias <b>lombok</b> with value <b>1.18.34</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getLombok() { return getVersion("lombok"); }

        /**
         * Version alias <b>robovm</b> with value <b>2.3.23</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getRobovm() { return getVersion("robovm"); }

        /**
         * Version alias <b>sqliteJdbc</b> with value <b>3.50.3.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getSqliteJdbc() { return getVersion("sqliteJdbc"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>spotless</b> with plugin id <b>com.diffplug.spotless</b> and
         * with version <b>6.25.0</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getSpotless() { return createPlugin("spotless"); }

    }

}
