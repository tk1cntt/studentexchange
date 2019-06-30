package vn.studentexchange.repository;

import vn.studentexchange.domain.UserBalance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the UserBalance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {

    @Query("select user_balance from UserBalance user_balance where user_balance.createBy.login = ?#{principal.username}")
    List<UserBalance> findByCreateByIsCurrentUser();

    Optional<UserBalance> findFirstByCreateByLogin(String username);
}
