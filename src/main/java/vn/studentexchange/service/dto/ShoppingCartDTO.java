package vn.studentexchange.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the ShoppingCart entity.
 */
public class ShoppingCartDTO implements Serializable {

    private Long id;

    private String avatar;

    private String aliwangwang;

    private Float depositAmount;

    private Float depositRatio;

    private Float serviceFee;

    private Float serviceFeeDiscount;

    private Boolean itemChecking;

    private Boolean itemWoodCrating;

    private String shopId;

    private String shopLink;

    private String shopName;

    private String shopNote;

    private String website;

    private Float tallyFee;

    private Float totalAmount;

    private Integer totalQuantity;

    private Float finalAmount;

    private Instant createAt;

    private Instant updateAt;

    private Long createById;

    private String createByLogin;

    private Long updateById;

    private String updateByLogin;

    private List<ShoppingCartItemDTO> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        ShoppingCartDTO shoppingCartDTO = (ShoppingCartDTO) o;
        if (shoppingCartDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shoppingCartDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
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
            ", createBy=" + getCreateById() +
            ", createBy='" + getCreateByLogin() + "'" +
            ", updateBy=" + getUpdateById() +
            ", updateBy='" + getUpdateByLogin() + "'" +
            "}";
    }

    /**
     * @return the items
     */
    public List<ShoppingCartItemDTO> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ShoppingCartItemDTO> items) {
        this.items = items;
    }
}
