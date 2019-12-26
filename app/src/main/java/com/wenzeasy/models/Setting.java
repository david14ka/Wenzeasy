package com.wenzeasy.models;

public class Setting {

    private String appName;
    private double defaultTax;
    private String defaultCurrency;
    private boolean currencyRight;
    private boolean payPalEnabled = true;
    private boolean stripeEnabled = true;

    public Setting() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public double getDefaultTax() {
        return defaultTax;
    }

    public void setDefaultTax(double defaultTax) {
        this.defaultTax = defaultTax;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public boolean isCurrencyRight() {
        return currencyRight;
    }

    public void setCurrencyRight(boolean currencyRight) {
        this.currencyRight = currencyRight;
    }

    public boolean isPayPalEnabled() {
        return payPalEnabled;
    }

    public void setPayPalEnabled(boolean payPalEnabled) {
        this.payPalEnabled = payPalEnabled;
    }

    public boolean isStripeEnabled() {
        return stripeEnabled;
    }

    public void setStripeEnabled(boolean stripeEnabled) {
        this.stripeEnabled = stripeEnabled;
    }
}
