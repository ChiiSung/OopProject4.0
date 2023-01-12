package part1;

import java.awt.EventQueue;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumnModel;

public class Frame2 {

	private JFrame frmTunFatimahFood;
	public User userLogin;

	List<Food>food = new ArrayList<>();
    List<Drink>drink = new ArrayList<>();
    List<OrderList>orderList = new ArrayList<>();
    List<User>user = new ArrayList<>();
    
    int qtSlider;
    
	/**
	 * Create the application.
	 * @throws FileNotFoundException 
	 */
	public Frame2(User userLogin) throws FileNotFoundException {
		initialize(userLogin);
	}
	
	public JFrame getFrame2() {
		return frmTunFatimahFood;
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws FileNotFoundException 
	 */
	private void initialize(User userNow) throws FileNotFoundException {
		
        this.userLogin = userNow;
        readFile();
        
        ImageIcon kl = new ImageIcon("klee.jpg");
        ImageIcon df = new ImageIcon("df.jpg");
        ImageIcon rt0 = new ImageIcon("return.png");
        Image rt1 = rt0.getImage();
        Image newrt1 = rt1.getScaledInstance(21, 21, java.awt.Image.SCALE_SMOOTH);
        ImageIcon rt = new ImageIcon(newrt1);
		
		frmTunFatimahFood = new JFrame();
		frmTunFatimahFood.setTitle("Tun Fatimah Food Ordering System ("+ userLogin.getName().toUpperCase() +")...");
		frmTunFatimahFood.setResizable(false);
		frmTunFatimahFood.setBounds(350, 150, 1280, 720);
		frmTunFatimahFood.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTunFatimahFood.setIconImage(kl.getImage());
		
		JPanel panel = new JPanel();
		frmTunFatimahFood.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(209, 78, 864, 509);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255,255,255,200));
		
		JPanel foodPanel = new JPanel(new GridLayout(2, -1, 5, 5));
		foodPanel.setVisible(true);
		
		JPanel foodPanelOut = new JPanel();
		foodPanelOut.setBounds(209, 78, 864, 509);
		foodPanelOut.setVisible(false);
		
		JPanel drinkPanel = new JPanel(new GridLayout(2, -1, 5, 5));
		drinkPanel.setVisible(true);
		
		JPanel drinkPanelOut = new JPanel();
		drinkPanelOut.setBounds(209, 78, 864, 509);
		drinkPanelOut.setVisible(false);
		
		JPanel viewPanel = new JPanel();
		viewPanel.setBackground(new Color(255,255,255,200));
		viewPanel.setBounds(209,78,864,509);
    	panel.add(viewPanel);
		viewPanel.setVisible(false);
		
		JPanel foodPanelDetail = new JPanel();
		foodPanelDetail.setLayout(null);
		foodPanelDetail.setBackground(new Color(255,255,255));
		foodPanelDetail.setBounds(209,78,864,509);
    	panel.add(foodPanelDetail);
    	foodPanelDetail.setVisible(false);
    	getSlider(foodPanelDetail);
    	
    	JPanel drinkPanelDetail = new JPanel();
    	drinkPanelDetail.setLayout(null);
		drinkPanelDetail.setBackground(new Color(255,255,255));
		drinkPanelDetail.setBounds(209,78,864,509);
    	panel.add(drinkPanelDetail);
    	drinkPanelDetail.setVisible(false);
    	getSlider(drinkPanelDetail);
		
		JScrollPane fsp = new JScrollPane();
		fsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		fsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		fsp.setVisible(true);
		foodPanelOut.setLayout(null);
		fsp.setViewportView(foodPanel);
		fsp.setBounds(0,37,864,472);
		foodPanelOut.add(fsp);
		panel.add(foodPanelOut);
		
		JScrollPane dsp = new JScrollPane();
		dsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		dsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		dsp.setVisible(true);
		drinkPanelOut.setLayout(null);
		dsp.setViewportView(drinkPanel);
		dsp.setBounds(0,41,864,468);
		drinkPanelOut.add(dsp);
		panel.add(drinkPanelOut);
		
