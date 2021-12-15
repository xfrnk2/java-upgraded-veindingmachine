package vendingmachine;

import vendingmachine.controller.CoinController;
import vendingmachine.controller.MoneyController;
import vendingmachine.controller.ProductController;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachine {
	private static final InputView inputView = new InputView();
	private static final OutputView outputView = new OutputView(); // 기본 템플릿을 만들어 놓자. 기본 골격. 리드미 작성 이후 할 것들.
	private static final CoinController coinController = new CoinController(inputView, outputView);
	private static final ProductController productController = new ProductController(inputView, outputView);
	private static final MoneyController moneyController = new MoneyController(inputView, outputView);

	public void run() {
		coinController.setupHoldingCoins();
		coinController.showHoldingCoins();
		productController.setupProducts();
		moneyController.setupMoney();
	}
}
