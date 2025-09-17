package com.badlogic.gdx.sqlite.robovm;

import com.badlogic.gdx.sqlite.Database;
import com.badlogic.gdx.sqlite.DatabaseManager;


/**
 * 
 * @author truongps
 * 
 */
public class RobovmDatabaseManager implements DatabaseManager {

	@Override
	public Database getNewDatabase(String dbName, int dbVersion,
			String dbOnCreateQuery, String dbOnUpgradeQuery) {
		return new RobovmDatabase(dbName, dbVersion, dbOnCreateQuery,
				dbOnUpgradeQuery);
	}
}
