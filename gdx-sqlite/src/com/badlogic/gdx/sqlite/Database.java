
package com.badlogic.gdx.sqlite;


/** This public interface contains the necessary methods to setup and execute queries on a database. The factory method
 * {@link DatabaseFactory#getNewDatabase(String, int, String, String)} will return a database object that implements this
 * interface. The typical sequence of method calls should be as follows:
 * <ul>
 * <li>{@link Database#setupDatabase()}</li>
 * <li>{@link Database#openOrCreateDatabase()}</li>
 * <li>{@link Database#execSQL(String)} OR</li>
 * <li>{@link Database#rawQuery(String)} OR</li>
 * <li>{@link Database#rawQuery(com.badlogic.gdx.sqlite.DatabaseCursor, String)}</li>
 * <li>{@link Database#closeDatabase()}</li>
 * </ul>
 * @author M Rafay Aleem */
public interface Database {

	/** This method is needed to be called only once before any database related activity can be performed. The method performs the
	 * necessary procedures for the database. However, a database will not be opened/created until
	 * {@link Database#openOrCreateDatabase()} is called. */
	public void setupDatabase ();

	/** Opens an already existing database or creates a new database if it doesn't already exist.
	 * @throws com.badlogic.gdx.sqlite.SQLiteGdxException */
	public void openOrCreateDatabase () throws com.badlogic.gdx.sqlite.SQLiteGdxException;

	/** Closes the opened database and releases all the resources related to this database.
	 * @throws com.badlogic.gdx.sqlite.SQLiteGdxException */
	public void closeDatabase () throws com.badlogic.gdx.sqlite.SQLiteGdxException;
	
   /**
    * Get a prepared statement.
    * 
    * @param prepStat give the {@link com.badlogic.gdx.sqlite.PreparedStatement prepared statement} to use
    * @param query the query to prepare
    * @return the prepared statement of the query
    * @throws com.badlogic.gdx.sqlite.SQLiteGdxRuntimeException
    */
   public com.badlogic.gdx.sqlite.PreparedStatement getPreparedStatement(String query) throws com.badlogic.gdx.sqlite.SQLiteGdxException;

   public void beginTransaction() throws com.badlogic.gdx.sqlite.SQLiteGdxException;
   
   public void setTransactionSuccessful() throws com.badlogic.gdx.sqlite.SQLiteGdxException;

   public void endTransaction() throws com.badlogic.gdx.sqlite.SQLiteGdxException;

	/** Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
	 * @param sql the SQL statement to be executed. Multiple statements separated by semicolons are not supported.
	 * @throws com.badlogic.gdx.sqlite.SQLiteGdxException */
	public void execSQL (String sql) throws com.badlogic.gdx.sqlite.SQLiteGdxException;

	/** Runs the provided SQL and returns a {@link com.badlogic.gdx.sqlite.DatabaseCursor} over the result set.
	 * @param sql the SQL query. The SQL string must not be ; terminated
	 * @return {@link com.badlogic.gdx.sqlite.DatabaseCursor}
	 * @throws com.badlogic.gdx.sqlite.SQLiteGdxException */
	public com.badlogic.gdx.sqlite.DatabaseCursor rawQuery (String sql) throws com.badlogic.gdx.sqlite.SQLiteGdxException;

	/** Runs the provided SQL and returns the same {@link com.badlogic.gdx.sqlite.DatabaseCursor} that was passed to this method. Use this method when you
	 * want to avoid reallocation of {@link com.badlogic.gdx.sqlite.DatabaseCursor} object. Note that you shall only pass the {@link com.badlogic.gdx.sqlite.DatabaseCursor} object
	 * that was previously returned by a rawQuery method. Creating your own {@link com.badlogic.gdx.sqlite.DatabaseCursor} and then passing it as an object
	 * will not work.
	 * @param cursor existing {@link com.badlogic.gdx.sqlite.DatabaseCursor} object
	 * @param sql the SQL query. The SQL string must not be ; terminated
	 * @return the passed {@link com.badlogic.gdx.sqlite.DatabaseCursor}.
	 * @throws com.badlogic.gdx.sqlite.SQLiteGdxException */
	public com.badlogic.gdx.sqlite.DatabaseCursor rawQuery (com.badlogic.gdx.sqlite.DatabaseCursor cursor, String sql) throws com.badlogic.gdx.sqlite.SQLiteGdxException;
	
   /**
    * Return the last insert query generated id.
    * 
    * @return the last generated id
    */
   public long getLastRowId() throws com.badlogic.gdx.sqlite.SQLiteGdxException;
}
