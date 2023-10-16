package jabpro.logic.parser;

import static jabpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jabpro.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import jabpro.logic.Messages;
import jabpro.logic.commands.FindCommand;
import jabpro.model.person.NameContainsKeywordsPredicate;
import jabpro.model.person.StatusContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new StatusContainsKeywordsPredicate(Arrays.asList("Interviewed")));
        assertParseSuccess(parser, " n/Alice Bob st/Interviewed", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t st/Interviewed", expectedFindCommand);
    }

    @Test
    public void parse_validArgsWithNoStatus_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new StatusContainsKeywordsPredicate(Arrays.asList("")));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t ", expectedFindCommand);
    }

}
