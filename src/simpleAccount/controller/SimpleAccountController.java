package simpleAccount.controller;
import simpleAccount.view.SimpleAccountView;
import simpleAccount.model.SimpleAccountModel;
import simpleAccount.view.JFrameView;
import simpleAccount.view.USDEdit;
import simpleAccount.view.EUROEdit;
import simpleAccount.view.YUANEdit;
import simpleAccount.model.User;

public class SimpleAccountController extends AbstractController {
	
	public SimpleAccountController(){
		setModel(new SimpleAccountModel());
		setView(new SimpleAccountView((SimpleAccountModel)getModel(), this));
		((JFrameView)getView()).setVisible(true);
	}
	public void operation(String option, User user, double amount){
		if(option.equals(SimpleAccountView.USD))
		{
			setView(new USDEdit((SimpleAccountModel)getModel(), this, user));
		}
		else if(option.equals(SimpleAccountView.EURO))
		{
			setView(new EUROEdit((SimpleAccountModel)getModel(), this, user));
		}
		else if(option.equals(SimpleAccountView.YUAN))
		{
			setView(new YUANEdit((SimpleAccountModel)getModel(), this, user));
		}
		else if(option.equals(USDEdit.WITHDRAW_USD))
		{
			((SimpleAccountModel)getModel()).withdrawUSD(user, amount);
		}
		else if(option.equals(USDEdit.DEPOSIT_USD))
		{
			((SimpleAccountModel)getModel()).depositUSD(user, amount);
		}
		else if(option.equals(EUROEdit.WITHDRAW_EURO))
		{
			((SimpleAccountModel)getModel()).withdrawEURO(user, amount);
		}
		else if(option.equals(EUROEdit.DEPOSIT_EURO))
		{
			((SimpleAccountModel)getModel()).depositEURO(user, amount);
		}
		else if(option.equals(YUANEdit.WITHDRAW_YUAN))
		{
			((SimpleAccountModel)getModel()).withdrawYUAN(user, amount);
		}
		else if(option.equals(YUANEdit.DEPOSIT_YUAN))
		{
			((SimpleAccountModel)getModel()).depositYUAN(user, amount);
		}
		else if(option.equals(SimpleAccountView.SAVE))
		{
			((SimpleAccountModel)getModel()).save();
		}
		else if(option.equals(SimpleAccountView.EXIT))
		{
			((SimpleAccountModel)getModel()).exit();
		}
	}
}