package jabpro.logic.parser;

import static java.util.Objects.requireNonNull;

import jabpro.commons.core.index.Index;
import jabpro.commons.exceptions.IllegalValueException;
import jabpro.logic.Messages;
import jabpro.logic.commands.RemarkCommand;
import jabpro.logic.parser.exceptions.ParseException;
import jabpro.model.person.Remark;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkCommand.MESSAGE_USAGE), ive);
        }

        String remarkText = argMultimap.getValue(CliSyntax.PREFIX_REMARK).orElse("");

        Remark remark = new Remark(remarkText);
        return new RemarkCommand(index, remark);
    }
}
