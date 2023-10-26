package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StatusTest {


    @Test
    public void toStringMethod() {
        assertEquals(new Status().toString(), "Preliminary");
    }

    @Test
    public void constructorTestingOffered() {
        assertEquals(new Status("offered"), new Status(StatusTypes.OFFERED));
    }

    @Test
    public void constructorTestingPrelim() {
        assert(new Status("preliminary").equals(new Status(StatusTypes.PRELIMINARY)));
    }
    @Test
    public void constructorTestingInt() {
        assert(new Status("interviewed").equals(
            new Status(StatusTypes.INTERVIEWED)));
    }
    @Test
    public void constructorTestingRej() {
        assert(new Status("rejected").equals(
            new Status(StatusTypes.REJECTED)));
    }

    @Test
    public void getStatusTypeTestR() {
        assert(new Status("rejected").getStatusType().equals(
            StatusTypes.REJECTED));
    }
    @Test
    public void getStatusTypeTestI() {
        assert(new Status("interviewed").getStatusType().equals(
            StatusTypes.INTERVIEWED));
    }
    @Test
    public void getStatusTypeTestP() {
        assertEquals(new Status("preliminary").getStatusType(), (StatusTypes.PRELIMINARY));
    }
    @Test
    public void getStatusTypeTestO() {
        assertEquals(new Status("offered").getStatusType(),
            StatusTypes.OFFERED);
    }
    @Test
    public void setStatusTypeTest() {
        Status testStatus = new Status();
        testStatus.setStatusType(StatusTypes.INTERVIEWED);
        assertEquals(testStatus.getStatusType(), StatusTypes.INTERVIEWED);
    }
}
