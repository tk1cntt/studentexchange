package vn.studentexchange.web.rest.util;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Hex;
import org.hashids.Hashids;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;
import vn.studentexchange.domain.enumeration.CurrencyType;
import vn.studentexchange.service.CurrencyRateService;
import vn.studentexchange.service.dto.CurrencyRateDTO;
import vn.studentexchange.service.dto.ShoppingCartDTO;
import vn.studentexchange.service.dto.ShoppingCartItemDTO;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class for handling pagination.
 *
 * <p>
 * Pagination uses the same principles as the <a href="https://developer.github.com/v3/#pagination">GitHub API</a>,
 * and follow <a href="http://tools.ietf.org/html/rfc5988">RFC 5988 (Link header)</a>.
 */
public final class Utils {
    static Hashids hashids = new Hashids("id.dropshipping.com.vn");

    private Utils() {
    }

    public static long generateNumber() {
        Date time = new Date();
        StringBuilder tmp = new StringBuilder();
        tmp.append(time.getSeconds());
        tmp.append(time.getYear());
        tmp.append(time.getHours());
        tmp.append(time.getDate());
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

    public static String encode(String key, String data) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            return new String(Hex.encodeHex(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeId(long data) {
        return hashids.encode(data);
    }

    public static long decodeId(String hash) {
        return hashids.decode(hash)[0];
    }

    public static ShoppingCartDTO calculate(ShoppingCartDTO currentCart, CurrencyRateService currencyRateService) {
        Optional<CurrencyRateDTO> rate =  currencyRateService.findByCurrency(CurrencyType.CNY);
        List<ShoppingCartItemDTO> items = currentCart.getItems();
        int totalQuantity = 0;
        float totalAmount = 0f;
        float tallyFee = 0f;
        float serviceFee = 0f;
        float finalAmount = 0f;
        for (ShoppingCartItemDTO item : items) {
            totalQuantity += item.getQuantity();
            totalAmount += (item.getItemPriceNDT() * item.getQuantity());
        }
        if (currentCart.isItemChecking() != null && currentCart.isItemChecking()) {
            tallyFee = (float) totalQuantity * Utils.getTallyFee(totalQuantity);
        }
        totalAmount = (float) Math.ceil(totalAmount * rate.get().getRate());
        serviceFee = (float) Math.ceil(totalAmount * Utils.getServiceFee(totalAmount));
        finalAmount = totalAmount + serviceFee + tallyFee;
        currentCart.setTallyFee(tallyFee);
        currentCart.setServiceFee(serviceFee);
        currentCart.setTotalAmount(totalAmount);
        currentCart.setTotalQuantity(totalQuantity);
        currentCart.setFinalAmount(finalAmount);
        return currentCart;
    }
}
