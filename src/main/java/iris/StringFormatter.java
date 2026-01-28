package iris;

import iris.exception.InvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StringFormatter {

    public static String toInputDateTimeFormat(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return localDateTime.format(dateTimeFormatter);
    }

    public static String toOutputDateTimeFormat(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mma");
        return localDateTime.format(dateTimeFormatter);
    }

    public static LocalDateTime toLocalDateTime(String dateTime) throws InvalidInputException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            return LocalDateTime.parse(dateTime.strip(), dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Date and time must be in yyyy-MM-dd HH:mm format");
        }
    }
}
