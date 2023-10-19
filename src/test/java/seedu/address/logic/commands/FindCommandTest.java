package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.StatusContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("firstName"));
        StatusContainsKeywordsPredicate firstStatusPredicate =
                new StatusContainsKeywordsPredicate(Collections.singletonList("firstStatus"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("secondName"));
        StatusContainsKeywordsPredicate secondStatusPredicate =
                new StatusContainsKeywordsPredicate(Collections.singletonList("secondStatus"));

        List<Predicate<Person>> firstPredicatesList = Arrays.asList(firstNamePredicate, firstStatusPredicate);
        List<Predicate<Person>> secondPredicatesList = Arrays.asList(secondNamePredicate, secondStatusPredicate);

        FindCommand findFirstCommand = new FindCommand(firstPredicatesList);
        FindCommand findSecondCommand = new FindCommand(secondPredicatesList);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicatesList);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        StatusContainsKeywordsPredicate statusPredicate = prepareStatusPredicate("");
        FindCommand command = new FindCommand(Arrays.asList(namePredicate, statusPredicate));
        expectedModel.updateFilteredPersonList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Kurz Elle Kunz");
        StatusContainsKeywordsPredicate statusPredicate = prepareStatusPredicate("Preliminary");
        FindCommand command = new FindCommand(Arrays.asList(namePredicate, statusPredicate));
        expectedModel.updateFilteredPersonList(Arrays.asList(namePredicate, statusPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }


    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        StatusContainsKeywordsPredicate statusPredicate = new StatusContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(Arrays.asList(namePredicate, statusPredicate));
        String expected = FindCommand.class.getCanonicalName() + "{predicates list=[" + namePredicate + ", "
                + statusPredicate + "]}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code StatusContainsKeywordsPredicate}.
     */
    private StatusContainsKeywordsPredicate prepareStatusPredicate(String userInput) {
        return new StatusContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
