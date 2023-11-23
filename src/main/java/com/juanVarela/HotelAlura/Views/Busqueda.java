package com.juanVarela.HotelAlura.Views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.juanVarela.HotelAlura.dao.EditarDAO;
import com.juanVarela.HotelAlura.dao.EliminarHuespedDAO;
import com.juanVarela.HotelAlura.dao.EliminarReservaDAO;
import com.juanVarela.HotelAlura.factory.ConnectionFactory;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.awt.AlphaComposite;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloReserva;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				URL imageURL = getClass().getResource("/imagenes/18.jpg");
				Image image = new ImageIcon(imageURL).getImage();
				Graphics2D g2d = (Graphics2D) g;
				float alpha = 0.7f;
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBuscar.setOpaque(false);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(SystemColor.infoText);
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(316, 60, 300, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(58, 176, 248));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloReserva = (DefaultTableModel) tbReservas.getModel();
		modeloReserva.addColumn("Numero de Reserva");
		modeloReserva.addColumn("Fecha Check In");
		modeloReserva.addColumn("Fecha Check Out");
		modeloReserva.addColumn("Valor");
		modeloReserva.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(SystemColor.menu);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(SystemColor.menu);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(SystemColor.menu);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(SystemColor.menu);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de salir?", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(SystemColor.menu);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(SystemColor.menu);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(SystemColor.menu);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.infoText);
		separator_1_2.setBackground(SystemColor.infoText);
		separator_1_2.setBounds(536, 153, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String searchTerm = txtBuscar.getText();
				if (!searchTerm.isEmpty()) {
					List<Map<String, String>> resultados = realizarBusquedaHuesped(searchTerm);
					List<Map<String, String>> resultados2 = realizarBusquedaReserva(searchTerm);

					// Limpiamos la tabla
					modeloHuesped.setRowCount(0);
					modeloReserva.setRowCount(0);

					// Llenar la tabla con los resultados de los huéspedes
					for (Map<String, String> resultadoH : resultados) {
						modeloHuesped.addRow(new Object[] { resultadoH.get("id"), resultadoH.get("nombre"),
								resultadoH.get("apellido"), resultadoH.get("fecha_nacimiento"),
								resultadoH.get("nacionalidad"), resultadoH.get("telefono"),
								resultadoH.get("id_reserva"), });
					}
					// Llenar la tabla con los resultados de las reservas
					for (Map<String, String> resultadoR : resultados2) {
						modeloReserva.addRow(new Object[] { resultadoR.get("id_reserva"),
								resultadoR.get("fecha_entrada"), resultadoR.get("fecha_salida"),
								resultadoR.get("valor"), resultadoR.get("forma_pago"), });
					}
				}
			}

			// Realizamos la búsqueda de nuestro huésped en la base de datos abriendo y
			// cerrando nuestra conexión
			private List<Map<String, String>> realizarBusquedaHuesped(String searchTerm) {
				List<Map<String, String>> resultadosHuesped = new ArrayList<>();

				Connection con = null;
				PreparedStatement statement = null;
				ResultSet rs = null;

				try {
					con = new ConnectionFactory().recuperaConexion();
					String huesped = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE apellido LIKE ?";
					statement = con.prepareStatement(huesped);
					statement.setString(1, "%" + searchTerm + "%");

					rs = statement.executeQuery();

					while (rs.next()) {
						Map<String, String> resultado = new HashMap<>();
						resultado.put("id", rs.getString("id"));
						resultado.put("nombre", rs.getString("nombre"));
						resultado.put("apellido", rs.getString("apellido"));
						resultado.put("fecha_nacimiento", rs.getString("fecha_nacimiento"));
						resultado.put("nacionalidad", rs.getString("nacionalidad"));
						resultado.put("telefono", rs.getString("telefono"));
						resultado.put("id_reserva", rs.getString("id_reserva"));
						resultadosHuesped.add(resultado);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al obtener resultados.");
				} finally {
					try {
						if (rs != null) {
							rs.close();
						}
						if (statement != null) {
							statement.close();
						}
						if (con != null) {
							con.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return resultadosHuesped;
			}

			// Realizamos la búsqueda de nuestra reserva en la base de datos abriendo y
			// cerrando nuestra conexión
			private List<Map<String, String>> realizarBusquedaReserva(String searchTerm) {
				List<Map<String, String>> resultadosReserva = new ArrayList<>();

				Connection con = null;
				PreparedStatement statement = null;
				ResultSet rs = null;

				try {
					con = new ConnectionFactory().recuperaConexion();
					String reserva = "SELECT r.fecha_entrada, r.fecha_salida, r.valor, r.forma_pago, h.id_reserva "
							+ "FROM reservas r " + "JOIN huespedes h ON r.id = h.id_reserva "
							+ "WHERE h.id_reserva = ?";
					statement = con.prepareStatement(reserva);
					statement.setString(1, searchTerm);
					rs = statement.executeQuery();

					while (rs.next()) {
						Map<String, String> resultado2 = new HashMap<>();
						resultado2.put("id_reserva", rs.getString("id_reserva"));
						resultado2.put("fecha_entrada", rs.getString("fecha_entrada"));
						resultado2.put("fecha_salida", rs.getString("fecha_salida"));
						resultado2.put("valor", rs.getString("valor"));
						resultado2.put("forma_pago", rs.getString("forma_pago"));
						resultadosReserva.add(resultado2);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al obtener resultados.");
				} finally {
					try {
						if (rs != null) {
							rs.close();
						}
						if (statement != null) {
							statement.close();
						}
						if (con != null) {
							con.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return resultadosReserva;
			}
		});

		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(58, 176, 248));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setBackground(SystemColor.black);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(SystemColor.infoText);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Obtenemos la fila seleccionada en la tabla de huéspedes
				int filaSeleccionadaH = tbHuespedes.getSelectedRow();

				// Verificamos que se haya seleccionado una fila válida
				if (filaSeleccionadaH >= 0 && filaSeleccionadaH < modeloHuesped.getRowCount()) {
					// Obtemos los datos de la fila seleccionada
					String nombre = modeloHuesped.getValueAt(filaSeleccionadaH, 1).toString();
					String apellido = modeloHuesped.getValueAt(filaSeleccionadaH, 2).toString();
					String fechaNacimiento = modeloHuesped.getValueAt(filaSeleccionadaH, 3).toString();
					String nacionalidad = modeloHuesped.getValueAt(filaSeleccionadaH, 4).toString();
					String telefono = modeloHuesped.getValueAt(filaSeleccionadaH, 5).toString();
					int idReserva = Integer.parseInt(modeloHuesped.getValueAt(filaSeleccionadaH, 6).toString());

					// Abre un cuadro de diálogo para la edición de datos
					String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", nombre);
					String nuevoApellido = JOptionPane.showInputDialog("Nuevo apellido:", apellido);
					if (!nuevoNombre.matches(".*\\d.*") && !nuevoApellido.matches(".*\\d.*")) {
						JDateChooser dateChooserFN = new JDateChooser();
						dateChooserFN.setDate(Date.valueOf(fechaNacimiento));
						JOptionPane.showMessageDialog(null, dateChooserFN, "Seleccione la nueva fecha de nacimiento",
								JOptionPane.QUESTION_MESSAGE);
						java.util.Date nuevaFechaNacimientoDate = dateChooserFN.getDate();
						LocalDate nuevaFechaNacimiento = nuevaFechaNacimientoDate.toInstant()
								.atZone(ZoneId.systemDefault()).toLocalDate();
						LocalDate fechaActual = LocalDate.now();
						long diferenciaEnAños = ChronoUnit.YEARS.between(nuevaFechaNacimiento, fechaActual);
						if (diferenciaEnAños >= 18) {
							java.sql.Date nuevaFechaNacimientoSql = new java.sql.Date(
									nuevaFechaNacimientoDate.getTime());
							String nuevaFechaNacimientoStr = nuevaFechaNacimientoSql.toString();
							JComboBox<String> formaPagoComboBox = new JComboBox<>(new String[] { "afgano-afgana",
									"alemán-alemana", "árabe-árabe", "argentino-argentina", "australiano-australiana",
									"belga-belga", "boliviano-boliviana", "brasileño-brasileña", "camboyano-camboyana",
									"canadiense-canadiense", "chileno-chilena", "chino-china", "colombiano-colombiana",
									"coreano-coreana", "costarricense-costarricense", "cubano-cubana", "danés-danesa",
									"ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña",
									"escocés-escocesa", "español-española", "estadounidense-estadounidense",
									"estonio-estonia", "etiope-etiope", "filipino-filipina", "finlandés-finlandesa",
									"francés-francesa", "galés-galesa", "griego-griega", "guatemalteco-guatemalteca",
									"haitiano-haitiana", "holandés-holandesa", "hondureño-hondureña",
									"indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní",
									"irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa",
									"jordano-jordana", "laosiano-laosiana", "letón-letona", "letonés-letonesa",
									"malayo-malaya", "marroquí-marroquí", "mexicano-mexicana",
									"nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa",
									"panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca",
									"portugués-portuguesa", "puertorriqueño-puertorriqueño", "dominicano-dominicana",
									"rumano-rumana", "ruso-rusa", "sueco-sueca", "suizo-suiza", "tailandés-tailandesa",
									"taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana", "uruguayo-uruguaya",
									"venezolano-venezolana", "vietnamita-vietnamita" });
							formaPagoComboBox.setSelectedItem(nacionalidad);
							JOptionPane.showMessageDialog(null, formaPagoComboBox, "Seleccione la nueva nacionalidad",
									JOptionPane.QUESTION_MESSAGE);
							String nuevaNacionalidad = formaPagoComboBox.getSelectedItem().toString();
							String nuevoTelefono = JOptionPane.showInputDialog("Nuevo teléfono:", telefono);
							if (nuevoTelefono.matches("\\d+")) {

								// Llamamos al método para editar en la base de datos
								editarHuespedEnBaseDeDatos(nuevoNombre, nuevoApellido, nuevaFechaNacimientoStr,
										nuevaNacionalidad, nuevoTelefono, idReserva);
								modeloHuesped.setValueAt(nuevoNombre, filaSeleccionadaH, 1);
								modeloHuesped.setValueAt(nuevoApellido, filaSeleccionadaH, 2);
								modeloHuesped.setValueAt(nuevaFechaNacimientoStr, filaSeleccionadaH, 3);
								modeloHuesped.setValueAt(nuevaNacionalidad, filaSeleccionadaH, 4);
								modeloHuesped.setValueAt(nuevoTelefono, filaSeleccionadaH, 5);
								modeloHuesped.setValueAt(idReserva, filaSeleccionadaH, 6);
							} else {
								JOptionPane.showMessageDialog(null, "Introduce un número de teléfono válido.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "La persona debe ser mayor de 18 años.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "El nombre o el apellido no deben contener números.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					return;
				}
				// Obtenemos la fila seleccionada en la tabla de reservas
				int filaSeleccionadaR = tbReservas.getSelectedRow();

				if (filaSeleccionadaR != -1) {
					// Obtemos los datos de la fila seleccionada
					String fechaEntrada = modeloReserva.getValueAt(filaSeleccionadaR, 1).toString();
					String fechaSalida = modeloReserva.getValueAt(filaSeleccionadaR, 2).toString();
					String formaPago = modeloReserva.getValueAt(filaSeleccionadaR, 4).toString();
					int id = Integer.parseInt(modeloReserva.getValueAt(filaSeleccionadaR, 0).toString());

					// Abrimos un cuadro de diálogo para la edición de datos
					JDateChooser dateChooserEntrada = new JDateChooser();
					dateChooserEntrada.setDate(Date.valueOf(fechaEntrada));
					dateChooserEntrada.setMinSelectableDate(new java.util.Date());
					JOptionPane.showMessageDialog(null, dateChooserEntrada, "Seleccione la fecha de entrada",
							JOptionPane.QUESTION_MESSAGE);
					String nuevaFechaEntrada = new SimpleDateFormat("yyyy-MM-dd").format(dateChooserEntrada.getDate());
					JDateChooser dateChooserSalida = new JDateChooser();
					dateChooserSalida.setDate(Date.valueOf(fechaSalida));
					dateChooserSalida.setMinSelectableDate(new java.util.Date());
					JOptionPane.showMessageDialog(null, dateChooserSalida, "Seleccione la fecha de salida",
							JOptionPane.QUESTION_MESSAGE);
					String nuevaFechaSalida = new SimpleDateFormat("yyyy-MM-dd").format(dateChooserSalida.getDate());
					LocalDate fechaEntrada1 = LocalDate.parse(nuevaFechaEntrada);
					LocalDate fechaSalida1 = LocalDate.parse(nuevaFechaSalida);
					long dias = ChronoUnit.DAYS.between(fechaEntrada1, fechaSalida1);
					double nuevoValor = dias * 250.00;
					JComboBox<String> formaPagoComboBox = new JComboBox<>(
							new String[] { "Tarjeta de crédito", "Tarjeta de débito", "Efectivo" });
					formaPagoComboBox.setSelectedItem(formaPago);
					JOptionPane.showMessageDialog(null, formaPagoComboBox, "Seleccione la forma de pago",
							JOptionPane.QUESTION_MESSAGE);
					String nuevaFormaPago = formaPagoComboBox.getSelectedItem().toString();

					// Llamamos al método para editar en la base de datos
					editarReservaEnBaseDeDatos(nuevaFechaEntrada, nuevaFechaSalida, nuevoValor, nuevaFormaPago);
					modeloReserva.setValueAt(nuevaFechaEntrada, filaSeleccionadaR, 1);
					modeloReserva.setValueAt(nuevaFechaSalida, filaSeleccionadaR, 2);
					modeloReserva.setValueAt(nuevoValor, filaSeleccionadaR, 3);
					modeloReserva.setValueAt(nuevaFormaPago, filaSeleccionadaR, 4);
					modeloReserva.setValueAt(id, filaSeleccionadaR, 0);
				} else {
					JOptionPane.showMessageDialog(Busqueda.this,
							"Por favor, seleccione una fila válida antes de editar.");
				}

			}

			// Realizamos conexión con la base de datos para insertar nuevo huésped
			private void editarHuespedEnBaseDeDatos(String nuevoNombre, String nuevoApellido,
					String nuevaFechaNacimiento, String nuevaNacionalidad, String nuevoTelefono, int idReserva) {
				Connection con = null;
				try {
					con = new ConnectionFactory().recuperaConexion();
					con.setAutoCommit(false);
					EditarDAO editarDAO = new EditarDAO(con);
					editarDAO.editarHuesped(nuevoNombre, nuevoApellido, nuevaFechaNacimiento, nuevaNacionalidad,
							nuevoTelefono, idReserva);
					con.commit();
				} catch (SQLException ex) {
					if (con != null) {
						try {
							con.rollback();
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null,
									"Error al editar en la base de datos: " + ex.getMessage());
						}
					}
				}
			}

			private int obtenerIdReservaSeleccionada() {
				int filaSeleccionadaR = tbReservas.getSelectedRow();

				if (filaSeleccionadaR != -1 && filaSeleccionadaR < modeloReserva.getRowCount()) {
					// Obtemos el ID de reserva desde la columna 0.
					String idReservaStr = modeloReserva.getValueAt(filaSeleccionadaR, 0).toString();
					try {
						return Integer.parseInt(idReservaStr);
					} catch (NumberFormatException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "El valor no es un numero.");
					}
				}
				return 0;
			}

			// Realizamos conexión con la base de datos para insertar nueva reserva
			private void editarReservaEnBaseDeDatos(String nuevaFechaEntrada, String nuevaFechaSalida,
					double nuevoValor, String nuevaFormaPago) {
				Connection con = null;
				try {
					con = new ConnectionFactory().recuperaConexion();
					con.setAutoCommit(false);
					// Obtenemos el ID de reserva seleccionada
					int idReserva = obtenerIdReservaSeleccionada();
					// Calculamos la nueva diferencia en días
					LocalDate fechaEntrada = LocalDate.parse(nuevaFechaEntrada);
					LocalDate fechaSalida = LocalDate.parse(nuevaFechaSalida);
					long nuevaDiferenciaEnDias = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
					// Calculamos el nuevo valor total
					double nuevoValorTotal = nuevaDiferenciaEnDias * 250.00;
					EditarDAO editarDAO = new EditarDAO(con);
					editarDAO.editarReserva(nuevaFechaEntrada, nuevaFechaSalida, nuevaFormaPago, idReserva,
							nuevoValorTotal);
					con.commit();
				} catch (SQLException ex) {
					if (con != null) {
						try {
							con.rollback();
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null,
									"Error al editar en la base de datos: " + ex.getMessage());
						}
					}
				}
			}
		});

		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(58, 176, 248));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(SystemColor.infoText);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tbHuespedes.getSelectedRow();
				// Verificamos que la fila esté seleccionada para poder eliminarla
				if (filaSeleccionada != -1 && filaSeleccionada < modeloHuesped.getRowCount()) {
					try {
						Connection con = new ConnectionFactory().recuperaConexion();
						EliminarHuespedDAO eliminarHuespedDAO = new EliminarHuespedDAO(con);
						Integer id = Integer.valueOf(modeloHuesped.getValueAt(filaSeleccionada, 0).toString());
						int cantidadEliminada = eliminarHuespedDAO.eliminarHuesped(id);
						if (cantidadEliminada > 0) {
							modeloHuesped.removeRow(filaSeleccionada);
							JOptionPane.showMessageDialog(Busqueda.this,
									cantidadEliminada + " huésped eliminado con éxito.");
						} else {
							JOptionPane.showMessageDialog(Busqueda.this, "No se eliminó ningún huésped.");
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}

				int filaSeleccionada2 = tbReservas.getSelectedRow();
				// Verificamos que la fila esté seleccionada para poder eliminarla
				if (filaSeleccionada2 != -1 && filaSeleccionada2 < modeloReserva.getRowCount()) {
					try {
						Connection con = new ConnectionFactory().recuperaConexion();
						EliminarReservaDAO eliminarReservaDAO = new EliminarReservaDAO(con);
						Integer id = Integer.valueOf(modeloReserva.getValueAt(filaSeleccionada2, 0).toString());
						int cantidadEliminada = eliminarReservaDAO.eliminarReserva(id);
						if (cantidadEliminada > 0) {
							modeloReserva.removeRow(filaSeleccionada2);
							JOptionPane.showMessageDialog(Busqueda.this,
									cantidadEliminada + " huésped eliminado con éxito.");
						} else {
							JOptionPane.showMessageDialog(Busqueda.this, "No se eliminó ningún huésped.");
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(Busqueda.this,
							"Por favor, seleccione una fila válida antes de eliminar.");
				}
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(58, 176, 248));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(SystemColor.infoText);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
