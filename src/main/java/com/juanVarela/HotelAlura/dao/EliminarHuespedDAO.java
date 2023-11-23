package com.juanVarela.HotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EliminarHuespedDAO {
	private Connection connection;

	public EliminarHuespedDAO(Connection connection) {
		this.connection = connection;
	}

	public int eliminarHuesped(Integer id) throws SQLException {
		try {
            int idReserva = buscarIdReserva(id);
            if (idReserva != -1) {
                eliminarReserva(idReserva);
            }

            String sql = "DELETE FROM huespedes WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                int rowsDeleted = statement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Se eliminó el huésped de la base de datos.");
                } else {
                    System.out.println("No se eliminó el huésped de la base de datos.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    private int buscarIdReserva(Integer idHuesped) throws SQLException {
        String sql = "SELECT id_reserva FROM huespedes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idHuesped);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_reserva");
            }
            return -1;
        }
    }

    private void eliminarReserva(int idReserva) throws SQLException {
        String sql = "DELETE FROM reservas WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idReserva);
            statement.executeUpdate();
            System.out.println("Se eliminó la reserva relacionada de la base de datos.");
        }
    }
}