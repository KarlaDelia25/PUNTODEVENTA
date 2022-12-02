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

import Dao.daoProveedores;
import Modelo.Proveedores;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class vProveedores extends JFrame {

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
	private JTable tblProveedores;
	private JScrollPane scrollPane;
	daoProveedores dao = new daoProveedores();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Proveedores> lista;
	int fila = -1;
	Proveedores provedores = new Proveedores();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vProveedores frame = new vProveedores();
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
		lista = dao.consultaProveedores();
		for (Proveedores pr : lista) {
			Object prov[] = new Object[4];
			prov[0] = pr.getId();
			prov[1] = pr.getNombre();
			prov[2] = pr.getEmail();
			prov[3] = pr.getTelefono();
			prov[4] = pr.getDireccion();
			modelo.addRow(prov);

		}
		tblProveedores.setModel(modelo);
	}

	public void limpiar() {
		lblId.setText("");
		txtNombre.setText("");
		txtEmail.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");

	}

	public vProveedores() {
		setTitle("PROVEEDORES");
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
							|| txtTelefono.getText().equals("") || txtDireccion.getText().equals("")) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "CAMPOS VACÍOS");
						return;
					}
					Proveedores proveedores = new Proveedores();
					proveedores.setNombre(txtNombre.getText());
					proveedores.setEmail(txtEmail.getText());
					proveedores.setTelefono(Integer.parseInt(txtTelefono.getText().toString()));
					proveedores.setDireccion(txtDireccion.getText());
					if (dao.insertarProveedores(proveedores)) {
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnAgregar.setBounds(10, 255, 89, 23);
		contentPane.add(btnAgregar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion=JOptionPane.showConfirmDialog(null,"ESTAS SEGURO DE ELIMINAR ESTE PROVEEDOR??","ELIMINAR CARACTERISTICAS",JOptionPane.YES_NO_OPTION);
				    if (opcion ==0) {
					if (dao.eliminarProveedores(provedores.getId())) {
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
					provedores.setNombre(txtNombre.getText());
					provedores.setEmail(txtEmail.getText());
					provedores.setTelefono(Integer.parseInt(txtTelefono.getText().toString()));
					provedores.setDireccion(txtDireccion.getText());
					if (dao.editarProveedores(provedores)) {
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

		tblProveedores = new JTable();
		tblProveedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblProveedores.getSelectedRow();
				provedores = lista.get(fila);
				lblId.setText("" + provedores.getId());
				txtNombre.setText(provedores.getNombre());
				txtEmail.setText(provedores.getEmail());
				txtTelefono.setText("" + provedores.getTelefono());
				txtDireccion.setText(provedores.getDireccion());

			}
		});
		tblProveedores.setModel(new DefaultTableModel(
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

