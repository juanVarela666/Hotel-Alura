package com.juanVarela.HotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HuespedDAO {
	private Connection connection;

	public HuespedDAO(Connection connection) {
		this.connection = connection;
	}

	public void insertHuesped(String nombre, String apellido, String fechaNacimiento, String nacionalidad,
			String telefono, int idReserva) throws SQLException {
		String sql = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, nombre);
			statement.setString(2, apellido);
			statement.setString(3, fechaNacimiento);
			statement.setString(4, nacionalidad);
			statement.setString(5, telefono);
			statement.setLong(6, idReserva);
			
			int executeUpdate = statement.executeUpdate();
			
			if (executeUpdate > 0) {
	            System.out.println("Se inserto la reserva en la base de datos.");
	        } else {
	            System.out.println("No se inserto la reserva en la base de datos.");
	        }
		}
	}
}
