package vn.studentexchange.service.mapper;

import vn.studentexchange.domain.*;
import vn.studentexchange.service.dto.PaymentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Payment and its DTO PaymentDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {

    @Mapping(source = "staffApproval.id", target = "staffApprovalId")
    @Mapping(source = "staffApproval.login", target = "staffApprovalLogin")
    @Mapping(source = "staffCancel.id", target = "staffCancelId")
    @Mapping(source = "staffCancel.login", target = "staffCancelLogin")
    @Mapping(source = "createBy.id", target = "createById")
    @Mapping(source = "createBy.login", target = "createByLogin")
    PaymentDTO toDto(Payment payment);

    @Mapping(source = "staffApprovalId", target = "staffApproval")
    @Mapping(source = "staffCancelId", target = "staffCancel")
    @Mapping(source = "createById", target = "createBy")
    Payment toEntity(PaymentDTO paymentDTO);

    default Payment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(id);
        return payment;
    }
}
