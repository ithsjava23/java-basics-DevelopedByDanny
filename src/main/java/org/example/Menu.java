package org.example;

import java.util.*;

public class Menu {

    private final Price[] pricesPerHour;
    private final Scanner scanner;
    private final Random random;
    private final String menu;
    private boolean programRunning;

    public Menu() {
        pricesPerHour = new Price[24];
        scanner = new Scanner(System.in);
        random = new Random();
        menu = """
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                5. Visualisering
                e. Avsluta
                """;
        programRunning = true;
    }

    public void run() {
        for (int i = 0; i < pricesPerHour.length; i++)
            pricesPerHour[i] = new Price(i, random.nextInt(40, 500));

        while (programRunning) {
            System.out.print(this.menu);
            chooseFromMenu();
        }
    }

    private void chooseFromMenu() {
        switch (scanner.nextLine()) {
            case "1" -> inputPrices();
            case "2" -> calcMinMaxAvg();
            case "3" -> sortPrices();
            case "4" -> startLoadingAt();
            case "5" -> visualize();
            case "e", "E" -> exitPrompt();
        }
    }

    private void visualize() {

        int rows = 7;
        int columns = 75;


        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < columns; c++) {


            }

        }
    }

    private void startLoadingAt() {
        var bestStartingTime = 0;
        var tempSumPrice = Integer.MAX_VALUE;

        for (int i = 0; i < pricesPerHour.length - 4; i++) {
            int sum = pricesPerHour[i % pricesPerHour.length].getPrice()
                      + pricesPerHour[(i + 1) % pricesPerHour.length].getPrice()
                      + pricesPerHour[(i + 2) % pricesPerHour.length].getPrice()
                      + pricesPerHour[(i + 3) % pricesPerHour.length].getPrice();

            if (sum < tempSumPrice) {
                tempSumPrice = sum;
                bestStartingTime = i;
            }
        }
        System.out.printf("""
                Påbörja laddning klockan %s
                Medelpris 4h: %s öre/kWh
                """, pricesPerHour[bestStartingTime].getHourString(), String.format(Locale.FRANCE, "%.1f", tempSumPrice / 4.0));
    }

    private void sortPrices() {
        Arrays.stream(pricesPerHour)
                .sorted(Comparator.comparingInt(Price::getPrice).reversed())
                .forEach(Price -> System.out.print(Price + "\n"));
// I also did a bubblesort!!

//        Price[] tempArray = pricesPerHour;
//        for (int i = 0; i < tempArray.length - 1; i++) {
//            for (int j = 0; j < tempArray.length - 1 - i; j++) {
//                if (tempArray[j].getPrice() < tempArray[j + 1].getPrice()) {
//                    Price temp = tempArray[j];
//                    tempArray[j] = tempArray[j + 1];
//                    tempArray[j + 1] = temp;
//                }
//            }
//        }
//        for (Price price : tempArray) {
//            System.out.println(price);
//        }
//        for (Price price : pricesPerHour) {
//            System.out.println(price);
//        }
    }

    private void calcMinMaxAvg() {
        var avg = 0;
        var minIndex = 0;
        var maxIndex = 0;

        for (int i = 0; i < pricesPerHour.length; i++) {
            avg += pricesPerHour[i].getPrice();
            if (pricesPerHour[minIndex].getPrice() > pricesPerHour[i].getPrice()) minIndex = i;
            if (pricesPerHour[maxIndex].getPrice() < pricesPerHour[i].getPrice()) maxIndex = i;
        }

        var avgOutputString = String.format(Locale.GERMANY, "%.2f", avg / 24.0);
        System.out.printf("""
                Lägsta pris: %s öre/kWh
                Högsta pris: %s öre/kWh
                Medelpris: %s öre/kWh
                """, pricesPerHour[minIndex].getPriceAndHourString(), pricesPerHour[maxIndex].getPriceAndHourString(), avgOutputString);
    }

    private void inputPrices() {
        for (int i = 0; i < pricesPerHour.length; i++) {
            pricesPerHour[i] = new Price(i, scanner.nextInt());
        }
    }

    private void exitPrompt() {
//        System.out.println("Programmet avslutas!");
        programRunning = false;
    }
}
