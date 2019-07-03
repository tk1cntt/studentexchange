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
 * A Delivery.
 */
@Entity
@Table(name = "delivery")
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "delivery_method")
    private String deliveryMethod;

    @Column(name = "delivery_method_name")
    private String deliveryMethodName;

    @Column(name = "export_time")
    private Instant exportTime;

    @Column(name = "packed_time")
    private Instant packedTime;

    @Column(name = "status")
    private String status;

    @Column(name = "status_name")
    private String statusName;

    @Column(name = "status_style")
    private String statusStyle;

    @Column(name = "total_weight")
    private Float totalWeight;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @OneToMany(mappedBy = "delivery")
    private Set<DeliveryPackage> packages = new HashSet<>();
    @OneToMany(mappedBy = "delivery")
    private Set<OrderPackage> orders = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private Warehouse warehouse;

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

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public Delivery deliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
        return this;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getDeliveryMethodName() {
        return deliveryMethodName;
    }

    public Delivery deliveryMethodName(String deliveryMethodName) {
        this.deliveryMethodName = deliveryMethodName;
        return this;
    }

    public void setDeliveryMethodName(String deliveryMethodName) {
        this.deliveryMethodName = deliveryMethodName;
    }

    public Instant getExportTime() {
        return exportTime;
    }

    public Delivery exportTime(Instant exportTime) {
        this.exportTime = exportTime;
        return this;
    }

    public void setExportTime(Instant exportTime) {
        this.exportTime = exportTime;
    }

    public Instant getPackedTime() {
        return packedTime;
    }

    public Delivery packedTime(Instant packedTime) {
        this.packedTime = packedTime;
        return this;
    }

    public void setPackedTime(Instant packedTime) {
        this.packedTime = packedTime;
    }

    public String getStatus() {
        return status;
    }

    public Delivery status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public Delivery statusName(String statusName) {
        this.statusName = statusName;
        return this;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusStyle() {
        return statusStyle;
    }

    public Delivery statusStyle(String statusStyle) {
        this.statusStyle = statusStyle;
        return this;
    }

    public void setStatusStyle(String statusStyle) {
        this.statusStyle = statusStyle;
    }

    public Float getTotalWeight() {
        return totalWeight;
    }

    public Delivery totalWeight(Float totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public void setTotalWeight(Float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public Delivery createAt(Instant createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public Delivery updateAt(Instant updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public Set<DeliveryPackage> getPackages() {
        return packages;
    }

    public Delivery packages(Set<DeliveryPackage> deliveryPackages) {
        this.packages = deliveryPackages;
        return this;
    }

    public Delivery addPackages(DeliveryPackage deliveryPackage) {
        this.packages.add(deliveryPackage);
        deliveryPackage.setDelivery(this);
        return this;
    }

    public Delivery removePackages(DeliveryPackage deliveryPackage) {
        this.packages.remove(deliveryPackage);
        deliveryPackage.setDelivery(null);
        return this;
    }

    public void setPackages(Set<DeliveryPackage> deliveryPackages) {
        this.packages = deliveryPackages;
    }

    public Set<OrderPackage> getOrders() {
        return orders;
    }

    public Delivery orders(Set<OrderPackage> orderPackages) {
        this.orders = orderPackages;
        return this;
    }

    public Delivery addOrders(OrderPackage orderPackage) {
        this.orders.add(orderPackage);
        orderPackage.setDelivery(this);
        return this;
    }

    public Delivery removeOrders(OrderPackage orderPackage) {
        this.orders.remove(orderPackage);
        orderPackage.setDelivery(null);
        return this;
    }

    public void setOrders(Set<OrderPackage> orderPackages) {
        this.orders = orderPackages;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Delivery warehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        return this;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public User getCreateBy() {
        return createBy;
    }

    public Delivery createBy(User user) {
        this.createBy = user;
        return this;
    }

    public void setCreateBy(User user) {
        this.createBy = user;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public Delivery updateBy(User user) {
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
        Delivery delivery = (Delivery) o;
        if (delivery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), delivery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Delivery{" +
            "id=" + getId() +
            ", deliveryMethod='" + getDeliveryMethod() + "'" +
            ", deliveryMethodName='" + getDeliveryMethodName() + "'" +
            ", exportTime='" + getExportTime() + "'" +
            ", packedTime='" + getPackedTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", statusName='" + getStatusName() + "'" +
            ", statusStyle='" + getStatusStyle() + "'" +
            ", totalWeight=" + getTotalWeight() +
            ", createAt='" + getCreateAt() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            "}";
    }
}
