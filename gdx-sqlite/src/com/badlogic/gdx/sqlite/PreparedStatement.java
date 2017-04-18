/**
 * 
 */

package com.badlogic.gdx.sqlite;

import java.io.InputStream;
import java.sql.Blob;

/** @author cycloneqi */
public interface PreparedStatement {

	public DatabaseCursor executeQuery () throws SQLiteGdxException;

	public void execute () throws SQLiteGdxException;

	public long executeInsert () throws SQLiteGdxException;

	public int executeUpdateDelete () throws SQLiteGdxException;

	public void clearParameters () throws SQLiteGdxException;

	public void close () throws SQLiteGdxException;

	public void setNull (int parameterIndex, int type) throws SQLiteGdxException;

	public void setInt (int parameterIndex, int value) throws SQLiteGdxException;

	public void setLong (int parameterIndex, long value) throws SQLiteGdxException;

	public void setFloat (int parameterIndex, float value) throws SQLiteGdxException;

	public void setDouble (int parameterIndex, double value) throws SQLiteGdxException;

	public void setString (int parameterIndex, String value) throws SQLiteGdxException;

	public void setBlob (int parameterIndex, Blob blob) throws SQLiteGdxException;

	public void setBlob (int parameterIndex, InputStream stream) throws SQLiteGdxException;

	public void setBytes (int parameterIndex, byte[] b) throws SQLiteGdxException;

}
