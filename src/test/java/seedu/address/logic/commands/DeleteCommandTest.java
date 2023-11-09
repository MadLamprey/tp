package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalTagPerson;
import static seedu.address.testutil.TypicalTags.TEST_TAG_NAME_STRING;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new EventBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new EventBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTagsUnfilteredList_success() {
        Person personToDelete = getTypicalTagPerson();

        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(List.of(TEST_TAG_NAME_STRING));
        List<Predicate<Person>> predicateList = new ArrayList<>() {{
                add(tagPredicate);
            }};

        DeleteCommand deleteCommand = new DeleteCommand(predicateList);
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new EventBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    // TODO
    @Test
    public void execute_validStatusUnfilteredList_success() {
    //        Person personToDelete = getTypicalStatusPerson(); // Alice is still preliminary
    //
    //        String testStatus = StatusTypes.INTERVIEWED.toString();
    //
    //        StatusContainsKeywordsPredicate statusPredicate =
    //              new StatusContainsKeywordsPredicate(List.of(testStatus));
    //        List<Predicate<Person>> predicateList = new ArrayList<>() {{
    //            add(statusPredicate);
    //        }};
    //
    //        DeleteCommand deleteCommand = new DeleteCommand(predicateList);
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
    //                Messages.format(personToDelete));
    //
    //        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new EventBook(), new UserPrefs());
    //        expectedModel.deletePerson(personToDelete);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    // TODO
    @Test
    public void execute_validTagsAndStatusUnfilteredList_success() {

    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    // TODO
    public void execute_invalidTagsUnfilteredList_throwsCommandException() {

    }


    // TODO
    @Test
   public void execute_emptyTagsUnfilteredList_throwsCommandException() {
    //        DeleteCommand deleteCommand = new DeleteCommand(EMPTY_TAG_SET);
    //
    //        assertCommandFailure(deleteCommand, model, DeleteCommand.MESSAGE_NO_TARGET_SPECIFIED);
    }

    // TODO
    @Test
    public void execute_invalidStatusUnfilteredList_throwsCommandException() {

    }

    // TODO
    @Test
    public void execute_noMatchingTagsOrStatusUnfilteredList_throwsCommandException() {
    //        DeleteCommand deleteCommand = new DeleteCommand(NO_MATCHING_TAG_SET);
    //
    //        assertCommandFailure(deleteCommand, model, DeleteCommand.MESSAGE_PERSONS_NOT_FOUND);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new EventBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    // TODO
    @Test
    public void equals() {
        DeleteCommand deleteByIndexFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteByIndexSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);
        //        DeleteCommand deleteByTagsFirstCommand = new DeleteCommand(TEST_TAG_SET);
        //        DeleteCommand deleteByTagsSecondCommand = new DeleteCommand(NO_MATCHING_TAG_SET);


        // same object -> returns true
        assertTrue(deleteByIndexFirstCommand.equals(deleteByIndexFirstCommand));
        //        assertTrue(deleteByTagsFirstCommand.equals(deleteByTagsFirstCommand));

        // same values (delete by index) -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteByIndexFirstCommand.equals(deleteFirstCommandCopy));

        // same values (delete by tags) -> returns true
        //        DeleteCommand deleteByTagsCommandCopy = new DeleteCommand(TEST_TAG_SET);
        //        assertTrue(deleteByTagsFirstCommand.equals(deleteByTagsCommandCopy));

        // different types -> returns false
        assertFalse(deleteByIndexFirstCommand.equals(1));
        //        assertFalse(deleteByTagsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteByIndexFirstCommand.equals(null));
        //        assertFalse(deleteByTagsFirstCommand.equals(null));

        // different person or set of tags -> returns false
        assertFalse(deleteByIndexFirstCommand.equals(deleteByIndexSecondCommand));
        //        assertFalse(deleteByTagsFirstCommand.equals(deleteByTagsSecondCommand));
    }

    @Test
    public void toString_index() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        String expectedToString = DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expectedToString, deleteCommand.toString());
    }

    // TODO
    @Test
    public void toString_tags() {
    //        DeleteCommand deleteCommand = new DeleteCommand(TEST_TAG_SET);
    //        String expectedString = DeleteCommand.class.getCanonicalName()
    //                + String.format("{targetTags=%s}", TEST_TAG_SET.toString());
    //        assertEquals(expectedString, deleteCommand.toString());
    }

    // TODO
    @Test
    public void toString_status() {

    }

    // TODO
    @Test
    public void toString_tagsAndStatus() {

    }

    @Test
    public void toString_invalid() {
        DeleteCommand deleteCommand = new DeleteCommand(Index.getDefaultIndex());
        String expectedToString = "seedu.address.logic.commands.DeleteCommand{invalid=No valid target specified}";
        assertEquals(expectedToString, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    @Test
    public void execute_eventDeleted_success() throws Exception {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        Event event = new Event(personToDelete, "Test",
                LocalDateTime.of(2023, 11, 1, 12, 0),
                LocalDateTime.of(2023, 11, 1, 13, 0));

        model.addEvent(event);
        assertEquals(1, model.getFilteredEventList().size());
        deleteCommand.execute(model);
        assertEquals(0, model.getFilteredEventList().size());
    }

    @Test
    public void execute_eventDeletedTags_success() throws Exception {
        Person personToDelete = getTypicalTagPerson();

        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(List.of(TEST_TAG_NAME_STRING));
        List<Predicate<Person>> predicateList = new ArrayList<>() {
            {
                add(tagPredicate);
            }
        };

        DeleteCommand deleteCommand = new DeleteCommand(predicateList);

        Event event = new Event(personToDelete, "Test",
                LocalDateTime.of(2023, 11, 1, 12, 0),
                LocalDateTime.of(2023, 11, 1, 13, 0));
        model.addEvent(event);
        assertEquals(1, model.getFilteredEventList().size());
        deleteCommand.execute(model);
        assertEquals(0, model.getFilteredEventList().size());
    }
}
