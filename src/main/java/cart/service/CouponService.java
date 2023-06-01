package cart.service;

import static java.util.stream.Collectors.toUnmodifiableList;

import cart.domain.Member;
import cart.domain.coupon.MemberCoupon;
import cart.domain.vo.Price;
import cart.repository.CouponRepository;
import cart.repository.MemberCouponRepository;
import cart.service.response.CouponResponse;
import cart.service.response.DiscountPriceResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    private final MemberCouponRepository memberCouponRepository;
    private final CouponRepository couponRepository;

    public CouponService(final MemberCouponRepository memberCouponRepository, final CouponRepository couponRepository) {
        this.memberCouponRepository = memberCouponRepository;
        this.couponRepository = couponRepository;
    }

    public List<CouponResponse> findMemberCoupons(final Member member) {
        final List<MemberCoupon> memberCoupons = memberCouponRepository.findByMemberId(member.getId());
        return memberCoupons.stream()
                .map(memberCoupon -> new CouponResponse(memberCoupon.getId(), memberCoupon.getCoupon().getName()))
                .collect(toUnmodifiableList());
    }

    public DiscountPriceResponse discount(final Member member, final Integer originPrice, final Long memberCouponId) {
        final MemberCoupon memberCoupon = memberCouponRepository.findById(memberCouponId);
        memberCoupon.checkOwner(member);
        final Price origin = new Price(originPrice);
        final Price discountPrice = memberCoupon.discount(origin);
        return DiscountPriceResponse.of(discountPrice, origin.subtract(discountPrice));
    }
}