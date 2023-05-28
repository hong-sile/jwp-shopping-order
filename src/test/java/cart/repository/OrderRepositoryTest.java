package cart.repository;

import static cart.TestDataFixture.MEMBER_1;
import static cart.TestDataFixture.PRODUCT_1;
import static cart.TestDataFixture.PRODUCT_2;
import static org.assertj.core.api.Assertions.assertThat;

import cart.dao.MemberDao;
import cart.dao.OrderDao;
import cart.dao.OrderProductDao;
import cart.dao.ProductDao;
import cart.domain.CartItem;
import cart.domain.Order;
import cart.domain.OrderProduct;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

@JdbcTest
@Import({OrderRepository.class, OrderDao.class, OrderProductDao.class, ProductDao.class, MemberDao.class})
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("Order를 저장하는 기능 테스트")
    void saveOrder() {
        final CartItem cartItem1 = new CartItem(MEMBER_1, PRODUCT_1);
        final CartItem cartItem2 = new CartItem(MEMBER_1, PRODUCT_2);
        final Order order = Order.of(MEMBER_1, List.of(cartItem1, cartItem2));

        final Order orderAfterSave = orderRepository.save(order);

        assertThat(orderAfterSave)
                .extracting(Order::getTimeStamp, Order::getMember)
                .contains(order.getTimeStamp(), MEMBER_1);
        assertThat(orderAfterSave.getOrderProducts())
                .extracting(OrderProduct::getProduct)
                .contains(cartItem1.getProduct(), cartItem2.getProduct());
    }

    @Test
    @DisplayName("Order를 조회하는 기능 테스트")
    void findById() {
        final CartItem cartItem1 = new CartItem(MEMBER_1, PRODUCT_1);
        final CartItem cartItem2 = new CartItem(MEMBER_1, PRODUCT_2);
        final Order order = Order.of(MEMBER_1, List.of(cartItem1, cartItem2));

        final Long orderId = orderRepository.save(order).getId();

        final Order orderAfterSave = orderRepository.findById(orderId);

        assertThat(orderAfterSave)
                .extracting(Order::getTimeStamp, Order::getMember)
                .contains(order.getTimeStamp(), MEMBER_1);
        assertThat(orderAfterSave.getOrderProducts())
                .extracting(OrderProduct::getProduct)
                .contains(cartItem1.getProduct(), cartItem2.getProduct());
    }
}