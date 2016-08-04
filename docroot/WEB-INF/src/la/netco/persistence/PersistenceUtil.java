package la.netco.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersistenceUtil {
	private static ConexionFactory conexionFactory = new ConexionFactory();
	private static Connection conection;
	private static ResultSet resultSet;
	private static Statement statement;

	public static ResultSet realizaConsulta(String consulta) throws SQLException {
		conection = conexionFactory.getConexion();

		statement = conection.createStatement();
		resultSet = statement.executeQuery(consulta);

		return resultSet;
	}

	public static void ejecutaSentencia(String consulta) throws SQLException {
		conection = conexionFactory.getConexion();

		statement = conection.createStatement();
		statement.executeQuery(consulta);
	}

	public static void terminaOperacion() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (conection != null) {
			conection.close();
		}

		resultSet = null;
		statement = null;
		conection = null;
	}
}
