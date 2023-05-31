package cart.service.response;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {

    private final Long id;
    private final List<OrderProductResponseDto> orderProducts;
    private final LocalDateTime timestamp;
    private final Integer totalPrice;

    private OrderResponseDto() {
        this(null, null, null, null);
    }

    public OrderResponseDto(final Long id,
                            final List<OrderProductResponseDto> orderProducts,
                            final LocalDateTime timestamp,
                            final Integer totalPrice) {
        this.id = id;
        this.orderProducts = orderProducts;
        this.timestamp = timestamp;
        this.totalPrice = totalPrice;
    }

    public List<OrderProductResponseDto> getOrderProducts() {
        return orderProducts;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Long getId() {
        return id;
    }

}