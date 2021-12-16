package vendingmachine.domain;

import java.util.List;

public class Product {

	private static final int ZERO = 0;
	private static final int TEN = 10;
	private static final int NAME_INDEX = 0;
	private static final int COST_INDEX = 1;
	private static final int NUMBER_INDEX = 2;
	private static final int COST_LOWER_BOUND = 100;
	private static final int NUMBER_LOWER_BOUND = 0;
	private static final int DETAILS_CONTAINER_SIZE = 3;
	private static final String COST = "가격";
	private static final String NUMBER = "수량";

	private static final String ERROR_INVALID_DETAILS_CONTAINER_SIZE = "상품 상세는 상품명, 가격, 수량 세 단위여야 합니다.";
	private static final String ERROR_NON_DIGIT = "%s의 %s이 숫자여야 합니다.";
	private static final String ERROR_COST_NOT_DIVISIBLE = "%s의 가격이 %d으로 나누어 떨어져야 합니다.";
	private static final String ERROR_INVALID_RANGE_LOWER_BOUND = "%s의 %s이 %d 이상이여야 합니다.";

	private String name;
	private int cost;
	private int number;

	public Product(final List<String> details) {
		validateSize(details);
		this.name = details.get(NAME_INDEX);
		this.cost = isDigit(details.get(COST_INDEX), COST);
		this.number = isDigit(details.get(NUMBER_INDEX), NUMBER);
		validateCost(cost);
		validateNumber(number);

	}

	private int isDigit(final String cost, final String type) {
		try {
			return Integer.parseInt(cost);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format(ERROR_NON_DIGIT, name, type));
		}
	}

	private void validateSize(List<String> details) {
		if (DETAILS_CONTAINER_SIZE != details.size()) {
			throw new IllegalArgumentException();
		}
	}

	private void validateCost(final int cost) {
		if (cost < COST_LOWER_BOUND) {
			throw new IllegalArgumentException(
				String.format(ERROR_INVALID_RANGE_LOWER_BOUND, name, COST, COST_LOWER_BOUND));
		}
		if (ZERO < cost % TEN) {
			throw new IllegalArgumentException(String.format(ERROR_COST_NOT_DIVISIBLE, name, TEN));
		}
	}

	private void validateNumber(final int number) {
		if (number < NUMBER_LOWER_BOUND) {
			throw new IllegalArgumentException(
				String.format(ERROR_INVALID_RANGE_LOWER_BOUND, name, NUMBER, NUMBER_LOWER_BOUND));
		}
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public int getNumber() {
		return number;
	}
}
