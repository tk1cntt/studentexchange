package vn.studentexchange.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import vn.studentexchange.domain.OrderCart;
import vn.studentexchange.domain.*; // for static metamodels
import vn.studentexchange.repository.OrderCartRepository;
import vn.studentexchange.service.dto.OrderCartCriteria;
import vn.studentexchange.service.dto.OrderCartDTO;
import vn.studentexchange.service.mapper.OrderCartMapper;

/**
 * Service for executing complex queries for OrderCart entities in the database.
 * The main input is a {@link OrderCartCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderCartDTO} or a {@link Page} of {@link OrderCartDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderCartQueryService extends QueryService<OrderCart> {

    private final Logger log = LoggerFactory.getLogger(OrderCartQueryService.class);

    private final OrderCartRepository orderCartRepository;

    private final OrderCartMapper orderCartMapper;

    public OrderCartQueryService(OrderCartRepository orderCartRepository, OrderCartMapper orderCartMapper) {
        this.orderCartRepository = orderCartRepository;
        this.orderCartMapper = orderCartMapper;
    }

    /**
     * Return a {@link List} of {@link OrderCartDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderCartDTO> findByCriteria(OrderCartCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OrderCart> specification = createSpecification(criteria);
        return orderCartMapper.toDto(orderCartRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderCartDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderCartDTO> findByCriteria(OrderCartCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OrderCart> specification = createSpecification(criteria);
        return orderCartRepository.findAll(specification, page)
            .map(orderCartMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrderCartCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OrderCart> specification = createSpecification(criteria);
        return orderCartRepository.count(specification);
    }

    /**
     * Function to convert OrderCartCriteria to a {@link Specification}
     */
    private Specification<OrderCart> createSpecification(OrderCartCriteria criteria) {
        Specification<OrderCart> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrderCart_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCode(), OrderCart_.code));
            }
            if (criteria.getShippingChinaCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getShippingChinaCode(), OrderCart_.shippingChinaCode));
            }
            if (criteria.getAvatar() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAvatar(), OrderCart_.avatar));
            }
            if (criteria.getAliwangwang() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAliwangwang(), OrderCart_.aliwangwang));
            }
            if (criteria.getAmountDiscount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmountDiscount(), OrderCart_.amountDiscount));
            }
            if (criteria.getAmountPaid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmountPaid(), OrderCart_.amountPaid));
            }
            if (criteria.getDepositAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDepositAmount(), OrderCart_.depositAmount));
            }
            if (criteria.getDepositRatio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDepositRatio(), OrderCart_.depositRatio));
            }
            if (criteria.getDepositTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDepositTime(), OrderCart_.depositTime));
            }
            if (criteria.getDomesticShippingChinaFeeNDT() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDomesticShippingChinaFeeNDT(), OrderCart_.domesticShippingChinaFeeNDT));
            }
            if (criteria.getDomesticShippingChinaFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDomesticShippingChinaFee(), OrderCart_.domesticShippingChinaFee));
            }
            if (criteria.getDomesticShippingVietnamFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDomesticShippingVietnamFee(), OrderCart_.domesticShippingVietnamFee));
            }
            if (criteria.getQuantityOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantityOrder(), OrderCart_.quantityOrder));
            }
            if (criteria.getQuantityPending() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantityPending(), OrderCart_.quantityPending));
            }
            if (criteria.getQuantityReceived() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantityReceived(), OrderCart_.quantityReceived));
            }
            if (criteria.getRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRate(), OrderCart_.rate));
            }
            if (criteria.getReceiverName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReceiverName(), OrderCart_.receiverName));
            }
            if (criteria.getReceiverAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReceiverAddress(), OrderCart_.receiverAddress));
            }
            if (criteria.getReceiverMobile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReceiverMobile(), OrderCart_.receiverMobile));
            }
            if (criteria.getReceiverNote() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReceiverNote(), OrderCart_.receiverNote));
            }
            if (criteria.getRefundAmountByAlipay() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRefundAmountByAlipay(), OrderCart_.refundAmountByAlipay));
            }
            if (criteria.getRefundAmountByComplaint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRefundAmountByComplaint(), OrderCart_.refundAmountByComplaint));
            }
            if (criteria.getRefundAmountByOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRefundAmountByOrder(), OrderCart_.refundAmountByOrder));
            }
            if (criteria.getRefundAmountPending() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRefundAmountPending(), OrderCart_.refundAmountPending));
            }
            if (criteria.getShippingChinaVietnamFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShippingChinaVietnamFee(), OrderCart_.shippingChinaVietnamFee));
            }
            if (criteria.getShippingChinaVietnamFeeDiscount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShippingChinaVietnamFeeDiscount(), OrderCart_.shippingChinaVietnamFeeDiscount));
            }
            if (criteria.getServiceFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getServiceFee(), OrderCart_.serviceFee));
            }
            if (criteria.getServiceFeeDiscount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getServiceFeeDiscount(), OrderCart_.serviceFeeDiscount));
            }
            if (criteria.getItemChecking() != null) {
                specification = specification.and(buildSpecification(criteria.getItemChecking(), OrderCart_.itemChecking));
            }
            if (criteria.getItemWoodCrating() != null) {
                specification = specification.and(buildSpecification(criteria.getItemWoodCrating(), OrderCart_.itemWoodCrating));
            }
            if (criteria.getShopId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getShopId(), OrderCart_.shopId));
            }
            if (criteria.getShopLink() != null) {
                specification = specification.and(buildStringSpecification(criteria.getShopLink(), OrderCart_.shopLink));
            }
            if (criteria.getShopName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getShopName(), OrderCart_.shopName));
            }
            if (criteria.getShopNote() != null) {
                specification = specification.and(buildStringSpecification(criteria.getShopNote(), OrderCart_.shopNote));
            }
            if (criteria.getWebsite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWebsite(), OrderCart_.website));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), OrderCart_.status));
            }
            if (criteria.getStatusName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusName(), OrderCart_.statusName));
            }
            if (criteria.getStatusStyle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusStyle(), OrderCart_.statusStyle));
            }
            if (criteria.getTallyFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTallyFee(), OrderCart_.tallyFee));
            }
            if (criteria.getTotalAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalAmount(), OrderCart_.totalAmount));
            }
            if (criteria.getTotalAmountNDT() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalAmountNDT(), OrderCart_.totalAmountNDT));
            }
            if (criteria.getTotalAmountChinaNDT() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalAmountChinaNDT(), OrderCart_.totalAmountChinaNDT));
            }
            if (criteria.getTotalPaidByCustomer() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalPaidByCustomer(), OrderCart_.totalPaidByCustomer));
            }
            if (criteria.getTotalServiceFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalServiceFee(), OrderCart_.totalServiceFee));
            }
            if (criteria.getTotalQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalQuantity(), OrderCart_.totalQuantity));
            }
            if (criteria.getFinalAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinalAmount(), OrderCart_.finalAmount));
            }
            if (criteria.getCreateAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateAt(), OrderCart_.createAt));
            }
            if (criteria.getUpdateAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateAt(), OrderCart_.updateAt));
            }
            if (criteria.getItemsId() != null) {
                specification = specification.and(buildSpecification(criteria.getItemsId(),
                    root -> root.join(OrderCart_.items, JoinType.LEFT).get(OrderItem_.id)));
            }
            if (criteria.getCommentsId() != null) {
                specification = specification.and(buildSpecification(criteria.getCommentsId(),
                    root -> root.join(OrderCart_.comments, JoinType.LEFT).get(OrderComment_.id)));
            }
            if (criteria.getHistoriesId() != null) {
                specification = specification.and(buildSpecification(criteria.getHistoriesId(),
                    root -> root.join(OrderCart_.histories, JoinType.LEFT).get(OrderHistory_.id)));
            }
            if (criteria.getPackagesId() != null) {
                specification = specification.and(buildSpecification(criteria.getPackagesId(),
                    root -> root.join(OrderCart_.packages, JoinType.LEFT).get(OrderPackage_.id)));
            }
            if (criteria.getTransactionsId() != null) {
                specification = specification.and(buildSpecification(criteria.getTransactionsId(),
                    root -> root.join(OrderCart_.transactions, JoinType.LEFT).get(OrderTransaction_.id)));
            }
            if (criteria.getBuyerId() != null) {
                specification = specification.and(buildSpecification(criteria.getBuyerId(),
                    root -> root.join(OrderCart_.buyer, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getChinaStockerId() != null) {
                specification = specification.and(buildSpecification(criteria.getChinaStockerId(),
                    root -> root.join(OrderCart_.chinaStocker, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getVietnamStockerId() != null) {
                specification = specification.and(buildSpecification(criteria.getVietnamStockerId(),
                    root -> root.join(OrderCart_.vietnamStocker, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getExporterId() != null) {
                specification = specification.and(buildSpecification(criteria.getExporterId(),
                    root -> root.join(OrderCart_.exporter, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getCreateById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreateById(),
                    root -> root.join(OrderCart_.createBy, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getUpdateById() != null) {
                specification = specification.and(buildSpecification(criteria.getUpdateById(),
                    root -> root.join(OrderCart_.updateBy, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
