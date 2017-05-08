package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditParameters extends JFrame {

	private JPanel contentPane;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditParameters frame = new EditParameters();
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
	public EditParameters() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(338, 52, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.setBounds(338, 106, 89, 23);
		contentPane.add(btnNewButton_4);
	}

}
