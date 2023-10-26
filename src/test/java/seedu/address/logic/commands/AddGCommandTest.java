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
import seedu.address.model.person.Github;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddGCommandTest {
    private static final String GITHUB_STUB = "Someusername";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withGithub(GITHUB_STUB).build();
        editedPerson.setGithub(new Github("xyz"));

        AddGCommand addGCommand = new AddGCommand(INDEX_FIRST_PERSON, new Github(editedPerson.getGithub().value));

        String expectedMessage = String.format(AddGCommand.MESSAGE_SUCCESS, editedPerson.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addGCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddGCommand addGCommand = new AddGCommand(outOfBoundIndex, new Github("xyz"));
        assertCommandFailure(addGCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddGCommand addGCommand = new AddGCommand(outOfBoundIndex, new Github("xyz"));

        assertCommandFailure(addGCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddGCommand standardCommand = new AddGCommand(INDEX_FIRST_PERSON, new Github("xyz"));

        // same values -> returns true
        AddGCommand commandWithSameValues = new AddGCommand(INDEX_FIRST_PERSON, new Github("xyz"));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddGCommand(INDEX_SECOND_PERSON, new Github("xyz"))));

        // different remark, same value-> returns true
        assertTrue(standardCommand.equals(new AddGCommand(INDEX_FIRST_PERSON, new Github("xyz"))));
    }
}

