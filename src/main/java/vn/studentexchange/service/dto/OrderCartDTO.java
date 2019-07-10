package vn.studentexchange.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import vn.studentexchange.domain.enumeration.OrderStatus;

/**
 * A DTO for the OrderCart entity.
 */
public class OrderCartDTO implements Serializable {

    private Long id;

    private Long code;

    private String shippingChinaCode;

    private String avatar;

    private String aliwangwang;

    private Float amountDiscount;

    private Float amountPaid;

    private Float depositAmount;

    private Float depositRatio;

    private Instant depositTime;

    private Float domesticShippingChinaFeeNDT;

    private Float domesticShippingChinaFee;

    private Float domesticShippingVietnamFee;

    private Integer quantityOrder;

    private Integer quantityPending;

    private Integer quantityReceived;

    private Float rate;

    private String receiverName;

    private String receiverAddress;

    private String receiverMobile;

    private String receiverNote;

    private Float refundAmountByAlipay;

    private Float refundAmountByComplaint;

    private Float refundAmountByOrder;

    private Float refundAmountPending;

    private Float shippingChinaVietnamFee;

    private Float shippingChinaVietnamFeeDiscount;

    private Float serviceFee;

    private Float serviceFeeDiscount;

    private Boolean itemChecking;

    private Boolean itemWoodCrating;

    private String shopId;

    private String shopLink;

    private String shopName;

    private String shopNote;

    private String website;

    private OrderStatus status;

    private String statusName;

    private String statusStyle;

    private Float tallyFee;

    private Float totalAmount;

    private Float totalAmountNDT;

    private Float totalAmountChinaNDT;

    private Float totalPaidByCustomer;

    private Float totalServiceFee;

    private Integer totalQuantity;

    private Float finalAmount;

    private Instant createAt;

    private Instant updateAt;

    private Long buyerId;

    private String buyerLogin;

    private Long chinaStockerId;

    private String chinaStockerLogin;

    private Long vietnamStockerId;

    private String vietnamStockerLogin;

    private Long exporterId;

    private String exporterLogin;

    private Long createById;

    private String createByLogin;

    private Long updateById;

    private String updateByLogin;

    private List<OrderItemDTO> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getShippingChinaCode() {
        return shippingChinaCode;
    }

