package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.LinkedIn;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddLCommandTest {
    private static final String LINKEDIN_STUB = "Someusername";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withLinkedIn(LINKEDIN_STUB).build();
        editedPerson.setLinkedIn(new LinkedIn("xyz"));

        AddLCommand addLCommand = new AddLCommand(INDEX_FIRST_PERSON, new LinkedIn(editedPerson.getLinkedIn().value));

        String expectedMessage = String.format(AddLCommand.MESSAGE_SUCCESS, editedPerson.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addLCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddLCommand addLCommand = new AddLCommand(outOfBoundIndex, new LinkedIn("xyz"));
        assertCommandFailure(addLCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddLCommand addLCommand = new AddLCommand(outOfBoundIndex, new LinkedIn("xyz"));

        assertCommandFailure(addLCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddLCommand standardCommand = new AddLCommand(INDEX_FIRST_PERSON, new LinkedIn("xyz"));

        // same values -> returns true
        AddLCommand commandWithSameValues = new AddLCommand(INDEX_FIRST_PERSON, new LinkedIn("xyz"));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddLCommand(INDEX_SECOND_PERSON, new LinkedIn("xyz"))));

        // different remark, same value-> returns true
        assertTrue(standardCommand.equals(new AddLCommand(INDEX_FIRST_PERSON, new LinkedIn("xyz"))));
    }
}

