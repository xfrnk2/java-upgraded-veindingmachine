package vendingmachine;

import java.util.Map;

import vendingmachine.controller.CoinController;
import vendingmachine.controller.MoneyController;
import vendingmachine.controller.ProductController;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Product;
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
		sell();
		getChanges();
	}

	private void sell() {
		while (moneyController.getCurrentMoney() > productController.getLeastProductCost()
			&& !productController.isAllProductOutOfStock()) {
			try {
				outputView.printCurrentMoney(moneyController.getCurrentMoney());
				outputView.printEnterProductNameToBuyRequest();
				String productName = inputView.scanProductName();
				Product product = productController.findProductByName(productName);
				productController.isSellable(product, moneyController.getCurrentMoney());
				moneyController.pay(product.getCost());
			} catch (IllegalArgumentException e) {
				outputView.printError(e.getMessage());
			}
		}
	}

	private void getChanges() {
		int money = moneyController.getCurrentMoney();
		Map<Coin, Integer> coins = coinController.exportChanges(money);
	}
}
