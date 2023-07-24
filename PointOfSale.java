

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import java.awt.Button;
//import java.awt.Toolkit;

public class PointOfSale {
	static String url="jdbc:mysql://localhost:3306/PointOfSale";
	private JFrame frame;
	private JTextField itemField;
	private JTextField prodField;
	private JTextField quantityField;
	private JTextField priceField;
	private JTable MonitorTable;
	private JTextField totalField;
	DefaultTableModel model;
	JTextArea recieptArea;
	private JTextField cashField;
	private JTextField balanceField;
	ImageIcon icon;
	Image image1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PointOfSale window = new PointOfSale();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PointOfSale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		icon=new ImageIcon("define your file path here");
		
		frame.setIconImage(icon.getImage());
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
	    JPanel panel = new JPanel();
	    panel.setForeground(Color.LIGHT_GRAY);
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(10, 11, 694, 561);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BAZENGATECH TECHNOLOGIES POINT OF SALE");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(93, 0, 548, 33);
		panel.add(lblNewLabel);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pname1=prodField.getText();
				int price=Integer.parseInt(priceField.getText());
				int q=Integer.parseInt(quantityField .getText());
				int total=q*price;
				
			
				model=(DefaultTableModel)MonitorTable.getModel();
				model.addRow(new Object[]{
					pname1,q,price,total 
				});
				int sum=0;
				for(int i=0;i<MonitorTable.getRowCount();i++) {
					sum=sum+Integer.parseInt(MonitorTable.getValueAt(i, 3).toString());
					
				}
				
