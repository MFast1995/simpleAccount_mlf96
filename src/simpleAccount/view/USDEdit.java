package simpleAccount.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.*;

import simpleAccount.controller.SimpleAccountController;
import simpleAccount.model.ModelEvent;
import simpleAccount.model.SimpleAccountModel;
import simpleAccount.model.User;

//class for window for editing in USD
public class USDEdit extends JFrameView {
	DecimalFormat df = new DecimalFormat("########.00");
	public static final String DISMISS = "Dismiss";
	public static final String  WITHDRAW_USD = "Withdraw USD";
	public static final String DEPOSIT_USD = "Deposit USD";
	JFormattedTextField amountField;
	User user;
	JLabel jl;
	public double amount = 0;

	public USDEdit(SimpleAccountModel model, SimpleAccountController controller, User user) {
		super(model, controller);
		this.user = user;
		this.setTitle(user.getID() + " " + user.getName() + " {USD}");
		this.setPreferredSize(new Dimension(500,150));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Handler handler = new Handler();
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		format.setRoundingMode(RoundingMode.DOWN); //removes extra digits after first two decimal points
		amountField = new JFormattedTextField(format);
		amountField.setValue(0.00);
		amountField.setColumns(30);
		amountField.addActionListener(handler);
		jl = new JLabel("Balance: " + df.format(user.getBalance()));
		JLabel jl2 = new JLabel("Please enter an amount in USD: ");
		JPanel textPanel = new JPanel();
		JPanel entryPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JButton dismiss = new JButton(DISMISS);
		dismiss.addActionListener(handler);
		JButton withdraw = new JButton(WITHDRAW_USD);
		withdraw.addActionListener(handler);
		JButton deposit = new JButton(DEPOSIT_USD);
		deposit.addActionListener(handler);
		
		textPanel.add(jl, null);
		textPanel.add(jl2, null);
		entryPanel.add((amountField), null);
		buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
		buttonPanel.add(deposit, null);
		buttonPanel.add(withdraw, null);
		buttonPanel.add(dismiss, null);
		this.getContentPane().add(textPanel, BorderLayout.NORTH);
		this.getContentPane().add(entryPanel, BorderLayout.CENTER);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		this.setAlwaysOnTop(true);
		pack();
	}

	public void modelChanged(ModelEvent event) {
		jl.setText("Balance: " + df.format(event.getAmount()));
		amountField.setValue(0.00);
	}
	
	public class Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == DISMISS)
				dispose();
			else
			{
				amount = Double.parseDouble(amountField.getValue().toString());
				((SimpleAccountController) getController()).operation(e.getActionCommand(), user, amount);
			}
		}
	}
}