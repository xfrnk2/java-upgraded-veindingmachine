package vendingmachine.controller;

import vendingmachine.domain.Money;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MoneyController {
	private final InputView inputView;
	private final OutputView outputView;
	private Money money;

	public MoneyController(final InputView inputView, final OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void setupMoney() {
		this.money = initializeMoney();
	}

	private Money initializeMoney() {
		int moneyAmount = enterMoney();
		return new Money(moneyAmount);
	}

	private int enterMoney() {
		try {
			outputView.printInsertMoneyRequest();
			return inputView.scanAmount();
		} catch (IllegalArgumentException e) {
			outputView.printError(e.getMessage());
			return enterMoney();
		}
	}
}
