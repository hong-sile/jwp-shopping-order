package cart.dao.dto;

import cart.domain.order.Order;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDto {

    private final Long id;
    private final Long memberId;
    private final Long memberCouponId;
    private final LocalDateTime timeStamp;

    public OrderDto(final Long id, final Long memberId, final Long memberCouponId, final LocalDateTime timeStamp) {
        this.id = id;
        this.memberId = memberId;
        this.memberCouponId = memberCouponId;
        this.timeStamp = timeStamp;
    }

    public OrderDto(final Long memberId, final Long memberCouponId, final LocalDateTime timeStamp) {
        this(null, memberId, memberCouponId, timeStamp);
    }

    public static OrderDto from(final Order order) {
        return order.getMemberCoupon()
                .map(value -> new OrderDto(order.getId(), order.getMemberId(), value.getId(), order.getOrderAt()))
                .orElseGet(() -> new OrderDto(order.getId(), order.getMemberId(), null, order.getOrderAt()));
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Optional<Long> getMemberCouponId() {
        return Optional.ofNullable(memberCouponId);
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
