package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
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
            + "Parameters: " + "[" + COMMAND_WORD + " <USERID> <USERNAME>]...\n"
            + "Example: " + COMMAND_WORD + " 2 alexyeoh";

    public static final String MESSAGE_SUCCESS = "Github account added.";

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
        model.addGithub(index, username);
        return new CommandResult(MESSAGE_SUCCESS);
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("index", index).add("username", username).toString();
    }

}
