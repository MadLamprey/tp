package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new ListCommand object with sorting options.
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_SORT);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String sortingAttribute = argMultimap.getValue(CliSyntax.PREFIX_SORT).orElse(null);

        // Create the sorting comparator based on the sorting attribute
        Comparator<Person> sortingComparator = createSortingComparator(sortingAttribute);


        return new ListCommand(sortingComparator);
    }

    /**
     * Creates a sorting comparator based on the specified sorting attribute.
     *
     * @param sortingAttribute The attribute by which the list should be sorted (e.g., "name" or "email").
     * @return A comparator for sorting based on the specified attribute.
     */
    private Comparator<Person> createSortingComparator(String sortingAttribute) {
        if ("name".equalsIgnoreCase(sortingAttribute)) {
            return Comparator.comparing(Person::getName);
        } else if ("email".equalsIgnoreCase(sortingAttribute)) {
            return Comparator.comparing(Person::getEmail);
        } else {
            return ListCommand.DEFAULT_COMPARATOR; // A comparator that does nothing
        }
    }
}
