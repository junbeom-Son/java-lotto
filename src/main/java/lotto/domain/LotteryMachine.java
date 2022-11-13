package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class LotteryMachine {

    private static final int LOTTERY_PRICE = 1_000;
    private static final String USER_MONEY_INPUT_MESSAGE = "구입 금액을 입력해 주세요.";
    private static final String PURCHASED_LOTTERY_NUMBER_MESSAGE = "개를 구매했습니다.";
    private static final String WINNING_LOTTERY_INPUT_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";

    private Lotto winningLotto;
    private int bonusNumber;

    public void purchaseLottery(User user) {
        user.setMoney(getUserMoneyInput(user));
        user.setNumberOfLotteries(calculateNumOfLotteries(user));
        user.setLotteries(generateLotteries(user.getNumberOfLotteries()));
    }

    public int getUserMoneyInput(User user) {
        System.out.println(USER_MONEY_INPUT_MESSAGE);
        String userInput = readLine();
        System.out.println();
        InputValidator validator = new InputValidator();

        validator.validateInputMoney(userInput);
        return Integer.parseInt(userInput);
    }

    public int calculateNumOfLotteries(User user) {
        int numberOfLotteries = user.getMoney() / LOTTERY_PRICE;
        System.out.println(numberOfLotteries + PURCHASED_LOTTERY_NUMBER_MESSAGE);
        return numberOfLotteries;
    }

    public List<Lotto> generateLotteries(int numberOfLotteries) {
        List<Lotto> lotteries = new ArrayList<>();
        for (int i = 0; i < numberOfLotteries; ++i) {
            List<Integer> lotteryNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lotteryNumbers.sort(Comparator.naturalOrder());
            Lotto lotto = new Lotto(lotteryNumbers);
            System.out.println(lotteryNumbers);
            lotteries.add(lotto);
        }
        System.out.println();
        return lotteries;
    }

    public void drawLottery() {
        System.out.println(WINNING_LOTTERY_INPUT_MESSAGE);
        String[] winningNumbersInput = readLine().split(",");

        InputValidator validator = new InputValidator();
        validator.validateWinningNumbers(winningNumbersInput);

        List<Integer> winningNumbers = new ArrayList<>();
        for (int i = 0; i < winningNumbersInput.length; ++i) {
            winningNumbers.add(Integer.parseInt(winningNumbersInput[i]));
        }
        winningNumbers.sort(Comparator.naturalOrder());
        this.winningLotto = new Lotto(winningNumbers);
        this.bonusNumber = drawBonusNumber();
    }

    private int drawBonusNumber() {
        System.out.println(BONUS_NUMBER_INPUT_MESSAGE);
        String bonusNumberInput = readLine();

        InputValidator validator = new InputValidator();
        validator.validateBonusNumber(bonusNumberInput, winningLotto.getNumbers());
        return Integer.parseInt(bonusNumberInput);
    }
}
