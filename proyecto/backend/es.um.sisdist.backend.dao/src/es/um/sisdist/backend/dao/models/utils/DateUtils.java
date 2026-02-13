package es.um.sisdist.backend.dao.models.utils;

import java.util.Date;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static Date getCurrentDateISO() {
        Instant now = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        String fecha =  DateTimeFormatter.ISO_INSTANT
                .withZone(ZoneOffset.UTC)
                .format(now);
        return Date.from(Instant.parse(fecha));
    }
}
