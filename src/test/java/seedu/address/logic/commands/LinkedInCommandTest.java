package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class LinkedInCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        LinkedInCommand linkedInCommand = new LinkedInCommand(INDEX_SECOND_PERSON);

        assertCommandSuccess(linkedInCommand, model, LinkedInCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        LinkedInCommand linkedInCommand = new LinkedInCommand(outOfBoundIndex);

        assertThrows(CommandException.class, () -> linkedInCommand.execute(model),
                MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
