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

import Dao.daoTaller;
import Modelo.Taller;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class vTaller extends JFrame {

	private JPanel contentPane;
	private JTextField txtDetalles;
	private JTextField txtCostototal;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnSeleccionar;
	private JLabel lblId;
	private JTable tblTaller;
	private JScrollPane scrollPane;
	daoTaller dao = new daoTaller();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Taller> lista;
	int fila = -1;
	Taller caracteristicas = new Taller();
	private JTextField textRefacciones;
	private JLabel hioj;
	private JTextField txtMecanico;
	private JTextField txtCliente;
	private JLabel lblCliente;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vTaller frame = new vTaller();
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
		lista = dao.consultaTaller();
		for (Taller ta : lista) {
			Object tall[] = new Object[4];
			tall[0] = ta.getId();
			tall[1] = ta.getDetalles();
			tall[2] = ta.getRefacciones();
			tall[3] = ta.getCostototal();
			tall[4] = ta.getMecanico();
			tall[5] = ta.getCliente();
			modelo.addRow(tall);

		}
		tblTaller.setModel(modelo);
	}

	public void limpiar() {
		lblId.setText("");
		txtDetalles.setText("");
		textRefacciones.setText("");
		txtCostototal.setText("");
		txtMecanico.setText("");

	}

	public vTaller() {
		setTitle("TALLER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 626);
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

		JLabel lblNewLabel_2 = new JLabel("DETALLES");
		lblNewLabel_2.setBounds(48, 51, 78, 14);
		contentPane.add(lblNewLabel_2);

		txtDetalles = new JTextField();
		txtDetalles.setBounds(10, 71, 153, 178);
		contentPane.add(txtDetalles);
		txtDetalles.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("REFACCIONES");
		lblNewLabel_2_1.setBounds(48, 260, 101, 14);
		contentPane.add(lblNewLabel_2_1);

		txtCostototal = new JTextField();
		txtCostototal.setColumns(10);
		txtCostototal.setBounds(62, 463, 101, 20);
		contentPane.add(txtCostototal);

		JLabel lblNewLabel_2_2 = new JLabel("COSTO TOTAL");
		lblNewLabel_2_2.setBounds(10, 469, 46, 14);
		contentPane.add(lblNewLabel_2_2);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtDetalles.getText().equals("") || textRefacciones.getText().equals("")
							|| txtCostototal.getText().equals("") || txtMecanico.getText().equals("")) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "CAMPOS VACÍOS");
						return;
					}
					Taller taller = new Taller();
					taller.setDetalles(txtDetalles.getText());
					taller.setRefacciones(textRefacciones.getText());
					taller.setCostototal(Double.parseDouble(txtCostototal.getText().toString()));
					taller.setMecanico(txtMecanico.getText());
					taller.setMecanico(txtCliente.getText());
					if (dao.insertarTaller(caracteristicas)) {
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnAgregar.setBounds(397, 553, 89, 23);
		contentPane.add(btnAgregar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion=JOptionPane.showConfirmDialog(null,"ESTAS SEGURO DE ELIMINAR LA REFACCIÓN??","ELIMINAR REFACCIÓN",JOptionPane.YES_NO_OPTION);
				    if (opcion ==0) {
					if (dao.eliminarTaller(caracteristicas.getId())) {
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
		btnEliminar.setBounds(496, 553, 89, 23);
		contentPane.add(btnEliminar);

		btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtDetalles.getText().equals("") || textRefacciones.getText().equals("")
							|| txtCostototal.getText().equals("") || txtMecanico.getText().equals("") || txtCliente.getText().equals("")){ 
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "CAMPOS VACÍOS");
						return;
					}
					caracteristicas.setDetalles(txtDetalles.getText());
					caracteristicas.setRefacciones(textRefacciones.getText());
					caracteristicas.setCostototal(Double.parseDouble(txtCostototal.getText().toString()));
					caracteristicas.setMecanico(txtMecanico.getText());
					caracteristicas.setCliente(txtCliente.getText());
					if (dao.editarTaller(caracteristicas)) {
						JOptionPane.showMessageDialog(null, "SE EDITÓ CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
					
				}
			}
		});
		btnEditar.setBounds(595, 553, 89, 23);
		contentPane.add(btnEditar);

		btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnBorrar.setBounds(694, 553, 89, 23);
		contentPane.add(btnBorrar);



		scrollPane = new JScrollPane();
		scrollPane.setBounds(173, 11, 610, 531);
		contentPane.add(scrollPane);

		tblTaller = new JTable();
		tblTaller.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblTaller.getSelectedRow();
				caracteristicas = lista.get(fila);
				lblId.setText("" + caracteristicas.getId());
				txtDetalles.setText(caracteristicas.getDetalles());
				textRefacciones.setText(caracteristicas.getRefacciones());
				txtCostototal.setText("" + caracteristicas.getCostototal());
				txtMecanico.setText(caracteristicas.getMecanico());
				txtCliente.setText(caracteristicas.getCliente());

			}
		});
		tblTaller.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblTaller);
		
		textRefacciones = new JTextField();
		textRefacciones.setColumns(10);
		textRefacciones.setBounds(10, 280, 153, 178);
		contentPane.add(textRefacciones);
		
		hioj = new JLabel("MECANICO");
		hioj.setBounds(10, 516, 46, 14);
		contentPane.add(hioj);
		
		txtMecanico = new JTextField();
		txtMecanico.setColumns(10);
		txtMecanico.setBounds(62, 513, 101, 20);
		contentPane.add(txtMecanico);
		
		JButton btnNewButton = new JButton("CONSULTAR DATOS DEL CLIENTE");
		btnNewButton.setBounds(213, 553, 174, 23);
		contentPane.add(btnNewButton);
		
		txtCliente = new JTextField();
		txtCliente.setColumns(10);
		txtCliente.setBounds(62, 553, 101, 20);
		contentPane.add(txtCliente);
		
		lblCliente = new JLabel("CLIENTE");
		lblCliente.setBounds(10, 556, 46, 14);
		contentPane.add(lblCliente);
		actualizarTabla();
		modelo.addColumn("ID");
		modelo.addColumn("DETALLES");
		modelo.addColumn("REFACCIONES");
		modelo.addColumn("COSTO TOTAL");
		modelo.addColumn("MECÁNICO");
		modelo.addColumn("CLIENTE");
		actualizarTabla();
	
	
	}
}