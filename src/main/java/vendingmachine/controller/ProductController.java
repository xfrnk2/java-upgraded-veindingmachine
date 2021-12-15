package vendingmachine.controller;

import java.util.ArrayList;
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
}
