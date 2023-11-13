package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.LinkedIn;
import seedu.address.model.person.Person;

/**
 * Adds LinkedIn account to candidate's existing details.
 */
public class AddLCommand extends Command {
    public static final String COMMAND_WORD = "addL";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds LinkedIn to details of a candidate. "
            + "Parameters: " + "[" + COMMAND_WORD
            + " <INDEX> u/<USERNAME>]...\nwhere INDEX must be a non-zero unsigned integer, "
            + "and within bounds of list size\n"
            + "Example: " + COMMAND_WORD + " 2 u/alexyeoh";

    public static final String MESSAGE_SUCCESS = "LinkedIn account added for: %1$s";

    private final Index index;

    private final LinkedIn username;

    /**
     * Creates an AddL command to add LinkedIn.
     * @param index
     * @param username
     */
    public AddLCommand(Index index, LinkedIn username) {
        this.index = index;
        this.username = username;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        personToEdit.setLinkedIn(username);
        model.setLastViewedPersonIndex(index);
        model.setPerson(personToEdit, personToEdit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(personToEdit), true);
    }

    /**
     * Generates a command execution success message based on whether
     * the username is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !username.value.isEmpty() ? MESSAGE_SUCCESS : "";
        return String.format(message, personToEdit.getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddLCommand)) {
            return false;
        }

        AddLCommand otherAddLCommand = (AddLCommand) other;
        return this.index.equals(otherAddLCommand.index) && this.username.equals(otherAddLCommand.username);
    }

}
