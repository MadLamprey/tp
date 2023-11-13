package seedu.address.logic.commands;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Redirects user to the Github account of a specific candidates.
 */
public class GithubCommand extends Command {

    public static final String COMMAND_WORD = "github";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redirects to user's Github account. "
            + "Parameters: " + "[" + COMMAND_WORD + " <INDEX>]...\n"
            + "where INDEX must be a non-zero unsigned integer, and within bounds of list size\n"
            + "Example: " + COMMAND_WORD + " 2";
    public static final String MESSAGE_SUCCESS = "Redirecting to Github ...";

    private final Index index;

    public GithubCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        String username = personToEdit.getGithub().value;
        if (username.isEmpty()) {
            throw new CommandException("No Github account has been added for this candidate.");
        }
        String githubUrl = "https://github.com/" + username;
        try {
            Desktop.getDesktop().browse(new URI(githubUrl));
        } catch (IOException | URISyntaxException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GithubCommand)) {
            return false;
        }

        GithubCommand otherGithubCommand = (GithubCommand) other;
        return this.index.equals(otherGithubCommand.index);
    }
}
