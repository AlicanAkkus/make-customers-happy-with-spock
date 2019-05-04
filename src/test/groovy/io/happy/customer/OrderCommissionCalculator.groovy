package io.happy.customer

class OrderCommissionCalculator {

    def cashCommission = BigDecimal.valueOf(2.0)
    def cardCommission = BigDecimal.valueOf(2.3)

    BigDecimal calculate(String paymentType, BigDecimal price) {
        paymentType == "CARD" ? calculateCardPaymentCommission(price) : calculateCashPaymentCommission(price)
    }

    BigDecimal calculateCashPaymentCommission(BigDecimal price) {
        price.multiply(cashCommission).div(100).round(2)
    }

    BigDecimal calculateCardPaymentCommission(BigDecimal price) {
        price.multiply(cardCommission).div(100).round(2)
    }
}