    public void setShippingChinaCode(String shippingChinaCode) {
        this.shippingChinaCode = shippingChinaCode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAliwangwang() {
        return aliwangwang;
    }

    public void setAliwangwang(String aliwangwang) {
        this.aliwangwang = aliwangwang;
    }

    public Float getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(Float amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Float getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Float depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Float getDepositRatio() {
        return depositRatio;
    }

    public void setDepositRatio(Float depositRatio) {
        this.depositRatio = depositRatio;
    }

    public Instant getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(Instant depositTime) {
        this.depositTime = depositTime;
    }

    public Float getDomesticShippingChinaFeeNDT() {
        return domesticShippingChinaFeeNDT;
    }

    public void setDomesticShippingChinaFeeNDT(Float domesticShippingChinaFeeNDT) {
        this.domesticShippingChinaFeeNDT = domesticShippingChinaFeeNDT;
    }

    public Float getDomesticShippingChinaFee() {
        return domesticShippingChinaFee;
    }

    public void setDomesticShippingChinaFee(Float domesticShippingChinaFee) {
        this.domesticShippingChinaFee = domesticShippingChinaFee;
    }

    public Float getDomesticShippingVietnamFee() {
        return domesticShippingVietnamFee;
    }

    public void setDomesticShippingVietnamFee(Float domesticShippingVietnamFee) {
        this.domesticShippingVietnamFee = domesticShippingVietnamFee;
    }

    public Integer getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(Integer quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public Integer getQuantityPending() {
        return quantityPending;
    }

    public void setQuantityPending(Integer quantityPending) {
        this.quantityPending = quantityPending;
    }

    public Integer getQuantityReceived() {
        return quantityReceived;
    }

    public void setQuantityReceived(Integer quantityReceived) {
        this.quantityReceived = quantityReceived;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverNote() {
        return receiverNote;
    }

    public void setReceiverNote(String receiverNote) {
        this.receiverNote = receiverNote;
    }

    public Float getRefundAmountByAlipay() {
        return refundAmountByAlipay;
    }

    public void setRefundAmountByAlipay(Float refundAmountByAlipay) {
        this.refundAmountByAlipay = refundAmountByAlipay;
    }

    public Float getRefundAmountByComplaint() {
        return refundAmountByComplaint;
    }

    public void setRefundAmountByComplaint(Float refundAmountByComplaint) {
        this.refundAmountByComplaint = refundAmountByComplaint;
    }

    public Float getRefundAmountByOrder() {
        return refundAmountByOrder;
    }

    public void setRefundAmountByOrder(Float refundAmountByOrder) {
        this.refundAmountByOrder = refundAmountByOrder;
    }

    public Float getRefundAmountPending() {
        return refundAmountPending;
    }

    public void setRefundAmountPending(Float refundAmountPending) {
        this.refundAmountPending = refundAmountPending;
    }

    public Float getShippingChinaVietnamFee() {
        return shippingChinaVietnamFee;
    }

    public void setShippingChinaVietnamFee(Float shippingChinaVietnamFee) {
        this.shippingChinaVietnamFee = shippingChinaVietnamFee;
    }

    public Float getShippingChinaVietnamFeeDiscount() {
        return shippingChinaVietnamFeeDiscount;
    }

    public void setShippingChinaVietnamFeeDiscount(Float shippingChinaVietnamFeeDiscount) {
        this.shippingChinaVietnamFeeDiscount = shippingChinaVietnamFeeDiscount;
    }

    public Float getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Float serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Float getServiceFeeDiscount() {
        return serviceFeeDiscount;
    }

    public void setServiceFeeDiscount(Float serviceFeeDiscount) {
        this.serviceFeeDiscount = serviceFeeDiscount;
    }

    public Boolean isItemChecking() {
        return itemChecking;
    }

    public void setItemChecking(Boolean itemChecking) {
        this.itemChecking = itemChecking;
    }

    public Boolean isItemWoodCrating() {
        return itemWoodCrating;
    }

    public void setItemWoodCrating(Boolean itemWoodCrating) {
        this.itemWoodCrating = itemWoodCrating;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopLink() {
        return shopLink;
    }

    public void setShopLink(String shopLink) {
        this.shopLink = shopLink;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopNote() {
        return shopNote;
    }

    public void setShopNote(String shopNote) {
        this.shopNote = shopNote;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusStyle() {
        return statusStyle;
    }

    public void setStatusStyle(String statusStyle) {
        this.statusStyle = statusStyle;
    }

    public Float getTallyFee() {
        return tallyFee;
    }

    public void setTallyFee(Float tallyFee) {
        this.tallyFee = tallyFee;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getTotalAmountNDT() {
        return totalAmountNDT;
    }

    public void setTotalAmountNDT(Float totalAmountNDT) {
        this.totalAmountNDT = totalAmountNDT;
    }

    public Float getTotalAmountChinaNDT() {
        return totalAmountChinaNDT;
    }

    public void setTotalAmountChinaNDT(Float totalAmountChinaNDT) {
        this.totalAmountChinaNDT = totalAmountChinaNDT;
    }

    public Float getTotalPaidByCustomer() {
        return totalPaidByCustomer;
    }

    public void setTotalPaidByCustomer(Float totalPaidByCustomer) {
        this.totalPaidByCustomer = totalPaidByCustomer;
    }

    public Float getTotalServiceFee() {
        return totalServiceFee;
    }

    public void setTotalServiceFee(Float totalServiceFee) {
        this.totalServiceFee = totalServiceFee;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Float getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Float finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long userId) {
        this.buyerId = userId;
    }

    public String getBuyerLogin() {
        return buyerLogin;
    }

    public void setBuyerLogin(String userLogin) {
        this.buyerLogin = userLogin;
    }

    public Long getChinaStockerId() {
        return chinaStockerId;
    }

    public void setChinaStockerId(Long userId) {
        this.chinaStockerId = userId;
    }

    public String getChinaStockerLogin() {
        return chinaStockerLogin;
    }

    public void setChinaStockerLogin(String userLogin) {
        this.chinaStockerLogin = userLogin;
    }

    public Long getVietnamStockerId() {
        return vietnamStockerId;
    }

    public void setVietnamStockerId(Long userId) {
        this.vietnamStockerId = userId;
    }

    public String getVietnamStockerLogin() {
        return vietnamStockerLogin;
    }

    public void setVietnamStockerLogin(String userLogin) {
        this.vietnamStockerLogin = userLogin;
    }

    public Long getExporterId() {
        return exporterId;
    }

    public void setExporterId(Long userId) {
        this.exporterId = userId;
    }

    public String getExporterLogin() {
        return exporterLogin;
    }

    public void setExporterLogin(String userLogin) {
        this.exporterLogin = userLogin;
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

    public Long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(Long userId) {
        this.updateById = userId;
    }

    public String getUpdateByLogin() {
        return updateByLogin;
    }

    public void setUpdateByLogin(String userLogin) {
        this.updateByLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderCartDTO orderCartDTO = (OrderCartDTO) o;
        if (orderCartDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderCartDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderCartDTO{" +
            "id=" + getId() +
            ", code=" + getCode() +
            ", shippingChinaCode='" + getShippingChinaCode() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", aliwangwang='" + getAliwangwang() + "'" +
            ", amountDiscount=" + getAmountDiscount() +
            ", amountPaid=" + getAmountPaid() +
            ", depositAmount=" + getDepositAmount() +
            ", depositRatio=" + getDepositRatio() +
            ", depositTime='" + getDepositTime() + "'" +
            ", domesticShippingChinaFeeNDT=" + getDomesticShippingChinaFeeNDT() +
            ", domesticShippingChinaFee=" + getDomesticShippingChinaFee() +
            ", domesticShippingVietnamFee=" + getDomesticShippingVietnamFee() +
            ", quantityOrder=" + getQuantityOrder() +
            ", quantityPending=" + getQuantityPending() +
            ", quantityReceived=" + getQuantityReceived() +
            ", rate=" + getRate() +
            ", receiverName='" + getReceiverName() + "'" +
            ", receiverAddress='" + getReceiverAddress() + "'" +
            ", receiverMobile='" + getReceiverMobile() + "'" +
            ", receiverNote='" + getReceiverNote() + "'" +
            ", refundAmountByAlipay=" + getRefundAmountByAlipay() +
            ", refundAmountByComplaint=" + getRefundAmountByComplaint() +
            ", refundAmountByOrder=" + getRefundAmountByOrder() +
            ", refundAmountPending=" + getRefundAmountPending() +
            ", shippingChinaVietnamFee=" + getShippingChinaVietnamFee() +
            ", shippingChinaVietnamFeeDiscount=" + getShippingChinaVietnamFeeDiscount() +
            ", serviceFee=" + getServiceFee() +
            ", serviceFeeDiscount=" + getServiceFeeDiscount() +
            ", itemChecking='" + isItemChecking() + "'" +
            ", itemWoodCrating='" + isItemWoodCrating() + "'" +
            ", shopId='" + getShopId() + "'" +
            ", shopLink='" + getShopLink() + "'" +
            ", shopName='" + getShopName() + "'" +
            ", shopNote='" + getShopNote() + "'" +
            ", website='" + getWebsite() + "'" +
            ", status='" + getStatus() + "'" +
            ", statusName='" + getStatusName() + "'" +
            ", statusStyle='" + getStatusStyle() + "'" +
            ", tallyFee=" + getTallyFee() +
            ", totalAmount=" + getTotalAmount() +
            ", totalAmountNDT=" + getTotalAmountNDT() +
            ", totalAmountChinaNDT=" + getTotalAmountChinaNDT() +
            ", totalPaidByCustomer=" + getTotalPaidByCustomer() +
            ", totalServiceFee=" + getTotalServiceFee() +
            ", totalQuantity=" + getTotalQuantity() +
            ", finalAmount=" + getFinalAmount() +
            ", createAt='" + getCreateAt() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            ", buyer=" + getBuyerId() +
            ", buyer='" + getBuyerLogin() + "'" +
            ", chinaStocker=" + getChinaStockerId() +
            ", chinaStocker='" + getChinaStockerLogin() + "'" +
            ", vietnamStocker=" + getVietnamStockerId() +
            ", vietnamStocker='" + getVietnamStockerLogin() + "'" +
            ", exporter=" + getExporterId() +
            ", exporter='" + getExporterLogin() + "'" +
            ", createBy=" + getCreateById() +
            ", createBy='" + getCreateByLogin() + "'" +
            ", updateBy=" + getUpdateById() +
            ", updateBy='" + getUpdateByLogin() + "'" +
            "}";
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
