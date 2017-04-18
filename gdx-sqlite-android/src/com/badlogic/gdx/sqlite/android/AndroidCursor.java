
package com.badlogic.gdx.sqlite.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sqlite.DatabaseCursor;
import com.badlogic.gdx.sqlite.DatabaseFactory;
import com.badlogic.gdx.sqlite.SQLiteGdxRuntimeException;

/** @author M Rafay Aleem */
public class AndroidCursor implements DatabaseCursor {

	private Cursor cursor = null;

	@Override
	public byte[] getBlob (int columnIndex) {
		try {
			return cursor.getBlob(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the blob", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public void reposition(int columnIndex) {

		try {
			cursor.moveToPosition(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error repositioning the cursor", e);
			throw new SQLiteGdxRuntimeException(e);
		}

	}

	@Override
	public double getDouble (int columnIndex) {
		try {
			return cursor.getDouble(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the double", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public float getFloat (int columnIndex) {
		try {
			return cursor.getFloat(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the float", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public int getInt (int columnIndex) {
		try {
			return cursor.getInt(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the int", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public long getLong (int columnIndex) {
		try {
			return cursor.getLong(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the long", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public short getShort (int columnIndex) {
		try {
			return cursor.getShort(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the short", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public String getString (int columnIndex) {
		try {
			return cursor.getString(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the string", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public boolean next () {
		try {
			return cursor.moveToNext();
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in moving the cursor to next", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public int getCount () {
		int count = -1;
		try {
			count = cursor.getCount();
			return count;
		} catch (SQLiteException e) {
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public void close () {
		try {
			cursor.close();
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in closing the cursor", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	public void setNativeCursor (Cursor cursorRef) {
		cursor = cursorRef;
	}

	@Override
	public byte[] getBlob (String columnName) {
		try {
			int columnIndex = cursor.getColumnIndex(columnName);
			return cursor.getBlob(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the blob", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public double getDouble (String columnName) {
		try {
			int columnIndex = cursor.getColumnIndex(columnName);
			return cursor.getDouble(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the double", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public float getFloat (String columnName) {
		try {
			int columnIndex = cursor.getColumnIndex(columnName);
			return cursor.getFloat(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the float", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public int getInt (String columnName) {
		try {
			int columnIndex = cursor.getColumnIndex(columnName);
			return cursor.getInt(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the int", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public long getLong (String columnName) {
		try {
			int columnIndex = cursor.getColumnIndex(columnName);
			return cursor.getLong(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the long", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public short getShort (String columnName) {
		try {
			int columnIndex = cursor.getColumnIndex(columnName);
			return cursor.getShort(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the short", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}

	@Override
	public String getString (String columnName) {
		try {
			int columnIndex = cursor.getColumnIndex(columnName);
			return cursor.getString(columnIndex);
		} catch (SQLiteException e) {
			Gdx.app.log(DatabaseFactory.ERROR_TAG, "There was an error in getting the string", e);
			throw new SQLiteGdxRuntimeException(e);
		}
	}
}
