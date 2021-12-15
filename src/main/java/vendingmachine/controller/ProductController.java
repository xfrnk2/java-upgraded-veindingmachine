package vendingmachine.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Splitter;

import vendingmachine.domain.Product;
import vendingmachine.domain.Products;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class ProductController {
	private static final String COMMA = ",";
	private static final String PREFIX = "[";
	private static final String SUFFIX = "]";
	private static final String ERROR_INVALID_PREFIX_AND_SUFFIX = "상품 상세는 대괄호([)로 시작해서 대괄호(])로 끝나야 합니다.";
	private static final String ERROR_CANNOT_FIND_PRODUCT = "해당 상품을 찾을 수 없습니다.";
	private static final String ERROR_NO_STOCK = "해당 상품의 재고가 모두 소진되었습니다.";
	private static final String ERROR_NOT_ENOUGH_MONEY = "상품 가격이 잔여 투입 금액보다 비쌉니다.";
	private static final int ZERO = 0;
	private static final int PRODUCT_NUMBER_LOWER_BOUND = 0;
	private final InputView inputView;
	private final OutputView outputView;
	private Products products;

	public ProductController(final InputView inputView, final OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void setupProducts() {
		this.products = initializeProducts();
	}

	private Products initializeProducts() {
		try {
			List<Product> products = enterProductList();
			return new Products(products);
		} catch (IllegalArgumentException e) {
			return initializeProducts();
		}
	}

	private List<Product> enterProductList() {
		try {
			outputView.printEnterProductsRequest();
			List<String> productList = inputView.scanProductList();
			return makeProducts(productList);
		} catch (IllegalArgumentException e) {
			outputView.printError(e.getMessage());
			return enterProductList();
		}
	}

	private List<Product> makeProducts(final List<String> productList) {
		List<Product> products = new ArrayList<>();
		for (String productDetail : productList) {
			if (!(productDetail.startsWith(PREFIX) && productDetail.endsWith(SUFFIX))) {
				throw new IllegalArgumentException(ERROR_INVALID_PREFIX_AND_SUFFIX);
			}
			String bracketExtracted = productDetail.substring(productDetail.indexOf(PREFIX) + 1,
				productDetail.lastIndexOf(SUFFIX));
			Product product = new Product(extractProductDetails(bracketExtracted));
			products.add(product);
		}
		return products;
	}

	private List<String> extractProductDetails(final String details) {
		return Splitter.on(COMMA)
			.omitEmptyStrings()
			.trimResults()
			.splitToList(details);
	}

	public Product findProductByName(String name) {
		return products.findAll()
			.stream()
			.filter((p) -> name.equals(p.getName()))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ERROR_CANNOT_FIND_PRODUCT));
	}

	public void isSellable(Product product, int money) {
		checkHaveEnoughStock(product);
		checkHaveEnoughMoney(product, money);
	}

	private void checkHaveEnoughStock(Product product) throws IllegalArgumentException {
		if (product.getNumber() <= PRODUCT_NUMBER_LOWER_BOUND) {
			throw new IllegalArgumentException(ERROR_NO_STOCK);
		}
	}

	private void checkHaveEnoughMoney(Product product, int money) throws IllegalArgumentException {
		if (product.getCost() > money) {
			throw new IllegalArgumentException(ERROR_NOT_ENOUGH_MONEY);
		}
	}

	public int getLeastProductCost() {
		return products.findAll()
			.stream()
			.min(Comparator.comparing(Product::getCost))
			.get()
			.getCost();
	}

	public boolean isAllProductOutOfStock() {
		return products.findAll().stream().map(Product::getNumber).allMatch((n) -> n == ZERO);
	}
}
