package org.keldeari.amber;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Utils {
    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    public static LocalDateTime orNow(LocalDateTime date) {
        if (date != null) {
            return date;
        } else {
            return LocalDateTime.now(ZoneOffset.UTC);
        }
    }

    public static LocalDateTime coalesce(LocalDateTime date1, LocalDateTime date2) {
        if (date1 != null) {
            return date1;
        } else {
            return date2;
        }
    }
}
