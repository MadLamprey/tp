package jabpro.model.person;

import static jabpro.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LinkedInTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LinkedIn(null));
    }

    @Test
    public void equals() {
        LinkedIn linkedIn = new LinkedIn("alexyeoh");
        assertTrue(linkedIn.equals(new LinkedIn("alexyeoh")));

        assertTrue(linkedIn.equals(linkedIn));

        assertFalse(linkedIn.equals(5.0f));

        assertFalse(linkedIn.equals(new LinkedIn("zhiwang")));
    }

    @Test
    public void toStringMethod() {
        LinkedIn linkedIn = new LinkedIn("alexyeoh");
        String s = "alexyeoh";
        assertEquals(linkedIn.toString(), s);
    }
}
