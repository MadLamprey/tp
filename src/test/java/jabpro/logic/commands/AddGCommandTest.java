package jabpro.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AddGCommandTest {

    @Test
    public void equals() {
        AddGCommand addG = new AddGCommand(2, "alexyeoh");
        assertTrue(addG.equals(addG));

        AddGCommand addG1 = new AddGCommand(2, "alexyeoh");
        assertTrue(addG.equals(addG1));

        AddGCommand addG2 = new AddGCommand(1, "zhiwang");
        assertFalse(addG.equals(addG2));

    }

    @Test
    public void toStringMethod() {
        AddGCommand addG = new AddGCommand(2, "alexyeoh");
        String s = AddGCommand.class.getCanonicalName() + "{targetIndex=2, username=alexyeoh}";
        assertEquals(addG.toString(), s);
    }
}
