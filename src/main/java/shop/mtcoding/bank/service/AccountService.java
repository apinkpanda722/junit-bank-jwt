package shop.mtcoding.bank.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.bank.domain.account.Account;
import shop.mtcoding.bank.domain.account.AccountRepository;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;
import shop.mtcoding.bank.dto.account.AccountReqDto;
import shop.mtcoding.bank.dto.account.AccountResDto.AccountSaveRespDto;
import shop.mtcoding.bank.handler.ex.CustomApiException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public AccountSaveRespDto 계좌등록(AccountReqDto.AccountSaveReqDto accountSaveReqDto, Long userId) {
        // User가 DB에 있는지 검증 겸 유저 엔티티 가져오기
        User userPS = userRepository.findById(userId).orElseThrow(
                () -> new CustomApiException("유저를 찾을 수 없습니다.")
        );

        // 해당 계좌가 DB에 있는 지 중복여부를 체크
        Optional<Account> accountOP = accountRepository.findByNumber(accountSaveReqDto.getNumber());
        if (accountOP.isPresent()) {
            throw new CustomApiException("해당 계좌가 이미 존재합니다.");
        }

        // 계좌 등록
        Account accountPS = accountRepository.save(accountSaveReqDto.toEntity(userPS));


        // DTO를 응답
        return new AccountSaveRespDto(accountPS);
    }

}