package vn.studentexchange.service.dto;

import java.io.Serializable;
import java.util.Objects;
import vn.studentexchange.domain.enumeration.OrderStatus;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the OrderCart entity. This class is used in OrderCartResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /order-carts?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrderCartCriteria implements Serializable {
    /**
     * Class for filtering OrderStatus
     */
    public static class OrderStatusFilter extends Filter<OrderStatus> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter code;

    private StringFilter shippingChinaCode;

    private StringFilter avatar;

    private StringFilter aliwangwang;

    private FloatFilter amountDiscount;

    private FloatFilter amountPaid;

    private FloatFilter depositAmount;

    private FloatFilter depositRatio;

    private InstantFilter depositTime;

    private FloatFilter domesticShippingChinaFeeNDT;

    private FloatFilter domesticShippingChinaFee;

    private FloatFilter domesticShippingVietnamFee;

    private IntegerFilter quantityOrder;

    private IntegerFilter quantityPending;

    private IntegerFilter quantityReceived;

    private FloatFilter rate;

    private StringFilter receiverName;

    private StringFilter receiverAddress;

    private StringFilter receiverMobile;

    private StringFilter receiverNote;

    private FloatFilter refundAmountByAlipay;

    private FloatFilter refundAmountByComplaint;

    private FloatFilter refundAmountByOrder;

    private FloatFilter refundAmountPending;

    private FloatFilter shippingChinaVietnamFee;

    private FloatFilter shippingChinaVietnamFeeDiscount;

    private FloatFilter serviceFee;

    private FloatFilter serviceFeeDiscount;

    private BooleanFilter itemChecking;

    private BooleanFilter itemWoodCrating;

    private StringFilter shopId;

    private StringFilter shopLink;

    private StringFilter shopName;

    private StringFilter shopNote;

    private StringFilter website;

    private OrderStatusFilter status;

    private StringFilter statusName;

    private StringFilter statusStyle;

    private FloatFilter tallyFee;

    private FloatFilter totalAmount;

    private FloatFilter totalAmountNDT;

    private FloatFilter totalPaidByCustomer;

    private FloatFilter totalServiceFee;

    private IntegerFilter totalQuantity;

    private FloatFilter finalAmount;

    private InstantFilter createAt;

    private InstantFilter updateAt;

    private LongFilter itemsId;

    private LongFilter commentsId;

    private LongFilter historiesId;

    private LongFilter packagesId;

    private LongFilter transactionsId;

    private LongFilter buyerId;

    private LongFilter chinaStockerId;

    private LongFilter vietnamStockerId;

    private LongFilter exporterId;

    private LongFilter createById;

    private LongFilter updateById;

    public OrderCartCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getCode() {
        return code;
    }

    public void setCode(LongFilter code) {
        this.code = code;
    }

    public StringFilter getShippingChinaCode() {
        return shippingChinaCode;
    }

    public void setShippingChinaCode(StringFilter shippingChinaCode) {
        this.shippingChinaCode = shippingChinaCode;
    }

    public StringFilter getAvatar() {
        return avatar;
    }

    public void setAvatar(StringFilter avatar) {
        this.avatar = avatar;
    }

    public StringFilter getAliwangwang() {
        return aliwangwang;
    }

    public void setAliwangwang(StringFilter aliwangwang) {
        this.aliwangwang = aliwangwang;
    }

    public FloatFilter getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(FloatFilter amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public FloatFilter getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(FloatFilter amountPaid) {
        this.amountPaid = amountPaid;
    }

    public FloatFilter getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(FloatFilter depositAmount) {
        this.depositAmount = depositAmount;
    }

    public FloatFilter getDepositRatio() {
        return depositRatio;
    }

    public void setDepositRatio(FloatFilter depositRatio) {
        this.depositRatio = depositRatio;
    }

    public InstantFilter getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(InstantFilter depositTime) {
        this.depositTime = depositTime;
    }

    public FloatFilter getDomesticShippingChinaFeeNDT() {
        return domesticShippingChinaFeeNDT;
    }

    public void setDomesticShippingChinaFeeNDT(FloatFilter domesticShippingChinaFeeNDT) {
        this.domesticShippingChinaFeeNDT = domesticShippingChinaFeeNDT;
    }

    public FloatFilter getDomesticShippingChinaFee() {
        return domesticShippingChinaFee;
    }

    public void setDomesticShippingChinaFee(FloatFilter domesticShippingChinaFee) {
        this.domesticShippingChinaFee = domesticShippingChinaFee;
    }

    public FloatFilter getDomesticShippingVietnamFee() {
        return domesticShippingVietnamFee;
    }

    public void setDomesticShippingVietnamFee(FloatFilter domesticShippingVietnamFee) {
        this.domesticShippingVietnamFee = domesticShippingVietnamFee;
    }

    public IntegerFilter getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(IntegerFilter quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public IntegerFilter getQuantityPending() {
        return quantityPending;
    }

    public void setQuantityPending(IntegerFilter quantityPending) {
        this.quantityPending = quantityPending;
    }

    public IntegerFilter getQuantityReceived() {
        return quantityReceived;
    }

    public void setQuantityReceived(IntegerFilter quantityReceived) {
        this.quantityReceived = quantityReceived;
    }

    public FloatFilter getRate() {
        return rate;
    }

    public void setRate(FloatFilter rate) {
        this.rate = rate;
    }

    public StringFilter getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(StringFilter receiverName) {
        this.receiverName = receiverName;
    }

    public StringFilter getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(StringFilter receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public StringFilter getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(StringFilter receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public StringFilter getReceiverNote() {
        return receiverNote;
    }

    public void setReceiverNote(StringFilter receiverNote) {
        this.receiverNote = receiverNote;
    }

    public FloatFilter getRefundAmountByAlipay() {
        return refundAmountByAlipay;
    }

    public void setRefundAmountByAlipay(FloatFilter refundAmountByAlipay) {
        this.refundAmountByAlipay = refundAmountByAlipay;
    }

    public FloatFilter getRefundAmountByComplaint() {
        return refundAmountByComplaint;
    }

    public void setRefundAmountByComplaint(FloatFilter refundAmountByComplaint) {
        this.refundAmountByComplaint = refundAmountByComplaint;
    }

    public FloatFilter getRefundAmountByOrder() {
        return refundAmountByOrder;
    }

    public void setRefundAmountByOrder(FloatFilter refundAmountByOrder) {
        this.refundAmountByOrder = refundAmountByOrder;
    }

    public FloatFilter getRefundAmountPending() {
        return refundAmountPending;
    }

    public void setRefundAmountPending(FloatFilter refundAmountPending) {
        this.refundAmountPending = refundAmountPending;
    }

    public FloatFilter getShippingChinaVietnamFee() {
        return shippingChinaVietnamFee;
    }

    public void setShippingChinaVietnamFee(FloatFilter shippingChinaVietnamFee) {
        this.shippingChinaVietnamFee = shippingChinaVietnamFee;
    }

    public FloatFilter getShippingChinaVietnamFeeDiscount() {
        return shippingChinaVietnamFeeDiscount;
    }

    public void setShippingChinaVietnamFeeDiscount(FloatFilter shippingChinaVietnamFeeDiscount) {
        this.shippingChinaVietnamFeeDiscount = shippingChinaVietnamFeeDiscount;
    }

    public FloatFilter getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(FloatFilter serviceFee) {
        this.serviceFee = serviceFee;
    }

    public FloatFilter getServiceFeeDiscount() {
        return serviceFeeDiscount;
    }

    public void setServiceFeeDiscount(FloatFilter serviceFeeDiscount) {
        this.serviceFeeDiscount = serviceFeeDiscount;
    }

    public BooleanFilter getItemChecking() {
        return itemChecking;
    }

    public void setItemChecking(BooleanFilter itemChecking) {
        this.itemChecking = itemChecking;
    }

    public BooleanFilter getItemWoodCrating() {
        return itemWoodCrating;
    }

    public void setItemWoodCrating(BooleanFilter itemWoodCrating) {
        this.itemWoodCrating = itemWoodCrating;
    }

    public StringFilter getShopId() {
        return shopId;
    }

    public void setShopId(StringFilter shopId) {
        this.shopId = shopId;
    }

    public StringFilter getShopLink() {
        return shopLink;
    }

    public void setShopLink(StringFilter shopLink) {
        this.shopLink = shopLink;
    }

    public StringFilter getShopName() {
        return shopName;
    }

    public void setShopName(StringFilter shopName) {
        this.shopName = shopName;
    }

    public StringFilter getShopNote() {
        return shopNote;
    }

    public void setShopNote(StringFilter shopNote) {
        this.shopNote = shopNote;
    }

    public StringFilter getWebsite() {
        return website;
    }

    public void setWebsite(StringFilter website) {
        this.website = website;
    }

    public OrderStatusFilter getStatus() {
        return status;
    }

    public void setStatus(OrderStatusFilter status) {
        this.status = status;
    }

    public StringFilter getStatusName() {
        return statusName;
    }

    public void setStatusName(StringFilter statusName) {
        this.statusName = statusName;
    }

    public StringFilter getStatusStyle() {
        return statusStyle;
    }

    public void setStatusStyle(StringFilter statusStyle) {
        this.statusStyle = statusStyle;
    }

    public FloatFilter getTallyFee() {
        return tallyFee;
    }

    public void setTallyFee(FloatFilter tallyFee) {
        this.tallyFee = tallyFee;
    }

    public FloatFilter getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(FloatFilter totalAmount) {
        this.totalAmount = totalAmount;
    }

    public FloatFilter getTotalAmountNDT() {
        return totalAmountNDT;
    }

    public void setTotalAmountNDT(FloatFilter totalAmountNDT) {
        this.totalAmountNDT = totalAmountNDT;
    }

    public FloatFilter getTotalPaidByCustomer() {
        return totalPaidByCustomer;
    }

    public void setTotalPaidByCustomer(FloatFilter totalPaidByCustomer) {
        this.totalPaidByCustomer = totalPaidByCustomer;
    }

    public FloatFilter getTotalServiceFee() {
        return totalServiceFee;
    }

    public void setTotalServiceFee(FloatFilter totalServiceFee) {
        this.totalServiceFee = totalServiceFee;
    }

    public IntegerFilter getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(IntegerFilter totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public FloatFilter getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(FloatFilter finalAmount) {
        this.finalAmount = finalAmount;
    }

    public InstantFilter getCreateAt() {
        return createAt;
    }

    public void setCreateAt(InstantFilter createAt) {
        this.createAt = createAt;
    }

    public InstantFilter getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(InstantFilter updateAt) {
        this.updateAt = updateAt;
    }

    public LongFilter getItemsId() {
        return itemsId;
    }

    public void setItemsId(LongFilter itemsId) {
        this.itemsId = itemsId;
    }

    public LongFilter getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(LongFilter commentsId) {
        this.commentsId = commentsId;
    }

    public LongFilter getHistoriesId() {
        return historiesId;
    }

    public void setHistoriesId(LongFilter historiesId) {
        this.historiesId = historiesId;
    }

    public LongFilter getPackagesId() {
        return packagesId;
    }

    public void setPackagesId(LongFilter packagesId) {
        this.packagesId = packagesId;
    }

    public LongFilter getTransactionsId() {
        return transactionsId;
    }

    public void setTransactionsId(LongFilter transactionsId) {
        this.transactionsId = transactionsId;
    }

    public LongFilter getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(LongFilter buyerId) {
        this.buyerId = buyerId;
    }

    public LongFilter getChinaStockerId() {
        return chinaStockerId;
    }

    public void setChinaStockerId(LongFilter chinaStockerId) {
        this.chinaStockerId = chinaStockerId;
    }

    public LongFilter getVietnamStockerId() {
        return vietnamStockerId;
    }

    public void setVietnamStockerId(LongFilter vietnamStockerId) {
        this.vietnamStockerId = vietnamStockerId;
    }

    public LongFilter getExporterId() {
        return exporterId;
    }

    public void setExporterId(LongFilter exporterId) {
        this.exporterId = exporterId;
    }

    public LongFilter getCreateById() {
        return createById;
    }

    public void setCreateById(LongFilter createById) {
        this.createById = createById;
    }

    public LongFilter getUpdateById() {
        return updateById;
    }

    public void setUpdateById(LongFilter updateById) {
        this.updateById = updateById;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrderCartCriteria that = (OrderCartCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(shippingChinaCode, that.shippingChinaCode) &&
            Objects.equals(avatar, that.avatar) &&
            Objects.equals(aliwangwang, that.aliwangwang) &&
            Objects.equals(amountDiscount, that.amountDiscount) &&
            Objects.equals(amountPaid, that.amountPaid) &&
            Objects.equals(depositAmount, that.depositAmount) &&
            Objects.equals(depositRatio, that.depositRatio) &&
            Objects.equals(depositTime, that.depositTime) &&
            Objects.equals(domesticShippingChinaFeeNDT, that.domesticShippingChinaFeeNDT) &&
            Objects.equals(domesticShippingChinaFee, that.domesticShippingChinaFee) &&
            Objects.equals(domesticShippingVietnamFee, that.domesticShippingVietnamFee) &&
            Objects.equals(quantityOrder, that.quantityOrder) &&
            Objects.equals(quantityPending, that.quantityPending) &&
            Objects.equals(quantityReceived, that.quantityReceived) &&
            Objects.equals(rate, that.rate) &&
            Objects.equals(receiverName, that.receiverName) &&
            Objects.equals(receiverAddress, that.receiverAddress) &&
            Objects.equals(receiverMobile, that.receiverMobile) &&
            Objects.equals(receiverNote, that.receiverNote) &&
            Objects.equals(refundAmountByAlipay, that.refundAmountByAlipay) &&
            Objects.equals(refundAmountByComplaint, that.refundAmountByComplaint) &&
            Objects.equals(refundAmountByOrder, that.refundAmountByOrder) &&
            Objects.equals(refundAmountPending, that.refundAmountPending) &&
            Objects.equals(shippingChinaVietnamFee, that.shippingChinaVietnamFee) &&
            Objects.equals(shippingChinaVietnamFeeDiscount, that.shippingChinaVietnamFeeDiscount) &&
            Objects.equals(serviceFee, that.serviceFee) &&
            Objects.equals(serviceFeeDiscount, that.serviceFeeDiscount) &&
            Objects.equals(itemChecking, that.itemChecking) &&
            Objects.equals(itemWoodCrating, that.itemWoodCrating) &&
            Objects.equals(shopId, that.shopId) &&
            Objects.equals(shopLink, that.shopLink) &&
            Objects.equals(shopName, that.shopName) &&
            Objects.equals(shopNote, that.shopNote) &&
            Objects.equals(website, that.website) &&
            Objects.equals(status, that.status) &&
            Objects.equals(statusName, that.statusName) &&
            Objects.equals(statusStyle, that.statusStyle) &&
            Objects.equals(tallyFee, that.tallyFee) &&
            Objects.equals(totalAmount, that.totalAmount) &&
            Objects.equals(totalAmountNDT, that.totalAmountNDT) &&
            Objects.equals(totalPaidByCustomer, that.totalPaidByCustomer) &&
            Objects.equals(totalServiceFee, that.totalServiceFee) &&
            Objects.equals(totalQuantity, that.totalQuantity) &&
            Objects.equals(finalAmount, that.finalAmount) &&
            Objects.equals(createAt, that.createAt) &&
            Objects.equals(updateAt, that.updateAt) &&
            Objects.equals(itemsId, that.itemsId) &&
            Objects.equals(commentsId, that.commentsId) &&
            Objects.equals(historiesId, that.historiesId) &&
            Objects.equals(packagesId, that.packagesId) &&
            Objects.equals(transactionsId, that.transactionsId) &&
            Objects.equals(buyerId, that.buyerId) &&
            Objects.equals(chinaStockerId, that.chinaStockerId) &&
            Objects.equals(vietnamStockerId, that.vietnamStockerId) &&
            Objects.equals(exporterId, that.exporterId) &&
            Objects.equals(createById, that.createById) &&
            Objects.equals(updateById, that.updateById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        shippingChinaCode,
        avatar,
        aliwangwang,
        amountDiscount,
        amountPaid,
        depositAmount,
        depositRatio,
        depositTime,
        domesticShippingChinaFeeNDT,
        domesticShippingChinaFee,
        domesticShippingVietnamFee,
        quantityOrder,
        quantityPending,
        quantityReceived,
        rate,
        receiverName,
        receiverAddress,
        receiverMobile,
        receiverNote,
        refundAmountByAlipay,
        refundAmountByComplaint,
        refundAmountByOrder,
        refundAmountPending,
        shippingChinaVietnamFee,
        shippingChinaVietnamFeeDiscount,
        serviceFee,
        serviceFeeDiscount,
        itemChecking,
        itemWoodCrating,
        shopId,
        shopLink,
        shopName,
        shopNote,
        website,
        status,
        statusName,
        statusStyle,
        tallyFee,
        totalAmount,
        totalAmountNDT,
        totalPaidByCustomer,
        totalServiceFee,
        totalQuantity,
        finalAmount,
        createAt,
        updateAt,
        itemsId,
        commentsId,
        historiesId,
        packagesId,
        transactionsId,
        buyerId,
        chinaStockerId,
        vietnamStockerId,
        exporterId,
        createById,
        updateById
        );
    }

    @Override
    public String toString() {
        return "OrderCartCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (shippingChinaCode != null ? "shippingChinaCode=" + shippingChinaCode + ", " : "") +
                (avatar != null ? "avatar=" + avatar + ", " : "") +
                (aliwangwang != null ? "aliwangwang=" + aliwangwang + ", " : "") +
                (amountDiscount != null ? "amountDiscount=" + amountDiscount + ", " : "") +
                (amountPaid != null ? "amountPaid=" + amountPaid + ", " : "") +
                (depositAmount != null ? "depositAmount=" + depositAmount + ", " : "") +
                (depositRatio != null ? "depositRatio=" + depositRatio + ", " : "") +
                (depositTime != null ? "depositTime=" + depositTime + ", " : "") +
                (domesticShippingChinaFeeNDT != null ? "domesticShippingChinaFeeNDT=" + domesticShippingChinaFeeNDT + ", " : "") +
                (domesticShippingChinaFee != null ? "domesticShippingChinaFee=" + domesticShippingChinaFee + ", " : "") +
                (domesticShippingVietnamFee != null ? "domesticShippingVietnamFee=" + domesticShippingVietnamFee + ", " : "") +
                (quantityOrder != null ? "quantityOrder=" + quantityOrder + ", " : "") +
                (quantityPending != null ? "quantityPending=" + quantityPending + ", " : "") +
                (quantityReceived != null ? "quantityReceived=" + quantityReceived + ", " : "") +
                (rate != null ? "rate=" + rate + ", " : "") +
                (receiverName != null ? "receiverName=" + receiverName + ", " : "") +
                (receiverAddress != null ? "receiverAddress=" + receiverAddress + ", " : "") +
                (receiverMobile != null ? "receiverMobile=" + receiverMobile + ", " : "") +
                (receiverNote != null ? "receiverNote=" + receiverNote + ", " : "") +
                (refundAmountByAlipay != null ? "refundAmountByAlipay=" + refundAmountByAlipay + ", " : "") +
                (refundAmountByComplaint != null ? "refundAmountByComplaint=" + refundAmountByComplaint + ", " : "") +
                (refundAmountByOrder != null ? "refundAmountByOrder=" + refundAmountByOrder + ", " : "") +
                (refundAmountPending != null ? "refundAmountPending=" + refundAmountPending + ", " : "") +
                (shippingChinaVietnamFee != null ? "shippingChinaVietnamFee=" + shippingChinaVietnamFee + ", " : "") +
                (shippingChinaVietnamFeeDiscount != null ? "shippingChinaVietnamFeeDiscount=" + shippingChinaVietnamFeeDiscount + ", " : "") +
                (serviceFee != null ? "serviceFee=" + serviceFee + ", " : "") +
                (serviceFeeDiscount != null ? "serviceFeeDiscount=" + serviceFeeDiscount + ", " : "") +
                (itemChecking != null ? "itemChecking=" + itemChecking + ", " : "") +
                (itemWoodCrating != null ? "itemWoodCrating=" + itemWoodCrating + ", " : "") +
                (shopId != null ? "shopId=" + shopId + ", " : "") +
                (shopLink != null ? "shopLink=" + shopLink + ", " : "") +
                (shopName != null ? "shopName=" + shopName + ", " : "") +
                (shopNote != null ? "shopNote=" + shopNote + ", " : "") +
                (website != null ? "website=" + website + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (statusName != null ? "statusName=" + statusName + ", " : "") +
                (statusStyle != null ? "statusStyle=" + statusStyle + ", " : "") +
                (tallyFee != null ? "tallyFee=" + tallyFee + ", " : "") +
                (totalAmount != null ? "totalAmount=" + totalAmount + ", " : "") +
                (totalAmountNDT != null ? "totalAmountNDT=" + totalAmountNDT + ", " : "") +
                (totalPaidByCustomer != null ? "totalPaidByCustomer=" + totalPaidByCustomer + ", " : "") +
                (totalServiceFee != null ? "totalServiceFee=" + totalServiceFee + ", " : "") +
                (totalQuantity != null ? "totalQuantity=" + totalQuantity + ", " : "") +
                (finalAmount != null ? "finalAmount=" + finalAmount + ", " : "") +
                (createAt != null ? "createAt=" + createAt + ", " : "") +
                (updateAt != null ? "updateAt=" + updateAt + ", " : "") +
                (itemsId != null ? "itemsId=" + itemsId + ", " : "") +
                (commentsId != null ? "commentsId=" + commentsId + ", " : "") +
                (historiesId != null ? "historiesId=" + historiesId + ", " : "") +
                (packagesId != null ? "packagesId=" + packagesId + ", " : "") +
                (transactionsId != null ? "transactionsId=" + transactionsId + ", " : "") +
                (buyerId != null ? "buyerId=" + buyerId + ", " : "") +
                (chinaStockerId != null ? "chinaStockerId=" + chinaStockerId + ", " : "") +
                (vietnamStockerId != null ? "vietnamStockerId=" + vietnamStockerId + ", " : "") +
                (exporterId != null ? "exporterId=" + exporterId + ", " : "") +
                (createById != null ? "createById=" + createById + ", " : "") +
                (updateById != null ? "updateById=" + updateById + ", " : "") +
            "}";
    }

}
