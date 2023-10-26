package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.person.Remark;


/**
 * Parses special input arguments and creates a new {@code RemarkCommand} object
 */
public class RemarkSyntaxHandler {
    public static final String REGEX_KEEP_REMARK = "\\*\\*REMARK\\*\\*";

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * @param args the string to be parsed
     * @return true if the string contains the special syntax for keeping the remark
     */
    public static boolean isKeepRemark(String args) {
        requireNonNull(args);
        Pattern pattern = Pattern.compile(REGEX_KEEP_REMARK);
        Matcher matcher = pattern.matcher(args);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * Generates a new remark based on the current remark and the special syntax **remark**
     * @param args the string to be parsed
     * @param currentRemark the current remark of the person
     * @return the new remark where **remark** is replaced by the current remark
     */
    public static Remark generateKeepRemarkCommand(String args, Remark currentRemark) {
        requireNonNull(args);
        String resultString = args.replaceAll(REGEX_KEEP_REMARK, currentRemark.value);
        System.out.println(resultString);
        return new Remark(resultString);
    }

}
