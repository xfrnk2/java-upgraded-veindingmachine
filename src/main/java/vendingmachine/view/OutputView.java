package vendingmachine.view;

import java.util.Map;

import vendingmachine.domain.Coin;

public class OutputView {
	public static final String ERROR_PREFIX = "[ERROR] %s";
	public static final String HOLDING_AMOUNT_REQUEST = "자판기가 보유하고 있는 금액을 입력해 주세요.";
	public static final String ENTER_PRODUCTS_REQUEST = "상품명과 가격, 수량을 입력해 주세요.";
	public static final String INSERT_MONEY_REQUEST = "투입 금액을 입력해 주세요.";
	public static final String COIN_AMOUNT_AND_NUMBER = "%d원 - %d개";

	public void printError(String error) {
		System.out.printf(ERROR_PREFIX, error);
		printNewLine();
	}

	private void printNewLine() {
		System.out.println();
	}

	public void printHoldingAmountRequest() {
		System.out.println(HOLDING_AMOUNT_REQUEST);
	}

	public void printHoldingCoins(Map<Coin, Integer> coins) {
		for (Map.Entry<Coin, Integer> coin: coins.entrySet()){
			System.out.println(String.format(COIN_AMOUNT_AND_NUMBER, coin.getKey().getAmount(), coin.getValue()));
		}
	}

	public void printEnterProductsRequest() {
		System.out.println(ENTER_PRODUCTS_REQUEST);
	}

	public void printInsertMoneyRequest() {
		System.out.println(INSERT_MONEY_REQUEST);
	}
}
