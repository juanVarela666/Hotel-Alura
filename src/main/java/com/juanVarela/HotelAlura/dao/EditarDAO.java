package com.juanVarela.HotelAlura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditarDAO {
	private Connection connection;
	
	public EditarDAO(Connection connection) {
		this.connection = connection;
	}

	public void editarHuesped(String nombre, String apellido, String nuevaFechaNacimiento, String nacionalidad,
	        String telefono, int idReserva) throws SQLException {
	    String sql = "UPDATE huespedes SET nombre=?, apellido=?, fecha_nacimiento=?, nacionalidad=?, telefono=?, id_reserva=? WHERE id_reserva=?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, nombre);
	        statement.setString(2, apellido);
	        statement.setString(3, nuevaFechaNacimiento);
	        statement.setString(4, nacionalidad);
	        statement.setString(5, telefono);
	        statement.setLong(6, idReserva);
	        statement.setLong(7, idReserva);

	        int executeUpdate = statement.executeUpdate();

	        if (executeUpdate > 0) {
	            System.out.println("Se actualizó el huésped en la base de datos.");
	        } else {
	            System.out.println("No se actualizó el huésped en la base de datos.");
	        }
	    }
	}
	
	public void editarReserva(String fechaEntrada, String fechaSalida, String formaPago, int idReserva, double nuevoValorTotal)
	        throws SQLException {
	    String sql = "UPDATE reservas SET fecha_entrada=?, fecha_salida=?, valor=?, forma_pago=? WHERE id=?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, fechaEntrada);
	        statement.setString(2, fechaSalida);
	        statement.setDouble(3, nuevoValorTotal);
	        statement.setString(4, formaPago);
	        statement.setInt(5, idReserva);

	        int executeUpdate = statement.executeUpdate();

	        if (executeUpdate > 0) {
	            System.out.println("Se actualizó la reserva en la base de datos.");
	        } else {
	            System.out.println("No se actualizó la reserva en la base de datos.");
	        }
	    }
	}
}
