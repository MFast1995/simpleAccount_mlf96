package simpleAccount.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import simpleAccount.model.AbstractModel;
import simpleAccount.model.ModelEvent;
import simpleAccount.view.SimpleAccountView;
import simpleAccount.model.User;

public class SimpleAccountModel extends AbstractModel {
	DecimalFormat df = new DecimalFormat("########.00");

	public synchronized void withdrawUSD(User user, double amount)
	{
		double balance = user.getBalance();
		balance -= amount;
		if(balance < 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Insufficient Funds", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ModelEvent me = new ModelEvent(this, 1, "", balance);
			notifyChanged(me);
			notifyAll();
		}
	}
	
	public synchronized void withdrawEURO(User user, double amount)
	{
		double balance = user.getBalance();
		balance -= (amount/.88);	
		if(balance < 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Insufficient Funds", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ModelEvent me = new ModelEvent(this, 1, "", balance);
			notifyChanged(me);
			notifyAll();
		}	
	}
	
	public synchronized void withdrawYUAN(User user, double amount)
	{
		double balance = user.getBalance();
		balance -= (amount/6.47);
		if(balance < 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Insufficient Funds", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ModelEvent me = new ModelEvent(this, 1, "", balance);
			notifyChanged(me);
			notifyAll();
		}
	}

	public synchronized void depositUSD(User user, double amount)
	{
		double balance = user.getBalance();
		balance += amount;
		if(amount < 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Not a positive number", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ModelEvent me = new ModelEvent(this, 1, "", balance);
			notifyChanged(me);
			notifyAll();
		}
	}
	
	public synchronized void depositEURO(User user, double amount)
	{
		double balance = user.getBalance();
		balance += (amount/.88);
		if(amount < 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Not a positive number", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ModelEvent me = new ModelEvent(this, 1, "", balance);
			notifyChanged(me);
			notifyAll();
		}
	}
	
	public synchronized void depositYUAN(User user, double amount)
	{
		double balance = user.getBalance();
		balance += (amount/6.47);
		if(amount < 0)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Not a positive number", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ModelEvent me = new ModelEvent(this, 1, "", balance);
			notifyChanged(me);
			notifyAll();
		}
	}
	
	public void save()
	{
		try
		{	
			PrintWriter pw = new PrintWriter("./input/"+ SimpleAccountView.fileName);
			for(User user : SimpleAccountView.users)
			{
				pw.write(user.getName() + "\n");
				pw.write(user.getID() + "\n");
				pw.write(df.format((user.getBalance())) + "\n");
				pw.write("\n");
			}
			pw.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Unable to open file: " + e);
		}
		finally
		{
			System.out.println("File Saved!");
		}
	}
	
	public void exit()
	{
		save(); //to save to file before exiting program
		System.exit(0);
	}
}
