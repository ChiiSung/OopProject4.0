package part1;

import java.awt.EventQueue;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ButtonGroup;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;

public class Frame2 {

	private JFrame frmTunFatimahFood;
	public User userLogin;

	List<Food>food = new ArrayList<>();
    List<Drink>drink = new ArrayList<>();
    List<OrderList>orderList = new ArrayList<>();
    List<User>user = new ArrayList<>();
    
    int qtSlider, sauce, arr, userArr;
    Boolean hot, large;
    
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
		viewPanel.setLayout(null);
		viewPanel.setBackground(new Color(255,255,255,200));
		viewPanel.setBounds(209,78,864,509);
    	panel.add(viewPanel);
		viewPanel.setVisible(false);
		
		JPanel foodPanelDetail = new JPanel();
		foodPanelDetail.setLayout(null);
		foodPanelDetail.setBackground(new Color(255,255,255,200));
		foodPanelDetail.setBounds(209,78,864,509);
    	panel.add(foodPanelDetail);
    	foodPanelDetail.setVisible(false);
    	
    	JPanel foodPanelDetailIn = new JPanel();
		foodPanelDetailIn.setLayout(null);
		foodPanelDetailIn.setBackground(Color.WHITE);
		foodPanelDetailIn.setBounds(208,41,452,438);
    	foodPanelDetail.add(foodPanelDetailIn);
    	
