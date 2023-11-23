package com.juanVarela.HotelAlura.Views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class MenuUsuario extends JFrame {

	private JPanel contentPane;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelRegistro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUsuario frame = new MenuUsuario();
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
	public MenuUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 609);
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				URL imageURL = getClass().getResource("/imagenes/50.jpg");
				Image image = new ImageIcon(imageURL).getImage();
				Graphics2D g2d = (Graphics2D) g;
				float alpha = 0.5f;
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

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
		header.setBounds(0, 0, 944, 36);
		contentPane.add(header);

		// Botón exit pide confirmación de usuario
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"¿Estás seguro de salir y no realizar ninguna acción?", "Confirmación",
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
		btnexit.setBounds(891, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel panelFecha = new JPanel();
		panelFecha.setBackground(new Color(0, 107, 215));
		panelFecha.setBounds(256, 84, 688, 121);
		contentPane.add(panelFecha);
		panelFecha.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Sistema de reservas México Mágico Suites");
		lblNewLabel_1.setBounds(108, 11, 479, 42);
		panelFecha.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Roboto", Font.PLAIN, 24));

		// Obtenemos fecha y hora actual y se formatea en una cadena para mostrar en
		// interfaz
		JLabel labelFecha = new JLabel("New label");
		labelFecha.setBounds(208, 64, 294, 36);
		panelFecha.add(labelFecha);
		labelFecha.setForeground(Color.WHITE);
		labelFecha.setFont(new Font("Roboto", Font.PLAIN, 33));
		Date fechaActual = new Date();
		String fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
		labelFecha.setText("Hoy es " + fecha);

		JLabel lblNewLabel = new JLabel("Bienvenido");
		lblNewLabel.setFont(new Font("Microsoft Uighur", Font.BOLD, 70));
		lblNewLabel.setBounds(342, 234, 256, 46);
		contentPane.add(lblNewLabel);

		JLabel labelDescripcion = new JLabel(
				"<html><body>Sistema de reserva de hotel. Controle y administre de forma óptima y fácil <br> el flujo de reservas y de huespédes del hotel.   </body></html>");
		labelDescripcion.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));

		labelDescripcion.setBounds(111, 291, 731, 66);
		contentPane.add(labelDescripcion);

		String textoDescripcion1 = "<html><body> Esta herramienta le permitirá llevar un control completo y detallado de sus reservas y huéspedes, tendrá acceso a heramientas especiales para tareas específicas como lo son:</body></html>";
		JLabel labelDescripcion_1 = new JLabel(textoDescripcion1);
		labelDescripcion_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		labelDescripcion_1.setBounds(111, 345, 750, 88);
		contentPane.add(labelDescripcion_1);

		JLabel lblNewLabel_3 = new JLabel("- Registro de Reservas y Huéspedes");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setBounds(111, 444, 355, 27);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("- Edición de Reservas y Huéspedes existentes");
		lblNewLabel_3_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3_1.setBounds(111, 482, 452, 27);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("- Eliminar todo tipo de registros");
		lblNewLabel_3_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3_2.setBounds(111, 520, 325, 27);
		contentPane.add(lblNewLabel_3_2);

		JPanel btnBusqueda = new JPanel();
		btnBusqueda.setBounds(26, 84, 200, 36);
		contentPane.add(btnBusqueda);
		btnBusqueda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBusqueda.setBackground(new Color(0, 91, 183));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBusqueda.setBackground(new Color(0, 107, 215));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Busqueda busqueda = new Busqueda();
				busqueda.setVisible(true);
				dispose();
			}
		});
		btnBusqueda.setBackground(new Color(0, 107, 215));
		btnBusqueda.setLayout(null);

		JLabel lblBusquedaDeReservas = new JLabel("Búsqueda");
		lblBusquedaDeReservas.setBounds(28, 0, 205, 34);
		btnBusqueda.add(lblBusquedaDeReservas);
		lblBusquedaDeReservas.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/pessoas.png")));
		lblBusquedaDeReservas.setHorizontalAlignment(SwingConstants.LEFT);
		lblBusquedaDeReservas.setForeground(Color.WHITE);
		lblBusquedaDeReservas.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnRegistro = new JPanel();
		btnRegistro.setBounds(26, 169, 200, 36);
		contentPane.add(btnRegistro);
		btnRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegistro.setBackground(new Color(0, 91, 183));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRegistro.setBackground(new Color(0, 107, 215));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasView reservas = null;
				reservas = new ReservasView();
				reservas.setVisible(true);
				dispose();
			}
		});
		btnRegistro.setBackground(new Color(0, 107, 215));
		btnRegistro.setLayout(null);

		labelRegistro = new JLabel("Registro de reservas");
		labelRegistro.setBounds(0, 0, 205, 34);
		btnRegistro.add(labelRegistro);
		labelRegistro.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/reservado.png")));
		labelRegistro.setForeground(SystemColor.text);
		labelRegistro.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelRegistro.setHorizontalAlignment(SwingConstants.LEFT);
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
