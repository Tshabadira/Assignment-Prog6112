package seriestv;

import java.util.Scanner;

public class SeriesTv {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Series seriesApp = new Series(sc);

        while (true) {
            System.out.print("\nEnter (1) to launch menu or (0) to exit: ");
            String launch = sc.nextLine().trim();

            if (launch.equals("1")) {
                System.out.println("\nLATEST SERIES - 2025");
                System.out.println("*************************************");
                System.out.println("Please select one of the following menu items:");
                System.out.println("(1) Capture a new series.");
                System.out.println("(2) Search for a series.");
                System.out.println("(3) Update series age restriction.");
                System.out.println("(4) Delete a series.");
                System.out.println("(5) Print series report - 2025.");
                System.out.println("(6) Exit Application.");
                System.out.print("Enter choice: ");

                String choice = sc.nextLine().trim();

                switch (choice) {
                    case "1":
                        seriesApp.captureSeries();
                        break;
                    case "2":
                        seriesApp.searchSeries();
                        break;
                    case "3":
                        seriesApp.updateSeries();
                        break;
                    case "4":
                        seriesApp.deleteSeries();
                        break;
                    case "5":
                        seriesApp.seriesReport();
                        break;
                    case "6":
                        seriesApp.exitSeriesApplication();
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else if (launch.equals("0")) {
                System.out.println("Exiting program... Goodbye!");
                break;
            } else {
                System.out.println("Invalid input. Please enter 1 to launch menu or 0 to exit.");
            }
        }

        sc.close();
    }
}
