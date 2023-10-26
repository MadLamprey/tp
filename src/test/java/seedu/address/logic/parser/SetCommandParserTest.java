package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StatusTypes;


public class SetCommandParserTest {
    @Test
    public void execute_validIndexAndStatus_success() throws ParseException {
        // Check if the status of the person in the model has been updated
        assert(new SetCommandParser().parse("1 Interviewed").equals(
                new SetCommand(Index.fromOneBased(1), StatusTypes.INTERVIEWED)));
    }


}
