package vn.studentexchange.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import vn.studentexchange.domain.enumeration.CurrencyType;

/**
 * A CurrencyRate.
 */
@Entity
@Table(name = "currency_rate")
public class CurrencyRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private CurrencyType currency;

    @Column(name = "rate")
    private Float rate;

    @Column(name = "create_at")
    private LocalDate createAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public CurrencyRate currency(CurrencyType currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public Float getRate() {
        return rate;
    }

    public CurrencyRate rate(Float rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public CurrencyRate createAt(LocalDate createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CurrencyRate currencyRate = (CurrencyRate) o;
        if (currencyRate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), currencyRate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
            "id=" + getId() +
            ", currency='" + getCurrency() + "'" +
            ", rate=" + getRate() +
            ", createAt='" + getCreateAt() + "'" +
            "}";
    }
}
