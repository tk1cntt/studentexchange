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

import vn.studentexchange.domain.Payment;
import vn.studentexchange.domain.*; // for static metamodels
import vn.studentexchange.repository.PaymentRepository;
import vn.studentexchange.service.dto.PaymentCriteria;
import vn.studentexchange.service.dto.PaymentDTO;
import vn.studentexchange.service.mapper.PaymentMapper;

/**
 * Service for executing complex queries for Payment entities in the database.
 * The main input is a {@link PaymentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PaymentDTO} or a {@link Page} of {@link PaymentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PaymentQueryService extends QueryService<Payment> {

    private final Logger log = LoggerFactory.getLogger(PaymentQueryService.class);

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    public PaymentQueryService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    /**
     * Return a {@link List} of {@link PaymentDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PaymentDTO> findByCriteria(PaymentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Payment> specification = createSpecification(criteria);
        return paymentMapper.toDto(paymentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PaymentDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PaymentDTO> findByCriteria(PaymentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Payment> specification = createSpecification(criteria);
        return paymentRepository.findAll(specification, page)
            .map(paymentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PaymentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Payment> specification = createSpecification(criteria);
        return paymentRepository.count(specification);
    }

    /**
     * Function to convert PaymentCriteria to a {@link Specification}
     */
    private Specification<Payment> createSpecification(PaymentCriteria criteria) {
        Specification<Payment> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Payment_.id));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), Payment_.amount));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCode(), Payment_.code));
            }
            if (criteria.getNewBalance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNewBalance(), Payment_.newBalance));
            }
            if (criteria.getNote() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNote(), Payment_.note));
            }
            if (criteria.getOrderCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrderCode(), Payment_.orderCode));
            }
            if (criteria.getMethod() != null) {
                specification = specification.and(buildSpecification(criteria.getMethod(), Payment_.method));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildSpecification(criteria.getType(), Payment_.type));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), Payment_.status));
            }
            if (criteria.getCreateAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateAt(), Payment_.createAt));
            }
            if (criteria.getWithdrawalFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWithdrawalFee(), Payment_.withdrawalFee));
            }
            if (criteria.getStaffApprovalId() != null) {
                specification = specification.and(buildSpecification(criteria.getStaffApprovalId(),
                    root -> root.join(Payment_.staffApproval, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getStaffCancelId() != null) {
                specification = specification.and(buildSpecification(criteria.getStaffCancelId(),
                    root -> root.join(Payment_.staffCancel, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getCustomerId() != null) {
                specification = specification.and(buildSpecification(criteria.getCustomerId(),
                    root -> root.join(Payment_.customer, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getCreateById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreateById(),
                    root -> root.join(Payment_.createBy, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
