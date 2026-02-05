package iris;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import iris.exception.InvalidInputException;

/**
 * This class provides static methods to convert objects to strings and vice versa.
 */
public class StringFormatter {

    /**
     * Converts LocalDateTime to string in yyyy-MM-dd HH:mm format.
     *
     * @param localDateTime LocalDateTime object.
     * @return Date and time string.
     */
    public static String toInputDateTimeFormat(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * Converts LocalDateTime to string in d MMM yyyy hh:mma format.
     *
     * @param localDateTime LocalDateTime object.
     * @return Date and time string.
     */
    public static String toOutputDateTimeFormat(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mma");
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * Converts string in yyyy-MM-dd HH:mm format to LocalDateTime.
     *
     * @param dateTime Date and time string.
     * @return LocalDateTime object.
     * @throws InvalidInputException
     */
    public static LocalDateTime toLocalDateTime(String dateTime) throws InvalidInputException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            return LocalDateTime.parse(dateTime.strip(), dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Date and time must be in yyyy-MM-dd HH:mm format");
        }
    }
}
