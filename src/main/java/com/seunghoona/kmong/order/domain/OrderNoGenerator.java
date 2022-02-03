package com.seunghoona.kmong.order.domain;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Map;

public class OrderNoGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        validClassCast(object);
        return createOrderNo(from(object));
    }

    private void validClassCast(Object object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }

        if (object.getClass() != Orders.class) {
            throw new ClassCastException();
        }
    }

    private Orders from(Object object) {
        return Orders.class.cast(object);
    }

    private String createOrderNo(Orders orders) {
        final Map<String, Long> orderNo = orders.getProductAndMemberId();
        final Long product = orderNo.get("product");
        final Long member = orderNo.get("member");
        return String.format("%8s%010d%010d", "20220101", product, member);
    }
}
