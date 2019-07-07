package vn.studentexchange.service.dto;

import vn.studentexchange.domain.enumeration.OrderTransactionType;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the OrderTransaction entity.
 */
public class OrderTransactionDTO implements Serializable {

    private Long id;

    private Float amount;

    private String note;

    private OrderTransactionType status;

    private Instant createAt;

    private Long orderCartId;

    private Long orderCodeId;

    private String orderCodeCode;

    private Long createById;

    private String createByLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OrderTransactionType getStatus() {
        return status;
    }

    public void setStatus(OrderTransactionType status) {
        this.status = status;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Long getOrderCartId() {
        return orderCartId;
    }

    public void setOrderCartId(Long orderCartId) {
        this.orderCartId = orderCartId;
    }

    public Long getOrderCodeId() {
        return orderCodeId;
    }

    public void setOrderCodeId(Long orderCartId) {
        this.orderCodeId = orderCartId;
    }

    public String getOrderCodeCode() {
        return orderCodeCode;
    }

    public void setOrderCodeCode(String orderCartCode) {
        this.orderCodeCode = orderCartCode;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long userId) {
        this.createById = userId;
    }

    public String getCreateByLogin() {
        return createByLogin;
    }

    public void setCreateByLogin(String userLogin) {
        this.createByLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderTransactionDTO orderTransactionDTO = (OrderTransactionDTO) o;
        if (orderTransactionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderTransactionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderTransactionDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", note='" + getNote() + "'" +
            ", status='" + getStatus() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            ", orderCart=" + getOrderCartId() +
            ", orderCode=" + getOrderCodeId() +
            ", orderCode='" + getOrderCodeCode() + "'" +
            ", createBy=" + getCreateById() +
            ", createBy='" + getCreateByLogin() + "'" +
            "}";
    }
}
