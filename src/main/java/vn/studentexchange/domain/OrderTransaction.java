package vn.studentexchange.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import vn.studentexchange.domain.enumeration.OrderTransactionType;

/**
 * A OrderTransaction.
 */
@Entity
@Table(name = "order_transaction")
public class OrderTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "note")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderTransactionType status;

    @Column(name = "create_at")
    private LocalDate createAt;

    @ManyToOne
    @JsonIgnoreProperties("transactions")
    private OrderCart orderCart;

    @ManyToOne
    @JsonIgnoreProperties("")
    private OrderCart orderCode;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User createBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public OrderTransaction amount(Float amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public OrderTransaction note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OrderTransactionType getStatus() {
        return status;
    }

    public OrderTransaction status(OrderTransactionType status) {
        this.status = status;
        return this;
    }

    public void setStatus(OrderTransactionType status) {
        this.status = status;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public OrderTransaction createAt(LocalDate createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public OrderCart getOrderCart() {
        return orderCart;
    }

    public OrderTransaction orderCart(OrderCart orderCart) {
        this.orderCart = orderCart;
        return this;
    }

    public void setOrderCart(OrderCart orderCart) {
        this.orderCart = orderCart;
    }

    public OrderCart getOrderCode() {
        return orderCode;
    }

    public OrderTransaction orderCode(OrderCart orderCart) {
        this.orderCode = orderCart;
        return this;
    }

    public void setOrderCode(OrderCart orderCart) {
        this.orderCode = orderCart;
    }

    public User getCreateBy() {
        return createBy;
    }

    public OrderTransaction createBy(User user) {
        this.createBy = user;
        return this;
    }

    public void setCreateBy(User user) {
        this.createBy = user;
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
        OrderTransaction orderTransaction = (OrderTransaction) o;
        if (orderTransaction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderTransaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderTransaction{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", note='" + getNote() + "'" +
            ", status='" + getStatus() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            "}";
    }
}
