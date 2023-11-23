package com.juanVarela.HotelAlura.Views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import java.awt.AlphaComposite;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.SwingConstants;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import javax.swing.JButton;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	int xMouse, yMouse;
	private JLabel labelExit;

	// Método para único usuario y contraseña
	public static boolean autenticar(String usuario, String contrasena) {
		return usuario.equals("admin") && contrasena.equals("admin");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				URL imageURL = getClass().getResource("/imagenes/login1.jpg");
				Image image = new ImageIcon(imageURL).getImage();
				Graphics2D g2d = (Graphics2D) g;
				float alpha = 0.6f;
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			}
		};
		panel.setBounds(0, 0, 910, 537);
		panel.setBackground(SystemColor.window);
		contentPane.add(panel);
		panel.setLayout(null);

		// Campo para ingresar usuario
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtUsuario.setForeground(SystemColor.activeCaptionBorder);
		txtUsuario.setBounds(65, 192, 324, 32);
		txtUsuario.setText("Usuario");
		txtUsuario.setForeground(Color.GRAY);
		txtUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtUsuario.getText().equals("Usuario")) {
					txtUsuario.setText("");
					txtUsuario.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtUsuario.getText().isEmpty()) {
					txtUsuario.setText("Usuario");
					txtUsuario.setForeground(Color.GRAY);
				}
			}
		});
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(484, 0, 0, 527);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel labelTitulo = new JLabel("INICIAR SESIÓN");
		labelTitulo.setForeground(SystemColor.infoText);
		labelTitulo.setFont(new Font("Dubai", Font.BOLD, 26));
		labelTitulo.setBounds(132, 92, 202, 26);
		panel.add(labelTitulo);
		EventQueue.invokeLater(() -> {
			labelTitulo.requestFocus();
		});

		// Campo para ingresar contraseña
		txtContrasena = new JPasswordField();
		txtContrasena.setForeground(SystemColor.activeCaptionBorder);
		txtContrasena.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtContrasena.setBounds(65, 292, 324, 32);
		txtContrasena.setEchoChar('*');
		txtContrasena.setText("**********");
		txtContrasena.setForeground(Color.GRAY);
		txtContrasena.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtContrasena.getText().equals("**********")) {
					txtContrasena.setText("");
					txtContrasena.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtContrasena.getText().isEmpty()) {
					txtContrasena.setText("**********");
					txtContrasena.setForeground(Color.GRAY);
				}
			}
		});

		panel.add(txtContrasena);

		JLabel LabelUsuario = new JLabel("USUARIO");
		LabelUsuario.setForeground(SystemColor.infoText);
		LabelUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		LabelUsuario.setBounds(182, 155, 107, 26);
		panel.add(LabelUsuario);

		JLabel lblContrasea = new JLabel("CONTRASEÑA");
		lblContrasea.setForeground(SystemColor.infoText);
		lblContrasea.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		lblContrasea.setBounds(158, 246, 140, 26);
		panel.add(lblContrasea);

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
		header.setBackground(SystemColor.menu);
		header.setBounds(0, 0, 399, 36);
		panel.add(header);
		header.setLayout(null);

		// Botón exit redirecciona a menú principal
		JPanel btnexit = new JPanel();
		btnexit.setBounds(399, 0, 53, 36);
		panel.add(btnexit);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.setVisible(true);
				dispose();
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
		btnexit.setBackground(SystemColor.menu);
		btnexit.setLayout(null);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setForeground(SystemColor.textInactiveText);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);

		// Botón y lógica para loguear
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setForeground(SystemColor.window);
		btnNewButton.setBackground(new Color(60, 60, 60));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String usuario = txtUsuario.getText();
					String contrasena = txtContrasena.getText();
					// Verifica que sé allá ingresado un usuario y contraseña correctos
					if (usuario != null && contrasena != null) {
						if (Login.autenticar(usuario, contrasena)) {
							MenuUsuario menuUsuario = new MenuUsuario();
							menuUsuario.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ingrese usuario y contraseña");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El formato no es válido.");
				}
			}
		});
		btnNewButton.setBounds(172, 362, 110, 36);
		panel.add(btnNewButton);

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
