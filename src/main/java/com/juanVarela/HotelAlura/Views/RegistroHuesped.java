package com.juanVarela.HotelAlura.Views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.AlphaComposite;
import java.awt.Color;

import com.juanVarela.HotelAlura.dao.EliminarReservaDAO;
import com.juanVarela.HotelAlura.dao.HuespedDAO;
import com.juanVarela.HotelAlura.factory.ConnectionFactory;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import java.sql.Connection;

import java.sql.SQLException;

import java.text.Format;
import java.util.Calendar;
import java.util.Date;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtNreserva;
	private JDateChooser txtFechaN;
	private JComboBox<Format> txtNacionalidad;
	private JLabel labelExit;
	private JLabel labelAtras;
	int xMouse, yMouse;
	private HuespedDAO huespedDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHuesped frame = new RegistroHuesped();
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

	public RegistroHuesped() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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

		// Botón guardar
		// Se conecta a la base de datos para guardar un nuevo huésped
		JPanel btnGuardar = new JPanel();
		btnGuardar.setBounds(723, 560, 122, 35);
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Connection con = new ConnectionFactory().recuperaConexion();
					huespedDAO = new HuespedDAO(con);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				// Verifica que no se encuentren vacías las casillas
				if (!txtNombre.getText().isEmpty() && !txtApellido.getText().isEmpty() && txtFechaN != null
						&& txtNacionalidad != null && txtTelefono != null) {
					String nombre = txtNombre.getText();
					String apellido = txtApellido.getText();
					String fechaNacimiento = ((JTextField) txtFechaN.getDateEditor().getUiComponent()).getText();
					String nacionalidad = txtNacionalidad.getSelectedItem().toString();
					String telefono = txtTelefono.getText();
					String numeroReserva = txtNreserva.getText();
					if (!numeroReserva.isEmpty()) {
						try {
							int idReserva = Integer.parseInt(numeroReserva);
							Date fechaNacimientoDate = txtFechaN.getDate();
							Date fechaActual = new Date();
							// Verifica si es mayor de edad
							Calendar calNacimiento = Calendar.getInstance();
							calNacimiento.setTime(fechaNacimientoDate);
							Calendar calActual = Calendar.getInstance();
							calActual.setTime(fechaActual);
							int añosDiferencia = calActual.get(Calendar.YEAR) - calNacimiento.get(Calendar.YEAR);
							if (añosDiferencia >= 18) {
								// Verifica que se ingresen solo números para el campo teléfono
								if (telefono.matches("\\d+")) {
									// Verifica que se inserten solo caracteres en el campo, nombre y apellido
									if (!nombre.matches(".*\\d.*") && !apellido.matches(".*\\d.*")) {
										huespedDAO.insertHuesped(nombre, apellido, fechaNacimiento, nacionalidad,
												telefono, idReserva);
										System.out.println("Se inserto el huesped en la base de datos.");
										Exito ventanaExito = new Exito(nombre, apellido, numeroReserva);
										ventanaExito.setVisible(true);
									} else {
										JOptionPane.showMessageDialog(null,
												"El nombre o el apellido no deben contener números.");
									}
								} else {
									JOptionPane.showMessageDialog(null, "Introduce un número de teléfono válido.");
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"La persona es menor de edad, no se puede registrar.");
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "El número de reserva no es válido.");
						} catch (SQLException e1) {

							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "El número de reserva está vacío.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
				}
			}

		});
		btnGuardar.setLayout(null);
		btnGuardar.setBackground(SystemColor.controlShadow);
		contentPane.add(btnGuardar);
		btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnGuardar.add(labelGuardar);

		txtNreserva = new JTextField();
		txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNreserva.setBounds(560, 495, 285, 33);
		txtNreserva.setColumns(10);
		txtNreserva.setBackground(Color.WHITE);
		txtNreserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNreserva);
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		// Botón regresar, se pide confirmación y se verifica si regresa el usuario, se
		// borra el registro anteriormente guardado
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"¿Estás seguro de regresar? se borrara el registro de la reserva", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					Connection con = null;
					try {
						con = new ConnectionFactory().recuperaConexion();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					EliminarReservaDAO eliminarReservaDAO = new EliminarReservaDAO(con);
					int reservaId = Integer.parseInt(txtNreserva.getText());
					try {
						if (reservaId != -1) {
							eliminarReservaDAO.eliminarReserva(reservaId);
							System.out.println("La reserva ha sido eliminada.");
						} else {
							System.out.println("No se pudo obtener el ID de la reserva.");
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
						System.out.println("Error al eliminar la reserva.");
					}
					ReservasView reservas = new ReservasView();
					reservas.setVisible(true);
					dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(12, 138, 199));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(Color.WHITE);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		// Botón exit, se pide confirmación y se verifica si sale el usuario, se borra
		// el registro anteriormente guardado
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"¿Estás seguro de salir y borrar el registro de la reserva?", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					Connection con = null;
					try {
						con = new ConnectionFactory().recuperaConexion();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					EliminarReservaDAO eliminarReservaDAO = new EliminarReservaDAO(con);
					int reservaId = Integer.parseInt(txtNreserva.getText());
					try {
						if (reservaId != -1) {
							eliminarReservaDAO.eliminarReserva(reservaId);
							JOptionPane.showMessageDialog(null, "La reserva ha sido eliminada.");
						} else {
							JOptionPane.showMessageDialog(null, "No se pudo obtener el ID de la reserva.");
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error al eliminar la reserva.");
					}
					MenuPrincipal principal = new MenuPrincipal();
					principal.setVisible(true);
					dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.white);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
		lblNumeroReserva.setBounds(560, 474, 253, 14);
		lblNumeroReserva.setForeground(SystemColor.infoText);
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNumeroReserva);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefono.setBounds(560, 424, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		txtTelefono.setText("xx-xx-xx-xx-xx");
		txtTelefono.setForeground(Color.GRAY);
		txtTelefono.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTelefono.getText().equals("xx-xx-xx-xx-xx")) {
					txtTelefono.setText("");
					txtTelefono.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTelefono.getText().isEmpty()) {
					txtTelefono.setText("xx-xx-xx-xx-xx");
					txtTelefono.setForeground(Color.GRAY);
				}
			}
		});
		contentPane.add(txtTelefono);

		txtNacionalidad = new JComboBox();
		txtNacionalidad.setBounds(560, 350, 289, 36);
		txtNacionalidad.setBackground(SystemColor.text);
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNacionalidad.setModel(new DefaultComboBoxModel(new String[] { "afgano-afgana", "alemán-alemana",
				"árabe-árabe", "argentino-argentina", "australiano-australiana", "belga-belga", "boliviano-boliviana",
				"brasileño-brasileña", "camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china",
				"colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", "cubano-cubana",
				"danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña",
				"escocés-escocesa", "español-española", "estadounidense-estadounidense", "estonio-estonia",
				"etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa", "galés-galesa",
				"griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana", "holandés-holandesa",
				"hondureño-hondureña", "indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní",
				"irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa", "jordano-jordana",
				"laosiano-laosiana", "letón-letona", "letonés-letonesa", "malayo-malaya", "marroquí-marroquí",
				"mexicano-mexicana", "nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa",
				"panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca", "portugués-portuguesa",
				"puertorriqueño-puertorriqueño", "dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca",
				"suizo-suiza", "tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana",
				"uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita" }));
		contentPane.add(txtNacionalidad);

		txtFechaN = new JDateChooser();
		txtFechaN.setBounds(560, 278, 285, 36);
		txtFechaN.getCalendarButton()
				.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(SystemColor.controlShadow);
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtFechaN);

		JLabel lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(562, 406, 253, 14);
		lblTelefono.setForeground(SystemColor.infoText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefono);

		JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
		lblNacionalidad.setBounds(560, 326, 255, 14);
		lblNacionalidad.setForeground(SystemColor.infoText);
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacionalidad);

		JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		lblFechaN.setBounds(560, 256, 255, 14);
		lblFechaN.setForeground(SystemColor.infoText);
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblFechaN);

		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(560, 189, 255, 14);
		lblApellido.setForeground(SystemColor.infoText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblApellido);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(562, 119, 253, 14);
		lblNombre.setForeground(SystemColor.infoText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNombre);

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				URL imageURL = getClass().getResource("/imagenes/31.jpg");
				Image image = new ImageIcon(imageURL).getImage();
				Graphics2D g2d = (Graphics2D) g;
				float alpha = 0.7f;
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			}
		};
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 36, 910, 598);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTitulo = new JLabel("REGISTRO HUÉSPED");
		lblTitulo.setBounds(573, 22, 285, 42);
		panel.add(lblTitulo);
		lblTitulo.setForeground(SystemColor.infoText);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 25));

		txtNombre = new JTextField();
		txtNombre.setBounds(560, 104, 285, 33);
		panel.add(txtNombre);
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		txtApellido = new JTextField();
		txtApellido.setBounds(560, 176, 285, 33);
		panel.add(txtApellido);
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
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

	// Método para obtener el ID de la reserva
	public void setReservaId(int reservaId) {
		String idReserva = String.valueOf(reservaId);
		txtNreserva.setText(idReserva);
		txtNreserva.setEditable(false);
	}

}
