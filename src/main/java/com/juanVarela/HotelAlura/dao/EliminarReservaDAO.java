package com.juanVarela.HotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarReservaDAO {
	private Connection connection;

	public EliminarReservaDAO(Connection connection) {
		this.connection = connection;
	}

	public int eliminarReserva(Integer id) throws SQLException {

			String sql = "DELETE FROM reservas WHERE id = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);
				int rowsDeleted = statement.executeUpdate();

				if (rowsDeleted > 0) {
					System.out.println("Se eliminó la reserva de la base de datos.");
				} else {
					System.out.println("No se eliminó la reserva de la base de datos.");
				}
			}
			return id;
		} 			
}
