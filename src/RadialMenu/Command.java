package RadialMenu;

import javafx.event.EventDispatchChain;
import javafx.event.EventTarget;

import java.util.ArrayList;

public class Command implements EventTarget {
	public int	id = -1;
	public String	title = "";
	public String	description = "";
	public String	icon = "";
	public ArrayList<Command> subCommmands = new ArrayList<Command>();

	public Command(	int	_id,
					String	_title,
					String	_description,
					String	_icon )
	{
		id = _id;
		title = _title;
		description = _description;
		icon = _icon;
	}

	public String toString()
	{
		return title + "(" + id + ")";
	}

	public void add(Command cmd) {
		this.subCommmands.add(cmd);
	}

	@Override
	public EventDispatchChain buildEventDispatchChain(EventDispatchChain tail) {
		System.out.println("buildEventDispatchChain");
		return null;
	}
}
