package cart.service;

import static java.util.stream.Collectors.toUnmodifiableList;

import cart.domain.product.Product;
import cart.domain.vo.Price;
import cart.repository.ProductRepository;
import cart.service.request.ProductRequest;
import cart.service.response.ProductResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponse::from).collect(Collectors.toList());
    }

    public ProductResponse getProductById(final Long productId) {
        final Product product = productRepository.findById(productId);
        return ProductResponse.from(product);
    }

    @Transactional
    public Long createProduct(final ProductRequest productRequest) {
        final Product product = new Product(productRequest.getName(), new Price(productRequest.getPrice()),
                productRequest.getImageUrl());
        return productRepository.insertProduct(product).getId();
    }

    @Transactional
    public void updateProduct(final Long productId, final ProductRequest productRequest) {
        final Product product = new Product(productRequest.getName(), new Price(productRequest.getPrice()),
                productRequest.getImageUrl());
        productRepository.updateProduct(productId, product);
    }

    @Transactional
    public void deleteProduct(final Long productId) {
        productRepository.deleteProduct(productId);
    }

    public List<ProductResponse> getProductPagination(final Long limit, final Long scrollCount) {
        return productRepository.findAllPagination(limit, scrollCount).stream()
                .map(ProductResponse::from)
                .collect(toUnmodifiableList());
    }
}
