package io.happy.customer

import java.time.LocalDateTime

class Order {

    Long userId
    String paymentMethod
    BigDecimal price
    BigDecimal commissionAmount
    LocalDateTime orderCreatedDate
}