package RadialMenu;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class CommandEvent extends Event {

	private static final long serialVersionUID = 20121107L;

	private Command command;

	public Command getCommand() {
		return command;
	}

	/**
	 * The only valid EventType for the CustomEvent.
	 */
	public static final EventType<CommandEvent> RUN_COMMAND =
			new EventType<>(Event.ANY, "RUN_COMMAND");

	/**
	 * Creates a new {@code CommandEvent} with an event type of {@code RUN_COMMAND}.
	 * The source and target of the event is set to {@code NULL_SOURCE_TARGET}.
	 */
	public CommandEvent() {
		super(RUN_COMMAND);
	}

	/**
	 * Construct a new {@code CommandEvent} with the specified event source and target.
	 * If the source or target is set to {@code null}, it is replaced by the
	 * {@code NULL_SOURCE_TARGET} value. All CommandEvents have their type set to
	 * {@code RUN_COMMAND}.
	 *
	 * @param source	the event source which sent the event
	 * @param target	the event target to associate with the event
	 */
	public CommandEvent(Object source, EventTarget target) {
		super(source, target, RUN_COMMAND);
//		System.out.println("CommandEvent");

		this.command = (Command) target;
	}

	@Override
	public CommandEvent copyFor(Object newSource, EventTarget newTarget) {
		return (CommandEvent) super.copyFor(newSource, newTarget);
	}

	@Override
	public EventType<? extends CommandEvent> getEventType() {
		return (EventType<? extends CommandEvent>) super.getEventType();
	}
}
