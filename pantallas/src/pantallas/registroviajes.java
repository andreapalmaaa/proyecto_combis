package pantallas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Time;
import conexion.Conexion;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;

public class registroviajes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ORIGEN;
	private JTextField DESTINO;
	Connection conexionbd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registroviajes frame = new registroviajes();
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
	public registroviajes() {
		Conexion c = new Conexion();
		conexionbd = c.conectar();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(112, 79, 129, 22);
		contentPane.add(textArea);
		
		JComboBox comboBoxUNIDAD = new JComboBox();
		comboBoxUNIDAD.setModel(new DefaultComboBoxModel(new String[] {"T01", "T02", "T03", "T04", "T05", "T06", "T07", "T08", "T09", "T10", "T11", "T12", "T13", "T14", "T15", "T16", "T17", "T18", "T19", "T20"}));
		comboBoxUNIDAD.setBounds(182, 36, 115, 22);
		contentPane.add(comboBoxUNIDAD);
		comboBoxUNIDAD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	
					String sql = "SELECT nombre FROM choferes WHERE id_combi=?";
	
					PreparedStatement ps =conexionbd.prepareStatement(sql);
	
					ps.setString(1,comboBoxUNIDAD.getSelectedItem().toString());
	
					ResultSet rs =ps.executeQuery();
	
					if(rs.next()) {
	
					textArea.setText(rs.getString("nombre"));

					}

				} catch(Exception ex) {

					System.out.println(ex);

				}

			}

		});
		
		JLabel lblNewLabel = new JLabel("Registro de viajes");
		lblNewLabel.setBounds(160, 11, 164, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Selecciona el numero de unidad");
		lblNewLabel_1.setBounds(10, 36, 162, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Chofer:");
		lblNewLabel_2.setBounds(10, 84, 129, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Ubicación actual:");
		lblNewLabel_2_1.setBounds(10, 124, 129, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Destino:");
		lblNewLabel_3.setBounds(10, 163, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Hora de llegada (destino):");
		lblNewLabel_3_1.setBounds(0, 236, 164, 14);
		contentPane.add(lblNewLabel_3_1);
		
		JTextArea textArea_1_1 = new JTextArea(LocalTime.now().withSecond(0).withNano(0).toString());
		textArea_1_1.setFont(new Font("Arial", Font.PLAIN, 13));;
		textArea_1_1.setBounds(155, 199, 129, 22);
		contentPane.add(textArea_1_1);
		
		ORIGEN = new JTextField();
		ORIGEN.setBounds(115, 121, 86, 20);
		contentPane.add(ORIGEN);
		ORIGEN.setColumns(10);
		
		DESTINO = new JTextField();
		DESTINO.setColumns(10);
		DESTINO.setBounds(115, 160, 86, 20);
		contentPane.add(DESTINO);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setBounds(324, 215, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Hora de salida (origen):");
		lblNewLabel_3_1_1.setBounds(0, 202, 145, 14);
		contentPane.add(lblNewLabel_3_1_1);
		
		JTextArea h_llegada = new JTextArea(LocalTime.now().withSecond(0).withNano(0).toString());
		h_llegada.setFont(new Font("Arial", Font.PLAIN, 13));
		h_llegada.setBounds(160, 233, 129, 22);
		contentPane.add(h_llegada);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql ="INSERT INTO viajes (id_combi, origen, destino, h_salida,h_llegada) VALUES (?, ?, ?, ?,?)";

					PreparedStatement ps =conexionbd.prepareStatement(sql);

					ps.setString(1,comboBoxUNIDAD.getSelectedItem().toString());

					ps.setString(2,ORIGEN.getText());

					ps.setString(3,DESTINO.getText());

					ps.setString(4,textArea_1_1.getText());
					
					ps.setString(5,h_llegada.getText());
					
					String horasalida = textArea_1_1.getText();
	                if (horasalida.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Ingresa correctamente la hora");
	                    return;
	                }
	                String horallegada = h_llegada.getText();

	                if(horallegada.isEmpty()) {
	                	JOptionPane.showMessageDialog(null,
	                	"Ingresa correctamente la hora de llegada");
	                	return;

	                }
	                
	                ps.executeUpdate();
	                JOptionPane.showMessageDialog(null,"Viaje registrado correctamente");
				}
	                catch(Exception ex) {

					System.out.println(ex);

				}

			}

		});
		
	}
}
