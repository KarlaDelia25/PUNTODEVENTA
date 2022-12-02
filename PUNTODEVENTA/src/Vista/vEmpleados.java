package Vista;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.daoEmpleados;
import Modelo.Empleados;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class vEmpleados extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnSeleccionar;
	private JLabel lblId;
	private JTable tblClientes;
	private JScrollPane scrollPane;
	daoEmpleados dao = new daoEmpleados();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Empleados> lista;
	int fila = -1;
	Empleados empleados = new Empleados();
	private JTable tblEmpleados;
	private JTextField txtRfc;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vClientes frame = new vClientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void actualizarTabla() {

		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.consultaempleados();
		for (Empleados em : lista) {
			Object emp[] = new Object[4];
			emp[0] = em.getIdEmpleados();
			emp[1] = em.getNombre();
			emp[2] = em.getEmail();
			emp[3] = em.getTelefono();
			emp[4] = em.getDireccion();
			modelo.addRow(emp);

		}
		tblEmpleados.setModel(modelo);
	}

	public void limpiar() {
		lblId.setText("");
		txtNombre.setText("");
		txtEmail.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		

	}

	public vEmpleados() {
		setTitle("EMPLEADOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 26, 46, 14);
		contentPane.add(lblNewLabel);

		lblId = new JLabel("0");
		lblId.setBounds(38, 26, 46, 14);
		contentPane.add(lblId);

		JLabel lblNewLabel_2 = new JLabel("NOMBRE");
		lblNewLabel_2.setBounds(10, 74, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtNombre = new JTextField();
		txtNombre.setBounds(62, 71, 101, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(62, 111, 101, 20);
		contentPane.add(txtEmail);

		JLabel lblNewLabel_2_1 = new JLabel("EMAIL");
		lblNewLabel_2_1.setBounds(10, 114, 46, 14);
		contentPane.add(lblNewLabel_2_1);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(62, 159, 101, 20);
		contentPane.add(txtTelefono);

		JLabel lblNewLabel_2_2 = new JLabel("TELEFONO");
		lblNewLabel_2_2.setBounds(10, 162, 46, 14);
		contentPane.add(lblNewLabel_2_2);

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(62, 190, 101, 20);
		contentPane.add(txtDireccion);

		JLabel uihh = new JLabel("DIREECION");
		uihh.setBounds(10, 188, 46, 14);
		contentPane.add(uihh);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNombre.getText().equals("") || txtEmail.getText().equals("")
							|| txtTelefono.getText().equals("") || txtDireccion.getText().equals("")||txtRfc.getText().equals("")){
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "CAMPOS VACÍOS");
						return;
					}
					Empleados empleados = new Empleados();
					empleados.setNombre(txtNombre.getText());
					empleados.setEmail(txtEmail.getText());
					empleados.setTelefono(Integer.parseInt(txtTelefono.getText().toString()));
					empleados.setDireccion(txtDireccion.getText());
					if (dao.insertarEmpleado(empleados)) {
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnAgregar.setBounds(10, 268, 89, 23);
		contentPane.add(btnAgregar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion=JOptionPane.showConfirmDialog(null,"ESTAS SEGURO DE ELIMINAR ESTE PROVEEDOR??","ELIMINAR CARACTERISTICAS",JOptionPane.YES_NO_OPTION);
				    if (opcion ==0) {
					if (dao.eliminarEmpleados(empleados.getIdEmpleados())) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "SE ELIMINÓ CORRECTAMENTE");

					} else {
						JOptionPane.showMessageDialog(null, "ERROR");

					}
				    }

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
				
			}
		});
		btnEliminar.setBounds(10, 302, 89, 23);
		contentPane.add(btnEliminar);

		btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNombre.getText().equals("") || txtEmail.getText().equals("")
							|| txtTelefono.getText().equals("") || txtDireccion.getText().equals("")) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "CAMPOS VACÍOS");
						return;
					}
					empleados.setNombre(txtNombre.getText());
					empleados.setEmail(txtEmail.getText());
					empleados.setTelefono(Integer.parseInt(txtTelefono.getText().toString()));
					empleados.setDireccion(txtDireccion.getText());
					if (dao.editarempleados(empleados)) {
						JOptionPane.showMessageDialog(null, "SE EDITÓ CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
					
				}
			}
		});
		btnEditar.setBounds(10, 337, 89, 23);
		contentPane.add(btnEditar);

		btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnBorrar.setBounds(10, 371, 89, 23);
		contentPane.add(btnBorrar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(173, 11, 610, 365);
		contentPane.add(scrollPane);
		
		tblEmpleados = new JTable();
		scrollPane.setViewportView(tblEmpleados);
		
		txtRfc = new JTextField();
		txtRfc.setColumns(10);
		txtRfc.setBounds(62, 224, 101, 20);
		contentPane.add(txtRfc);
		
		JLabel lblRfc = new JLabel("RFC");
		lblRfc.setBounds(10, 227, 46, 14);
		contentPane.add(lblRfc);

		tblEmpleados = new JTable();
		tblEmpleados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblEmpleados.getSelectedRow();
				empleados = lista.get(fila);
				lblId.setText("" + empleados.getIdEmpleados());
				txtNombre.setText(empleados.getNombre());
				txtEmail.setText(empleados.getEmail());
				txtTelefono.setText("" + empleados.getTelefono());
				txtDireccion.setText(empleados.getDireccion());

			}
		});
		tblClientes.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		actualizarTabla();

		
		modelo.addColumn("ID");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("EMAIL");
		modelo.addColumn("TELÉFONO");
		modelo.addColumn("DIRECCIÓN");
		actualizarTabla();
	
	
	}
}
