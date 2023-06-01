package cart.domain.order;

import cart.domain.product.Product;
import cart.domain.vo.Price;
import cart.domain.vo.Quantity;

public class OrderProduct {

    private final Long id;
    private final Product product;
    private final Quantity quantity;

    public OrderProduct(final Long id, final Product product, final Quantity quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderProduct(final Product product, final Quantity quantity) {
        this(null, product, quantity);
    }

    public Price calculateTotalPrice() {
        return product.calculatePrice(quantity);
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
