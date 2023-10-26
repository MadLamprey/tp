package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ExportCommandParserTest {
    @Test
    public void execute_validIndexAndStatus_success() throws ParseException {
        assert(new ExportCommandParser().parse("").equals(
                new ExportCommand()));
    }
}
