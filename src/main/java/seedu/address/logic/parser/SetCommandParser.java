package seedu.address.logic.parser;


import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StatusTypes;

/**
 * Parses input arguments and creates a new EditCommand object
 */

public class SetCommandParser implements Parser<SetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetCommand
     * and returns a SetCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+");

            if (splitArgs.length < 2) {
                throw new ParseException(SetCommand.MESSAGE_USAGE);
            }

            Index index = ParserUtil.parseIndex(splitArgs[0]);
            StatusTypes newStatusType = ParserUtil.parseStatusType((splitArgs[1]));

            return new SetCommand(index, newStatusType);

        } catch (ParseException e) {
            throw new ParseException(SetCommand.MESSAGE_USAGE, e);
        }
    }
}

