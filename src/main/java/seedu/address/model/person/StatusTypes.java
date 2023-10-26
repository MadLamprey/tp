package seedu.address.model.person;
/**
 * Represents a StatusType in the Status class.
 */
public enum StatusTypes {
    PRELIMINARY("Preliminary"),
    INTERVIEWED("Interviewed"),
    REJECTED("Rejected"),
    OFFERED("Offered");

    private final String statusName;

    StatusTypes(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return this.statusName;
    }

    /**
     * Checks if the provided status is a valid status.
     *
     * @param input The string status to be checked for validity.
     * @return {@code true} if the status is valid, {@code false} otherwise.
     */
    public static boolean isValidStatusType(String input) {
        return input.equals("interviewed") || input.equals("preliminary")
                || input.equals("rejected")
                || input.equals("offered");
    }

}
