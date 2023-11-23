package com.juanVarela.HotelAlura.Views;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Exito extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel_1;

	/**
	 * Create the dialog.
	 */

	public static void main(String[] args) {
		try {
			Exito dialog = new Exito();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Exito(String nombre, String apellido, String numeroReserva) {		
		URL imageUrl = Exito.class.getResource("/imagenes/Exito2.png");
	    ImageIcon imageIcon = new ImageIcon(imageUrl);
	    Image image = imageIcon.getImage();
	    
	    contentPanel = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D g2d = (Graphics2D) g.create();
	            g2d.setComposite(AlphaComposite.SrcOver.derive(0.1f));
	            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	            g2d.dispose();
	        }
	    };
	    	    
        setBounds(100, 100, 394, 226);                   
        getContentPane().setLayout(new BorderLayout());        
        contentPanel.setBackground(SystemColor.menu);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        contentPanel.setLayout(null);        
        lblNewLabel_1 = new JLabel();
        lblNewLabel_1.setText("<html>Datos guardados satisfactoriamente para: " + nombre + " " + apellido
                + "<br>Con el número de reserva: " + numeroReserva + "</html>");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(SystemColor.infoText);
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_1.setBounds(27, 31, 322, 97);
        contentPanel.add(lblNewLabel_1);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton); 
        
        
        

        JButton cancelButton = new JButton("Cancel");                			               
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {              
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton); 
        getRootPane().setDefaultButton(cancelButton);
    }

	public Exito() {
		// TODO Auto-generated constructor stub
	}
}