		JButton rtnFood = new JButton();
		rtnFood.setLocation(10, 10);
		rtnFood.setIcon(rt);
		rtnFood.setSize(63, 21);
		rtnFood.setFont(new Font("Tahoma", Font.PLAIN, 28));
		rtnFood.setActionCommand("Return");
		rtnFood.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			panel_1.setVisible(true);
			foodPanelOut.setVisible(false);
		  }
		});
		foodPanelOut.add(rtnFood);

		JButton rtnDrink = new JButton();
		rtnDrink.setLocation(10, 10);
		rtnDrink.setIcon(rt);
		rtnDrink.setSize(63, 21);
		rtnDrink.setFont(new Font("Tahoma", Font.PLAIN, 28));
		rtnDrink.setActionCommand("Return");
		rtnDrink.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			panel_1.setVisible(true);
			drinkPanelOut.setVisible(false);
		  }
		});
		drinkPanelOut.add(rtnDrink);
		
		JButton btnAddFood = new JButton("Add Food ");
		btnAddFood.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnAddFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				foodPanelOut.setVisible(true);
			}
		});
		btnAddFood.setBounds(208, 40, 452, 80);
		panel_1.add(btnAddFood);
		
		
		JButton btnAddDrink = new JButton("Add Drink");
		btnAddDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				drinkPanelOut.setVisible(true);
			}
		});
		btnAddDrink.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnAddDrink.setBounds(208, 130, 452, 80);
		panel_1.add(btnAddDrink);
		
		JButton back = new JButton();
		back.setLocation(10, 10);
		viewPanel.add(back);
		back.setIcon(rt);
		back.setSize(63, 21);
		back.setActionCommand("Return");
		back.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			viewPanel.setVisible(false);
			panel_1.setVisible(true);
		  }
		});
		
		JButton backFood = new JButton();
		backFood.setIcon(rt);
		backFood.setLocation(10, 10);
		backFood.setSize(63, 21);
		foodPanelDetail.add(backFood);
		backFood.setActionCommand("Return");
		backFood.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			foodPanelDetail.setVisible(false);
			panel_1.setVisible(true);
		  }
		});
		
		JButton backDrink = new JButton();
		backDrink.setIcon(rt);
		backDrink.setLocation(10, 10);
		backDrink.setSize(63, 21);
		drinkPanelDetail.add(backDrink);
		backDrink.setActionCommand("Return");
		backDrink.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			drinkPanelDetail.setVisible(false);
			panel_1.setVisible(true);
		  }
		});
		
		int userArr = orderList.get(0).findUser(orderList, userLogin);
		if(userArr != -1) {
			getTable(viewPanel);
		}
		
		JButton btnViewOrder = new JButton("View Order");
		btnViewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(orderList.size() !=0) {
            		int userArr = orderList.get(0).findUser(orderList, userLogin);
                    if(userArr != -1) {
                		viewPanel.removeAll();
                		viewPanel.add(back);
                    	getTable(viewPanel);
                    	panel_1.setVisible(false);
                    	viewPanel.setVisible(true);
                    }else{
                    	JOptionPane.showMessageDialog(null, "You have not ordered anything", "Undefined", JOptionPane.WARNING_MESSAGE);
                    }
				}else {
					JOptionPane.showMessageDialog(null, "You have not ordered anything", "Undefined", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnViewOrder.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnViewOrder.setBounds(208, 220, 452, 80);
		panel_1.add(btnViewOrder);
		
		JButton btnCancelOrder = new JButton("Cancel Order");
		btnCancelOrder.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnCancelOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(orderList.size() !=0) {
					int userArr = orderList.get(0).findUser(orderList, userLogin);
            		if(userArr == -1) {
            			JOptionPane.showMessageDialog(null, "You have not ordered anything", "Undefined", JOptionPane.WARNING_MESSAGE);
            		}else {
            			String[] choices = { "Cancel all order", "Cancel an item from the order"};
        			    String input = (String) JOptionPane.showInputDialog(null, "Choose now...", "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE,null, choices, choices[1]); 
        			    if(input != null) {
	        			    if(input.equalsIgnoreCase(choices[0])) {
	        			    	if(JOptionPane.showConfirmDialog(null, "Do you want to cancel all order?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_NO_OPTION) {
	        			    		orderList.remove(userArr);
	        			    	}
	        			    }else if(input.equalsIgnoreCase(choices[1])) {
	        			    	cancelProduct(userArr);
	        			    }
        			    }
            		}
				}else {
					JOptionPane.showMessageDialog(null, "You have not ordered anything", "Undefined", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnCancelOrder.setBounds(208, 310, 452, 80);
		panel_1.add(btnCancelOrder);
		
		
		JButton btnSaveAndQuit = new JButton("Exit and Save");
		btnSaveAndQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmTunFatimahFood.setVisible(false);
				try {
					Frame1 frame1 = new Frame1();
					frame1.getFrame1().setVisible(true);
					writeFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSaveAndQuit.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnSaveAndQuit.setBounds(208, 400, 452, 80);
		panel_1.add(btnSaveAndQuit);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1266, 683);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(df);
		
		JButton[] btnFood = new JButton[food.size()];
		for(int i=0; i<btnFood.length ; i++) {
			btnFood[i] = new JButton(food.get(i).toString());
			btnFood[i].setFont(new Font("Tahoma", Font.PLAIN, 28));
			btnFood[i].setActionCommand(food.get(i).toString());
			btnFood[i].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				foodPanelOut.setVisible(false);
				foodPanelDetail.setVisible(true);
			  }
			});
			foodPanel.add(btnFood[i]);
		}
		
		JButton[] btnDrink = new JButton[drink.size()];
		for(int i=0; i<btnDrink.length ; i++) {
			btnDrink[i] = new JButton(drink.get(i).toString());
			btnDrink[i].setFont(new Font("Tahoma", Font.PLAIN, 28));
			btnDrink[i].setActionCommand(drink.get(i).toString());
			btnDrink[i].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				drinkPanelOut.setVisible(false);
				drinkPanelDetail.setVisible(true);
			  }
			});
			drinkPanel.add(btnDrink[i]);
		}
	}
	
	public void getTable(JPanel viewPanel) {
		int userArr = orderList.get(0).findUser(orderList, userLogin);
		double totalPrice=0;
		double totalCalories=0;
		int total = 0;
		int size = orderList.get(userArr).getFood().size() + orderList.get(userArr).getDrink().size();
		String product[][] = new String[size+1][4];
		for(int j=0 ; j<size ; j++) {
			if(orderList.get(userArr).getFood().size()>j) {
				int quantity = orderList.get(userArr).getFood().get(j).getQuantityInOrder();
				double cal = orderList.get(userArr).getFood().get(j).getDetail();
				double price = orderList.get(userArr).getFood().get(j).getPrice();
				product[j][0] = orderList.get(userArr).getFood().get(j).getProductName() + " (" + orderList.get(userArr).getFood().get(j).getSauce() + ")";
				product[j][1] = String.valueOf(quantity);
				product[j][2] = String.valueOf(cal*quantity);
				product[j][3] = String.format("%.2f",price*quantity);
				totalPrice += price*quantity;
				totalCalories += cal*quantity;
				total += quantity;
			}else{
				int quantity = orderList.get(userArr).getDrink().get(j-orderList.get(userArr).getFood().size()).getQuantityInOrder();
				double cal = orderList.get(userArr).getDrink().get(j-orderList.get(userArr).getFood().size()).getDetail();
				double price = orderList.get(userArr).getDrink().get(j-orderList.get(userArr).getFood().size()).getPrice();
				product[j][0] = orderList.get(userArr).getDrink().get(j-orderList.get(userArr).getFood().size()).toGUI();
				product[j][1] = String.valueOf(quantity);
				product[j][2] = String.valueOf(cal*quantity);
				product[j][3] = String.format("%.2f",price*quantity);
				totalPrice += price*quantity;
				totalCalories += cal*quantity;
				total += quantity;
			}
		}
		product[size][0] = "Total";
		product[size][1] = String.valueOf(total);
		product[size][2] = String.valueOf(totalCalories);
		product[size][3] = String.format("%.2f",totalPrice);
		
		String column[]={"Product","Quantity","Calories(kcal)","Price(RM)"};         
		JTable jt = new JTable(product, column);
		jt.setRowHeight(30);
		jt.setEnabled(false);
		jt.setVisible(true);
		jt.setFont(new Font("Serif", Font.PLAIN, 20));
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		TableColumnModel colModel = jt.getColumnModel();
		colModel.getColumn(0).setPreferredWidth(300);    
		colModel.getColumn(1).setPreferredWidth(40);
		viewPanel.setLayout(null);
		JScrollPane sp = new JScrollPane(jt); 
		sp.setBounds(172, 10, 522, 489);
		viewPanel.add(sp);
	}
	
	public void readFile() throws FileNotFoundException {
        
        //Read product from the file product.txt
        Scanner readFile = new Scanner(new File("product.txt"));
        int fileloop = 0;
        while (readFile.hasNextLine()) {//when file is not empty
            String a = readFile.nextLine();
            String[] b = a.split("#",2);
            String[] c = b[0].split("/", 3);
            String[] d = b[1].split("/",3);
            if(c[0].equalsIgnoreCase("!")) {
                //Food is empty. Therefore, do nothing
            }else {
                //Read and input food into arrayList
                food.add(new Food ("",0,0));
                food.get(fileloop).setProductName(c[0]);
                food.get(fileloop).setDetail(Double.valueOf(c[1]));
                food.get(fileloop).setPrice(Double.valueOf(c[2]));
            }
            if(d[0].equalsIgnoreCase("!")) {
                //Drink is empty. Therefore, do nothing
            }else {
                //Read and input drink into arrayList
                drink.add(new Drink("",0,0));
                drink.get(fileloop).setProductName(d[0]);
                drink.get(fileloop).setDetail(Double.valueOf(d[1]));
                drink.get(fileloop).setPrice(Double.valueOf(d[2]));
            }
            fileloop++;
        }
      //Read user from file
        Scanner readUserFile = new Scanner(new File("user.txt"));
        fileloop = 0;
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
      //Read orderList from file
        Scanner readOrderListFile = new Scanner(new File("orderList.txt"));
        fileloop = 0;
        //when the orderList is not empty
        while (readOrderListFile.hasNextLine()) {

            orderList.add(new OrderList(null,null,null,null));
            String a = readOrderListFile.nextLine();
            String[] b = a.split("#", 0);

            //User
            for(int i = 0; i<user.size() ; i++) {
                //compare the string b[0] with the NoMatrics in the user arrayList
                if(b[0].equalsIgnoreCase(user.get(i).getNoMatrics())) {
                    //set user to orderList
                    orderList.get(fileloop).setUser(user.get(i));
                    break;
                }
            }

            //Food
            List<Food> foodReadFile = new ArrayList<>();
            String[] c1 = b[1].split("@",0);
            for(int i=1 ; i < c1.length ;i++) {
                String[] d1 = c1[i].split("/",0);
                int sauce = 0;
                if(d1[4].equalsIgnoreCase("spicy")) {
                    sauce = 1;
                }else if(d1[4].equalsIgnoreCase("tomato")) {
                    sauce = 2;
                }else if(d1[4].equalsIgnoreCase("normal")) {
                    sauce = 3;
                }
                //set foodname, calories, price, quantity and sauce into foodReadFile arrayList
                foodReadFile.add(new Food(d1[0], Double.valueOf(d1[1]), Double.valueOf(d1[2]), Integer.valueOf(d1[3]), sauce));
                //Set food into orderList arrayList
                orderList.get(fileloop).setFood(foodReadFile);
            }

            //Drink
            List<Drink>drinkReadFile = new ArrayList<>();
            String[] c2 = b[2].split("@",0);
            for(int i=1 ; i < c2.length ;i++) {
                String[] d2 = c2[i].split("/",0);
                //set DrinkName, calories, price, quantity, goLarge status and addIce status into drinkReadFile arrayList
                drinkReadFile.add(new Drink(d2[0], Double.valueOf(d2[1]), Double.valueOf(d2[2]), Integer.valueOf(d2[3]) , Boolean.valueOf(d2[4]), Boolean.valueOf(d2[5])));
                //Set drink into orderList arrayList
                orderList.get(fileloop).setDrink(drinkReadFile);
            }

            //Date
            //set date into orderList
            orderList.get(fileloop).setOrderTime(b[3]);
            fileloop++;
        }
	}
	
	public void writeFile() throws IOException {
		 //Write orderList into orderList.txt
        File file = null;
        try {
            file = new File("orderList.txt");
            file.createNewFile();
        }catch (IOException a) {
            System.out.println("An error occurred.");
            a.printStackTrace();
        }

        FileWriter orderFile = new FileWriter("orderList.txt");
        for(int i = 0 ; i < orderList.size() ; i++) {
            orderFile.write(orderList.get(i).toFile() + "\n");
        }
        orderFile.close();
	}
	
	public void cancelProduct(int userArr) {
		int cancelOrder = -1;
		String productList[] = new String[orderList.get(userArr).getFood().size()+orderList.get(userArr).getDrink().size()];
		productList[0] = "";
		for(int i = 0 ; i < orderList.get(userArr).getFood().size() ; i++) {
	         productList[i] += orderList.get(userArr).getFood().get(i).toString() + " --- " + orderList.get(userArr).getFood().get(i).getQuantityInOrder();
	    }
		productList[orderList.get(userArr).getFood().size()] = "";
	    for(int i = orderList.get(userArr).getFood().size(); i<orderList.get(userArr).getFood().size() + orderList.get(userArr).getDrink().size(); i++) {
	       productList[i] += orderList.get(userArr).getDrink().get(i - orderList.get(userArr).getFood().size()).toString() + " --- " + orderList.get(userArr).getDrink().get(i - orderList.get(userArr).getFood().size()).getQuantityInOrder();
	    }
		String x = (String)JOptionPane.showInputDialog(null, "Select a order to cancel", "Product", JOptionPane.QUESTION_MESSAGE, null, productList, "Tahoma");
		for(int i=0; i<productList.length ; i++) {
			if(productList[i].equalsIgnoreCase(x)) {
				cancelOrder = i+1;
				break;
			}
		}
		if(x!=null) {
			JOptionPane optionPane = new JOptionPane();
			JSlider slider = new JSlider();
		    slider.setMajorTickSpacing(1);
		    if(cancelOrder > 0 && cancelOrder<= orderList.get(userArr).getFood().size() && orderList.get(userArr).getFood().size() != 0) {
		    	slider.setMaximum(orderList.get(userArr).getFood().get(cancelOrder -1).getQuantityInOrder());
		    }else if(cancelOrder > 0 && cancelOrder <= orderList.get(userArr).getFood().size() + orderList.get(userArr).getDrink().size()) {
		    	slider.setMaximum(orderList.get(userArr).getDrink().get(cancelOrder-1-orderList.get(userArr).getFood().size()).getQuantityInOrder());
		    }
		    slider.setValue(0);
		    optionPane.setInputValue(0);
		    slider.setPaintTicks(true);
		    slider.setPaintLabels(true);
		    ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JSlider theSlider = (JSlider) changeEvent.getSource();
		        if (!theSlider.getValueIsAdjusting()) {
		          optionPane.setInputValue(new Integer(theSlider.getValue()));
		        }
		      }
		    };
		    slider.addChangeListener(changeListener);
		    optionPane.setMessage(new Object[] { "Select a value: ", slider });
		    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		    optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		    JDialog dialog = optionPane.createDialog(null, "My Slider");
		    dialog.setVisible(true);
			
			int numberOfItem = (int)optionPane.getInputValue();
			if(cancelOrder > 0 && cancelOrder<= orderList.get(userArr).getFood().size() && orderList.get(userArr).getFood().size() != 0) {
	            int restFood = orderList.get(userArr).getFood().get(cancelOrder -1).getQuantityInOrder() - numberOfItem;
	            
	            if(restFood == 0) {
	                orderList.get(userArr).getFood().remove(cancelOrder -1);
	                JOptionPane.showMessageDialog(null,"The food has been removed","Cancel Order",JOptionPane.INFORMATION_MESSAGE);
	            }else if(restFood > 0 && numberOfItem > 0) {
	                orderList.get(userArr).getFood().get(cancelOrder -1).setQuantityInOrder(restFood);
	                JOptionPane.showMessageDialog(null, "The "+ orderList.get(userArr).getFood().get(cancelOrder -1).getProductName() + "(" + orderList.get(userArr).getFood().get(cancelOrder -1).getSauce() +") has been removed, still remaining " + restFood,"Cancel Order" ,JOptionPane.INFORMATION_MESSAGE);
	            }
	        }else if(cancelOrder > 0 && cancelOrder <= orderList.get(userArr).getFood().size() + orderList.get(userArr).getDrink().size()) {
	            //cancel order for drink
	            int drinkArray = cancelOrder - orderList.get(userArr).getFood().size() -1;
	            int restDrink = orderList.get(userArr).getDrink().get(drinkArray).getQuantityInOrder() - numberOfItem;
	            if(restDrink == 0) {
	                orderList.get(userArr).getDrink().remove(drinkArray);
	                JOptionPane.showMessageDialog(null,"The drink has been removed","Cancel Order",JOptionPane.INFORMATION_MESSAGE);
	            }else if(restDrink > 0 && numberOfItem > 0) {
	                orderList.get(userArr).getDrink().get(drinkArray).setQuantityInOrder(restDrink);
	                JOptionPane.showMessageDialog(null, "The "+ orderList.get(userArr).getDrink().get(drinkArray).toGUI() + " has been removed, still remaining " + restDrink,"Cancel Order" ,JOptionPane.INFORMATION_MESSAGE);
	            }
	        }
	        if(orderList.get(userArr).getFood().size() == 0 && orderList.get(userArr).getDrink().size() == 0 ){
	            orderList.remove(orderList.get(0).findUser(orderList, userLogin));
	        }
		}
	}
	
	public void getSlider(JPanel panel) {
		JSlider slider = new JSlider();
	    slider.setMajorTickSpacing(1);
	    slider.setValue(0);
	    slider.setMaximum(20);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    ChangeListener changeListener = new ChangeListener() {
	      public void stateChanged(ChangeEvent changeEvent) {
	        JSlider theSlider = (JSlider) changeEvent.getSource();
	        if (!theSlider.getValueIsAdjusting()) {
	        	qtSlider = Integer.valueOf(slider.getValue());
	        }
	      }
	    };
	    slider.addChangeListener(changeListener);
	    slider.setVisible(true);
	    panel.add(slider);
	}
}
