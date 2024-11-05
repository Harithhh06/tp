package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.AddressContainsKeywordsPredicate;

/**
 * Finds and lists all persons in ClientHub whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindAddressCommand extends Command {

    public static final String COMMAND_WORD = "fa";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all clients whose address contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " tampines";

    private final AddressContainsKeywordsPredicate predicate;

    public FindAddressCommand(AddressContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                Messages.getMessagePersonsListedOverview(model.getDisplayPersons().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindAddressCommand)) {
            return false;
        }

        FindAddressCommand otherFindAddressCommand = (FindAddressCommand) other;
        return predicate.equals(otherFindAddressCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
