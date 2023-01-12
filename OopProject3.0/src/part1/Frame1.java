package part1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JToggleButton;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.JLabel;

public class Frame1 {

	private JFrame frame;
	private JPasswordField passwordField_1;
	private JTextField textField;

	List<User>user;
    List<Food>food;
    List<Drink>drink;
    List<OrderList>orderList;
    
    User userLogin;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws FileNotFoundException 
	 */
	public Frame1() throws FileNotFoundException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws FileNotFoundException 
	 */
	private void initialize() throws FileNotFoundException {
		List<User>user = new ArrayList<>();

        //Read user from file
        Scanner readUserFile = new Scanner(new File("user.txt"));
        int fileloop = 0;
        while (readUserFile.hasNextLine()) {
            String a = readUserFile.nextLine();
            String[] b = a.split("/", 3);

            //Read and input user into arrayList
            user.add(new User("","",""));
            user.get(fileloop).setName(b[0]);
            user.get(fileloop).setNoMatrics(b[1]);

            // now convert the string to byte array
            // for decryption
            byte[] bb = new byte[b[2].length()];
            for (int i=0; i<b[2].length(); i++) {
                bb[i] = (byte) b[2].charAt(i);
            }
            String password = "";
            try {
                // decrypt the text
                String key = "Bar12345Bar12345";
                SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, aesKey);
                password = new String(cipher.doFinal(bb));
            }catch (Exception e) { //print error message if there is an error
                e.printStackTrace();
            }
            //read password into user arrayList
            user.get(fileloop).setPassword(password);

            fileloop++;
        }
        
        ImageIcon tf = new ImageIcon("tf.png");
        ImageIcon kl = new ImageIcon("klee.jpg");
        
		frame = new JFrame();
		frame.setForeground(Color.RED);
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.setResizable(false);
		frame.setBounds(600, 250, 720, 550);
		frame.setTitle("Tun Fatimah Food Ordering System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(kl.getImage());
		
		JList list = new JList();
		list.setBounds(330, 204, 1, 1);
		frame.getContentPane().add(list);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 128, 128));
		panel.setBounds(112, 75, 460, 335);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(255,255,255,200));
		
		Label label = new Label("No.Matrics :");
		label.setBounds(101, 141, 71, 21);
		panel.add(label);
		
		Label label_1 = new Label("Password :");
		label_1.setBounds(105, 175, 67, 21);
		panel.add(label_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(191, 175, 160, 30);
		panel.add(passwordField_1);
		
		Label label_2 = new Label("User Login");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 24));
		label_2.setBounds(153, 20, 153, 47);
		panel.add(label_2);
		label_2.setBackground(new Color(255,255,255,255));
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(184, 254, 85, 21);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean haveUser = false;
				if(textField.getText().equalsIgnoreCase("") && textField.getText()!=null) {
                	JOptionPane.showMessageDialog(null, "Please enter your No.Matrics", "Empty input", JOptionPane.WARNING_MESSAGE);
				}else if(passwordField_1.getText()!=null && passwordField_1.getText().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Please enter your password", "Empty input", JOptionPane.WARNING_MESSAGE);
				}else {
					for(int i=0; i<user.size(); i++){
						haveUser = false;
	                    if(user.get(i).getNoMatrics().equalsIgnoreCase(textField.getText())){
	                    	if(user.get(i).getPassword().equalsIgnoreCase(passwordField_1.getText())){
	                            userLogin = user.get(i);
	                    		JOptionPane.showMessageDialog(null, "Welcome to Food Ordering System for Tun Fatimah Residential College, "+userLogin.getName().toUpperCase(), "Login Success", JOptionPane.PLAIN_MESSAGE);
	                    		frame.setVisible(false);
	                    		Frame2 frame2;
								try {
									frame2 = new Frame2(userLogin);
									frame2.getFrame2().setVisible(true);
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
	                    		haveUser = true;
	                    		break;
	                    	}else {
	                    		JOptionPane.showMessageDialog(null, "Password Incorrect", "Wrong Password", JOptionPane.WARNING_MESSAGE);
	                    		haveUser = true;
	                    		break;
	                    	}	
	                    }
	                }
					if(!haveUser) {
					JOptionPane.showMessageDialog(null, "User does not found", "Error", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		textField = new JTextField();
		textField.setBounds(191, 132, 160, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 696, 503);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(tf);
		
	}
	
	public JFrame getFrame1() {
		return frame;
	}
}
