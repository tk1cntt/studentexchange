package vn.studentexchange.web.rest.util;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Utility class for handling pagination.
 *
 * <p>
 * Pagination uses the same principles as the <a href="https://developer.github.com/v3/#pagination">GitHub API</a>,
 * and follow <a href="http://tools.ietf.org/html/rfc5988">RFC 5988 (Link header)</a>.
 */
public final class Utils {

    private Utils() {
    }

    public static long generateNumber() {
        Date time = new Date();
        StringBuilder tmp = new StringBuilder();
        tmp.append(time.getDate());
        tmp.append(time.getHours());
        tmp.append(time.getYear());
        tmp.append(time.getSeconds());
        tmp.append(time.getMonth());
        tmp.append(time.getMinutes());
        return Long.valueOf(tmp.toString());
    }
}
