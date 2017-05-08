package classes;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.TitledBorder;

import java.io.*;


public class GUI extends JFrame
{
	private	JScrollPane scrollPane;
	private JTable table;
	private MyTableModel model;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel worksheetLabel;
	
	private static Structure struct;
	private static IOClass ioObject;
	
	public class EditDecision extends JFrame 
	{
		private JPanel contentPane;
		public EditDecision() 
		{
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Rename Decision: ");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel.setBounds(10, 37, 160, 36);
			contentPane.add(lblNewLabel);
			
			textField_5 = new JTextField();
			textField_5.setBounds(159, 48, 191, 20);
			contentPane.add(textField_5);
			textField_5.setColumns(10);
			
			JButton btnRename = new JButton("Rename");
			btnRename.setBounds(178, 102, 89, 23);
			btnRename.addActionListener(new RenameDecisionListener());
			contentPane.add(btnRename);
		}
	}
	
	public class EditParameters extends JFrame {

		private JPanel contentPane;


		/**
		 * Create the frame.
		 */
		public EditParameters() 
		{
			setDefaultCloseOperation(EditOptions.HIDE_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblAddParam = new JLabel("Add Parameter: ");
			lblAddParam.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblAddParam.setBounds(10, 39, 128, 43);
			contentPane.add(lblAddParam);
			
			JLabel lblDeleteParam = new JLabel("Delete Parameter: ");
			lblDeleteParam.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblDeleteParam.setBounds(10, 93, 146, 43);
			contentPane.add(lblDeleteParam);
			
			textField_3 = new JTextField();
			textField_3.setBounds(155, 53, 173, 20);
			contentPane.add(textField_3);
			textField_3.setColumns(10);
			
			textField_4 = new JTextField();
			textField_4.setBounds(155, 107, 173, 20);
			contentPane.add(textField_4);
			textField_4.setColumns(10);
			
			JButton btnNewButton_3 = new JButton("Add");
			btnNewButton_3.addActionListener(new ParamAddListener());
			btnNewButton_3.setBounds(338, 52, 89, 23);
			contentPane.add(btnNewButton_3);
			
			JButton btnNewButton_4 = new JButton("Delete");
			btnNewButton_4.addActionListener(new ParamDeleteListener());
			btnNewButton_4.setBounds(338, 106, 89, 23);
			contentPane.add(btnNewButton_4);
		}

	}
	
	public class EditOptions extends JFrame 
	{


		/**
		 * Create the frame.
		 */
		private JPanel contentPane;

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
			btnNewButton.addActionListener(new GUI.AddListener());
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
			btnDelete.addActionListener(new GUI.DeleteListener());
			btnDelete.setBounds(322, 101, 89, 23);
			contentPane.add(btnDelete);
		}
	}
	
	
	class EditDecisionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			EditDecision eDecisions = new EditDecision();
			eDecisions.setVisible(true);
		}
	}
	
	class RenameDecisionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			model.rename(textField_5.getText());
			textField_5.setText("");
		}
	}
	
	class ComputeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			model.computeRankings();
		}
	}
	
	class EditOptionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			EditOptions eOptions = new EditOptions();
			eOptions.setVisible(true);
		}
	}
	
	class EditParamListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			EditParameters eParams = new EditParameters();
			eParams.setVisible(true);
		}
	}
	
	class AddListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			model.addOption(textField.getText());
			textField.setText("");
		}
	}
	
	class DeleteListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			model.deleteOption(textField_1.getText());
			textField_1.setText("");
		}
	}
	
	class ParamAddListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			model.addParameter(textField_3.getText());
			textField_3.setText("");
		}
	}
	
	class ParamDeleteListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			model.deleteParameter(textField_4.getText());
			textField_4.setText("");
		}
	}
	
	class OpenListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(GUI.this);
			if(returnVal == JFileChooser.APPROVE_OPTION) 
			{
				String decode = fc.getSelectedFile().getAbsolutePath();
				try
				{
					struct = ioObject.decodeFile(decode);
					worksheetLabel.setText("Worksheet: " + fc.getSelectedFile().getName());
					model.setStructure(struct);
				}
				catch(IOException x){}
			}
		}
	}
	
	class NewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(GUI.this);
			if(returnVal == JFileChooser.APPROVE_OPTION) 
			{
				String decode = fc.getSelectedFile().getAbsolutePath();
				try
				{
					struct = ioObject.decodeFile(decode);
					model.setStructure(struct);
				}
				catch(IOException x){}
			}
			struct = new Structure(0, fc.getSelectedFile().getName());
			model.setStructure(struct);
		}
	}
	
	class AboutListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(GUI.this, "(C)opyright Spencer Brydges 2014");
		}
	}
	
	class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
	class SaveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			model.saveContents();
		}
	}
	
	public GUI(Dimension screenSize)
	{
		super();
		ioObject = new IOClass();

		getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, screenSize.width/2, 91);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnEditParameters = new JButton("Edit Parameters");
		btnEditParameters.setBounds(294, 68, 143, 23);
		btnEditParameters.addActionListener(new EditParamListener());
		panel.add(btnEditParameters);
		
		JButton btnEditOptions = new JButton("Edit Options");
		btnEditOptions.setBounds(163, 68, 121, 23);
		btnEditOptions.addActionListener(new EditOptionListener());
		panel.add(btnEditOptions);
		
		JButton btnEditDecision = new JButton("Edit Decision");
		btnEditDecision.setBounds(10, 68, 143, 23);
		btnEditDecision.addActionListener(new EditDecisionListener());
		panel.add(btnEditDecision);
		
		JButton btnComputeRankings = new JButton("Compute Rankings");
		btnComputeRankings.setBounds(449, 68, 121, 23);
		btnComputeRankings.addActionListener(new ComputeListener());
		panel.add(btnComputeRankings);
		
		worksheetLabel = new JLabel("Worksheet: ");
		worksheetLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		worksheetLabel.setBounds(10, 0, 385, 35);
		panel.add(worksheetLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Status: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(405, 1, 178, 35);
		panel.add(lblNewLabel_1);
		
		
		JPanel panel_1 = new JPanel();
		//panel_1.setBounds(0, 92, 593, 536);
		panel_1.setBounds(0, 92, screenSize.width-15, 536);
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		TextArea textArea = new TextArea();
		textArea.setBounds(0, 634, screenSize.width-15, (screenSize.height/4)+40);
		getContentPane().add(textArea);
		model = new MyTableModel(struct, ioObject, textArea);
		table = new JTable(model);
		
		
		scrollPane = new JScrollPane(table);
		Dimension d = new Dimension(panel_1.getWidth(), panel_1.getHeight());
		scrollPane.setPreferredSize(d);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new NewListener());
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new OpenListener());
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new SaveListener());
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ExitListener());
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new AboutListener());
		mnHelp.add(mntmAbout);
		
		
	}
}