package jabpro.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a person's LinkedIn details in JABPro.
 */
public class LinkedIn {

    public final String value;

    /**
     * Constructs a {@code LinkedIn}.
     * @param linkedin
     */
    public LinkedIn(String linkedin) {
        requireNonNull(linkedin);
        value = linkedin;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LinkedIn)) {
            return false;
        }

        LinkedIn otherLinkedIn = (LinkedIn) other;
        return value.equals(otherLinkedIn.value);
    }
}
