package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindNameCommand extends Command {

    public static final String COMMAND_WORD = "fn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain all of "
            + "the prefix of the specified keywords (case-insensitive, and alphabets only) and displays them as a "
            + "list with index numbers.\n"
            + "Parameters: KEYWORDS\n"
            + "Example: " + COMMAND_WORD + "alice";

    private final NameContainsKeywordsPredicate predicate;

    public FindNameCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getDisplayPersons().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindNameCommand)) {
            return false;
        }

        FindNameCommand otherFindNameCommand = (FindNameCommand) other;
        return predicate.equals(otherFindNameCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
