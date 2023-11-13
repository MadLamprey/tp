package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Github;
import seedu.address.model.person.Person;

/**
 * Adds GitHub account to candidates existing details.
 */
public class AddGCommand extends Command {
    public static final String COMMAND_WORD = "addG";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds Github to details of a candidate. "
            + "Parameters: " + "[" + COMMAND_WORD + " <INDEX> u/<USERNAME>]...\n"
            + "where INDEX must be a non-zero unsigned integer, and within bounds of list size\n"
            + "Example: " + COMMAND_WORD + " 2 u/alexyeoh";

    public static final String MESSAGE_SUCCESS = "Github account added for: %1$s";

    private final Index index;

    private final Github username;

    /**
     * Creates an AddG Command to add Github.
     * @param index
     * @param username
     */
    public AddGCommand(Index index, Github username) {
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
        personToEdit.setGithub(username);
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
        if (!(other instanceof AddGCommand)) {
            return false;
        }

        AddGCommand otherAddGCommand = (AddGCommand) other;
        return this.index.equals(otherAddGCommand.index) && this.username.equals(otherAddGCommand.username);
    }

}
