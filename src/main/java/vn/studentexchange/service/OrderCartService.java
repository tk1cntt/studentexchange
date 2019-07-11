package vn.studentexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import vn.studentexchange.domain.OrderCart;
import vn.studentexchange.repository.OrderCartRepository;
import vn.studentexchange.service.dto.OrderCartDTO;
import vn.studentexchange.service.dto.OrderHistoryDTO;
import vn.studentexchange.service.mapper.OrderCartMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

/**
 * Service Implementation for managing OrderCart.
 */
@Service
@Transactional
public class OrderCartService {

    private final Logger log = LoggerFactory.getLogger(OrderCartService.class);

    private final OrderCartRepository orderCartRepository;

    private final OrderCartMapper orderCartMapper;

    public OrderCartService(OrderCartRepository orderCartRepository, OrderCartMapper orderCartMapper) {
        this.orderCartRepository = orderCartRepository;
        this.orderCartMapper = orderCartMapper;
    }

    /**
     * Save a orderCart.
     *
     * @param orderCartDTO the entity to save
     * @return the persisted entity
     */
    public OrderCartDTO save(OrderCartDTO orderCartDTO) {
        log.debug("Request to save OrderCart : {}", orderCartDTO);

        OrderCart orderCart = orderCartMapper.toEntity(orderCartDTO);
        orderCart = orderCartRepository.save(orderCart);
        return orderCartMapper.toDto(orderCart);
    }

    /**
     * Get all the orderCarts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<OrderCartDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderCarts");
        return orderCartRepository.findAll(pageable)
            .map(orderCartMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<OrderCartDTO> findByOwner(String username, Pageable pageable) {
        log.debug("Request to get all House of owner [{}]");
        return orderCartRepository.findByCreateByLoginOrderByCreateAtDesc(username, pageable)
            .map(orderCartMapper::toDto);
    }

    /**
     * Get one orderCart by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<OrderCartDTO> findOne(Long id) {
        log.debug("Request to get OrderCart : {}", id);
        return orderCartRepository.findById(id)
            .map(orderCartMapper::toDto);
    }

    /**
     * Delete the orderCart by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete OrderCart : {}", id);
        orderCartRepository.deleteById(id);
    }
}
