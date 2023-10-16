package jabpro.logic.commands;

import jabpro.commons.util.ToStringBuilder;
import jabpro.logic.commands.exceptions.CommandException;
import jabpro.model.Model;
import jabpro.model.person.StatusTypes;

/**
 * Sets the status of a person in the address book.
 */

public class SetCommand extends Command {
    public static final String COMMAND_WORD = "set";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the status of a person. " + "Parameters: "
            + "[" + COMMAND_WORD + " <USERID> <STATUS>]...\n"
            + "Example: " + COMMAND_WORD + " 5 Interviewed";

    public static final String MESSAGE_SUCCESS = "Status set for: %1$s";

    private final int targetIndex;
    private final StatusTypes newStatus;

    /**
     * Creates a SetCommand to set the status of the person at the specified target index.
     *
     * @param targetIndex The index of the person to set the status.
     * @param newStatus   The new status to set.
     */

    public SetCommand(int targetIndex, StatusTypes newStatus) {
        this.targetIndex = targetIndex;
        this.newStatus = newStatus;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        /*if (!model.hasPersonAtIndex(targetIndex)) {
            throw new CommandException("Invalid index. Please provide a valid person index.");
        }

        Person personToSetStatus = model.getPersonAtIndex(targetIndex);
        Status personStatus = personToSetStatus.getStatus();
        personStatus.setStatusType(newStatus);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToSetStatus.getName()));*/
        return new CommandResult(String.format(MESSAGE_SUCCESS, "PLACEHOLDER"));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("targetIndex", targetIndex)
                .add("newStatus", newStatus).toString();
    }
}


