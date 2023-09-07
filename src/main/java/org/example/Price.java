package org.example;

public class Price {
    private final int hour;
    private final int price;

    public Price(int hour, int price) {
        this.hour = hour;
        this.price = price;
    }

    public String getHourString() {
        return String.format("%02d", hour);
    }

    public String getPriceAndHourString(){
        return String.format("%02d", hour) + "-" + String.format("%02d", hour + 1) + ", " + price;
    }
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%02d", hour) + "-" + String.format("%02d", hour + 1) + " " + price + " öre";
    }
}
