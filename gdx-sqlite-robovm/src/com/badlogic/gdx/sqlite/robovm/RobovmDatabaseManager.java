package com.badlogic.gdx.sqlite.robovm;

import com.badlogic.gdx.sqlite.Database;
import com.badlogic.gdx.sqlite.DatabaseManager;

/**
 * RoboVM implementation of {@link DatabaseManager} creating platform databases.
 * <p>Thread-safety: returned {@link Database} instances are not thread-safe.
 * Author: truongps
 */
public class RobovmDatabaseManager implements DatabaseManager {

    @Override
    public Database getNewDatabase(
            String dbName, int dbVersion, String dbOnCreateQuery, String dbOnUpgradeQuery) {
        return new RobovmDatabase(dbName, dbVersion, dbOnCreateQuery, dbOnUpgradeQuery);
    }
}
