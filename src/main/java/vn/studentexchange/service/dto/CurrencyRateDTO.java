package vn.studentexchange.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import vn.studentexchange.domain.enumeration.CurrencyType;

/**
 * A DTO for the CurrencyRate entity.
 */
public class CurrencyRateDTO implements Serializable {

    private Long id;

    private CurrencyType currency;

    private Float rate;

    private Instant createAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrencyRateDTO currencyRateDTO = (CurrencyRateDTO) o;
        if (currencyRateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), currencyRateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CurrencyRateDTO{" +
            "id=" + getId() +
            ", currency='" + getCurrency() + "'" +
            ", rate=" + getRate() +
            ", createAt='" + getCreateAt() + "'" +
            "}";
    }
}
