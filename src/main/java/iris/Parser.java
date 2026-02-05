package iris;

import iris.command.ClearCommand;
import iris.command.Command;
import iris.command.DeadlineCommand;
import iris.command.DeleteCommand;
import iris.command.EventCommand;
import iris.command.ExitCommand;
import iris.command.FindCommand;
import iris.command.ListCommand;
import iris.command.MarkCommand;
import iris.command.ToDoCommand;
import iris.command.UnknownCommand;
import iris.command.UnmarkCommand;
import iris.exception.InvalidInputException;

/**
 * This class provides a single static method to parse user commands.
 */
public class Parser {

    /**
     * Parses command input by the user.
     *
     * @param fullCommand Full command string.
     * @return Command to execute.
     * @throws InvalidInputException
     */
    public static Command parse(String fullCommand) throws InvalidInputException {
        String[] inputs = fullCommand.split(" ", 2);
        String command = inputs[0];
        String input = inputs.length > 1 ? inputs[1] : "";

        return switch (command) {
        case "bye" -> new ExitCommand();
        case "list" -> new ListCommand();
        case "mark" -> new MarkCommand(parseIndex(input));
        case "unmark" -> new UnmarkCommand(parseIndex(input));
        case "todo" -> new ToDoCommand(parseDescription(input));
        case "deadline" -> parseDeadline(input);
        case "event" -> parseEvent(input);
        case "delete" -> new DeleteCommand(parseIndex(input));
        case "find" -> new FindCommand(parseDescription(input));
        case "clear" -> new ClearCommand();
        default -> new UnknownCommand();
        };
    }

    private static int parseIndex(String input) throws InvalidInputException {
        try {
            return Integer.parseInt(input.strip());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please provide a valid index.");
        }
    }

    private static String parseDescription(String input) {
        return input.strip();
    }

    private static String parseArgument(String input) {
        return input.split(" ", 2)[1].strip();
    }

    private static DeadlineCommand parseDeadline(String input) throws InvalidInputException {
        String[] deadlineParts = input.split("/", 2);

        if (deadlineParts.length < 2 || !deadlineParts[1].startsWith("by ")) {
            throw new InvalidInputException("Please provide a deadline.");
        }

        String description = parseDescription(deadlineParts[0]);
        String by = parseArgument(deadlineParts[1]);

        return new DeadlineCommand(description, by);
    }

    private static EventCommand parseEvent(String input) throws InvalidInputException {
        String[] eventParts = input.split("/", 3);

        if (eventParts.length < 3 || !eventParts[1].startsWith("from ") || !eventParts[2].startsWith("to ")) {
            throw new InvalidInputException("Please provide a start and end time.");
        }

        String description = parseDescription(eventParts[0]);
        String from = parseArgument(eventParts[1]);
        String to = parseArgument(eventParts[2]);

        return new EventCommand(description, from, to);
    }
}
