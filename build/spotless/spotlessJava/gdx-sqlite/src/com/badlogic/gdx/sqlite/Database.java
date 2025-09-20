package com.badlogic.gdx.sqlite;

/**
 * Contract for setting up, opening, querying and closing a SQLite database instance. The factory
 * method {@link DatabaseFactory#getNewDatabase(String, int, String, String)} returns an
 * implementation appropriate for the current platform.
 *
 * <p>Thread-safety: Implementations are generally <strong>not</strong> thread-safe; callers should
 * perform all operations from a single thread unless the specific backend states otherwise. Typical
 * call sequence:
 *
 * <ul>
 *   <li>{@link Database#setupDatabase()}
 *   <li>{@link Database#openOrCreateDatabase()}
 *   <li>{@link Database#execSQL(String)} OR
 *   <li>{@link Database#rawQuery(String)} OR
 *   <li>{@link Database#rawQuery(com.badlogic.gdx.sqlite.DatabaseCursor, String)}
 *   <li>{@link Database#closeDatabase()}
 * </ul>
 *
 * @author M Rafay Aleem
 */
public interface Database {

  /**
   * This method is needed to be called only once before any database related activity can be
   * performed. The method performs the necessary procedures for the database. However, a database
   * will not be opened/created until {@link Database#openOrCreateDatabase()} is called.
   */
  void setupDatabase();

  /**
   * Opens an already existing database or creates a new database if it doesn't already exist.
   *
   * @throws com.badlogic.gdx.sqlite.SQLiteGdxException
   */
  void openOrCreateDatabase() throws com.badlogic.gdx.sqlite.SQLiteGdxException;

  /**
   * Closes the opened database and releases all the resources related to this database.
   *
   * @throws com.badlogic.gdx.sqlite.SQLiteGdxException
   */
  void closeDatabase() throws com.badlogic.gdx.sqlite.SQLiteGdxException;

  /**
   * Obtain a new {@link com.badlogic.gdx.sqlite.PreparedStatement} for the given SQL query.
   *
   * <p>The returned prepared statement must be closed by the caller when no longer needed to free
   * native resources. Use the various {@code setXxx(...)} methods on the resulting {@link
   * com.badlogic.gdx.sqlite.PreparedStatement} to bind parameters before execution.
   *
   * @param query SQL statement to compile (may contain {@code ?} placeholders for bound parameters)
   * @return a compiled prepared statement ready for parameter binding and execution
   * @throws com.badlogic.gdx.sqlite.SQLiteGdxException if the statement cannot be compiled (syntax
   *     error, database closed, etc.)
   */
  com.badlogic.gdx.sqlite.PreparedStatement getPreparedStatement(String query)
      throws com.badlogic.gdx.sqlite.SQLiteGdxException;

  /**
   * Begin a database transaction. Nested transactions are not supported; attempting to begin a
   * second transaction before ending the first may result in an exception. After finishing the
   * work, call {@link #setTransactionSuccessful()} (optional) and then {@link #endTransaction()} to
   * end the transaction.
   *
   * @throws com.badlogic.gdx.sqlite.SQLiteGdxException if a transaction is already active or the
   *     begin fails
   */
  void beginTransaction() throws com.badlogic.gdx.sqlite.SQLiteGdxException;

  /**
   * Marks the current transaction as successful. This must be called before {@link
   * #endTransaction()} to commit the changes; otherwise the transaction will be rolled back.
   * Calling this method outside an active transaction has no effect or may throw an exception
   * depending on the backend.
   *
   * @throws com.badlogic.gdx.sqlite.SQLiteGdxException if there is no active transaction
   */
  void setTransactionSuccessful() throws com.badlogic.gdx.sqlite.SQLiteGdxException;

  /**
   * End the current transaction. If {@link #setTransactionSuccessful()} was called, the transaction
   * is committed; otherwise it is rolled back.
   *
   * @throws com.badlogic.gdx.sqlite.SQLiteGdxException if ending the transaction fails or if no
   *     transaction is active
   */
  void endTransaction() throws com.badlogic.gdx.sqlite.SQLiteGdxException;

  /**
   * Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns
   * data.
   *
   * @param sql the SQL statement to be executed. Multiple statements separated by semicolons are
   *     not supported.
   * @throws com.badlogic.gdx.sqlite.SQLiteGdxException
   */
  void execSQL(String sql) throws com.badlogic.gdx.sqlite.SQLiteGdxException;

  /**
   * Runs the provided SQL and returns a {@link com.badlogic.gdx.sqlite.DatabaseCursor} over the
   * result set.
   *
   * @param sql the SQL query. The SQL string must not be ; terminated
   * @return {@link com.badlogic.gdx.sqlite.DatabaseCursor}
   * @throws com.badlogic.gdx.sqlite.SQLiteGdxException
   */
  com.badlogic.gdx.sqlite.DatabaseCursor rawQuery(String sql)
      throws com.badlogic.gdx.sqlite.SQLiteGdxException;

  /**
   * Runs the provided SQL and returns the same {@link com.badlogic.gdx.sqlite.DatabaseCursor} that
   * was passed to this method. Use this method when you want to avoid reallocation of {@link
   * com.badlogic.gdx.sqlite.DatabaseCursor} object. Note that you shall only pass the {@link
   * com.badlogic.gdx.sqlite.DatabaseCursor} object that was previously returned by a rawQuery
   * method. Creating your own {@link com.badlogic.gdx.sqlite.DatabaseCursor} and then passing it as
   * an object will not work.
   *
   * @param cursor existing {@link com.badlogic.gdx.sqlite.DatabaseCursor} object
   * @param sql the SQL query. The SQL string must not be ; terminated
   * @return the passed {@link com.badlogic.gdx.sqlite.DatabaseCursor}.
   * @throws com.badlogic.gdx.sqlite.SQLiteGdxException
   */
  com.badlogic.gdx.sqlite.DatabaseCursor rawQuery(
      com.badlogic.gdx.sqlite.DatabaseCursor cursor, String sql)
      throws com.badlogic.gdx.sqlite.SQLiteGdxException;

  /**
   * Return the row id of the last successful {@code INSERT} operation executed on this database
   * connection.
   *
   * @return the last generated row id, or {@code -1} if no insert has been performed in this
   *     session
   * @throws com.badlogic.gdx.sqlite.SQLiteGdxException if the value cannot be retrieved
   */
  long getLastRowId() throws com.badlogic.gdx.sqlite.SQLiteGdxException;
}
