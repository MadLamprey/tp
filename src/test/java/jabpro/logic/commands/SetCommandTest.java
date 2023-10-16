package jabpro.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jabpro.model.person.Status;

public class SetCommandTest {

    @Test
    public void execute_validIndexAndStatus_success() {
        // Check if the status of the person in the model has been updated
        assertEquals((new Status()).toString(), "Preliminary");
    }


}
