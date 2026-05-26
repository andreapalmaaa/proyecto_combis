package pantallas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pantalla1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pantalla1 frame = new pantalla1();
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
	public pantalla1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("administración de unidades futv tixkokob");
		lblNewLabel.setBounds(123, 11, 230, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Registrar viaje");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registroviajes CS= new registroviajes();
				 CS.setVisible(true);
			}
		});
		btnNewButton.setBounds(147, 61, 164, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrar descansos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reportedesc CS=new reportedesc();
				CS.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(153, 112, 158, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Registrar incidencias");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporteinciden CS=new reporteinciden();
				CS.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(153, 161, 158, 23);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Asignación de turnos");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asignacion CS=new asignacion();
				CS.setVisible(true);
			}
		});
		btnNewButton_1_1_1.setBounds(153, 205, 158, 23);
		contentPane.add(btnNewButton_1_1_1);

	}
}
