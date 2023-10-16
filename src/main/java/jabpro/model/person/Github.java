package jabpro.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a person's Github details in JABPro.
 */
public class Github {

    public final String value;

    /**
     * Constructs a {@code Github}.
     * @param github A valid Github username
     */
    public Github(String github) {
        requireNonNull(github);
        value = github;
    }

    @Override
    public String toString() {
        return value != null ? value : "";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Github)) {
            return false;
        }

        Github otherGithub = (Github) other;
        return value.equals(otherGithub.value);
    }
}
