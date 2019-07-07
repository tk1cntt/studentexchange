package vn.studentexchange.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UserBalance entity.
 */
public class TokenExchangeDTO implements Serializable {

    private String code;

    private String state;

    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenExchangeDTO that = (TokenExchangeDTO) o;
        return Objects.equals(code, that.code) &&
            Objects.equals(state, that.state) &&
            Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, state, status);
    }
}
