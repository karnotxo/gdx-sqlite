package com.badlogic.gdx.sqlite;

/**
 * Abstraction over a forward (and optionally random) access result set returned from a database
 * query.
 *
 * <p>Thread-safety: Implementations are <strong>not</strong> thread-safe. A cursor instance must
 * only be accessed from the thread that created it.
 *
 * <p>Extended by Angel Biedma to add lookup by column name.
 *
 * @author M Rafay Aleem
 */
public interface DatabaseCursor {

  /**
   * Returns the value of the requested column as a byte array.
   *
   * @param columnIndex the zero-based index of the target column.
   * @return the value of that column as a byte array.
   */
  byte[] getBlob(int columnIndex);

  /**
   * Returns the value of the requested column as a byte array.
   *
   * @param columnName name of column in database
   * @return the value of that column as a byte array.
   */
  byte[] getBlob(String columnName);

  /**
   * Returns the value of the requested column as a double.
   *
   * @param columnIndex the zero-based index of the target column.
   * @return the value of that column as a double.
   */
  double getDouble(int columnIndex);

  /**
   * Returns the value of the requested column as a double.
   *
   * @param columnName name of column in database
   * @return the value of that column as a double.
   */
  double getDouble(String columnName);

  /**
   * Returns the value of the requested column as a float.
   *
   * @param columnIndex the zero-based index of the target column.
   * @return the value of that column as a float.
   */
  float getFloat(int columnIndex);

  /**
   * Returns the value of the requested column as a float.
   *
   * @param columnName name of column in database
   * @return the value of that column as a float.
   */
  float getFloat(String columnName);

  /**
   * Returns the value of the requested column as a int.
   *
   * @param columnIndex the zero-based index of the target column.
   * @return the value of that column as a int.
   */
  int getInt(int columnIndex);

  /**
   * Returns the value of the requested column as a int.
   *
   * @param columnName name of column in database
   * @return the value of that column as a int.
   */
  int getInt(String columnName);

  /**
   * Returns the value of the requested column as a long.
   *
   * @param columnIndex the zero-based index of the target column.
   * @return the value of that column as a long.
   */
  long getLong(int columnIndex);

  /**
   * Returns the value of the requested column as a long.
   *
   * @param columnName name of column in database
   * @return the value of that column as a long.
   */
  long getLong(String columnName);

  /**
   * Returns the value of the requested column as a short.
   *
   * @param columnIndex the zero-based index of the target column.
   * @return the value of that column as a short.
   */
  short getShort(int columnIndex);

  /**
   * Returns the value of the requested column as a short.
   *
   * @param columnName name of column in database
   * @return the value of that column as a short.
   */
  short getShort(String columnName);

  /**
   * Returns the value of the requested column as a string.
   *
   * @param columnIndex the zero-based index of the target column.
   * @return the value of that column as a string.
   */
  String getString(int columnIndex);

  /**
   * Returns the value of the requested column as a string.
   *
   * @param columnName name of column in database
   * @return the value of that column as a string.
   */
  String getString(String columnName);

  /**
   * Move the cursor to the next row.
   *
   * @return whether the move was successful.
   */
  boolean next();

  /**
   * Returns the number of rows in the result set represented by this cursor.
   *
   * @return total row count
   */
  int getCount();

  /** Closes the Cursor, releasing all of its resources and making it completely invalid. */
  void close();

  /**
   * Repositions the cursor to the given absolute zero-based row index.
   *
   * <p>After calling this method you may call the typed getters (e.g. {@link #getInt(int)}) to read
   * column values for that row. Behavior is undefined for an index &lt; 0 or &gt;= {@link
   * #getCount()}.
   *
   * @param columnIndex zero-based row index to move to
   */
  void reposition(int columnIndex);
}
