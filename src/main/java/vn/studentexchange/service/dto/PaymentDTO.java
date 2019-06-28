package vn.studentexchange.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import vn.studentexchange.domain.enumeration.PaymentMethod;
import vn.studentexchange.domain.enumeration.PaymentType;
import vn.studentexchange.domain.enumeration.PaymentStatusType;

/**
 * A DTO for the Payment entity.
 */
public class PaymentDTO implements Serializable {

    private Long id;

    private Float amount;

    private Long code;

    private Float newBalance;

    private String note;

    private String orderCode;

    private PaymentMethod method;

    private PaymentType type;

    private PaymentStatusType status;

    private LocalDate createAt;

    private Float withdrawalFee;

    private Long staffApprovalId;

    private String staffApprovalLogin;

    private Long staffCancelId;

    private String staffCancelLogin;

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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Float getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Float newBalance) {
        this.newBalance = newBalance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public PaymentStatusType getStatus() {
        return status;
    }

    public void setStatus(PaymentStatusType status) {
        this.status = status;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public Float getWithdrawalFee() {
        return withdrawalFee;
    }

    public void setWithdrawalFee(Float withdrawalFee) {
        this.withdrawalFee = withdrawalFee;
    }

    public Long getStaffApprovalId() {
        return staffApprovalId;
    }

    public void setStaffApprovalId(Long userId) {
        this.staffApprovalId = userId;
    }

    public String getStaffApprovalLogin() {
        return staffApprovalLogin;
    }

    public void setStaffApprovalLogin(String userLogin) {
        this.staffApprovalLogin = userLogin;
    }

    public Long getStaffCancelId() {
        return staffCancelId;
    }

    public void setStaffCancelId(Long userId) {
        this.staffCancelId = userId;
    }

    public String getStaffCancelLogin() {
        return staffCancelLogin;
    }

    public void setStaffCancelLogin(String userLogin) {
        this.staffCancelLogin = userLogin;
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

        PaymentDTO paymentDTO = (PaymentDTO) o;
        if (paymentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paymentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
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
            ", staffApproval=" + getStaffApprovalId() +
            ", staffApproval='" + getStaffApprovalLogin() + "'" +
            ", staffCancel=" + getStaffCancelId() +
            ", staffCancel='" + getStaffCancelLogin() + "'" +
            ", createBy=" + getCreateById() +
            ", createBy='" + getCreateByLogin() + "'" +
            "}";
    }
}
