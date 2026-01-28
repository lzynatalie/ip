package iris;

import iris.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StringFormatterTest {

    @Test
    public void toLocalDateTime_normalInput_success() throws InvalidInputException {
        assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0),
                StringFormatter.toLocalDateTime("2025-01-01 00:00"));

        assertEquals(LocalDateTime.of(2030, 12, 12, 23, 59),
                StringFormatter.toLocalDateTime("2030-12-12 23:59"));
    }

    @Test
    public void toLocalDateTime_trailingSpaces_success() throws InvalidInputException {
        assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0),
                StringFormatter.toLocalDateTime("2025-01-01 00:00     "));

        assertEquals(LocalDateTime.of(2030, 12, 12, 23, 59),
                StringFormatter.toLocalDateTime("2030-12-12 23:59     "));
    }

    @Test
    public void toLocalDateTime_slashedDate_exceptionThrown() {
        try {
            assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0),
                    StringFormatter.toLocalDateTime("2025/01/01 00:00"));
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid input: Date and time must be in yyyy-MM-dd HH:mm format",
                    e.getMessage());
        }
    }

    @Test
    public void toLocalDateTime_militaryTime_exceptionThrown() {
        try {
            assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0),
                    StringFormatter.toLocalDateTime("2025-01-01 0000"));
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid input: Date and time must be in yyyy-MM-dd HH:mm format",
                    e.getMessage());
        }
    }

    @Test
    public void toLocalDateTime_extraSpace_exceptionThrown() {
        try {
            assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0),
                    StringFormatter.toLocalDateTime("2025-01-01  00:00"));
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid input: Date and time must be in yyyy-MM-dd HH:mm format",
                    e.getMessage());
        }
    }

    @Test
    public void toLocalDateTime_emptyString_exceptionThrown() {
        try {
            assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0),
                    StringFormatter.toLocalDateTime(""));
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid input: Date and time must be in yyyy-MM-dd HH:mm format",
                    e.getMessage());
        }
    }
}
