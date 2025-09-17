/**
 * 
 */

package com.badlogic.gdx.sqlite.desktop;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.badlogic.gdx.sqlite.DatabaseCursor;
import com.badlogic.gdx.sqlite.SQLiteGdxException;

/** @author cycloneqi */
public class PreparedStatement implements com.badlogic.gdx.sqlite.PreparedStatement {
	private java.sql.PreparedStatement statement;

	public PreparedStatement (java.sql.PreparedStatement preparedStatement) {
		this.statement = preparedStatement;
	}

	@Override
	public DatabaseCursor executeQuery () throws SQLiteGdxException {
		try {
			ResultSet _result = statement.executeQuery();
			DesktopCursor _cursor = new DesktopCursor();
			_cursor.setNativeCursor(_result);
			return _cursor;
		} catch (SQLException e) {
			throw new SQLiteGdxException("There is an error in executing the prepared statement", e);
		}
	}

	@Override
	public void execute () throws SQLiteGdxException {
		try {
			statement.execute();
		} catch (SQLException e) {
			throw new SQLiteGdxException("There is an error in executing the prepared statement", e);
		}
	}

	@Override
	public long executeInsert () throws SQLiteGdxException {
		try {
			statement.execute();
			return statement.getGeneratedKeys().getLong(1);
		} catch (SQLException e) {
			throw new SQLiteGdxException("There is an error in executing the prepared statement", e);
		}
	}

	@Override
	public int executeUpdateDelete () throws SQLiteGdxException {
		try {
			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLiteGdxException("There is an error in executing the prepared statement", e);
		}
	}

	@Override
	public void clearParameters () throws SQLiteGdxException {
		try {
			statement.clearParameters();
		} catch (SQLException e) {
			throw new SQLiteGdxException("There is an error in clearing parameters the prepared statement", e);
		}
	}

	@Override
	public void close () throws SQLiteGdxException {
		try {
			statement.close();
		} catch (SQLException e) {
			throw new SQLiteGdxException("Prepared statement not closed correctly", e);
		}
	}

	@Override
	public void setNull (int parameterIndex, int type) throws SQLiteGdxException {
		try {
			statement.setNull(parameterIndex, type);
		} catch (SQLException e) {
			throw new SQLiteGdxException("Can't set null value tostatement for parameter index : " + parameterIndex, e);
		}
	}

	@Override
	public void setInt (int parameterIndex, int value) throws SQLiteGdxException {
		try {
			statement.setInt(parameterIndex, value);
		} catch (SQLException e) {
			throw new SQLiteGdxException("Can't set int value to statement for parameter index : " + parameterIndex, e);
		}
	}

	@Override
	public void setLong (int parameterIndex, long value) throws SQLiteGdxException {
		try {
			statement.setLong(parameterIndex, value);
		} catch (SQLException e) {
			throw new SQLiteGdxException("Can't set long value to statement for parameter index : " + parameterIndex, e);
		}
	}

	@Override
	public void setFloat (int parameterIndex, float value) throws SQLiteGdxException {
		try {
			statement.setFloat(parameterIndex, value);
		} catch (SQLException e) {
			throw new SQLiteGdxException("Can't set float value to statement for parameter index : " + parameterIndex, e);
		}
	}

	@Override
	public void setDouble (int parameterIndex, double value) throws SQLiteGdxException {
		try {
			statement.setDouble(parameterIndex, value);
		} catch (SQLException e) {
			throw new SQLiteGdxException("Can't set double value to statement for parameter index : " + parameterIndex, e);
		}
	}

	@Override
	public void setString (int parameterIndex, String value) throws SQLiteGdxException {
		try {
			statement.setString(parameterIndex, value);
		} catch (SQLException e) {
			throw new SQLiteGdxException("Can't set string value to statement for parameter index : " + parameterIndex, e);
		}
	}

	@Override
	public void setBlob (int parameterIndex, Blob blob) throws SQLiteGdxException {
		try {
			statement.setBlob(parameterIndex, blob);
		} catch (SQLException e) {
			throw new SQLiteGdxException("Can't set blob to statement for parameter index : " + parameterIndex, e);
		}
	}

	@Override
	public void setBlob (int parameterIndex, InputStream stream) throws SQLiteGdxException {
		try {
			statement.setBlob(parameterIndex, stream);
		} catch (SQLException e) {
			throw new SQLiteGdxException("Can't set blob to statement for parameter index : " + parameterIndex, e);
		}
	}

	@Override
	public void setBytes (int parameterIndex, byte[] b) throws SQLiteGdxException {
		try {
			statement.setBytes(parameterIndex, b);
		} catch (SQLException e) {
			throw new SQLiteGdxException("Can't set bytes to statement for parameter index : " + parameterIndex, e);
		}
	}

	public void setStatement (java.sql.PreparedStatement statement) {
		this.statement = statement;
	}

	@Override
	public String toString () {
		return statement.toString();
	}
}
