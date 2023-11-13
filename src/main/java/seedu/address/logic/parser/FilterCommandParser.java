package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_METRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VALUE;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Score;
import seedu.address.model.person.ScoreList;
import seedu.address.model.statistics.StatisticMetric;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;


/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    private static Logger logger = LogsCenter.getLogger(FilterCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * @param args String of arguments
     * @return FilterCommand object
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        UniqueTagList uniqueTagList = new UniqueTagList();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_METRIC, PREFIX_VALUE);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TAG, PREFIX_METRIC, PREFIX_VALUE);

        if (!(argMultimap.getValue(PREFIX_TAG).isPresent() && argMultimap.getValue(PREFIX_METRIC).isPresent())) {
            throw new ParseException(String.format("Incomplete parameter inputs. t/TAG and met/SCORE are compulsory"
                    + "fields. \n" + FilterCommand.MESSAGE_USAGE));
        }
        String tagName = argMultimap.getValue(PREFIX_TAG).orElse("");
        if (!(Tag.isValidTagName(tagName)
                && ScoreList.isValidScoreTag(uniqueTagList.getTag(tagName, "assessment")))) {
            throw new ParseException(String.format(FilterCommand.MESSAGE_INVALID_TAG));
        }

        if (!StatisticMetric.isValidMetric(argMultimap.getValue(PREFIX_METRIC).orElse(""))) {
            throw new ParseException(String.format(FilterCommand.MESSAGE_INVALID_METRIC));
        }
        Tag tag = new Tag(argMultimap.getValue(PREFIX_TAG).get(), "assessment");
        StatisticMetric metric = StatisticMetric.valueOf(argMultimap.getValue(PREFIX_METRIC).get().toUpperCase());

        if (!StatisticMetric.isScoreRelevant(argMultimap.getValue(PREFIX_METRIC).orElse(""))) {
            return new FilterCommand(tag, metric, 0);
        }

        if (!argMultimap.getValue(PREFIX_VALUE).isPresent()) {
            throw new ParseException(String.format("val/VALUE is missing, it is compulsory. \n"
                    + FilterCommand.MESSAGE_USAGE));
        }

        try {
            int value = Integer.parseInt(argMultimap.getValue(PREFIX_VALUE).orElse("0"));
            if (!Score.isValidScoreValue(value)) {
                throw new ParseException(String.format(FilterCommand.MESSAGE_INVALID_VALUE));
            }
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(FilterCommand.MESSAGE_INVALID_VALUE));
        }

        int value = Integer.parseInt(argMultimap.getValue(PREFIX_VALUE).get());

        return new FilterCommand(tag, metric, value);
    }

}
