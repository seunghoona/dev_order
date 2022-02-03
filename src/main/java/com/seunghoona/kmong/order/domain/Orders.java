package com.seunghoona.kmong.order.domain;

import com.seunghoona.kmong.common.BaseEntity;
import com.seunghoona.kmong.member.domain.Member;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Orders extends BaseEntity {

    @Id
    @GenericGenerator(name = "orderNo_gen",
            strategy = "com.seunghoona.kmong.order.domain.OrderNoGenerator")
    @GeneratedValue(generator = "orderNo_gen")
    @Column(name = "orderNo")
    private String orderNo;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", updatable = false, nullable = false)
    private Member member;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_orders_to_product_id"))
    private Product product;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.WAITING;

    @Embedded
    private Money amount;

    public static Orders create(Product product, Member member) {
        return new Orders(product, member);
    }

    private Orders(Product product, Member member) {
        this.product = product;
        this.member = member;
    }

    public Map<String, Long> getProductAndMemberId() {
        Map<String, Long> map = new HashMap<>();
        map.put("product", product.getId());
        map.put("member", member.getId());
        return map;
    }

}
