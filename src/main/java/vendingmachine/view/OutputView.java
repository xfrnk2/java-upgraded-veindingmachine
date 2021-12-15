package vendingmachine.view;

public class OutputView {
	public static final String ERROR_PREFIX = "[ERROR] %s";
	public static final String HOLDING_AMOUNT_REQUEST = "자판기가 보유하고 있는 금액을 입력해 주세요.";

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
}
