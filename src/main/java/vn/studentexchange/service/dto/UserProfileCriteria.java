package vn.studentexchange.service.dto;

import java.io.Serializable;
import java.util.Objects;
import vn.studentexchange.domain.enumeration.Gender;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the UserProfile entity. This class is used in UserProfileResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /user-profiles?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UserProfileCriteria implements Serializable {
    /**
     * Class for filtering Gender
     */
    public static class GenderFilter extends Filter<Gender> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter fullName;

    private GenderFilter gender;

    private StringFilter email;

    private StringFilter mobile;

    private StringFilter address;

    private LocalDateFilter createAt;

    private LocalDateFilter updateAt;

    private LongFilter createById;

    private LongFilter updateById;

    private LongFilter cityId;

    private LongFilter districtId;

    private LongFilter addressId;

    public UserProfileCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFullName() {
        return fullName;
    }

    public void setFullName(StringFilter fullName) {
        this.fullName = fullName;
    }

    public GenderFilter getGender() {
        return gender;
    }

    public void setGender(GenderFilter gender) {
        this.gender = gender;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getMobile() {
        return mobile;
    }

    public void setMobile(StringFilter mobile) {
        this.mobile = mobile;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public LocalDateFilter getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateFilter createAt) {
        this.createAt = createAt;
    }

    public LocalDateFilter getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateFilter updateAt) {
        this.updateAt = updateAt;
    }

    public LongFilter getCreateById() {
        return createById;
    }

    public void setCreateById(LongFilter createById) {
        this.createById = createById;
    }

    public LongFilter getUpdateById() {
        return updateById;
    }

    public void setUpdateById(LongFilter updateById) {
        this.updateById = updateById;
    }

    public LongFilter getCityId() {
        return cityId;
    }

    public void setCityId(LongFilter cityId) {
        this.cityId = cityId;
    }

    public LongFilter getDistrictId() {
        return districtId;
    }

    public void setDistrictId(LongFilter districtId) {
        this.districtId = districtId;
    }

    public LongFilter getAddressId() {
        return addressId;
    }

    public void setAddressId(LongFilter addressId) {
        this.addressId = addressId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UserProfileCriteria that = (UserProfileCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fullName, that.fullName) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(email, that.email) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(address, that.address) &&
            Objects.equals(createAt, that.createAt) &&
            Objects.equals(updateAt, that.updateAt) &&
            Objects.equals(createById, that.createById) &&
            Objects.equals(updateById, that.updateById) &&
            Objects.equals(cityId, that.cityId) &&
            Objects.equals(districtId, that.districtId) &&
            Objects.equals(addressId, that.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fullName,
        gender,
        email,
        mobile,
        address,
        createAt,
        updateAt,
        createById,
        updateById,
        cityId,
        districtId,
        addressId
        );
    }

    @Override
    public String toString() {
        return "UserProfileCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fullName != null ? "fullName=" + fullName + ", " : "") +
                (gender != null ? "gender=" + gender + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (mobile != null ? "mobile=" + mobile + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (createAt != null ? "createAt=" + createAt + ", " : "") +
                (updateAt != null ? "updateAt=" + updateAt + ", " : "") +
                (createById != null ? "createById=" + createById + ", " : "") +
                (updateById != null ? "updateById=" + updateById + ", " : "") +
                (cityId != null ? "cityId=" + cityId + ", " : "") +
                (districtId != null ? "districtId=" + districtId + ", " : "") +
                (addressId != null ? "addressId=" + addressId + ", " : "") +
            "}";
    }

}
