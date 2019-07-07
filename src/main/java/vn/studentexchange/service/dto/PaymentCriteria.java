package vn.studentexchange.service.dto;

import java.io.Serializable;
import java.util.Objects;
import vn.studentexchange.domain.enumeration.PaymentMethod;
import vn.studentexchange.domain.enumeration.PaymentType;
import vn.studentexchange.domain.enumeration.PaymentStatusType;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the Payment entity. This class is used in PaymentResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /payments?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PaymentCriteria implements Serializable {
    /**
     * Class for filtering PaymentMethod
     */
    public static class PaymentMethodFilter extends Filter<PaymentMethod> {
    }
    /**
     * Class for filtering PaymentType
     */
    public static class PaymentTypeFilter extends Filter<PaymentType> {
    }
    /**
     * Class for filtering PaymentStatusType
     */
    public static class PaymentStatusTypeFilter extends Filter<PaymentStatusType> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FloatFilter amount;

    private LongFilter code;

    private FloatFilter newBalance;

    private StringFilter note;

    private StringFilter orderCode;

    private PaymentMethodFilter method;

    private PaymentTypeFilter type;

    private PaymentStatusTypeFilter status;

    private InstantFilter createAt;

    private FloatFilter withdrawalFee;

    private LongFilter staffApprovalId;

    private LongFilter staffCancelId;

    private LongFilter customerId;

    private LongFilter createById;

    public PaymentCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FloatFilter getAmount() {
        return amount;
    }

    public void setAmount(FloatFilter amount) {
        this.amount = amount;
    }

    public LongFilter getCode() {
        return code;
    }

    public void setCode(LongFilter code) {
        this.code = code;
    }

    public FloatFilter getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(FloatFilter newBalance) {
        this.newBalance = newBalance;
    }

    public StringFilter getNote() {
        return note;
    }

    public void setNote(StringFilter note) {
        this.note = note;
    }

    public StringFilter getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(StringFilter orderCode) {
        this.orderCode = orderCode;
    }

    public PaymentMethodFilter getMethod() {
        return method;
    }

    public void setMethod(PaymentMethodFilter method) {
        this.method = method;
    }

    public PaymentTypeFilter getType() {
        return type;
    }

    public void setType(PaymentTypeFilter type) {
        this.type = type;
    }

    public PaymentStatusTypeFilter getStatus() {
        return status;
    }

    public void setStatus(PaymentStatusTypeFilter status) {
        this.status = status;
    }

    public InstantFilter getCreateAt() {
        return createAt;
    }

    public void setCreateAt(InstantFilter createAt) {
        this.createAt = createAt;
    }

    public FloatFilter getWithdrawalFee() {
        return withdrawalFee;
    }

    public void setWithdrawalFee(FloatFilter withdrawalFee) {
        this.withdrawalFee = withdrawalFee;
    }

    public LongFilter getStaffApprovalId() {
        return staffApprovalId;
    }

    public void setStaffApprovalId(LongFilter staffApprovalId) {
        this.staffApprovalId = staffApprovalId;
    }

    public LongFilter getStaffCancelId() {
        return staffCancelId;
    }

    public void setStaffCancelId(LongFilter staffCancelId) {
        this.staffCancelId = staffCancelId;
    }

    public LongFilter getCustomerId() {
        return customerId;
    }

    public void setCustomerId(LongFilter customerId) {
        this.customerId = customerId;
    }

    public LongFilter getCreateById() {
        return createById;
    }

    public void setCreateById(LongFilter createById) {
        this.createById = createById;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PaymentCriteria that = (PaymentCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(code, that.code) &&
            Objects.equals(newBalance, that.newBalance) &&
            Objects.equals(note, that.note) &&
            Objects.equals(orderCode, that.orderCode) &&
            Objects.equals(method, that.method) &&
            Objects.equals(type, that.type) &&
            Objects.equals(status, that.status) &&
            Objects.equals(createAt, that.createAt) &&
            Objects.equals(withdrawalFee, that.withdrawalFee) &&
            Objects.equals(staffApprovalId, that.staffApprovalId) &&
            Objects.equals(staffCancelId, that.staffCancelId) &&
            Objects.equals(customerId, that.customerId) &&
            Objects.equals(createById, that.createById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        amount,
        code,
        newBalance,
        note,
        orderCode,
        method,
        type,
        status,
        createAt,
        withdrawalFee,
        staffApprovalId,
        staffCancelId,
        customerId,
        createById
        );
    }

    @Override
    public String toString() {
        return "PaymentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (newBalance != null ? "newBalance=" + newBalance + ", " : "") +
                (note != null ? "note=" + note + ", " : "") +
                (orderCode != null ? "orderCode=" + orderCode + ", " : "") +
                (method != null ? "method=" + method + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (createAt != null ? "createAt=" + createAt + ", " : "") +
                (withdrawalFee != null ? "withdrawalFee=" + withdrawalFee + ", " : "") +
                (staffApprovalId != null ? "staffApprovalId=" + staffApprovalId + ", " : "") +
                (staffCancelId != null ? "staffCancelId=" + staffCancelId + ", " : "") +
                (customerId != null ? "customerId=" + customerId + ", " : "") +
                (createById != null ? "createById=" + createById + ", " : "") +
            "}";
    }

}
