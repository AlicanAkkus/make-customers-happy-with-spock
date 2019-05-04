package io.happy.customer

import java.time.LocalDateTime

class OrderCreateFacade {

    OrderCommissionCalculator orderCommissionCalculator

    OrderCreateFacade(OrderCommissionCalculator orderCommissionCalculator) {
        this.orderCommissionCalculator = orderCommissionCalculator
    }

    Order create(Long userId, String paymentMethod, BigDecimal price) {
        return new Order().tap {
            it.userId = userId
            it.paymentMethod = paymentMethod
            it.price = price
            it.commissionAmount = orderCommissionCalculator.calculate(paymentMethod, price)
            it.orderCreatedDate = LocalDateTime.now()
        }
    }
}