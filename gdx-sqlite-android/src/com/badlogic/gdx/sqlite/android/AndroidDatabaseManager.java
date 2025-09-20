package com.badlogic.gdx.sqlite.android;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.sqlite.Database;
import com.badlogic.gdx.sqlite.DatabaseCursor;
import com.badlogic.gdx.sqlite.DatabaseManager;
import com.badlogic.gdx.sqlite.PreparedStatement;
import com.badlogic.gdx.sqlite.SQLiteGdxException;

/**
 * @author M Rafay Aleem
 */
public class AndroidDatabaseManager implements DatabaseManager {

  private Context context;

  private class AndroidDatabase implements Database {

    private SQLiteDatabaseHelper helper;
    private SQLiteDatabase database;
    private Context context;

    private final String dbName;
    private final int dbVersion;
    private final String dbOnCreateQuery;
    private final String dbOnUpgradeQuery;

    private AndroidDatabase(
        Context context,
        String dbName,
        int dbVersion,
        String dbOnCreateQuery,
        String dbOnUpgradeQuery) {
      this.context = context;
      this.dbName = dbName;
      this.dbVersion = dbVersion;
      this.dbOnCreateQuery = dbOnCreateQuery;
      this.dbOnUpgradeQuery = dbOnUpgradeQuery;
    }

    @Override
    public void setupDatabase() {
      helper =
          new SQLiteDatabaseHelper(
              this.context, dbName, null, dbVersion, dbOnCreateQuery, dbOnUpgradeQuery);
    }

    @Override
    public void openOrCreateDatabase() throws SQLiteGdxException {
      try {
        database = helper.getWritableDatabase();
      } catch (SQLiteException e) {
        throw new SQLiteGdxException(e);
      }
    }

    @Override
    public void closeDatabase() throws SQLiteGdxException {
      try {
        helper.close();
      } catch (SQLiteException e) {
        throw new SQLiteGdxException(e);
      }
    }

    @Override
    public void execSQL(String sql) throws SQLiteGdxException {
      try {
        database.execSQL(sql);
      } catch (SQLException e) {
        throw new SQLiteGdxException(e);
      }
    }

    @Override
    public DatabaseCursor rawQuery(String sql) throws SQLiteGdxException {
      AndroidCursor aCursor = new AndroidCursor();
      try {
        Cursor tmp = database.rawQuery(sql, null);
        aCursor.setNativeCursor(tmp);
        return aCursor;
      } catch (SQLiteException e) {
        throw new SQLiteGdxException(e);
      }
    }

    @Override
    public DatabaseCursor rawQuery(DatabaseCursor cursor, String sql) throws SQLiteGdxException {
      AndroidCursor aCursor = (AndroidCursor) cursor;
      try {
        Cursor tmp = database.rawQuery(sql, null);
        aCursor.setNativeCursor(tmp);
        return aCursor;
      } catch (SQLiteException e) {
        throw new SQLiteGdxException(e);
      }
    }

    @Override
    public void beginTransaction() {
      database.beginTransaction();
    }

    @Override
    public void setTransactionSuccessful() {
      database.setTransactionSuccessful();
    }

    @Override
    public void endTransaction() {
      database.endTransaction();
    }

    @Override
    public long getLastRowId() throws SQLiteGdxException {
      try {
        return rawQuery("SELECT last_insert_rowid();").getLong(0);
      } catch (SQLiteException e) {
        throw new SQLiteGdxException("There was an error in getting the last generated id", e);
      }
    }

    @Override
    public PreparedStatement getPreparedStatement(String sql) throws SQLiteGdxException {
      //			return new PreparedStatement(sql, this);
      return null;
    }

    /**
     * @return the database
     */
    public SQLiteDatabase getDatabase() {
      return database;
    }
  }

  public AndroidDatabaseManager() {
    AndroidApplication app = (AndroidApplication) Gdx.app;
    context = app.getApplicationContext();
  }

  @Override
  public Database getNewDatabase(
      String databaseName,
      int databaseVersion,
      String databaseCreateQuery,
      String dbOnUpgradeQuery) {
    return new AndroidDatabase(
        this.context, databaseName, databaseVersion, databaseCreateQuery, dbOnUpgradeQuery);
  }
}
