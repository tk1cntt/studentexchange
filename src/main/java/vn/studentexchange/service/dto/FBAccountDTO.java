package vn.studentexchange.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FBAccountDTO {

    private String id;
    private int code;
    @JsonProperty("access_token")
    private String accessToken;

    private FBAccountPhoneDTO phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public FBAccountPhoneDTO getPhone() {
        return phone;
    }

    public void setPhone(FBAccountPhoneDTO phone) {
        this.phone = phone;
    }
}
