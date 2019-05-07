package io.happy.customer

import spock.lang.Specification

import static org.spockframework.util.Assert.that

class OrderCreateFacadeTest extends Specification {

    def orderCommissionCalculator = Mock(OrderCommissionCalculator)
    def orderCreateFacade = new OrderCreateFacade(orderCommissionCalculator)

    void "should create order"() {
        given:
        def userId = 998
        def paymentMethod = "CARD"
        def price = 100

        and:
        orderCommissionCalculator.calculate("CARD", 100) >> 2.3

        when:
        def order = orderCreateFacade.create(userId, paymentMethod, price)

        then:
        with(order) {
            it.userId == 998
            it.paymentMethod == "CARD"
            it.price == 100
            it.commissionAmount == 2.3
            it.orderCreatedDate.toLocalDate()
        }
    }

    void "should create order and verify interactions"() {
        given:
        def userId = 998
        def paymentMethod = "CARD"
        def price = 100

        and:
        1 * orderCommissionCalculator.calculate("CARD", 100) >> 2.3
        0 * _._

        when:
        def order = orderCreateFacade.create(userId, paymentMethod, price)

        then:
        with(order) {
            it.userId == 998
            it.paymentMethod == "CARD"
            it.price == 100
            it.commissionAmount == 2.3
            it.orderCreatedDate.toLocalDate()
        }
    }

    void "should create order and verify interactions with that keywoard"() {
        given:
        def userId = 998
        def paymentMethod = "CARD"
        def price = 100

        and:
        1 * orderCommissionCalculator.calculate("CARD", 100) >> 2.3
        0 * _._

        when:
        def order = orderCreateFacade.create(userId, paymentMethod, price)

        then:
        that orderShouldCreated(order)
    }

    boolean orderShouldCreated(Order order) {
        order.userId == 998
        order.paymentMethod == "CARD"
        order.price == 100
        order.commissionAmount == 2.3
        order.orderCreatedDate.toLocalDate()
    }

    void "should not create order when price is zero"() {
        given:
        def userId = 998
        def paymentMethod = "CARD"
        def price = 0

        and:
        1 * orderCommissionCalculator.calculate("CARD", 0) >> { throw new Exception("price could not be zero") }
        0 * _._

        when:
        orderCreateFacade.create(userId, paymentMethod, price)

        then:
        def e = thrown(Exception)
        e.message == "price could not be zero"
    }
}