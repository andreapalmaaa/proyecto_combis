package pantallas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;

public class reporteinciden extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField descripcion;
	private JTextField hora;
	private JTextField textfecha;
	Connection conexionbd;
	private JTable reporte;
    DefaultTableModel modelo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reporteinciden frame = new reporteinciden();
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
	public reporteinciden() {
		Conexion c = new Conexion();
		conexionbd = c.conectar();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Reportar incidencia");
		lblNewLabel.setBounds(158, 11, 141, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Selecciona el numero de unidad:");
		lblNewLabel_1_1.setBounds(10, 40, 169, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Placa:");
		lblNewLabel_1.setBounds(10, 80, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JTextArea placa = new JTextArea();
		placa.setBounds(76, 75, 117, 22);
		contentPane.add(placa);
		
		JLabel lblNewLabel_6 = new JLabel("Numero de licencia:");
		lblNewLabel_6.setBounds(10, 124, 98, 14);
		contentPane.add(lblNewLabel_6);
		
		JTextArea licencia = new JTextArea();
		licencia.setBounds(118, 119, 88, 19);
		contentPane.add(licencia);
		
		JComboBox comboBoxunidad = new JComboBox();
		comboBoxunidad.setModel(new DefaultComboBoxModel(new String[] {"T01", "T02", "T03", "T04", "T05", "T06", "T07", "T08", "T09", "T10", "T11", "T12", "T13", "T14", "T15", "T16", "T17", "T18", "T19", "T20"}));
		comboBoxunidad.setBounds(189, 36, 44, 22);
		contentPane.add(comboBoxunidad);
		comboBoxunidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "SELECT combis.placa, choferes.num_licencia FROM combis INNER JOIN choferes ON combis.id_combi = choferes.id_combi WHERE combis.id_combi=?";

					PreparedStatement ps = conexionbd.prepareStatement(sql);

					ps.setString(1,comboBoxunidad.getSelectedItem().toString());

					ResultSet rs = ps.executeQuery();

					if(rs.next()) {
						placa.setText(rs.getString("placa"));
						licencia.setText(rs.getString("num_licencia"));

					}

				} catch(Exception ex) {

					System.out.println(ex);

				}

			}
		});
		
		
		JLabel fecha = new JLabel("Fecha:");
		fecha.setBounds(10, 163, 98, 17);
		contentPane.add(fecha);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo de incidencia:");
		lblNewLabel_2.setBounds(10, 233, 88, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Descripción:");
		lblNewLabel_3.setBounds(10, 308, 88, 14);
		contentPane.add(lblNewLabel_3);
		
		descripcion = new JTextField();
		descripcion.setColumns(10);
		descripcion.setBounds(76, 291, 293, 49);
		contentPane.add(descripcion);
		
		JLabel lblhora = new JLabel("hora:");
		lblhora.setBounds(10, 196, 46, 14);
		contentPane.add(lblhora);
		
		hora = new JTextField(LocalTime.now().withSecond(0).withNano(0).toString());
		hora.setBounds(93, 193, 86, 20);
		contentPane.add(hora);
		hora.setColumns(10);
		
		JComboBox tipoinci = new JComboBox();
		tipoinci.setModel(new DefaultComboBoxModel(new String[] {"", "choque", "trafico", "se descompuso el vehiculo", "incidente con pasajero"}));
		tipoinci.setBounds(108, 229, 141, 22);
		contentPane.add(tipoinci);
		
		JLabel lblNewLabel_4 = new JLabel("estado:");
		lblNewLabel_4.setBounds(10, 264, 46, 18);
		contentPane.add(lblNewLabel_4);
		
		JComboBox estado = new JComboBox();
		estado.setModel(new DefaultComboBoxModel(new String[] {"Resuelta", "En proceso"}));
		estado.setBounds(76, 258, 110, 22);
		contentPane.add(estado);
		
		JLabel lblNewLabel_5 = new JLabel("reporte:");
		lblNewLabel_5.setBounds(146, 385, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton_2 = new JButton("ver reporte en excel");
		btnNewButton_2.setBounds(452, 437, 145, 37);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportarExcel exp = new ExportarExcel();
		        exp.exportarExcel(reporte);
			}
		});
		
		JButton btnregistrar = new JButton("Registrar");
		btnregistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "INSERT INTO incidencias (id_combi, tipo, descripcion, fecha, estado, hora) VALUES (?, ?, ?, ?, ?, ?)";

					PreparedStatement ps = conexionbd.prepareStatement(sql);

					ps.setString(1, comboBoxunidad.getSelectedItem().toString());
					ps.setString(2, tipoinci.getSelectedItem().toString());
					ps.setString(3, descripcion.getText());
					ps.setString(4, textfecha.getText());
					ps.setString(5, estado.getSelectedItem().toString());
					ps.setString(6, hora.getText());

					ps.executeUpdate();
	                JOptionPane.showMessageDialog(null,"Incidencia registrada correctamente");
					} 
					catch(Exception ex) {
						System.out.println(ex);
				}
			}
		});
				
		btnregistrar.setBounds(91, 351, 89, 23);
		contentPane.add(btnregistrar);
		
		textfecha = new JTextField((java.time.LocalDate.now().toString()));
		textfecha.setBounds(93, 161, 86, 20);
		contentPane.add(textfecha);
		textfecha.setColumns(10);
		
		String columnas[] = {"Unidad", "Placa", "Licencia", "Fecha", "Hora", "Tipo", "Estado", "Descripcion"};
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(columnas);
        
        reporte = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(reporte);
        scroll.setBounds(22, 409, 420, 111);
        contentPane.add(scroll);
		
        JButton btnNewButton_1 = new JButton("Imprimir reporte");
        btnNewButton_1.setBounds(189, 351, 110, 23);
        contentPane.add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelo.addRow(new Object[]{
                    comboBoxunidad.getSelectedItem(),
                    placa.getText(),
                    licencia.getText(),
                    textfecha.getText(),
                    hora.getText(),
                    tipoinci.getSelectedItem(),
                    estado.getSelectedItem(),
                    descripcion.getText()
                });
            }
        });
    } 

} 
       
			
