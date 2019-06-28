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

    public static int getTallyFee(int quantity) {
        if (quantity > 100) {
            return 1500;
        } else if (quantity > 20) {
            return 2000;
        } else if (quantity > 5) {
            return 3000;
        } else {
            return 5000;
        }
    }

	public static float getServiceFee(float totalAmount) {
        if (totalAmount > 300000000) {
            return 0.01f;
        } else if (totalAmount <= 300000000  && totalAmount > 100000000) {
            return 0.02f;
        } else if (totalAmount <= 100000000  && totalAmount > 50000000) {
            return 0.03f;
        } else if (totalAmount <= 50000000  && totalAmount > 10000000) {
            return 0.04f;
        } else {
            return 0.05f;
        }
	}
}
