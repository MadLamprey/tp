package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
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
            + "Parameters: " + "[" + COMMAND_WORD + " <USERID> <USERNAME>]...\n"
            + "Example: " + COMMAND_WORD + " 2 alexyeoh";

    public static final String MESSAGE_SUCCESS = "LinkedIn account added.";

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
        model.addLinkedIn(index, username);
        return new CommandResult(MESSAGE_SUCCESS);
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("index", index).add("username", username).toString();
    }

}
