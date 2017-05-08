package classes;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.TitledBorder;

import java.io.*;


public class EditOptions extends JFrame 
{

	private static JScrollPane jscroll;
	private JTable table;
	private MyTableModel model;
	private JTextField textField;
	private JPanel contentPane;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public EditOptions() 
	{
		setDefaultCloseOperation(EditOptions.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddOption = new JLabel("Add Option: ");
		lblAddOption.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddOption.setBounds(10, 35, 114, 45);
		contentPane.add(lblAddOption);
		
		textField = new JTextField();
		textField.setBounds(139, 50, 173, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		//btnNewButton.addActionListener(new GUI.AddListener());
		btnNewButton.setBounds(322, 49, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblDeleteOption = new JLabel("Delete Option: ");
		lblDeleteOption.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDeleteOption.setBounds(10, 91, 152, 36);
		contentPane.add(lblDeleteOption);
		
		textField_1 = new JTextField();
		textField_1.setBounds(139, 102, 173, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(322, 101, 89, 23);
		contentPane.add(btnDelete);
	}
}