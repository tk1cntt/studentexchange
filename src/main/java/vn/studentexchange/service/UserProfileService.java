package vn.studentexchange.service;

import vn.studentexchange.domain.User;
import vn.studentexchange.domain.UserProfile;
import vn.studentexchange.repository.UserProfileRepository;
import vn.studentexchange.repository.UserRepository;
import vn.studentexchange.service.dto.UserProfileDTO;
import vn.studentexchange.service.mapper.UserProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service Implementation for managing UserProfile.
 */
@Service
@Transactional
public class UserProfileService {

    private final Logger log = LoggerFactory.getLogger(UserProfileService.class);

    private final UserProfileRepository userProfileRepository;

    private final UserProfileMapper userProfileMapper;

    private final UserRepository userRepository;

    public UserProfileService(UserProfileRepository userProfileRepository, UserProfileMapper userProfileMapper, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userProfileMapper = userProfileMapper;
        this.userRepository = userRepository;
    }

    /**
     * Save a userProfile.
     *
     * @param userProfileDTO the entity to save
     * @return the persisted entity
     */
    public UserProfileDTO save(UserProfileDTO userProfileDTO) {
        log.debug("Request to save UserProfile : {}", userProfileDTO);

        UserProfile userProfile = userProfileMapper.toEntity(userProfileDTO);
        userProfile = userProfileRepository.save(userProfile);
        return userProfileMapper.toDto(userProfile);
    }

    public Optional<UserProfileDTO> save(UserProfileDTO userProfileDTO, String username) {
        log.debug("Request to save UserProfile : {}", userProfileDTO);
        Optional<User> existingUser = userRepository.findOneByLogin(username);
        UserProfile userProfile = userProfileMapper.toEntity(userProfileDTO);
        userProfile.setCreateAt(LocalDate.now());
        userProfile.setCreateBy(existingUser.get());
        userProfile = userProfileRepository.save(userProfile);
        return Optional.of(userProfileMapper.toDto(userProfile));
    }

    /**
     * Get all the userProfiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<UserProfileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserProfiles");
        return userProfileRepository.findAll(pageable)
            .map(userProfileMapper::toDto);
    }


    /**
     * Get one userProfile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<UserProfileDTO> findOne(Long id) {
        log.debug("Request to get UserProfile : {}", id);
        return userProfileRepository.findById(id)
            .map(userProfileMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<UserProfileDTO> findByOwner(String username) {
        log.debug("Request to get UserProfile : {}", username);
        return userProfileRepository.findFirstByCreateByLogin(username)
            .map(userProfileMapper::toDto);
    }

    /**
     * Delete the userProfile by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserProfile : {}", id);
        userProfileRepository.deleteById(id);
    }
}
