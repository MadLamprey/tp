package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Adds an Event to JABPro.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds Event relating to a candidate. "
            + "Parameters: " + "[" + COMMAND_WORD + " <INDEX> d/<DESCRIPTION> bt/<BEGIN_TIME> et/<END_TIME>]...\n"
            + "where INDEX must be a non-zero unsigned integer.\n"
            + "Example: " + COMMAND_WORD + " 2 d/Interview Round 1 bt/2023-10-22 09:00 et/2023-10-22 10:00";

    public static final String MESSAGE_SUCCESS = "Event added: %1$s";

    private final Event event;

    /**
     * Creates an EventCommand to add event.
     * @param event The event to be added.
     */
    public EventCommand(Event event) {
        requireNonNull(event);
        this.event = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        Index targetIndex = event.getIndex();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(targetIndex.getZeroBased());
        Event event1 = new Event(person, event.getDescription(), event.getStart_time(), event.getEnd_time());
        model.addEvent(event1);
        return new CommandResult(String.format(MESSAGE_SUCCESS, event1),
                false, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCommand)) {
            return false;
        }

        EventCommand otherEventCommand = (EventCommand) other;
        return event.equals(otherEventCommand.event);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("event", event)
                .toString();
    }
}
