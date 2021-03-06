package vn.studentexchange.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.studentexchange.domain.OrderComment;
import vn.studentexchange.service.dto.OrderCommentDTO;

/**
 * Mapper for the entity OrderComment and its DTO OrderCommentDTO.
 */
@Mapper(componentModel = "spring", uses = {OrderCartMapper.class})
public interface OrderCommentMapper extends EntityMapper<OrderCommentDTO, OrderComment> {

    @Mapping(source = "orderCart.id", target = "orderCartId")
    OrderCommentDTO toDto(OrderComment orderComment);

    @Mapping(source = "orderCartId", target = "orderCart")
    OrderComment toEntity(OrderCommentDTO orderCommentDTO);

    default OrderComment fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderComment orderComment = new OrderComment();
        orderComment.setId(id);
        return orderComment;
    }
}
