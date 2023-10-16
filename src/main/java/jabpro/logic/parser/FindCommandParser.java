package jabpro.logic.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import jabpro.logic.Messages;
import jabpro.logic.commands.FindCommand;
import jabpro.logic.parser.exceptions.ParseException;
import jabpro.model.person.NameContainsKeywordsPredicate;
import jabpro.model.person.StatusContainsKeywordsPredicate;




/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_STATUS);

        String[] nameKeywords = parseKeywordsList(argMultimap.getAllValues(CliSyntax.PREFIX_NAME));
        String[] statusKeywords = parseKeywordsList(argMultimap.getAllValues(CliSyntax.PREFIX_STATUS));

        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                new StatusContainsKeywordsPredicate(Arrays.asList(statusKeywords)));
    }

    /**
     * Parses a list of keywords into an array of strings.
     *
     * @param keywordsList A list of keywords, where each element may contain multiple words.
     * @return An array of strings where each element represents an individual keyword.
     *
     *     The method first converts the list of keywords into a string representation,
     *     e.g., [Alex, Yeoh] (including square brackets). It then removes the square brackets
     *     from the string representation, resulting in a cleaned string, e.g., Alex, Yeoh (no square brackets).
     *     Finally, the cleaned string is split into an array of strings, where each word separated
     *     by a whitespace or comma is considered a single element.
     *
     *     Example:
     *     If keywordsList is ["John Doe"], the returned array will be ["John", "Doe"].
     */
    private String[] parseKeywordsList(List<String> keywordsList) {
        String list = keywordsList.toString();
        String cleanedList = list.replaceAll("[\\[\\]]", "");
        return cleanedList.split("\\s+");
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
