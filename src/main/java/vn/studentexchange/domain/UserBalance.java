package vn.studentexchange.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A UserBalance.
 */
@Entity
@Table(name = "user_balance")
public class UserBalance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance_available")
    private Float balanceAvailable;

    @Column(name = "balance_freezing")
    private Float balanceFreezing;

    @Column(name = "cash")
    private Float cash;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User createBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getBalanceAvailable() {
        return balanceAvailable;
    }

    public UserBalance balanceAvailable(Float balanceAvailable) {
        this.balanceAvailable = balanceAvailable;
        return this;
    }

    public void setBalanceAvailable(Float balanceAvailable) {
        this.balanceAvailable = balanceAvailable;
    }

    public Float getBalanceFreezing() {
        return balanceFreezing;
    }

    public UserBalance balanceFreezing(Float balanceFreezing) {
        this.balanceFreezing = balanceFreezing;
        return this;
    }

    public void setBalanceFreezing(Float balanceFreezing) {
        this.balanceFreezing = balanceFreezing;
    }

    public Float getCash() {
        return cash;
    }

    public UserBalance cash(Float cash) {
        this.cash = cash;
        return this;
    }

    public void setCash(Float cash) {
        this.cash = cash;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public UserBalance createAt(Instant createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public UserBalance updateAt(Instant updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public User getCreateBy() {
        return createBy;
    }

    public UserBalance createBy(User user) {
        this.createBy = user;
        return this;
    }

    public void setCreateBy(User user) {
        this.createBy = user;
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
        UserBalance userBalance = (UserBalance) o;
        if (userBalance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userBalance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserBalance{" +
            "id=" + getId() +
            ", balanceAvailable=" + getBalanceAvailable() +
            ", balanceFreezing=" + getBalanceFreezing() +
            ", cash=" + getCash() +
            ", createAt='" + getCreateAt() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            "}";
    }
}
