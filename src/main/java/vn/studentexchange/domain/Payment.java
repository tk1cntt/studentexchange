package vn.studentexchange.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import vn.studentexchange.domain.enumeration.PaymentMethod;

import vn.studentexchange.domain.enumeration.PaymentType;

import vn.studentexchange.domain.enumeration.PaymentStatusType;

/**
 * A Payment.
 */
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "code")
    private Long code;

    @Column(name = "new_balance")
    private Float newBalance;

    @Column(name = "note")
    private String note;

    @Column(name = "order_code")
    private String orderCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "method")
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private PaymentType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatusType status;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "withdrawal_fee")
    private Float withdrawalFee;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User staffApproval;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User staffCancel;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User customer;

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

    public Payment amount(Float amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Long getCode() {
        return code;
    }

    public Payment code(Long code) {
        this.code = code;
        return this;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Float getNewBalance() {
        return newBalance;
    }

    public Payment newBalance(Float newBalance) {
        this.newBalance = newBalance;
        return this;
    }

    public void setNewBalance(Float newBalance) {
        this.newBalance = newBalance;
    }

    public String getNote() {
        return note;
    }

    public Payment note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public Payment orderCode(String orderCode) {
        this.orderCode = orderCode;
        return this;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public Payment method(PaymentMethod method) {
        this.method = method;
        return this;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentType getType() {
        return type;
    }

    public Payment type(PaymentType type) {
        this.type = type;
        return this;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public PaymentStatusType getStatus() {
        return status;
    }

    public Payment status(PaymentStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(PaymentStatusType status) {
        this.status = status;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public Payment createAt(Instant createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Float getWithdrawalFee() {
        return withdrawalFee;
    }

    public Payment withdrawalFee(Float withdrawalFee) {
        this.withdrawalFee = withdrawalFee;
        return this;
    }

    public void setWithdrawalFee(Float withdrawalFee) {
        this.withdrawalFee = withdrawalFee;
    }

    public User getStaffApproval() {
        return staffApproval;
    }

    public Payment staffApproval(User user) {
        this.staffApproval = user;
        return this;
    }

    public void setStaffApproval(User user) {
        this.staffApproval = user;
    }

    public User getStaffCancel() {
        return staffCancel;
    }

    public Payment staffCancel(User user) {
        this.staffCancel = user;
        return this;
    }

    public void setStaffCancel(User user) {
        this.staffCancel = user;
    }

    public User getCustomer() {
        return customer;
    }

    public Payment customer(User user) {
        this.customer = user;
        return this;
    }

    public void setCustomer(User user) {
        this.customer = user;
    }

    public User getCreateBy() {
        return createBy;
    }

    public Payment createBy(User user) {
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
        Payment payment = (Payment) o;
        if (payment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Payment{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", code=" + getCode() +
            ", newBalance=" + getNewBalance() +
            ", note='" + getNote() + "'" +
            ", orderCode='" + getOrderCode() + "'" +
            ", method='" + getMethod() + "'" +
            ", type='" + getType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            ", withdrawalFee=" + getWithdrawalFee() +
            "}";
    }
}
