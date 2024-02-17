package shop.mtcoding.bank.dto.account;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.bank.domain.account.Account;
import shop.mtcoding.bank.domain.user.User;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountReqDto {

    @Setter
    @Getter
    public static class AccountTransferReqDto {
        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long withDrawNubmer;
        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long depositNubmer;
        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long withDrawPassword;
        @NotNull
        private Long amount;
        @NotEmpty
        @Pattern(regexp = "TRANSFER")
        private String gubun;
    }

    @Setter
    @Getter
    public static class AccountWithdrawReqDto {
        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long nubmer;
        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long password;
        @NotNull
        private Long amount;
        @NotEmpty
        @Pattern(regexp = "WITHDRAW")
        private String gubun;
    }

    @Setter
    @Getter
    public static class AccountDepositReqDto {
        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long number;
        @NotNull
        private Long amount;
        @NotEmpty
        @Pattern(regexp = "DEPOSIT")
        private String gubun; // DEPOSIT(입금)
        @NotEmpty
        @Pattern(regexp = "^[0-9]{11}")
        private String tel;
    }

    @Setter
    @Getter
    public static class AccountSaveReqDto {
        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long number;
        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long password;

        public Account toEntity(User user) {
            return Account.builder()
                    .number(number)
                    .password(password)
                    .balance(1000L)
                    .user(user)
                    .build();
        }
    }
}
