package iris;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import iris.command.DeadlineCommand;
import iris.exception.InvalidInputException;

public class ParserTest {

    @Test
    public void parseDeadline_normalInput_success() throws InvalidInputException {
        assertEquals(new DeadlineCommand("description", "2025-01-01 00:00"),
                Parser.parse("deadline description /by 2025-01-01 00:00"));

        assertEquals(new DeadlineCommand("description", "2025-01-01 00:00"),
                Parser.parse("deadline description/by 2025-01-01 00:00"));
    }

    @Test
    public void parseDeadline_extraSpaces_success() throws InvalidInputException {
        assertEquals(new DeadlineCommand("description", "2025-01-01 00:00"),
                Parser.parse("deadline description     /by     2025-01-01 00:00     "));
    }

    @Test
    public void parseDeadline_missingDeadline_exceptionThrown() {
        try {
            assertEquals(new DeadlineCommand("", ""),
                    Parser.parse("deadline description"));
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid input: Please provide a deadline.", e.getMessage());
        }
    }

    @Test
    public void parseDeadline_missingCommand_exceptionThrown() {
        try {
            assertEquals(new DeadlineCommand("", ""),
                    Parser.parse("deadline description /"));
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid input: Please provide a deadline.", e.getMessage());
        }
    }

    @Test
    public void parseDeadline_missingSpace_exceptionThrown() {
        try {
            assertEquals(new DeadlineCommand("", ""),
                    Parser.parse("deadline description /by2025-01-01 00:00"));
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid input: Please provide a deadline.", e.getMessage());
        }
    }

    @Test
    public void parseDeadline_wrongCommand_exceptionThrown() {
        try {
            assertEquals(new DeadlineCommand("", ""),
                    Parser.parse("deadline description /due 2025-01-01 00:00"));
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid input: Please provide a deadline.", e.getMessage());
        }
    }
}