				totalField.setText(Integer.toString(sum));
				
				
				itemField.setText(null);
				prodField.setText(null);
				priceField.setText(null);
				quantityField.setText(null);
				totalField.requestFocus();
				
				
			}
			
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(10, 517, 85, 33);
		panel.add(btnAdd);
		
		JLabel itemLabel = new JLabel("ITEM BARCODE");
		itemLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		itemLabel.setBounds(415, 58, 94, 30);
		panel.add(itemLabel);
		
		itemField = new JTextField();
		itemField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
		        	 Connection conn=DriverManager.getConnection("make your database connection parameters present here");
		        	String number=itemField.getText();
		        	 PreparedStatement stm = conn.prepareStatement("select id, barcode ,Product,UnitPrice from ProdTable where barcode=?");
		        	stm .setString(1,number);
			       
			           ResultSet rs=stm.executeQuery();
			          
			           if(rs.next()==true) {
			        	   String prodname=rs.getString(3);
			        	   String price=rs.getString(4);
			        	   prodField.setText(prodname);
			        	   priceField.setText(price);
			        	   
			           }else {
			        	   prodField.setText(null);
			        	   priceField.setText(null);
			           }
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}});
		itemField.setBackground(new Color(169, 169, 169));
		itemField.setFont(new Font("Tahoma", Font.BOLD, 18));
		itemField.setBounds(519, 55, 147, 33);
		panel.add(itemField);
		itemField.setColumns(10);
		
		JLabel productLabel = new JLabel("PRODUCT NAME");
		productLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		productLabel.setBounds(415, 99, 108, 30);
		panel.add(productLabel);
		prodField = new JTextField();
		prodField.setBackground(new Color(169, 169, 169));
		prodField.setFont(new Font("Tahoma", Font.BOLD, 18));
		prodField.setColumns(10);
		prodField.setBounds(519, 96, 147, 33);
		panel.add(prodField);
		
		JLabel QuantLabel = new JLabel("QUANTITY");
		QuantLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		QuantLabel.setBounds(415, 187, 94, 30);
		panel.add(QuantLabel);
		
		quantityField = new JTextField();
		quantityField.setBackground(new Color(169, 169, 169));
		quantityField.setFont(new Font("Tahoma", Font.BOLD, 18));
		quantityField.setColumns(10);
		quantityField.setBounds(519, 184, 147, 33);
		panel.add(quantityField);
		
		JLabel priceLabel = new JLabel("UNIT PRICE");
		priceLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		priceLabel.setBounds(415, 143, 94, 30);
		panel.add(priceLabel);
		
		priceField = new JTextField();
		priceField.setBackground(new Color(169, 169, 169));
		priceField.setFont(new Font("Tahoma", Font.BOLD, 18));
		priceField.setColumns(10);
		priceField.setBounds(519, 140, 147, 33);
		panel.add(priceField);
		
	/*	scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 466, 265);
		panel.add(scrollPane);
		
		table = new JTable();
	
			model=new DefaultTableModel();
		Object []column= {"name","cellNo","Email"};

model.setColumnIdentifiers(column); table.setModel(model);
		scrollPane.setViewportView(table);
		table.setBackground(new Color(192, 192, 192));*/
		model=new DefaultTableModel();
		Object []column= {"Poduct","Quantity","UnitPrice","Total"};
	
		model.setColumnIdentifiers(column);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 39, 413, 205);
		panel.add(scrollPane_1);
		
		MonitorTable = new JTable();
		scrollPane_1.setViewportView(MonitorTable);
		MonitorTable.setModel(model);
		MonitorTable.setBackground(new Color(95, 158, 160));
		
		JLabel totalLabel = new JLabel("TOTAL");
		totalLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		totalLabel.setBounds(41, 275, 94, 30);
		panel.add(totalLabel);
		
		totalField = new JTextField();
		totalField.setBackground(new Color(169, 169, 169));
		totalField.setFont(new Font("Tahoma", Font.BOLD, 18));
		totalField.setColumns(10);
		totalField.setBounds(145, 272, 147, 33);
		panel.add(totalField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(316, 245, 357, 316);
		panel.add(scrollPane);
		
		JTextArea recieptArea_1 = new JTextArea();
		scrollPane.setViewportView(recieptArea_1);
		recieptArea_1.setBackground(new Color(192, 192, 192));
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cash=cashField.getText();
				String total=totalField.getText();
				String balance=balanceField.getText();
				Double total1=Double.parseDouble(totalField.getText());
				Double tax=0.16*total1;
				DefaultTableModel model=new DefaultTableModel();
				
				
				model=(DefaultTableModel)MonitorTable.getModel();
				recieptArea_1.setBackground(Color.blue);
				//this is the area where the reciept is described on how it should be printed for the customer
				
				recieptArea_1.setText(recieptArea_1.getText() + "*********************************************************** \n");
				recieptArea_1.setText(recieptArea_1.getText() + " Welcome To the Best Shopping Destination In the Region \n ");
				recieptArea_1.setText(recieptArea_1.getText() + "\t Kamau  Supermarket         \n");
				recieptArea_1.setText(recieptArea_1.getText() + "\tP.O Box 1377, Nyeri.        \n");
				recieptArea_1.setText(recieptArea_1.getText() + "\tTOTAL BILLING STATEMENT \n" );
				recieptArea_1.setFont(recieptArea_1.getFont().deriveFont(Font.BOLD, 13f));
				recieptArea_1.setText(recieptArea_1.getText() + "************************************************************\n");
				recieptArea_1.setText(recieptArea_1.getText() + "Product Name "+"\t"+"Price"+"\t"+"Amount"+"\n\t");
				for(int i=0;i<model.getRowCount();i++) {
					String pname=(String)model.getValueAt(i, 0);
					int price=(Integer)model.getValueAt(i, 2);
					int amount=(Integer)model.getValueAt(i, 3);
					recieptArea_1.setText(recieptArea_1.getText()+pname +"\t"+price+"\t"+amount+"\n\t");
					
				}
				recieptArea_1.setText(recieptArea_1.getText() +  "\n");
				recieptArea_1.setText(recieptArea_1.getText() + "     SubTotal :"+total+  "\n");
				recieptArea_1.setText(recieptArea_1.getText() + "************************************************************\n");
				recieptArea_1.setText(recieptArea_1.getText() + "     VAT :"+tax+  "\n");
				recieptArea_1.setText(recieptArea_1.getText() + "************************************************************\n");
				recieptArea_1.setText(recieptArea_1.getText() + "     cash :" +cash+ "\n");
				recieptArea_1.setText(recieptArea_1.getText() + "************************************************************\n");
				recieptArea_1.setText(recieptArea_1.getText() + "     Balance :"+balance+  "\n");
				recieptArea_1.setText(recieptArea_1.getText() + "************************************************************\n");
				recieptArea_1.setText(recieptArea_1.getText() + "\t THANK YOU FOR SHOPPING WITH US \t\n ");
				
				
				
				
				model=(DefaultTableModel)MonitorTable.getModel();
				totalField.setText(null);
				try {
					recieptArea_1.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPrint.setBounds(105, 517, 94, 33);
		panel.add(btnPrint);
		
		JLabel cashLabel = new JLabel("CASH");
		cashLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cashLabel.setBounds(41, 340, 94, 30);
		panel.add(cashLabel);
		
		cashField = new JTextField();
		cashField.setFont(new Font("Tahoma", Font.BOLD, 18));
		cashField.setColumns(10);
		cashField.setBackground(new Color(169, 169, 169));
		cashField.setBounds(145, 337, 147, 33);
		panel.add(cashField);
		
		JLabel balanceLabel = new JLabel("BALANCE");
		balanceLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		balanceLabel.setBounds(41, 387, 94, 30);
		panel.add(balanceLabel);
		
		balanceField = new JTextField();
		balanceField.setFont(new Font("Tahoma", Font.BOLD, 18));
		balanceField.setColumns(10);
		balanceField.setBackground(new Color(169, 169, 169));
		balanceField.setBounds(145, 384, 147, 33);
		panel.add(balanceField);
		
		JButton btnBalance = new JButton("BALANCE");
		btnBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cash=Integer.parseInt(cashField.getText());
				int total1=Integer.parseInt(totalField.getText());
				int balance=cash-total1;
				balanceField.setText(Integer.toString(balance));
				
			}
		});
		btnBalance.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBalance.setBounds(41, 462, 213, 33);
		panel.add(btnBalance);
		
		JLabel Warning = new JLabel("Remember to enter the  cash field to avoid errors");
		Warning.setForeground(Color.RED);
		Warning.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		Warning.setBounds(22, 305, 270, 33);
		panel.add(Warning);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				
				totalField.setText(null);
				cashField.setText(null);
				balanceField.setText(null);
				MonitorTable.clearSelection();
				quantityField.setText(null);
			
				
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnClear.setBounds(209, 517, 94, 33);
		panel.add(btnClear);
	}
}
