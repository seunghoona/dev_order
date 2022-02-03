package com.seunghoona.kmong.order.domain;

import com.seunghoona.kmong.common.utils.LocalDateTimeUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Map;

public class OrderNoGenerator implements IdentifierGenerator {

    public static final String ORDER_NO_FORMAT = "%8sKM%010d%010d";

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
        return String.format(ORDER_NO_FORMAT, LocalDateTimeUtils.nowBasicFormat(), product, member);
    }
}
