package RadialMenu;

import javafx.event.EventHandler;

public class CommandEventHandler implements EventHandler<CommandEvent> {
	@Override
	public void handle(CommandEvent event) {
		Command command = (Command) event.getTarget();
//		command.strike();

		System.out.println("Reactor received strike: "); // + reactor.getI() + ", " + reactor.getJ());
	}
}
