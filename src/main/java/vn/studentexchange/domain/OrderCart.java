package vn.studentexchange.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import vn.studentexchange.domain.enumeration.OrderStatus;

/**
 * A OrderCart.
 */
@Entity
@Table(name = "order_cart")
public class OrderCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private Long code;

    @Column(name = "shipping_china_code")
    private String shippingChinaCode;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "aliwangwang")
    private String aliwangwang;

    @Column(name = "amount_discount")
    private Float amountDiscount;

    @Column(name = "amount_paid")
    private Float amountPaid;

    @Column(name = "deposit_amount")
    private Float depositAmount;

    @Column(name = "deposit_ratio")
    private Float depositRatio;

    @Column(name = "deposit_time")
    private Instant depositTime;

    @Column(name = "domestic_shipping_china_fee_ndt")
    private Float domesticShippingChinaFeeNDT;

    @Column(name = "domestic_shipping_china_fee")
    private Float domesticShippingChinaFee;

    @Column(name = "domestic_shipping_vietnam_fee")
    private Float domesticShippingVietnamFee;

    @Column(name = "quantity_order")
    private Integer quantityOrder;

    @Column(name = "quantity_pending")
    private Integer quantityPending;

    @Column(name = "quantity_received")
    private Integer quantityReceived;

    @Column(name = "rate")
    private Float rate;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "receiver_mobile")
    private String receiverMobile;

    @Column(name = "receiver_note")
    private String receiverNote;

    @Column(name = "refund_amount_by_alipay")
    private Float refundAmountByAlipay;

    @Column(name = "refund_amount_by_complaint")
    private Float refundAmountByComplaint;

    @Column(name = "refund_amount_by_order")
    private Float refundAmountByOrder;

    @Column(name = "refund_amount_pending")
    private Float refundAmountPending;

    @Column(name = "shipping_china_vietnam_fee")
    private Float shippingChinaVietnamFee;

    @Column(name = "shipping_china_vietnam_fee_discount")
    private Float shippingChinaVietnamFeeDiscount;

    @Column(name = "service_fee")
    private Float serviceFee;

    @Column(name = "service_fee_discount")
    private Float serviceFeeDiscount;

    @Column(name = "item_checking")
    private Boolean itemChecking;

    @Column(name = "item_wood_crating")
    private Boolean itemWoodCrating;

    @Column(name = "shop_id")
    private String shopId;

    @Column(name = "shop_link")
    private String shopLink;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "shop_note")
    private String shopNote;

    @Column(name = "website")
    private String website;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "status_name")
    private String statusName;

    @Column(name = "status_style")
    private String statusStyle;

    @Column(name = "tally_fee")
    private Float tallyFee;

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "total_amount_ndt")
    private Float totalAmountNDT;

    @Column(name = "total_amount_china_ndt")
    private Float totalAmountChinaNDT;

    @Column(name = "total_paid_by_customer")
    private Float totalPaidByCustomer;

    @Column(name = "total_service_fee")
    private Float totalServiceFee;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "final_amount")
    private Float finalAmount;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @OneToMany(mappedBy = "orderCart")
    private Set<OrderItem> items = new HashSet<>();
    @OneToMany(mappedBy = "orderCart")
    private Set<OrderComment> comments = new HashSet<>();
    @OneToMany(mappedBy = "orderCart")
    private Set<OrderHistory> histories = new HashSet<>();
    @OneToMany(mappedBy = "orderCart")
    private Set<OrderPackage> packages = new HashSet<>();
    @OneToMany(mappedBy = "orderCart")
    private Set<OrderTransaction> transactions = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private User buyer;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User chinaStocker;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User vietnamStocker;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User exporter;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User createBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User updateBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public OrderCart code(Long code) {
        this.code = code;
        return this;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getShippingChinaCode() {
        return shippingChinaCode;
    }

    public OrderCart shippingChinaCode(String shippingChinaCode) {
        this.shippingChinaCode = shippingChinaCode;
        return this;
    }

    public void setShippingChinaCode(String shippingChinaCode) {
        this.shippingChinaCode = shippingChinaCode;
    }

    public String getAvatar() {
        return avatar;
    }

    public OrderCart avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAliwangwang() {
        return aliwangwang;
    }

    public OrderCart aliwangwang(String aliwangwang) {
        this.aliwangwang = aliwangwang;
        return this;
    }

    public void setAliwangwang(String aliwangwang) {
        this.aliwangwang = aliwangwang;
    }

    public Float getAmountDiscount() {
        return amountDiscount;
    }

    public OrderCart amountDiscount(Float amountDiscount) {
        this.amountDiscount = amountDiscount;
        return this;
    }

    public void setAmountDiscount(Float amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public OrderCart amountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Float getDepositAmount() {
        return depositAmount;
    }

    public OrderCart depositAmount(Float depositAmount) {
        this.depositAmount = depositAmount;
        return this;
    }

    public void setDepositAmount(Float depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Float getDepositRatio() {
        return depositRatio;
    }

    public OrderCart depositRatio(Float depositRatio) {
        this.depositRatio = depositRatio;
        return this;
    }

    public void setDepositRatio(Float depositRatio) {
        this.depositRatio = depositRatio;
    }

    public Instant getDepositTime() {
        return depositTime;
    }

    public OrderCart depositTime(Instant depositTime) {
        this.depositTime = depositTime;
        return this;
    }

    public void setDepositTime(Instant depositTime) {
        this.depositTime = depositTime;
    }

    public Float getDomesticShippingChinaFeeNDT() {
        return domesticShippingChinaFeeNDT;
    }

    public OrderCart domesticShippingChinaFeeNDT(Float domesticShippingChinaFeeNDT) {
        this.domesticShippingChinaFeeNDT = domesticShippingChinaFeeNDT;
        return this;
    }

    public void setDomesticShippingChinaFeeNDT(Float domesticShippingChinaFeeNDT) {
        this.domesticShippingChinaFeeNDT = domesticShippingChinaFeeNDT;
    }

    public Float getDomesticShippingChinaFee() {
        return domesticShippingChinaFee;
    }

    public OrderCart domesticShippingChinaFee(Float domesticShippingChinaFee) {
        this.domesticShippingChinaFee = domesticShippingChinaFee;
        return this;
    }

    public void setDomesticShippingChinaFee(Float domesticShippingChinaFee) {
        this.domesticShippingChinaFee = domesticShippingChinaFee;
    }

    public Float getDomesticShippingVietnamFee() {
        return domesticShippingVietnamFee;
    }

    public OrderCart domesticShippingVietnamFee(Float domesticShippingVietnamFee) {
        this.domesticShippingVietnamFee = domesticShippingVietnamFee;
        return this;
    }

    public void setDomesticShippingVietnamFee(Float domesticShippingVietnamFee) {
        this.domesticShippingVietnamFee = domesticShippingVietnamFee;
    }

    public Integer getQuantityOrder() {
        return quantityOrder;
    }

    public OrderCart quantityOrder(Integer quantityOrder) {
        this.quantityOrder = quantityOrder;
        return this;
    }

    public void setQuantityOrder(Integer quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public Integer getQuantityPending() {
        return quantityPending;
    }

    public OrderCart quantityPending(Integer quantityPending) {
        this.quantityPending = quantityPending;
        return this;
    }

    public void setQuantityPending(Integer quantityPending) {
        this.quantityPending = quantityPending;
    }

    public Integer getQuantityReceived() {
        return quantityReceived;
    }

    public OrderCart quantityReceived(Integer quantityReceived) {
        this.quantityReceived = quantityReceived;
        return this;
    }

    public void setQuantityReceived(Integer quantityReceived) {
        this.quantityReceived = quantityReceived;
    }

    public Float getRate() {
        return rate;
    }

    public OrderCart rate(Float rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public OrderCart receiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public OrderCart receiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public OrderCart receiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
        return this;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverNote() {
        return receiverNote;
    }

    public OrderCart receiverNote(String receiverNote) {
        this.receiverNote = receiverNote;
        return this;
    }

    public void setReceiverNote(String receiverNote) {
        this.receiverNote = receiverNote;
    }

    public Float getRefundAmountByAlipay() {
        return refundAmountByAlipay;
    }

    public OrderCart refundAmountByAlipay(Float refundAmountByAlipay) {
        this.refundAmountByAlipay = refundAmountByAlipay;
        return this;
    }

    public void setRefundAmountByAlipay(Float refundAmountByAlipay) {
        this.refundAmountByAlipay = refundAmountByAlipay;
    }

    public Float getRefundAmountByComplaint() {
        return refundAmountByComplaint;
    }

    public OrderCart refundAmountByComplaint(Float refundAmountByComplaint) {
        this.refundAmountByComplaint = refundAmountByComplaint;
        return this;
    }

    public void setRefundAmountByComplaint(Float refundAmountByComplaint) {
        this.refundAmountByComplaint = refundAmountByComplaint;
    }

    public Float getRefundAmountByOrder() {
        return refundAmountByOrder;
    }

    public OrderCart refundAmountByOrder(Float refundAmountByOrder) {
        this.refundAmountByOrder = refundAmountByOrder;
        return this;
    }

    public void setRefundAmountByOrder(Float refundAmountByOrder) {
        this.refundAmountByOrder = refundAmountByOrder;
    }

    public Float getRefundAmountPending() {
        return refundAmountPending;
    }

    public OrderCart refundAmountPending(Float refundAmountPending) {
        this.refundAmountPending = refundAmountPending;
        return this;
    }

    public void setRefundAmountPending(Float refundAmountPending) {
        this.refundAmountPending = refundAmountPending;
    }

    public Float getShippingChinaVietnamFee() {
        return shippingChinaVietnamFee;
    }

    public OrderCart shippingChinaVietnamFee(Float shippingChinaVietnamFee) {
        this.shippingChinaVietnamFee = shippingChinaVietnamFee;
        return this;
    }

    public void setShippingChinaVietnamFee(Float shippingChinaVietnamFee) {
        this.shippingChinaVietnamFee = shippingChinaVietnamFee;
    }

    public Float getShippingChinaVietnamFeeDiscount() {
        return shippingChinaVietnamFeeDiscount;
    }

    public OrderCart shippingChinaVietnamFeeDiscount(Float shippingChinaVietnamFeeDiscount) {
        this.shippingChinaVietnamFeeDiscount = shippingChinaVietnamFeeDiscount;
        return this;
    }

    public void setShippingChinaVietnamFeeDiscount(Float shippingChinaVietnamFeeDiscount) {
        this.shippingChinaVietnamFeeDiscount = shippingChinaVietnamFeeDiscount;
    }

    public Float getServiceFee() {
        return serviceFee;
    }

    public OrderCart serviceFee(Float serviceFee) {
        this.serviceFee = serviceFee;
        return this;
    }

    public void setServiceFee(Float serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Float getServiceFeeDiscount() {
        return serviceFeeDiscount;
    }

    public OrderCart serviceFeeDiscount(Float serviceFeeDiscount) {
        this.serviceFeeDiscount = serviceFeeDiscount;
        return this;
    }

    public void setServiceFeeDiscount(Float serviceFeeDiscount) {
        this.serviceFeeDiscount = serviceFeeDiscount;
    }

    public Boolean isItemChecking() {
        return itemChecking;
    }

    public OrderCart itemChecking(Boolean itemChecking) {
        this.itemChecking = itemChecking;
        return this;
    }

    public void setItemChecking(Boolean itemChecking) {
        this.itemChecking = itemChecking;
    }

    public Boolean isItemWoodCrating() {
        return itemWoodCrating;
    }

    public OrderCart itemWoodCrating(Boolean itemWoodCrating) {
        this.itemWoodCrating = itemWoodCrating;
        return this;
    }

    public void setItemWoodCrating(Boolean itemWoodCrating) {
        this.itemWoodCrating = itemWoodCrating;
    }

    public String getShopId() {
        return shopId;
    }

    public OrderCart shopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopLink() {
        return shopLink;
    }

    public OrderCart shopLink(String shopLink) {
        this.shopLink = shopLink;
        return this;
    }

    public void setShopLink(String shopLink) {
        this.shopLink = shopLink;
    }

    public String getShopName() {
        return shopName;
    }

    public OrderCart shopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopNote() {
        return shopNote;
    }

    public OrderCart shopNote(String shopNote) {
        this.shopNote = shopNote;
        return this;
    }

    public void setShopNote(String shopNote) {
        this.shopNote = shopNote;
    }

    public String getWebsite() {
        return website;
    }

    public OrderCart website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderCart status(OrderStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public OrderCart statusName(String statusName) {
        this.statusName = statusName;
        return this;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusStyle() {
        return statusStyle;
    }

    public OrderCart statusStyle(String statusStyle) {
        this.statusStyle = statusStyle;
        return this;
    }

    public void setStatusStyle(String statusStyle) {
        this.statusStyle = statusStyle;
    }

    public Float getTallyFee() {
        return tallyFee;
    }

    public OrderCart tallyFee(Float tallyFee) {
        this.tallyFee = tallyFee;
        return this;
    }

    public void setTallyFee(Float tallyFee) {
        this.tallyFee = tallyFee;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public OrderCart totalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getTotalAmountNDT() {
        return totalAmountNDT;
    }

    public OrderCart totalAmountNDT(Float totalAmountNDT) {
        this.totalAmountNDT = totalAmountNDT;
        return this;
    }

    public void setTotalAmountNDT(Float totalAmountNDT) {
        this.totalAmountNDT = totalAmountNDT;
    }

    public Float getTotalAmountChinaNDT() {
        return totalAmountChinaNDT;
    }

    public OrderCart totalAmountChinaNDT(Float totalAmountChinaNDT) {
        this.totalAmountChinaNDT = totalAmountChinaNDT;
        return this;
    }

    public void setTotalAmountChinaNDT(Float totalAmountChinaNDT) {
        this.totalAmountChinaNDT = totalAmountChinaNDT;
    }

    public Float getTotalPaidByCustomer() {
        return totalPaidByCustomer;
    }

    public OrderCart totalPaidByCustomer(Float totalPaidByCustomer) {
        this.totalPaidByCustomer = totalPaidByCustomer;
        return this;
    }

    public void setTotalPaidByCustomer(Float totalPaidByCustomer) {
        this.totalPaidByCustomer = totalPaidByCustomer;
    }

    public Float getTotalServiceFee() {
        return totalServiceFee;
    }

    public OrderCart totalServiceFee(Float totalServiceFee) {
        this.totalServiceFee = totalServiceFee;
        return this;
    }

    public void setTotalServiceFee(Float totalServiceFee) {
        this.totalServiceFee = totalServiceFee;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public OrderCart totalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
        return this;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Float getFinalAmount() {
        return finalAmount;
    }

    public OrderCart finalAmount(Float finalAmount) {
        this.finalAmount = finalAmount;
        return this;
    }

    public void setFinalAmount(Float finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public OrderCart createAt(Instant createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public OrderCart updateAt(Instant updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public OrderCart items(Set<OrderItem> orderItems) {
        this.items = orderItems;
        return this;
    }

    public OrderCart addItems(OrderItem orderItem) {
        this.items.add(orderItem);
        orderItem.setOrderCart(this);
        return this;
    }

    public OrderCart removeItems(OrderItem orderItem) {
        this.items.remove(orderItem);
        orderItem.setOrderCart(null);
        return this;
    }

    public void setItems(Set<OrderItem> orderItems) {
        this.items = orderItems;
    }

    public Set<OrderComment> getComments() {
        return comments;
    }

    public OrderCart comments(Set<OrderComment> orderComments) {
        this.comments = orderComments;
        return this;
    }

    public OrderCart addComments(OrderComment orderComment) {
        this.comments.add(orderComment);
        orderComment.setOrderCart(this);
        return this;
    }

    public OrderCart removeComments(OrderComment orderComment) {
        this.comments.remove(orderComment);
        orderComment.setOrderCart(null);
        return this;
    }

    public void setComments(Set<OrderComment> orderComments) {
        this.comments = orderComments;
    }

    public Set<OrderHistory> getHistories() {
        return histories;
    }

    public OrderCart histories(Set<OrderHistory> orderHistories) {
        this.histories = orderHistories;
        return this;
    }

    public OrderCart addHistories(OrderHistory orderHistory) {
        this.histories.add(orderHistory);
        orderHistory.setOrderCart(this);
        return this;
    }

    public OrderCart removeHistories(OrderHistory orderHistory) {
        this.histories.remove(orderHistory);
        orderHistory.setOrderCart(null);
        return this;
    }

    public void setHistories(Set<OrderHistory> orderHistories) {
        this.histories = orderHistories;
    }

    public Set<OrderPackage> getPackages() {
        return packages;
    }

    public OrderCart packages(Set<OrderPackage> orderPackages) {
        this.packages = orderPackages;
        return this;
    }

    public OrderCart addPackages(OrderPackage orderPackage) {
        this.packages.add(orderPackage);
        orderPackage.setOrderCart(this);
        return this;
    }

    public OrderCart removePackages(OrderPackage orderPackage) {
        this.packages.remove(orderPackage);
        orderPackage.setOrderCart(null);
        return this;
    }

    public void setPackages(Set<OrderPackage> orderPackages) {
        this.packages = orderPackages;
    }

    public Set<OrderTransaction> getTransactions() {
        return transactions;
    }

    public OrderCart transactions(Set<OrderTransaction> orderTransactions) {
        this.transactions = orderTransactions;
        return this;
    }

    public OrderCart addTransactions(OrderTransaction orderTransaction) {
        this.transactions.add(orderTransaction);
        orderTransaction.setOrderCart(this);
        return this;
    }

    public OrderCart removeTransactions(OrderTransaction orderTransaction) {
        this.transactions.remove(orderTransaction);
        orderTransaction.setOrderCart(null);
        return this;
    }

    public void setTransactions(Set<OrderTransaction> orderTransactions) {
        this.transactions = orderTransactions;
    }

    public User getBuyer() {
        return buyer;
    }

    public OrderCart buyer(User user) {
        this.buyer = user;
        return this;
    }

    public void setBuyer(User user) {
        this.buyer = user;
    }

    public User getChinaStocker() {
        return chinaStocker;
    }

    public OrderCart chinaStocker(User user) {
        this.chinaStocker = user;
        return this;
    }

    public void setChinaStocker(User user) {
        this.chinaStocker = user;
    }

    public User getVietnamStocker() {
        return vietnamStocker;
    }

    public OrderCart vietnamStocker(User user) {
        this.vietnamStocker = user;
        return this;
    }

    public void setVietnamStocker(User user) {
        this.vietnamStocker = user;
    }

    public User getExporter() {
        return exporter;
    }

    public OrderCart exporter(User user) {
        this.exporter = user;
        return this;
    }

    public void setExporter(User user) {
        this.exporter = user;
    }

    public User getCreateBy() {
        return createBy;
    }

    public OrderCart createBy(User user) {
        this.createBy = user;
        return this;
    }

    public void setCreateBy(User user) {
        this.createBy = user;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public OrderCart updateBy(User user) {
        this.updateBy = user;
        return this;
    }

    public void setUpdateBy(User user) {
        this.updateBy = user;
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
        OrderCart orderCart = (OrderCart) o;
        if (orderCart.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderCart.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderCart{" +
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
            "}";
    }
}
