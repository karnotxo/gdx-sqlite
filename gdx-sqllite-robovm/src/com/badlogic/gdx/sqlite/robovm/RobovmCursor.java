package com.badlogic.gdx.sqlite.robovm;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.badlogic.gdx.sql.DatabaseCursor;
/**
 * 
 * @author truongps
 *
 */
public class RobovmCursor implements DatabaseCursor {

	ResultSet nativeCursor;

	public RobovmCursor(ResultSet resultSet) {
		setNativeCursor(resultSet);
	}

	@Override
	public byte[] getBlob(int columnIndex) {
		// return nativeCursor.getBlob(columnName);
		return null;
	}

	@Override
	public double getDouble(int columnIndex) {
		try {
			return nativeCursor.getDouble(columnIndex + 1);
		} catch (SQLException e) {
			return -1;
		}
	}

	@Override
	public float getFloat(int columnIndex) {
		try {
			return nativeCursor.getFloat(columnIndex + 1);
		} catch (SQLException e) {
			return -1;
		}
	}

	@Override
	public int getInt(int columnIndex) {
		try {
			return nativeCursor.getInt(columnIndex + 1);
		} catch (SQLException e) {
			return -1;
		}
	}

	@Override
	public long getLong(int columnIndex) {
		try {
			return nativeCursor.getLong(columnIndex + 1);
		} catch (SQLException e) {
			return -1;
		}
	}

	@Override
	public short getShort(int columnIndex) {
		try {
			return nativeCursor.getShort(columnIndex + 1);
		} catch (SQLException e) {
			return -1;
		}
	}

	@Override
	public String getString(int columnIndex) {
		try {
			return nativeCursor.getString(columnIndex + 1);
		} catch (SQLException e) {
			return "";
		}
	}

	@Override
	public boolean next() {
		try {
			return nativeCursor.next();
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public int getCount() {
		try {
			return nativeCursor.getRow();
		} catch (SQLException e) {
			return 0;
		}
	}

	@Override
	public void close() {
		try {
			nativeCursor.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setNativeCursor(ResultSet resultSet) {
		this.nativeCursor = resultSet;
	}

	@Override
	public byte[] getBlob(String columnName) {
		byte[] result = null;
		try {
			Blob blob = nativeCursor.getBlob(columnName);
			result = blob.getBytes(0L, (int) blob.length());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public double getDouble(String columnName) {
		try {
			return nativeCursor.getDouble(columnName);
		} catch (SQLException e) {
			return -1;
		}
	}

	@Override
	public float getFloat(String columnName) {
		try {
			return nativeCursor.getFloat(columnName);
		} catch (SQLException e) {
			return -1;
		}
	}

	@Override
	public int getInt(String columnName) {
		try {
			return nativeCursor.getInt(columnName);
		} catch (SQLException e) {
			return -1;
		}
	}

	@Override
	public long getLong(String columnName) {
		try {
			return nativeCursor.getLong(columnName);
		} catch (SQLException e) {
			return -1;
		}
	}

	@Override
	public short getShort(String columnName) {
		try {
			return nativeCursor.getShort(columnName);
		} catch (SQLException e) {
			return -1;
		}
	}

	@Override
	public String getString(String columnName) {
		try {
			return nativeCursor.getString(columnName);
		} catch (SQLException e) {
			return "";
		}
	}

}
