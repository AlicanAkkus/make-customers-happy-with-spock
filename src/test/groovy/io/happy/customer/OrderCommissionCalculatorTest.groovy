package io.happy.customer

import spock.lang.Specification
import spock.lang.Unroll

class OrderCommissionCalculatorTest extends Specification {

    def orderCommissionCalculator = new OrderCommissionCalculator()

    @Unroll
    void "should calculate #paymentType order commission as #expectedAmount when price is #price"() {
        expect:
        expectedAmount == orderCommissionCalculator.calculate(paymentType, price)

        where:
        paymentType | price | expectedAmount
        "CARD"      | 100   | 2.3
        "CARD"      | 80    | 1.84
        "CASH"      | 100   | 2.0
    }
}