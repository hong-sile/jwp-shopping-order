package cart.dao.dto;

import cart.domain.product.Product;
import cart.domain.vo.Price;

public class ProductDto {

    private final Long id;
    private final String name;
    private final int price;
    private final String imageUrl;

    public ProductDto(final Long id, final String name, final int price, final String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductDto(final String name, final int price, final String imageUrl) {
        this(null, name, price, imageUrl);
    }

    public static ProductDto from(final Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice().getValue(), product.getImageUrl());
    }

    public Product toProduct() {
        return new Product(id, name, new Price(price), imageUrl);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
