package jabpro.logic.parser;

import java.util.Comparator;

import jabpro.logic.Messages;
import jabpro.logic.commands.ListCommand;
import jabpro.logic.parser.exceptions.ParseException;
import jabpro.model.person.Person;

/**
 * Parses input arguments and creates a new ListCommand object with sorting options.
 */
public class ListCommandParser implements Parser<ListCommand> {

    @Override
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_SORT);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String sortingAttribute = argMultimap.getValue(CliSyntax.PREFIX_SORT).orElse(null);

        if (sortingAttribute == null) {
            return new ListCommand();
        }

        // Create the sorting comparator based on the sorting attribute
        Comparator<Person> sortingComparator = createSortingComparator(sortingAttribute);

        return new ListCommand(sortingComparator);
    }

    private Comparator<Person> createSortingComparator(String sortingAttribute) {
        if ("name".equalsIgnoreCase(sortingAttribute)) {
            return Comparator.comparing(Person::getName);
        } else {
            // Default: no sorting (you can change this behavior as needed)
            return null;
        }
    }
}
