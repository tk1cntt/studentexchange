package vn.studentexchange.service.mapper;

import vn.studentexchange.domain.*;
import vn.studentexchange.service.dto.UserBalanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserBalance and its DTO UserBalanceDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserBalanceMapper extends EntityMapper<UserBalanceDTO, UserBalance> {

    @Mapping(source = "createBy.id", target = "createById")
    @Mapping(source = "createBy.login", target = "createByLogin")
    UserBalanceDTO toDto(UserBalance userBalance);

    @Mapping(source = "createById", target = "createBy")
    UserBalance toEntity(UserBalanceDTO userBalanceDTO);

    default UserBalance fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserBalance userBalance = new UserBalance();
        userBalance.setId(id);
        return userBalance;
    }
}
