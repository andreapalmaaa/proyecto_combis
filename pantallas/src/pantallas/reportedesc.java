package pantallas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextField;

public class reportedesc extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fin;
	private JTextField ini;
	Connection conexionbd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reportedesc frame = new reportedesc();
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
	public reportedesc() {
		Conexion c = new Conexion();
		conexionbd = c.conectar();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registrar descanso");
		lblNewLabel.setBounds(121, 11, 148, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Selecciona el numero de unidad:");
		lblNewLabel_1.setBounds(30, 55, 169, 14);
		contentPane.add(lblNewLabel_1);
		
		JTextArea chofer = new JTextArea();
		chofer.setBounds(152, 93, 86, 19);
		contentPane.add(chofer);
		
		fin = new JTextField(LocalTime.now().withSecond(0).withNano(0).toString());
		fin.setBounds(175, 175, 76, 20);
		contentPane.add(fin);
		fin.setColumns(10);
		
		ini = new JTextField(LocalTime.now().withSecond(0).withNano(0).toString());
		ini.setBounds(175, 147, 76, 17);
		contentPane.add(ini);
		ini.setColumns(10);
		
		JComboBox comboBoxuni = new JComboBox();
		comboBoxuni.setModel(new DefaultComboBoxModel(new String[] {"T01", "T02", "T03", "T04", "T05", "T06", "T07", "T08", "T09", "T10", "T11", "T12", "T13", "T14", "T15", "T16", "T17", "T18", "T19", "T20"}));
		comboBoxuni.setBounds(195, 51, 44, 22);
		contentPane.add(comboBoxuni);
		comboBoxuni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "SELECT nombre FROM choferes WHERE id_combi=?";
	
					PreparedStatement ps =conexionbd.prepareStatement(sql);
					ps.setString(1,comboBoxuni.getSelectedItem().toString());
					ResultSet rs =ps.executeQuery();
	
					if(rs.next()) {
	
					chofer.setText(rs.getString("nombre"));

					}

				} catch(Exception ex) {

					System.out.println(ex);

				}

			}

		});
		
		
		JLabel lblNewLabel_2 = new JLabel("Chofer en descanso:");
		lblNewLabel_2.setBounds(30, 98, 112, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Hora de inicio del descanso:");
		lblNewLabel_3.setBounds(30, 149, 135, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql ="INSERT INTO descansos (id_combi,nombre,inicio,fin) VALUES (?, ?, ?, ?)";

					PreparedStatement ps = conexionbd.prepareStatement(sql);

					ps.setString(1,comboBoxuni.getSelectedItem().toString());

					ps.setString(2,chofer.getText());

					ps.setString(3,ini.getText());

					ps.setString(4,fin.getText());
				
					String horasini = ini.getText();
	                if (horasini.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Ingresa correctamente la hora de inicio del descanso");
	                    return;
	                }
	                String horafin = fin.getText();

	                if(horafin.isEmpty()) {
	                	JOptionPane.showMessageDialog(null,"Ingresa correctamente la hora del finn del descanso");
	                	return;

	                }
	                
	                ps.executeUpdate();
	                JOptionPane.showMessageDialog(null,"Descanso registrado correctamente");
				}
	                catch(Exception ex) {

					System.out.println(ex);

				}

			}

		});
		
		btnNewButton.setBounds(152, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3_1 = new JLabel("Hora fin del descanso:");
		lblNewLabel_3_1.setBounds(30, 185, 135, 14);
		contentPane.add(lblNewLabel_3_1);
		
		
	}
}
