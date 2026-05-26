package pantallas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class asignacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					asignacion frame = new asignacion();
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
	public asignacion() {
		Conexion c = new Conexion();
		Connection conexionbd = c.conectar();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Combi a salir:");
		lblNewLabel.setBounds(10, 24, 89, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("orden combis proximas a salir:");
		lblNewLabel_1.setBounds(230, 84, 158, 14);
		contentPane.add(lblNewLabel_1);
		
		JComboBox unidades = new JComboBox();
		unidades.setModel(new DefaultComboBoxModel(new String[] {"T01", "T02", "T03", "T04", "T05", "T06", "T07", "T08", "T09", "T10", "T11", "T12", "T13", "T14", "T15", "T16", "T17", "T18", "T19", "T20"}));
		unidades.setBounds(82, 22, 73, 18);
		contentPane.add(unidades);
		
		JLabel lblNewLabel_2 = new JLabel("combis que ya salieron:");
		lblNewLabel_2.setBounds(10, 84, 112, 14);
		contentPane.add(lblNewLabel_2);
		
		JTextArea yasalieron = new JTextArea();
		yasalieron.setBounds(10, 98, 188, 152);
		contentPane.add(yasalieron);
		
		JTextArea proximassalir = new JTextArea();
		proximassalir.setBounds(230, 98, 188, 152);
		contentPane.add(proximassalir);
		
		
		JButton registrar = new JButton("registrar salida");
		registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql =
					"UPDATE combis SET estado=? WHERE id_combi=?";

					PreparedStatement ps =conexionbd.prepareStatement(sql);
					ps.setBoolean(1, true);
					ps.setString(2,unidades.getSelectedItem().toString());

					ps.executeUpdate();
					yasalieron.setText("");
					proximassalir.setText("");

					String sql2 ="SELECT * FROM combis";

					PreparedStatement ps2 =conexionbd.prepareStatement(sql2);

					ResultSet rs =ps2.executeQuery();

					while(rs.next()) {

						if(rs.getBoolean("estado")==true) {

							yasalieron.append(rs.getString("id_combi")+ "\n");

						}

						else {
							proximassalir.append(rs.getString("id_combi")+ "\n");
						}
					}

				} catch(Exception ex) {

					System.out.println(ex);

				}

			}
		});
		registrar.setBounds(167, 20, 107, 23);
		contentPane.add(registrar);
	}
}
