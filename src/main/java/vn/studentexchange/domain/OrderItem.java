package vn.studentexchange.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A OrderItem.
 */
@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_link")
    private String itemLink;

    @Column(name = "item_price")
    private Float itemPrice;

    @Column(name = "item_price_ndt")
    private Float itemPriceNDT;

    @Column(name = "item_note")
    private String itemNote;

    @Column(name = "properties_id")
    private String propertiesId;

    @Column(name = "properties_image")
    private String propertiesImage;

    @Column(name = "properties_md_5")
    private String propertiesMD5;

    @Column(name = "properties_name")
    private String propertiesName;

    @Column(name = "properties_type")
    private String propertiesType;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "require_min")
    private Integer requireMin;

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "total_amount_ndt")
    private Float totalAmountNDT;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @ManyToOne
    @JsonIgnoreProperties("items")
    private OrderCart orderCart;

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

    public String getItemId() {
        return itemId;
    }

    public OrderItem itemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemImage() {
        return itemImage;
    }

    public OrderItem itemImage(String itemImage) {
        this.itemImage = itemImage;
        return this;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public OrderItem itemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemLink() {
        return itemLink;
    }

    public OrderItem itemLink(String itemLink) {
        this.itemLink = itemLink;
        return this;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public Float getItemPrice() {
        return itemPrice;
    }

    public OrderItem itemPrice(Float itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public void setItemPrice(Float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Float getItemPriceNDT() {
        return itemPriceNDT;
    }

    public OrderItem itemPriceNDT(Float itemPriceNDT) {
        this.itemPriceNDT = itemPriceNDT;
        return this;
    }

    public void setItemPriceNDT(Float itemPriceNDT) {
        this.itemPriceNDT = itemPriceNDT;
    }

    public String getItemNote() {
        return itemNote;
    }

    public OrderItem itemNote(String itemNote) {
        this.itemNote = itemNote;
        return this;
    }

    public void setItemNote(String itemNote) {
        this.itemNote = itemNote;
    }

    public String getPropertiesId() {
        return propertiesId;
    }

    public OrderItem propertiesId(String propertiesId) {
        this.propertiesId = propertiesId;
        return this;
    }

    public void setPropertiesId(String propertiesId) {
        this.propertiesId = propertiesId;
    }

    public String getPropertiesImage() {
        return propertiesImage;
    }

    public OrderItem propertiesImage(String propertiesImage) {
        this.propertiesImage = propertiesImage;
        return this;
    }

    public void setPropertiesImage(String propertiesImage) {
        this.propertiesImage = propertiesImage;
    }

    public String getPropertiesMD5() {
        return propertiesMD5;
    }

    public OrderItem propertiesMD5(String propertiesMD5) {
        this.propertiesMD5 = propertiesMD5;
        return this;
    }

    public void setPropertiesMD5(String propertiesMD5) {
        this.propertiesMD5 = propertiesMD5;
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public OrderItem propertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
        return this;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public String getPropertiesType() {
        return propertiesType;
    }

    public OrderItem propertiesType(String propertiesType) {
        this.propertiesType = propertiesType;
        return this;
    }

    public void setPropertiesType(String propertiesType) {
        this.propertiesType = propertiesType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItem quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRequireMin() {
        return requireMin;
    }

    public OrderItem requireMin(Integer requireMin) {
        this.requireMin = requireMin;
        return this;
    }

    public void setRequireMin(Integer requireMin) {
        this.requireMin = requireMin;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public OrderItem totalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getTotalAmountNDT() {
        return totalAmountNDT;
    }

    public OrderItem totalAmountNDT(Float totalAmountNDT) {
        this.totalAmountNDT = totalAmountNDT;
        return this;
    }

    public void setTotalAmountNDT(Float totalAmountNDT) {
        this.totalAmountNDT = totalAmountNDT;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public OrderItem createAt(Instant createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public OrderItem updateAt(Instant updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public OrderCart getOrderCart() {
        return orderCart;
    }

    public OrderItem orderCart(OrderCart orderCart) {
        this.orderCart = orderCart;
        return this;
    }

    public void setOrderCart(OrderCart orderCart) {
        this.orderCart = orderCart;
    }

    public User getCreateBy() {
        return createBy;
    }

    public OrderItem createBy(User user) {
        this.createBy = user;
        return this;
    }

    public void setCreateBy(User user) {
        this.createBy = user;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public OrderItem updateBy(User user) {
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
        OrderItem orderItem = (OrderItem) o;
        if (orderItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderItem{" +
            "id=" + getId() +
            ", itemId='" + getItemId() + "'" +
            ", itemImage='" + getItemImage() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", itemLink='" + getItemLink() + "'" +
            ", itemPrice=" + getItemPrice() +
            ", itemPriceNDT=" + getItemPriceNDT() +
            ", itemNote='" + getItemNote() + "'" +
            ", propertiesId='" + getPropertiesId() + "'" +
            ", propertiesImage='" + getPropertiesImage() + "'" +
            ", propertiesMD5='" + getPropertiesMD5() + "'" +
            ", propertiesName='" + getPropertiesName() + "'" +
            ", propertiesType='" + getPropertiesType() + "'" +
            ", quantity=" + getQuantity() +
            ", requireMin=" + getRequireMin() +
            ", totalAmount=" + getTotalAmount() +
            ", totalAmountNDT=" + getTotalAmountNDT() +
            ", createAt='" + getCreateAt() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            "}";
    }
}
