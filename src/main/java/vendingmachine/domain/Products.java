package vendingmachine.domain;

import java.util.List;

public class Products {
	private final List<Product> products;

	public Products(final List<Product> products) {
		this.products = products;
	}

	public List<Product> findAll() {
		return products;
	}
}