    	JButton btnAddOrderFood = new JButton("Add Order");
    	btnAddOrderFood.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			orderFood();
    			foodPanelDetail.setVisible(false);
    			panel_1.setVisible(true);
    		}
    	});
    	btnAddOrderFood.setBounds(150, 390, 155, 40);
    	
    	JPanel drinkPanelDetail = new JPanel();
    	drinkPanelDetail.setLayout(null);
		drinkPanelDetail.setBackground(new Color(255,255,255,200));
		drinkPanelDetail.setBounds(209,78,864,509);
    	panel.add(drinkPanelDetail);
    	drinkPanelDetail.setVisible(false);
    	
    	JPanel drinkPanelDetailIn = new JPanel();
		drinkPanelDetailIn.setLayout(null);
		drinkPanelDetailIn.setBackground(new Color(255,255,255));
		drinkPanelDetailIn.setBounds(208,41,452,438);
    	drinkPanelDetail.add(drinkPanelDetailIn);
    	drinkPanelDetailIn.setVisible(true);
    	
    	JButton btnAddOrderDrink = new JButton("Add Order");
    	btnAddOrderDrink.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			orderDrink();
    			drinkPanelDetail.setVisible(false);
    			panel_1.setVisible(true);
    		}
    	});
    	btnAddOrderDrink.setBounds(150, 390, 155, 40);
		
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
		
		userArr = orderList.get(0).findUser(orderList, userLogin);
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
        			    String input = (String) JOptionPane.showInputDialog(null, "Choose one...", "Cancel Order", JOptionPane.QUESTION_MESSAGE,null, choices, choices[1]); 
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
			btnFood[i].setActionCommand(String.valueOf(i));
			btnFood[i].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				foodPanelOut.setVisible(false);
				foodPanelDetail.setVisible(true);
				arr = Integer.valueOf(e.getActionCommand());
				sauce = 3;
				qtSlider = 1;
				foodPanelDetailIn.removeAll();
		    	detailPanel(foodPanelDetailIn,1);
		    	foodPanelDetailIn.add(btnAddOrderFood);
			  }
			});
			foodPanel.add(btnFood[i]);
		}
		
		JButton[] btnDrink = new JButton[drink.size()];
		for(int i=0; i<btnDrink.length ; i++) {
			btnDrink[i] = new JButton(drink.get(i).toString());
			btnDrink[i].setFont(new Font("Tahoma", Font.PLAIN, 28));
			btnDrink[i].setActionCommand(String.valueOf(i));
			btnDrink[i].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				drinkPanelOut.setVisible(false);
				drinkPanelDetail.setVisible(true);
				arr = Integer.valueOf(e.getActionCommand());
				hot = true;
				large = false;
				qtSlider = 1;
				drinkPanelDetailIn.removeAll();
		    	detailPanel(drinkPanelDetailIn,2);
		    	drinkPanelDetailIn.add(btnAddOrderDrink);
			  }
			});
			drinkPanel.add(btnDrink[i]);
		}
	}
	
	protected void orderFood() {
		Food foodOrder = new Food("",0,0);
		foodOrder.setProductName(food.get(arr).getProductName());
		foodOrder.setDetail(food.get(arr).getDetail());
		foodOrder.setPrice(food.get(arr).getPrice());
        foodOrder.setSauce(sauce);
        foodOrder.setQuantityInOrder(qtSlider);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String strdate = formatter.format(date);
		if(orderList.size() == 0) { 
            orderList.add(new OrderList(userLogin, foodOrder, strdate));
        }else {
            orderList.get(0).setOrderFoodGui(orderList, userLogin, foodOrder);
        }
	}
	
	protected void orderDrink() {
		Drink drinkOrder = new Drink("",0,0);
		drinkOrder.setProductName(drink.get(arr).getProductName());
		drinkOrder.setDetail(drink.get(arr).getDetail());
		drinkOrder.setPrice(drink.get(arr).getPrice());
        drinkOrder.setQuantityInOrder(qtSlider);
        if(!hot) {
        	drinkOrder.addIce();
        }
        if(large) {
        	drinkOrder.goLarge();
        }
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String strdate = formatter.format(date);
		if(orderList.size() == 0) { 
            orderList.add(new OrderList(userLogin, drinkOrder, strdate));
        }else {
            orderList.get(0).setOrderDrinkGui(orderList, userLogin, drinkOrder);
        }
	}

	protected void getTable(JPanel viewPanel) {
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
	
	protected void readFile() throws FileNotFoundException {
        
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

            if(!b[1].equalsIgnoreCase("")) {
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
           }else {
        	   orderList.get(fileloop).getFood().remove(0);
           }
           if(!b[2].equalsIgnoreCase("")) {
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
           }else {
        	   orderList.get(fileloop).getDrink().remove(0);
           }

            //Date
            //set date into orderList
            orderList.get(fileloop).setOrderTime(b[3]);
            fileloop++;
        }
	}
	
	protected void writeFile() throws IOException {
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
	
	protected void cancelProduct(int userArr) {
		int cancelOrder = -1;
		String productList[] = new String[orderList.get(userArr).getFood().size()+orderList.get(userArr).getDrink().size()];
		for(int i = 0 ; i < orderList.get(userArr).getFood().size() ; i++) {
			productList[i] = "";
	        productList[i] += orderList.get(userArr).getFood().get(i).toString() + " --- " + orderList.get(userArr).getFood().get(i).getQuantityInOrder();
	    }
	    for(int i = orderList.get(userArr).getFood().size(); i<orderList.get(userArr).getFood().size() + orderList.get(userArr).getDrink().size(); i++) {
	    	productList[i] = "";
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
		    JDialog dialog = optionPane.createDialog(null, "Cancel Quantity");
		    dialog.setVisible(true);

		    Object selectedValue = optionPane.getValue();
		    int n = -1;

		    if(selectedValue == null) {
		        n = JOptionPane.CLOSED_OPTION;      
		    }else {
		        n = Integer.parseInt(selectedValue.toString());
		    }
			int numberOfItem = (int)optionPane.getInputValue();
			if(n == JOptionPane.OK_OPTION) {
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
	}
	
	protected void detailPanel(JPanel panel, int foodOrDrink) {
		
		JSlider slider = new JSlider();
	    slider.setMajorTickSpacing(1);
    	slider.setMaximum(20);
    	slider.setMinimum(1);
    	slider.setValue(1);
    	slider.setBounds(10, 318, 425, 62);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    ChangeListener changeListener = new ChangeListener() { 
	      public void stateChanged(ChangeEvent changeEvent) {
	        JSlider theSlider = (JSlider) changeEvent.getSource();
	        if (!theSlider.getValueIsAdjusting()) {
	        	qtSlider = new Integer(theSlider.getValue());
	        }
	      }
	    };
	    slider.addChangeListener(changeListener);
    	panel.add(slider);
	    
    	JRadioButton sauce1 = new JRadioButton("Spicy");
    	sauce1.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	sauce1.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			sauce = 1;
    		}
    	});
    	JRadioButton sauce2 = new JRadioButton("Tomato");
    	sauce2.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	sauce2.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			sauce = 2;
    		}
    	});
    	JRadioButton sauce3 = new JRadioButton("Normal");
    	sauce3.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	sauce3.setSelected(true);
    	sauce3.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			sauce = 3;
    		}
    	});
    	ButtonGroup sauce123 = new ButtonGroup();
    	sauce123.add(sauce1);sauce123.add(sauce2);sauce123.add(sauce3);
    	
    	JRadioButton sauce4 = new JRadioButton("Hot (+0.00)");
    	sauce4.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	sauce4.setSelected(true);
    	sauce4.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			hot = true;
    		}
    	});
    	JRadioButton sauce5 = new JRadioButton("Cold (Add Ice)(+0.50)");
    	sauce5.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	sauce5.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			hot = false;
    		}
    	});
    	ButtonGroup sauce45 = new ButtonGroup();
    	sauce45.add(sauce4);sauce45.add(sauce5);
    	
    	JRadioButton sauce6 = new JRadioButton("Large (+1.50)");
    	sauce6.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	sauce6.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			large = true;
    		}
    	});
    	JRadioButton sauce7 = new JRadioButton("Small (+0.00)");
    	sauce7.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	sauce7.setSelected(true);
    	sauce7.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			large = false;
    		}
    	});
    	ButtonGroup sauce67 = new ButtonGroup();
    	sauce67.add(sauce6);sauce67.add(sauce7);
    	
    	if(foodOrDrink ==1) {
    	
    	JPanel saucePanel = new JPanel();
    	saucePanel.setSize(426, 45);
    	saucePanel.setLocation(10, 140);
    	saucePanel.setVisible(true);
    	panel.add(saucePanel);
    	saucePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	saucePanel.add(sauce3);
    	saucePanel.add(sauce2);
    	saucePanel.add(sauce1);
    	
    	JTextArea txtrFoodName = new JTextArea();
    	txtrFoodName.setEditable(false);
    	txtrFoodName.setBackground(Color.WHITE);
    	txtrFoodName.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	txtrFoodName.setText("Food Name :");
    	txtrFoodName.setBounds(10, 10, 120, 50);
    	panel.add(txtrFoodName);
    	
    	JTextArea txtFood = new JTextArea(food.get(arr).getProductName());
    	txtFood.setEditable(false);
    	txtFood.setLineWrap(true);
    	txtFood.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	txtFood.setBackground(new Color(240,240,240));
    	txtFood.setBounds(136, 10, 297, 100);
    	panel.add(txtFood);
    	
    	JTextPane txtsauce = new JTextPane();
    	txtsauce.setEditable(false);
    	txtsauce.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	txtsauce.setText("Sauce :");
    	txtsauce.setBounds(10, 110, 169, 30);
    	panel.add(txtsauce);
    	
    	JTextPane txtQuantity = new JTextPane();
    	txtQuantity.setEditable(false);
    	txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	txtQuantity.setText("Quantity :");
    	txtQuantity.setBounds(10, 288, 178, 30);
    	panel.add(txtQuantity);
    	panel.setVisible(true);
    	
    	}else if(foodOrDrink == 2) {
    	
    	JPanel sizePanel = new JPanel();
    	sizePanel.setSize(426, 45);
    	sizePanel.setLocation(10, 140);
    	sizePanel.setVisible(true);
    	panel.add(sizePanel);
    	sizePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	sizePanel.add(sauce7);
    	sizePanel.add(sauce6);
    	
    	JTextPane txtsize = new JTextPane();
    	txtsize.setEditable(false);
    	txtsize.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	txtsize.setText("Size :");
    	txtsize.setBounds(10, 110, 169, 30);
    	panel.add(txtsize);
    	
    	JTextArea txtDrinkName = new JTextArea();
    	txtDrinkName.setEditable(false);
    	txtDrinkName.setBackground(Color.WHITE);
    	txtDrinkName.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	txtDrinkName.setText("Drink Name :");
    	txtDrinkName.setBounds(10, 10, 120, 50);
    	panel.add(txtDrinkName);
    	
    	JTextArea txtDrink = new JTextArea(drink.get(arr).getProductName());
    	txtDrink.setEditable(false);
    	txtDrink.setLineWrap(true);
    	txtDrink.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	txtDrink.setBackground(new Color(240,240,240));
    	txtDrink.setBounds(136, 10, 297, 100);
    	panel.add(txtDrink);
    	
    	JPanel temPanel = new JPanel();
    	temPanel.setSize(426, 45);
    	temPanel.setLocation(10, 233);
    	temPanel.setVisible(true);
    	panel.add(temPanel);
    	temPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	temPanel.add(sauce4);
    	temPanel.add(sauce5);
    	
    	}
	}
}
