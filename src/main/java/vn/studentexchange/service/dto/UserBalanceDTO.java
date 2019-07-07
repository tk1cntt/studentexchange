package vn.studentexchange.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the UserBalance entity.
 */
public class UserBalanceDTO implements Serializable {

    private Long id;

    private Float balanceAvailable = 0f;

    private Float balanceFreezing = 0f;

    private Float cash  = 0f;

    private Instant createAt;

    private Instant updateAt;

    private Long createById;

    private String createByLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getBalanceAvailable() {
        return balanceAvailable;
    }

    public void setBalanceAvailable(Float balanceAvailable) {
        this.balanceAvailable = balanceAvailable;
    }

    public Float getBalanceFreezing() {
        return balanceFreezing;
    }

    public void setBalanceFreezing(Float balanceFreezing) {
        this.balanceFreezing = balanceFreezing;
    }

    public Float getCash() {
        return cash;
    }

    public void setCash(Float cash) {
        this.cash = cash;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserBalanceDTO userBalanceDTO = (UserBalanceDTO) o;
        if (userBalanceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userBalanceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserBalanceDTO{" +
            "id=" + getId() +
            ", balanceAvailable=" + getBalanceAvailable() +
            ", balanceFreezing=" + getBalanceFreezing() +
            ", cash=" + getCash() +
            ", createAt='" + getCreateAt() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            ", createBy=" + getCreateById() +
            ", createBy='" + getCreateByLogin() + "'" +
            "}";
    }
}
