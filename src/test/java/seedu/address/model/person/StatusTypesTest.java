package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StatusTypesTest {

    @Test
    public void isValidInterviewed() {
        assertTrue(StatusTypes.isValidStatusType("interviewed"));
    }

    @Test
    public void isValidPreliminary() {
        assertTrue(StatusTypes.isValidStatusType("preliminary"));
    }
    @Test
    public void isValidOffered() {
        assertTrue(StatusTypes.isValidStatusType("offered"));
    }
    @Test
    public void isValidRejected() {
        assertTrue(StatusTypes.isValidStatusType("rejected"));
    }
}
