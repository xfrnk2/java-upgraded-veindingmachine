package vendingmachine.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Coins;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class CoinController {
	public static final String ERROR_MINIMUM_COIN_AMOUNT_MISMATCH = "자판기 보유 금액은 동전 최소 단위로 나누어 떨어져야 합니다.";
	private final InputView inputView;
	private final OutputView outputView;
	private Coins coins;

	public CoinController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void setupHoldingCoins() {
		this.coins = initializeCoins();
	}

	private Coins initializeCoins() {
		try {
			outputView.printHoldingAmountRequest();
			final int totalAmount = inputView.scanAmount();
			return new Coins(makeCoins(totalAmount));
		} catch (IllegalArgumentException e) {
			outputView.printError(e.getMessage());
			return initializeCoins();
		}
	}

	private Map<Coin, Integer> makeCoins(int totalAmount) {
		Map<Coin, Integer> coins = new LinkedHashMap<>();
		for (Coin coin : Coin.values()) {
			final int amount = coin.getAmount();
			coins.put(coin, totalAmount / amount);
			totalAmount = totalAmount % amount;
		}
		final boolean isUpperThanZero = 0 < totalAmount;
		if (isUpperThanZero) {
			throw new IllegalArgumentException(ERROR_MINIMUM_COIN_AMOUNT_MISMATCH);
		}
		return coins;
	}
}
