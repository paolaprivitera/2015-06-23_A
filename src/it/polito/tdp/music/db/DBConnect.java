package it.polito.tdp.music.db;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class DBConnect {
	
	private final static String jdbcURL = "jdbc:mysql://localhost/musicmicro?user=root&password=root24&serverTimezone=Europe/Rome";

	private static class PersistentConnection implements Connection {

		private Connection conn;
		
		public PersistentConnection(Connection c) {
			this.conn = c ;
		}

		@Override
		public boolean isWrapperFor(Class<?> arg0) throws SQLException {
			return conn.isWrapperFor(arg0);
		}

		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			return conn.unwrap(iface);
		}

		@Override
		public void abort(Executor executor) throws SQLException {
			conn.abort(executor);
		}

		@Override
		public void clearWarnings() throws SQLException {
			conn.clearWarnings();

		}

		@Override
		public void close() throws SQLException {
			// DO NOTHING!!
		}

		@Override
		public void commit() throws SQLException {
			conn.commit();

		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements)
				throws SQLException {
			return conn.createArrayOf(typeName, elements);
		}

		@Override
		public Blob createBlob() throws SQLException {
			return conn.createBlob();
		}

		@Override
		public Clob createClob() throws SQLException {
			return conn.createClob();
		}

		@Override
		public NClob createNClob() throws SQLException {
			return conn.createNClob();
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			return conn.createSQLXML();
		}

		@Override
		public Statement createStatement() throws SQLException {
			return conn.createStatement();
		}

		@Override
		public Statement createStatement(int resultSetType,
				int resultSetConcurrency) throws SQLException {
			return conn.createStatement(resultSetType, resultSetConcurrency);
		}

		@Override
		public Statement createStatement(int resultSetType,
				int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {

			return conn.createStatement(resultSetType, resultSetConcurrency,
					resultSetHoldability);
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes)
				throws SQLException {
			return conn.createStruct(typeName, attributes);
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			return conn.getAutoCommit();
		}

		@Override
		public String getCatalog() throws SQLException {
			return conn.getCatalog();
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			return conn.getClientInfo();
		}

		@Override
		public String getClientInfo(String name) throws SQLException {
			return conn.getClientInfo(name);
		}

		@Override
		public int getHoldability() throws SQLException {
			return conn.getHoldability();
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			return conn.getMetaData();
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			return conn.getNetworkTimeout();
		}

		@Override
		public String getSchema() throws SQLException {
			return conn.getSchema();
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			return conn.getTransactionIsolation();
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			return conn.getTypeMap();
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			return conn.getWarnings();
		}

		@Override
		public boolean isClosed() throws SQLException {
			return conn.isClosed();
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			return conn.isReadOnly();
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {
			return conn.isValid(timeout);
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {
			return conn.nativeSQL(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {
			return conn.prepareCall(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType,
				int resultSetConcurrency) throws SQLException {
			return conn.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType,
				int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			return conn.prepareCall(sql, resultSetType, resultSetConcurrency,
					resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql)
				throws SQLException {
			return conn.prepareStatement(sql);
		}

		@Override
		public PreparedStatement prepareStatement(String sql,
				int autoGeneratedKeys) throws SQLException {
			return conn.prepareStatement(sql, autoGeneratedKeys);
		}

		@Override
		public PreparedStatement prepareStatement(String sql,
				int[] columnIndexes) throws SQLException {
			return conn.prepareStatement(sql, columnIndexes);
		}

		@Override
		public PreparedStatement prepareStatement(String sql,
				String[] columnNames) throws SQLException {
			return conn.prepareStatement(sql, columnNames);
		}

		@Override
		public PreparedStatement prepareStatement(String sql,
				int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return conn.prepareStatement(sql, resultSetType,
					resultSetConcurrency);
		}

		@Override
		public PreparedStatement prepareStatement(String sql,
				int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return conn.prepareStatement(sql, resultSetType,
					resultSetConcurrency);
		}

		@Override
		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			conn.releaseSavepoint(savepoint);
		}

		@Override
		public void rollback() throws SQLException {
			conn.rollback();
		}

		@Override
		public void rollback(Savepoint savepoint) throws SQLException {
			conn.rollback(savepoint);
		}

		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException {
			conn.setAutoCommit(autoCommit);
		}

		@Override
		public void setCatalog(String catalog) throws SQLException {
			conn.setCatalog(catalog);
		}

		@Override
		public void setClientInfo(Properties properties)
				throws SQLClientInfoException {
			conn.setClientInfo(properties);
		}

		@Override
		public void setClientInfo(String name, String value)
				throws SQLClientInfoException {
			conn.setClientInfo(name, value);
		}

		@Override
		public void setHoldability(int holdability) throws SQLException {
			conn.setHoldability(holdability);
		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds)
				throws SQLException {
			conn.setNetworkTimeout(executor, milliseconds);
		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			conn.setReadOnly(readOnly);
		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			return conn.setSavepoint();
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {
			return conn.setSavepoint(name);
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			conn.setSchema(schema);
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			conn.setTransactionIsolation(level);
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			conn.setTypeMap(map);
		}

	}

	private static Connection myConn = null;

	public static Connection getConnection() {

		if (myConn != null)
			return myConn;

		Connection c;
		try {
			c = DriverManager.getConnection(jdbcURL);
			c = new PersistentConnection(c) ; // inibisce il metodo close()
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossibile connettersi al database", e);
		}

		myConn = c;
		return c;

	}

}
