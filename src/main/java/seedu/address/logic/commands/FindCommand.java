package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches users by categories (e.g. name, status) "
            + "whose details match the given keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alex bernice st/interviewed";


    private final List<Predicate<Person>> predicatesList;

    /**
     * Creates an FindCommand to find the specified {@code Person}
     */
    public FindCommand(List<Predicate<Person>> predicatesList) {
        this.predicatesList = predicatesList;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicatesList);

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        Boolean isListEqual = false;
        List<Predicate<Person>> otherPredicates = otherFindCommand.predicatesList;

        if (predicatesList.size() != otherPredicates.size()) {
            return false;
        }

        for (int i = 0; i < predicatesList.size(); i++) {
            isListEqual = predicatesList.get(i).equals(otherPredicates.get(i));
        }
        return isListEqual;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicates list", predicatesList)
                .toString();
    }
}
