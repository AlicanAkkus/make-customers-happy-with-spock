package io.happy.customer

import spock.lang.Specification
import spock.lang.Unroll

import static org.spockframework.util.Assert.that

class OrderCommissionCalculatorTest extends Specification {

    def orderCommissionCalculator = new OrderCommissionCalculator()

    @Unroll
    void "should calculate #paymentType order commission as #expectedAmount when price is #price"() {
        expect:
        expectedAmount == orderCommissionCalculator.calculate(paymentType, price)

        where:
        paymentType | price || expectedAmount
        "CARD"      | 100   || 2.3
        "CARD"      | 80    || 1.84
        "CASH"      | 100   || 2.0
    }

    void "should calculate card payment order commission"() {
        given: "given by card payment and price is 100"
        def paymentType = "CARD"
        def price = 100

        when: "when calculate card payment commission"
        def commissionAmount = orderCommissionCalculator.calculate(paymentType, price)

        then: "commission should equal to 2.3"
        commissionAmount == 2.3
    }

    void "should calculate cash payment order commission"() {
        given:
        def paymentType = "CASH"
        def price = 100

        when:
        def commissionAmount = orderCommissionCalculator.calculate(paymentType, price)

        then:
        that commissionAmount == 2.0
    }
}