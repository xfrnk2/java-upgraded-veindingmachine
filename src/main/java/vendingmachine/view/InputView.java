package vendingmachine.view;

import java.util.List;

import com.google.common.base.Splitter;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	private static final String ERROR_NON_DIGIT_AMOUNT = "금액은 숫자여야 합니다.";
	private static final String ERROR_NOT_UPPER_THAN_ZERO = "금액은 0이상의 숫자여야 합니다.";
	private static final String ERROR_EMPTY_PRODUCT_LIST = "상품 목록은 빈 값이 아니여야 합니다.";
	public static final String SEMICOLON = ";";
	public static final int AMOUNT_LOWER_BOUND = 0;
	public static final int PRODUCTS_NUMBER_LOWER_BOUND = 0;

	public int scanAmount() {
		try {
			int number = Integer.parseInt(Console.readLine());
			if (number < AMOUNT_LOWER_BOUND) {
				throw new IllegalArgumentException(ERROR_NOT_UPPER_THAN_ZERO);
			}
			return number;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_NON_DIGIT_AMOUNT);
		}
	}

	public List<String> scanProductList() {
		List<String> productList = Splitter.on(SEMICOLON)
			.omitEmptyStrings()
			.trimResults()
			.splitToList(Console.readLine());
		if (productList.size() <= PRODUCTS_NUMBER_LOWER_BOUND) {
			throw new IllegalArgumentException(ERROR_EMPTY_PRODUCT_LIST);
		}
		return productList;
	}
}
