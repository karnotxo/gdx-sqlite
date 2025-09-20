package com.badlogic.gdx.sqlite.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sqlite.DatabaseCursor;
import com.badlogic.gdx.sqlite.DatabaseFactory;
import com.badlogic.gdx.sqlite.SQLiteGdxRuntimeException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a Desktop implementation of the public interface {@link DatabaseCursor}. Note that
 * columns in JDBC are not zero-based and hence +1 has been added to accomodate for this difference.
 *
 * @author M Rafay Aleem
 */
public class DesktopCursor implements DatabaseCursor {

  // Buffered rows: each row is an Object[] with length equal to column count.
  private final List<Object[]> rows = new ArrayList<>();
  private final Map<String, Integer> columnNameToIndex = new HashMap<>();
  private int columnCount = 0;
  private int cursor = -1; // index into rows; -1 before first

  @Override
  public byte[] getBlob(int columnIndex) {
    Blob blob = (Blob) getCell(columnIndex);
    try {
      return blob.getBytes(1, (int) blob.length());
    } catch (SQLException e) {
      Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the blob", e);
      throw new SQLiteGdxRuntimeException(e);
    }
  }

  @Override
  public void reposition(int columnIndex) {

    if (columnIndex < 0 || columnIndex >= rows.size()) {
      throw new SQLiteGdxRuntimeException(
          new SQLException("Row index out of bounds: " + columnIndex + " size=" + rows.size()));
    }
    cursor = columnIndex;
  }

  @Override
  public double getDouble(int columnIndex) {
    return ((Number) getCell(columnIndex)).doubleValue();
  }

  @Override
  public float getFloat(int columnIndex) {
    return ((Number) getCell(columnIndex)).floatValue();
  }

  @Override
  public int getInt(int columnIndex) {
    return ((Number) getCell(columnIndex)).intValue();
  }

  @Override
  public long getLong(int columnIndex) {
    return ((Number) getCell(columnIndex)).longValue();
  }

  @Override
  public short getShort(int columnIndex) {
    return ((Number) getCell(columnIndex)).shortValue();
  }

  @Override
  public String getString(int columnIndex) {
    Object val = getCell(columnIndex);
    return val != null ? val.toString() : null;
  }

  @Override
  public boolean next() {
    if (cursor + 1 < rows.size()) {
      cursor++;
      return true;
    }
    return false;
  }

  @Override
  public int getCount() {
    return rows.size();
  }

  @Override
  public void close() {
    // nothing to do, data buffered eagerly
  }

  public void setNativeCursor(ResultSet resultSetRef) {
    rows.clear();
    columnNameToIndex.clear();
    cursor = -1;
    try {
      ResultSetMetaData meta = resultSetRef.getMetaData();
      columnCount = meta.getColumnCount();
      for (int i = 1; i <= columnCount; i++) {
        String label = meta.getColumnLabel(i);
        if (label == null || label.length() == 0) {
          label = meta.getColumnName(i);
        }
        columnNameToIndex.put(label.toLowerCase(), i - 1); // store lower-case for case-insensitive
      }
      while (resultSetRef.next()) {
        Object[] row = new Object[columnCount];
        for (int i = 1; i <= columnCount; i++) {
          row[i - 1] = resultSetRef.getObject(i);
        }
        rows.add(row);
      }
    } catch (SQLException e) {
      Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error buffering the results", e);
      throw new SQLiteGdxRuntimeException(e);
    }
  }

  private Object getCell(int zeroBasedColumn) {
    if (cursor < 0 || cursor >= rows.size()) {
      throw new SQLiteGdxRuntimeException(new SQLException("Cursor not on a valid row: " + cursor));
    }
    if (zeroBasedColumn < 0 || zeroBasedColumn >= columnCount) {
      throw new SQLiteGdxRuntimeException(
          new SQLException(
              "Column index out of bounds: " + zeroBasedColumn + " count=" + columnCount));
    }
    return rows.get(cursor)[zeroBasedColumn];
  }

  private int columnIndex(String columnName) {
    if (columnName == null) {
      throw new SQLiteGdxRuntimeException(new SQLException("Column name is null"));
    }
    Integer idx = columnNameToIndex.get(columnName.toLowerCase());
    if (idx == null) {
      throw new SQLiteGdxRuntimeException(
          new SQLException(
              "Column not found: " + columnName + " available=" + columnNameToIndex.keySet()));
    }
    return idx;
  }

  @Override
  public byte[] getBlob(String columnName) {
    Blob blob = (Blob) getCell(columnIndex(columnName));
    try {
      return blob.getBytes(1, (int) blob.length());
    } catch (SQLException e) {
      Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the blob", e);
      throw new SQLiteGdxRuntimeException(e);
    }
  }

  @Override
  public double getDouble(String columnName) {
    return ((Number) getCell(columnIndex(columnName))).doubleValue();
  }

  @Override
  public float getFloat(String columnName) {
    return ((Number) getCell(columnIndex(columnName))).floatValue();
  }

  @Override
  public int getInt(String columnName) {
    return ((Number) getCell(columnIndex(columnName))).intValue();
  }

  @Override
  public long getLong(String columnName) {
    return ((Number) getCell(columnIndex(columnName))).longValue();
  }

  @Override
  public short getShort(String columnName) {
    return ((Number) getCell(columnIndex(columnName))).shortValue();
  }

  @Override
  public String getString(String columnName) {
    Object val = getCell(columnIndex(columnName));
    return val != null ? val.toString() : null;
  }
}
