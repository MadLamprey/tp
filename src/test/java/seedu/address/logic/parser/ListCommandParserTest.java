package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParserTest {

    private ListCommandParser parser;

    @BeforeEach
    public void setUp() {
        parser = new ListCommandParser();
    }

    @Test
    public void parse_noSortingAttribute_returnsListCommandWithDefaultComparator() throws ParseException {
        String args = "";

        ListCommand result = parser.parse(args);

        // Ensure the ListCommand uses the default sorting comparator
        assertEquals(result.getSortingComparator(), ListCommand.DEFAULT_COMPARATOR);
    }
}

