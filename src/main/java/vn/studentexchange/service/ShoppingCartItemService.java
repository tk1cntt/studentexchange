package vn.studentexchange.service;

import vn.studentexchange.domain.ShoppingCartItem;
import vn.studentexchange.repository.ShoppingCartItemRepository;
import vn.studentexchange.service.dto.ShoppingCartItemDTO;
import vn.studentexchange.service.mapper.ShoppingCartItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ShoppingCartItem.
 */
@Service
@Transactional
public class ShoppingCartItemService {

    private final Logger log = LoggerFactory.getLogger(ShoppingCartItemService.class);

    private final ShoppingCartItemRepository shoppingCartItemRepository;

    private final ShoppingCartItemMapper shoppingCartItemMapper;

    public ShoppingCartItemService(ShoppingCartItemRepository shoppingCartItemRepository, ShoppingCartItemMapper shoppingCartItemMapper) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.shoppingCartItemMapper = shoppingCartItemMapper;
    }

    /**
     * Save a shoppingCartItem.
     *
     * @param shoppingCartItemDTO the entity to save
     * @return the persisted entity
     */
    public ShoppingCartItemDTO save(ShoppingCartItemDTO shoppingCartItemDTO) {
        log.debug("Request to save ShoppingCartItem : {}", shoppingCartItemDTO);

        ShoppingCartItem shoppingCartItem = shoppingCartItemMapper.toEntity(shoppingCartItemDTO);
        shoppingCartItem = shoppingCartItemRepository.save(shoppingCartItem);
        return shoppingCartItemMapper.toDto(shoppingCartItem);
    }

    /**
     * Get all the shoppingCartItems.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ShoppingCartItemDTO> findAll() {
        log.debug("Request to get all ShoppingCartItems");
        return shoppingCartItemRepository.findAll().stream()
            .map(shoppingCartItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one shoppingCartItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ShoppingCartItemDTO> findOne(Long id) {
        log.debug("Request to get ShoppingCartItem : {}", id);
        return shoppingCartItemRepository.findById(id)
            .map(shoppingCartItemMapper::toDto);
    }

    /**
     * Delete the shoppingCartItem by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ShoppingCartItem : {}", id);
        shoppingCartItemRepository.deleteById(id);
    }
}
