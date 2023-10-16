package jabpro.model.person;

import java.util.List;
import java.util.function.Predicate;

import jabpro.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class StatusContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public StatusContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getStatus().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatusContainsKeywordsPredicate)) {
            return false;
        }

        StatusContainsKeywordsPredicate otherStatusContainsKeywordsPredicate = (StatusContainsKeywordsPredicate) other;
        return keywords.equals(otherStatusContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return keywords.toString().replaceAll("[\\[\\]]", "");
    }
}
