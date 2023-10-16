package jabpro.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AddLCommandTest {

    @Test
    public void equals() {
        AddLCommand addL = new AddLCommand(2, "alexyeoh");
        assertTrue(addL.equals(addL));

        AddLCommand addL1 = new AddLCommand(2, "alexyeoh");
        assertTrue(addL.equals(addL1));

        AddLCommand addL2 = new AddLCommand(1, "zhiwang");
        assertFalse(addL.equals(addL2));

    }

    @Test
    public void toStringMethod() {
        AddLCommand addL = new AddLCommand(2, "alexyeoh");
        String s = AddLCommand.class.getCanonicalName() + "{targetIndex=2, username=alexyeoh}";
        assertEquals(addL.toString(), s);
    }
}
