package vn.studentexchange.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.studentexchange.domain.UserShippingAddress;
import vn.studentexchange.service.dto.UserShippingAddressDTO;

/**
 * Mapper for the entity UserShippingAddress and its DTO UserShippingAddressDTO.
 */
@Mapper(componentModel = "spring", uses = {UserProfileMapper.class, UserMapper.class, CityMapper.class, DistrictMapper.class})
public interface UserShippingAddressMapper extends EntityMapper<UserShippingAddressDTO, UserShippingAddress> {

    @Mapping(source = "userProfile.id", target = "userProfileId")
    @Mapping(source = "createBy.id", target = "createById")
    @Mapping(source = "createBy.login", target = "createByLogin")
    @Mapping(source = "updateBy.id", target = "updateById")
    @Mapping(source = "updateBy.login", target = "updateByLogin")
    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "city.name", target = "cityName")
    @Mapping(source = "district.id", target = "districtId")
    @Mapping(source = "district.name", target = "districtName")
    @Mapping(source = "district.type", target = "districtType")
    UserShippingAddressDTO toDto(UserShippingAddress userShippingAddress);

    @Mapping(source = "userProfileId", target = "userProfile")
    @Mapping(source = "createById", target = "createBy")
    @Mapping(source = "updateById", target = "updateBy")
    @Mapping(source = "cityId", target = "city")
    @Mapping(source = "districtId", target = "district")
    UserShippingAddress toEntity(UserShippingAddressDTO userShippingAddressDTO);

    default UserShippingAddress fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserShippingAddress userShippingAddress = new UserShippingAddress();
        userShippingAddress.setId(id);
        return userShippingAddress;
    }
}
