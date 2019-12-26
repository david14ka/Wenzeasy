package com.wenzeasy.models;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethod {
    private String name;
    private String description;
    private String logo;
    private String route;
    private boolean isDefault;

    public PaymentMethod(String name, String description, String logo, String route, boolean isDefault) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.route = route;
        this.isDefault = isDefault;
    }


    class PaymentMethodList {
        List<PaymentMethod> paymentMethodList;
        List<PaymentMethod> cashList;

        public PaymentMethodList() {
            this.paymentMethodList = new ArrayList<>();
            this.cashList = new ArrayList<>();
        }

        public List<PaymentMethod> paymentMethodList() {

            this.paymentMethodList.add(new PaymentMethod("Visa Card", "Click to pay with your Visa Card", "/Checkout", "assets/img/visacard.png",
                    true));
            this.paymentMethodList.add(new PaymentMethod("MasterCard", "Click to pay with your MasterCard", "/Checkout", "assets/img/mastercard.png", false));
            this.paymentMethodList.add(new PaymentMethod("PayPal", "Click to pay with your PayPal account", "/PayPal", "assets/img/paypal.png", false));

            return this.paymentMethodList;
        }

        public List<PaymentMethod> paymentMethodCashList() {

            this.paymentMethodList.add(new PaymentMethod("Visa Card", "Click to pay with your Visa Card", "/Checkout", "assets/img/visacard.png",
                    true));
            this.paymentMethodList.add(new PaymentMethod("MasterCard", "Click to pay with your MasterCard", "/Checkout", "assets/img/mastercard.png", false));
            this.paymentMethodList.add(new PaymentMethod("PayPal", "Click to pay with your PayPal account", "/PayPal", "assets/img/paypal.png", false));
            this.cashList.add(new PaymentMethod("Cash on Delivery", "Click to pay cash on delivery", "/CashOnDelivery", "assets/img/cash.png",false));
                    this.cashList.add(new PaymentMethod("Pay on Pickup", "Click to pay on pickup", "/PayOnPickup", "assets/img/pay_pickup.png",false));

            return this.cashList;
        }
    }
}
