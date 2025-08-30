import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Parser {

    public static Command parse(String fullCommand) {
        if (fullCommand.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        }
        else if (fullCommand.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (fullCommand.toLowerCase().startsWith("mark")) {
            return parseMarkCommand(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("unmark")) {
            return parseUnmarkCommand(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("delete")) {
            return parseDeleteCommand(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("todo")) {
            return parseToDoCommand(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("deadline")) {
            return parseDeadlineCommand(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("event")) {
            return parseEventCommand(fullCommand);
        } else {
            throw new UnsupportedOperationException("I'm sorry, I don't know what that means.");
        }
    }

    private static Command parseMarkCommand(String fullCommand) {
        try {
            int idx = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new MarkCommand(idx);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid task number for 'mark'.");
        }
    }

    private static Command parseUnmarkCommand(String fullCommand) {
        try {
            int idx = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new UnmarkCommand(idx);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid task number for 'unmark'.");
        }
    }

    // Delete Command Parsing
    private static Command parseDeleteCommand(String fullCommand) {
        try {
            int idx = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new DeleteCommand(idx);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid task number for 'delete'.");
        }
    }

    // ToDo Command Parsing
    private static Command parseToDoCommand(String fullCommand) {
        ArrayList<String> parsedData = new ArrayList<>();

        String description = fullCommand.substring(4).trim();
        if (description.isBlank()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        parsedData.add("todo");
        parsedData.add(description);
        return new AddCommand(parsedData);
    }

    // Deadline Command Parsing
    private static Command parseDeadlineCommand(String fullCommand) {
        ArrayList<String> parsedData = new ArrayList<>();

        if (!fullCommand.contains(" /by")) {
            throw new IllegalArgumentException("Deadline must contain /by.");
        }
        String[] sections = fullCommand.substring(8).split(" /by", 2);
        String description = sections[0].trim();
        String by = sections[1].trim();
        if (description.isBlank() || by.isBlank()) {
            throw new IllegalArgumentException("Deadline description and /by cannot be empty.");
        }
        parsedData.add("deadline");
        parsedData.add(description);
        parsedData.add(by);
        return new AddCommand(parsedData);
    }

    // Event Command Parsing
    private static Command parseEventCommand(String fullCommand) {
        ArrayList<String> parsedData = new ArrayList<>();
        if (!fullCommand.contains(" /from") || !fullCommand.contains(" /to")) {
            throw new IllegalArgumentException("Event must contain /from and /to.");
        }
        String[] sections = fullCommand.substring(5).split(" /from| /to", 3);
        String description = sections[0].trim();
        String from = sections[1].trim();
        String to = sections[2].trim();
        if (description.isBlank() || from.isBlank() || to.isBlank()) {
            throw new IllegalArgumentException("Event description, /from, and /to cannot be empty.");
        }
        parsedData.add("event");
        parsedData.add(description);
        parsedData.add(from);
        parsedData.add(to);
        return new AddCommand(parsedData);
    }

    // Parse date and time to LocalDateTime
    protected static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm' format.");
        }
    }
}