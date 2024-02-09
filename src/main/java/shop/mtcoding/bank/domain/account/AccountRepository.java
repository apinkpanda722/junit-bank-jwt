package shop.mtcoding.bank.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // jpa query method
    // select * from account where number = :number
    // TODO 리팩토링 해야 함 (계좌 소유자 확인시에 쿼리가 두번 나가기 때문이다. join fetch)
    Optional<Account> findByNumber(Long number);
}
