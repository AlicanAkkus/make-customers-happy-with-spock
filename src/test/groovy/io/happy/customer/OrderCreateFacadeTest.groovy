package io.happy.customer

import spock.lang.Specification

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
}