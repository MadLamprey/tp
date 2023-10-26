package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.person.Remark;

public class RemarkCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);
    private RemarkCommandParser parser = new RemarkCommandParser();
    private final String nonEmptyRemark = "Some remark.";

    @Test
    public void parse_indexSpecified_success() {
        // have remark
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + nonEmptyRemark;
        RemarkCommand expectedCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(nonEmptyRemark));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no remark
        userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK;
        expectedCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecified_failure() {
        // Negative numbers
        String userInput = " -5";
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);

        // Out of bounds check
        String userInput2 = " 100000000000";
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_FORMAT);

        // Blank field
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no index
        userInput = PREFIX_REMARK + nonEmptyRemark;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_keepRemark_success() {
        // Important to note that a remark will have trailing white spaces trimmed
        // That is a remark with trailing white spaces will be treated as a remark without trailing white spaces
        Index targetIndex = INDEX_FIRST_PERSON;
        String expectedRemarkValue = "**REMARK** " + nonEmptyRemark;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + expectedRemarkValue;
        RemarkCommand expectedCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(expectedRemarkValue), true);
        assertParseSuccess(parser, userInput, expectedCommand);

        //Not a keep remark, simulate mistype
        String wrongRemarkValue = "**REMARK* " + nonEmptyRemark;
        String userInput2 = targetIndex.getOneBased() + " " + PREFIX_REMARK + wrongRemarkValue;
        RemarkCommand expectedCommand2 = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(wrongRemarkValue));
        assertParseSuccess(parser, userInput2, expectedCommand2);

        // No parse failure since it is not a keep remark but an original remark
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD + " " + nonEmptyRemark, expectedMessage);
    }
}
