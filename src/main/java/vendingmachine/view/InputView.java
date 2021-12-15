package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	public static final String ERROR_NON_DIGIT_AMOUNT = "금액은 숫자여야 합니다.";
	public static final String ERROR_NOT_UPPER_THAN_ZERO = "금액은 0이상의 숫자여야 합니다.";

	public int scanAmount() {
		try {
			int number = Integer.parseInt(Console.readLine());
			if (number < 0) {
				throw new IllegalArgumentException(ERROR_NOT_UPPER_THAN_ZERO);
			}
			return number;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_NON_DIGIT_AMOUNT);
		}
	}
}
