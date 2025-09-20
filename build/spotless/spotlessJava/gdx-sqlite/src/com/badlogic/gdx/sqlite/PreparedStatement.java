/** */
package com.badlogic.gdx.sqlite;

import java.io.InputStream;
import java.sql.Blob;

/**
 * Represents a compiled SQL statement with optional bound parameters. Instances are created via
 * {@link Database#getPreparedStatement(String)} and must be explicitly {@link #close() closed} to
 * free underlying native resources when no longer needed.
 *
 * <p>Thread-safety: Not thread-safe. Use only from the thread that created it.
 *
 * <p>Bind parameters are 1-based (i.e. the first parameter placeholder {@code ?} has index {@code
 * 1}). After binding the required parameters invoke one of the execute methods depending on the
 * expected result. Typical usage:
 *
 * <pre>
 * PreparedStatement stmt = db.getPreparedStatement("INSERT INTO person(name, age) VALUES(?, ?)");
 * stmt.setString(1, name);
 * stmt.setInt(2, age);
 * long id = stmt.executeInsert();
 * stmt.close();
 * </pre>
 *
 * author cycloneqi
 */
public interface PreparedStatement {

  /**
   * Execute a {@code SELECT} statement and return a cursor over the result set.
   *
   * @return cursor positioned before the first row (call {@link DatabaseCursor#next()} to iterate)
   * @throws SQLiteGdxException if execution fails
   */
  DatabaseCursor executeQuery() throws SQLiteGdxException;

  /**
   * Execute a statement that does not produce a result set (e.g. DDL or an update where you do not
   * need the affected row count).
   *
   * @throws SQLiteGdxException if execution fails
   */
  void execute() throws SQLiteGdxException;

  /**
   * Execute an {@code INSERT} statement.
   *
   * @return row id of the newly inserted row or {@code -1} if not available
   * @throws SQLiteGdxException if execution fails
   */
  long executeInsert() throws SQLiteGdxException;

  /**
   * Execute an {@code UPDATE} or {@code DELETE} statement.
   *
   * @return number of rows affected
   * @throws SQLiteGdxException if execution fails
   */
  int executeUpdateDelete() throws SQLiteGdxException;

  /**
   * Clear all currently bound parameters so the statement can be reused.
   *
   * @throws SQLiteGdxException if clearing fails
   */
  void clearParameters() throws SQLiteGdxException;

  /**
   * Close this prepared statement and release associated resources. After calling this method
   * further use of the instance results in undefined behavior.
   *
   * @throws SQLiteGdxException if closing fails
   */
  void close() throws SQLiteGdxException;

  /**
   * Bind a NULL value to the specified parameter index.
   *
   * @param parameterIndex 1-based parameter index
   * @param type SQL type code (e.g. from {@link java.sql.Types})
   * @throws SQLiteGdxException if binding fails
   */
  void setNull(int parameterIndex, int type) throws SQLiteGdxException;

  /**
   * Bind an {@code int} value.
   *
   * @param parameterIndex 1-based parameter index
   * @param value value to bind
   * @throws SQLiteGdxException if binding fails
   */
  void setInt(int parameterIndex, int value) throws SQLiteGdxException;

  /**
   * Bind a {@code long} value.
   *
   * @param parameterIndex 1-based parameter index
   * @param value value to bind
   * @throws SQLiteGdxException if binding fails
   */
  void setLong(int parameterIndex, long value) throws SQLiteGdxException;

  /**
   * Bind a {@code float} value.
   *
   * @param parameterIndex 1-based parameter index
   * @param value value to bind
   * @throws SQLiteGdxException if binding fails
   */
  void setFloat(int parameterIndex, float value) throws SQLiteGdxException;

  /**
   * Bind a {@code double} value.
   *
   * @param parameterIndex 1-based parameter index
   * @param value value to bind
   * @throws SQLiteGdxException if binding fails
   */
  void setDouble(int parameterIndex, double value) throws SQLiteGdxException;

  /**
   * Bind a {@link String} value.
   *
   * @param parameterIndex 1-based parameter index
   * @param value value to bind (may be {@code null})
   * @throws SQLiteGdxException if binding fails
   */
  void setString(int parameterIndex, String value) throws SQLiteGdxException;

  /**
   * Bind a {@link Blob} value from a {@link java.sql.Blob} instance.
   *
   * @param parameterIndex 1-based parameter index
   * @param blob blob value to bind
   * @throws SQLiteGdxException if binding fails
   */
  void setBlob(int parameterIndex, Blob blob) throws SQLiteGdxException;

  /**
   * Bind a BLOB value from the current contents of the given {@link InputStream}. Implementations
   * may read the entire stream into memory.
   *
   * @param parameterIndex 1-based parameter index
   * @param stream source stream (caller retains ownership; may be consumed immediately)
   * @throws SQLiteGdxException if binding fails
   */
  void setBlob(int parameterIndex, InputStream stream) throws SQLiteGdxException;

  /**
   * Bind a BLOB value from the given byte array.
   *
   * @param parameterIndex 1-based parameter index
   * @param b data to bind (not copied necessarily; do not modify until after execution)
   * @throws SQLiteGdxException if binding fails
   */
  void setBytes(int parameterIndex, byte[] b) throws SQLiteGdxException;
}
