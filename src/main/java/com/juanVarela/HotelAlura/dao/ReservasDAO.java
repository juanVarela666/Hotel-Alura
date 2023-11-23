package com.juanVarela.HotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservasDAO {
	private Connection connection;

	public ReservasDAO(Connection connection) {
		this.connection = connection;
	}

	public int insertReserva(String fechaEntrada, String fechaSalida, String valor, String formaPago)
			throws SQLException {
		String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_pago) VALUES (?, ?, ?, ?)";

		try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, fechaEntrada);
			statement.setString(2, fechaSalida);
			statement.setString(3, valor);
			statement.setString(4, formaPago);

			int executeUpdate = statement.executeUpdate();
			
			if (executeUpdate > 0) {
				System.out.println("Se inserto la reserva en la base de datos.");
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				}
			}
		}
		return 0;	
	}
}
