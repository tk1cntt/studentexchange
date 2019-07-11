package vn.studentexchange.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import vn.studentexchange.domain.enumeration.OrderTransactionType;

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

    private Long orderId;

    private String orderCode;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderCartId) {
        this.orderId = orderCartId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCartCode) {
        this.orderCode = orderCartCode;
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
            ", order=" + getOrderId() +
            ", order='" + getOrderCode() + "'" +
            ", createBy=" + getCreateById() +
            ", createBy='" + getCreateByLogin() + "'" +
            "}";
    }
}
