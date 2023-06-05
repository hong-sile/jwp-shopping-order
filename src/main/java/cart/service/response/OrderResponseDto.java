package cart.service.response;

import static java.util.stream.Collectors.toUnmodifiableList;

import cart.domain.order.Order;
import java.util.List;

public class OrderResponseDto {

    private final Long id;
    private final List<OrderProductResponseDto> orderProducts;
    private final String timestamp;
    private final String couponName;
    private final Integer originPrice;
    private final Integer discountPrice;
    private final Integer totalPrice;

    private OrderResponseDto() {
        this(null, null, null, null, null, null, null);
    }

    public OrderResponseDto(final Long id, final List<OrderProductResponseDto> orderProducts,
                            final String timestamp,
                            final Integer originPrice, final String couponName, final Integer discountPrice,
                            final Integer totalPrice) {
        this.id = id;
        this.orderProducts = orderProducts;
        this.timestamp = timestamp;
        this.originPrice = originPrice;
        this.couponName = couponName;
        this.discountPrice = discountPrice;
        this.totalPrice = totalPrice;
    }

    public static OrderResponseDto from(final Order order) {
        final List<OrderProductResponseDto> orderProductResponses = order.getOrderProducts().stream()
                .map(OrderProductResponseDto::from)
                .collect(toUnmodifiableList());

        return order.getMemberCoupon()
                .map(memberCoupon -> createOrderResponse(order, orderProductResponses,
                        memberCoupon.getCoupon().getName()))
                .orElseGet(() -> createOrderResponse(order, orderProductResponses, null));
    }

    private static OrderResponseDto createOrderResponse(final Order order,
                                                        final List<OrderProductResponseDto> orderProductResponses,
                                                        final String couponName) {
        return new OrderResponseDto(
                order.getId()
                , orderProductResponses
                , order.getOrderAt().toString()
                , order.getOriginPrice().getValue()
                , couponName
                , order.getDiscountPrice().getValue()
                , order.getTotalPrice().getValue());
    }

    public Long getId() {
        return id;
    }

    public List<OrderProductResponseDto> getOrderProducts() {
        return orderProducts;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Integer getOriginPrice() {
        return originPrice;
    }

    public String getCouponName() {
        return couponName;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }
}
