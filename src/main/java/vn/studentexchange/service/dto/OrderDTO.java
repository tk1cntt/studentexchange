package vn.studentexchange.service.dto;

public class OrderDTO {
    private String userShippingAddressId;
    private String shopid;

    public String getUserShippingAddressId() {
        return userShippingAddressId;
    }

    public void setUserShippingAddressId(String userShippingAddressId) {
        this.userShippingAddressId = userShippingAddressId;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }
}
