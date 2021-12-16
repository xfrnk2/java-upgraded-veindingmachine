package vendingmachine.domain;

import java.util.Map;

public class Coins {
	Map<Coin, Integer> coins;

	public Coins(Map<Coin, Integer> coins) {
		this.coins = coins;
	}

	public Map<Coin, Integer> findAll() {
		return coins;
	}

	public void reduce(Map<Coin, Integer> changes) {
		for (Coin coin : changes.keySet()) {
			coins.put(coin, coins.get(coin) - changes.get(coin));
		}
	}
}
