package simpleAccount.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import simpleAccount.controller.SimpleAccountController;
import simpleAccount.model.SimpleAccountModel;
import simpleAccount.model.User;
import simpleAccount.model.ModelEvent;

public class SimpleAccountView extends JFrameView {
	public static final String USD = "Edit in USD";
	public static final String EURO = "Edit in EURO";
	public static final String YUAN = "Edit in YUAN";
	public static final String SAVE = "Save";
	public static final String EXIT = "Exit";
	public static String fileName;
	public JComboBox<String> jcb;
	public int index = 0;
	public static ArrayList<User> users = new ArrayList<User>(); 
	
	//Constructor that initializes window along with buttons and listeners
	public SimpleAccountView(SimpleAccountModel model, SimpleAccountController controller) {
		super(model, controller);
		jcb = new JComboBox<>();
		for(User user : users)
			jcb.addItem(user.getID() + " " + user.getName());
		jcb.setSize(100,300);
		this.getContentPane().add(jcb, BorderLayout.CENTER);
		this.setTitle("Main Menu");
		this.setPreferredSize(new Dimension(500, 300));
		this.setVisible(true);
		this.setAlwaysOnTop( true );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//sets so program terminates when window is closed
		
		JPanel buttonPanel = new JPanel();
		Handler handler = new Handler();
		jcb.addActionListener(handler);
		JButton editUSD = new JButton(USD);
		editUSD.addActionListener(handler);
		JButton editEURO = new JButton(EURO);
		editEURO.addActionListener(handler);
		JButton editYUAN = new JButton(YUAN);
		editYUAN.addActionListener(handler);
		buttonPanel.setLayout(new GridLayout(3, 1, 20, 5));
		this.getContentPane().add(buttonPanel, BorderLayout.WEST);
		buttonPanel.add(editUSD, null);
		buttonPanel.add(editEURO, null);
		buttonPanel.add(editYUAN, null);
		
		JPanel savePanel = new JPanel();
		JButton exit = new JButton(EXIT);
		exit.addActionListener(handler);
		JButton save = new JButton(SAVE);
		save.addActionListener(handler);
		savePanel.setLayout(new GridLayout(1, 2, 20, 5));
		this.getContentPane().add(savePanel, BorderLayout.SOUTH);
		savePanel.add(save,  null);
		savePanel.add(exit, null);
		pack();
	}

	public void modelChanged(ModelEvent event) {			//function to update model
		users.get(index).setBalance(event.getAmount());
	}

	public class Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == jcb)			//when a selection is made, updates index of user selected
			{
				index = jcb.getSelectedIndex();
			}
			else
				((SimpleAccountController) getController()).operation(e.getActionCommand(),
						users.get(index), 0);
		}
	}

	public static void main(String[] args) {
		System.out.print("What file would you like to use? \n" +
						   "Please ensure the file is in the input folder.\n" +
						   "Enter the name of the file with extension.\n" +
						   "> ");
		Scanner sc = new Scanner(System.in);	//Scanner to request file name for data entry and saving
		fileName = sc.nextLine();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("./input/" + fileName));
			String name;			//imports users from file to ArrayList users
			int accountNumber;
			double balance;
			try
			{
				while((name = br.readLine()) != null)	//iterates until end of file
				{
					accountNumber = Integer.parseInt(br.readLine());
					balance = Double.parseDouble(br.readLine());
					br.readLine();	
					users.add(new User(name, accountNumber, balance));
				}
				new SimpleAccountController();
			}
			catch(NumberFormatException e)
			{
				System.err.println("Unable to import users " + e);
			}
			finally
			{
				br.close();
			}
		}
		catch(IOException e)
		{
			System.err.println("Cannot open given file: " + e);
		}
		sc.close();	//close scanner
	}
}