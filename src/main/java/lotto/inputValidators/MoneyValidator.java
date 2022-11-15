package lotto.inputValidators;

import lotto.messages.ErrorMessage;

public class MoneyValidator {
    static public void validateInputMoney(String inputMoney) {
        if (NumberValidator.includeNotNumericCharacter(inputMoney)) {
            System.out.println(ErrorMessage.MONEY_INPUT_ERROR_MESSAGE.getMessage());
            throw new IllegalArgumentException();
        }

        int userMoney = Integer.parseInt(inputMoney);
        if (!isMultipleOfOneThousand(userMoney)) {
            throw new IllegalArgumentException();
        }
    }

    static private boolean isMultipleOfOneThousand(int userMoney) {
        if (userMoney % 1000 == 0) {
            return true;
        }
        return false;
    }

}
