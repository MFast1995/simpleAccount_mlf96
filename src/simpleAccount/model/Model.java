package simpleAccount.model;
import simpleAccount.model.ModelEvent;

public interface Model {
	void notifyChanged(ModelEvent e);
}
