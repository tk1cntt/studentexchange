package vn.studentexchange.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ShoppingCart.
 */
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "aliwangwang")
    private String aliwangwang;

    @Column(name = "deposit_amount")
    private Float depositAmount;

    @Column(name = "deposit_ratio")
    private Float depositRatio;

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

    @Column(name = "tally_fee")
    private Float tallyFee;

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "final_amount")
    private Float finalAmount;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @OneToMany(mappedBy = "shoppingCart")
    private Set<ShoppingCartItem> items = new HashSet<>();
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

    public String getAvatar() {
        return avatar;
    }

    public ShoppingCart avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAliwangwang() {
        return aliwangwang;
    }

    public ShoppingCart aliwangwang(String aliwangwang) {
        this.aliwangwang = aliwangwang;
        return this;
    }

    public void setAliwangwang(String aliwangwang) {
        this.aliwangwang = aliwangwang;
    }

    public Float getDepositAmount() {
        return depositAmount;
    }

    public ShoppingCart depositAmount(Float depositAmount) {
        this.depositAmount = depositAmount;
        return this;
    }

    public void setDepositAmount(Float depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Float getDepositRatio() {
        return depositRatio;
    }

    public ShoppingCart depositRatio(Float depositRatio) {
        this.depositRatio = depositRatio;
        return this;
    }

    public void setDepositRatio(Float depositRatio) {
        this.depositRatio = depositRatio;
    }

    public Float getServiceFee() {
        return serviceFee;
    }

    public ShoppingCart serviceFee(Float serviceFee) {
        this.serviceFee = serviceFee;
        return this;
    }

    public void setServiceFee(Float serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Float getServiceFeeDiscount() {
        return serviceFeeDiscount;
    }

    public ShoppingCart serviceFeeDiscount(Float serviceFeeDiscount) {
        this.serviceFeeDiscount = serviceFeeDiscount;
        return this;
    }

    public void setServiceFeeDiscount(Float serviceFeeDiscount) {
        this.serviceFeeDiscount = serviceFeeDiscount;
    }

    public Boolean isItemChecking() {
        return itemChecking;
    }

    public ShoppingCart itemChecking(Boolean itemChecking) {
        this.itemChecking = itemChecking;
        return this;
    }

    public void setItemChecking(Boolean itemChecking) {
        this.itemChecking = itemChecking;
    }

    public Boolean isItemWoodCrating() {
        return itemWoodCrating;
    }

    public ShoppingCart itemWoodCrating(Boolean itemWoodCrating) {
        this.itemWoodCrating = itemWoodCrating;
        return this;
    }

    public void setItemWoodCrating(Boolean itemWoodCrating) {
        this.itemWoodCrating = itemWoodCrating;
    }

    public String getShopId() {
        return shopId;
    }

    public ShoppingCart shopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopLink() {
        return shopLink;
    }

    public ShoppingCart shopLink(String shopLink) {
        this.shopLink = shopLink;
        return this;
    }

    public void setShopLink(String shopLink) {
        this.shopLink = shopLink;
    }

    public String getShopName() {
        return shopName;
    }

    public ShoppingCart shopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopNote() {
        return shopNote;
    }

    public ShoppingCart shopNote(String shopNote) {
        this.shopNote = shopNote;
        return this;
    }

    public void setShopNote(String shopNote) {
        this.shopNote = shopNote;
    }

    public String getWebsite() {
        return website;
    }

    public ShoppingCart website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Float getTallyFee() {
        return tallyFee;
    }

    public ShoppingCart tallyFee(Float tallyFee) {
        this.tallyFee = tallyFee;
        return this;
    }

    public void setTallyFee(Float tallyFee) {
        this.tallyFee = tallyFee;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public ShoppingCart totalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public ShoppingCart totalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
        return this;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Float getFinalAmount() {
        return finalAmount;
    }

    public ShoppingCart finalAmount(Float finalAmount) {
        this.finalAmount = finalAmount;
        return this;
    }

    public void setFinalAmount(Float finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public ShoppingCart createAt(Instant createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public ShoppingCart updateAt(Instant updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public Set<ShoppingCartItem> getItems() {
        return items;
    }

    public ShoppingCart items(Set<ShoppingCartItem> shoppingCartItems) {
        this.items = shoppingCartItems;
        return this;
    }

    public ShoppingCart addItems(ShoppingCartItem shoppingCartItem) {
        this.items.add(shoppingCartItem);
        shoppingCartItem.setShoppingCart(this);
        return this;
    }

    public ShoppingCart removeItems(ShoppingCartItem shoppingCartItem) {
        this.items.remove(shoppingCartItem);
        shoppingCartItem.setShoppingCart(null);
        return this;
    }

    public void setItems(Set<ShoppingCartItem> shoppingCartItems) {
        this.items = shoppingCartItems;
    }

    public User getCreateBy() {
        return createBy;
    }

    public ShoppingCart createBy(User user) {
        this.createBy = user;
        return this;
    }

    public void setCreateBy(User user) {
        this.createBy = user;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public ShoppingCart updateBy(User user) {
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
        ShoppingCart shoppingCart = (ShoppingCart) o;
        if (shoppingCart.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shoppingCart.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
            "id=" + getId() +
            ", avatar='" + getAvatar() + "'" +
            ", aliwangwang='" + getAliwangwang() + "'" +
            ", depositAmount=" + getDepositAmount() +
            ", depositRatio=" + getDepositRatio() +
            ", serviceFee=" + getServiceFee() +
            ", serviceFeeDiscount=" + getServiceFeeDiscount() +
            ", itemChecking='" + isItemChecking() + "'" +
            ", itemWoodCrating='" + isItemWoodCrating() + "'" +
            ", shopId='" + getShopId() + "'" +
            ", shopLink='" + getShopLink() + "'" +
            ", shopName='" + getShopName() + "'" +
            ", shopNote='" + getShopNote() + "'" +
            ", website='" + getWebsite() + "'" +
            ", tallyFee=" + getTallyFee() +
            ", totalAmount=" + getTotalAmount() +
            ", totalQuantity=" + getTotalQuantity() +
            ", finalAmount=" + getFinalAmount() +
            ", createAt='" + getCreateAt() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            "}";
    }
}
