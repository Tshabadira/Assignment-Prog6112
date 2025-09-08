package seriestv;

import java.util.ArrayList;
import java.util.Scanner;

public class Series {
    private final ArrayList<SeriesModel> seriesList = new ArrayList<>();
    private final Scanner sc;

    public Series(Scanner sc) {
        this.sc = sc;
    }

    private int readIntWithAgeValidation(String prompt, boolean isAge) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            try {
                int val = Integer.parseInt(input);
                if (isAge) {
                    if (val < 2 || val > 18) {
                        System.out.println("You have entered a incorrect series age!!!");
                        System.out.print("Please re-enter the series age >> ");
                        continue;
                    }
                    return val;
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                if (isAge) {
                    System.out.println("You have entered a incorrect series age!!!");
                    System.out.print("Please re-enter the series age >> ");
                } else {
                    System.out.println("Invalid number. Please enter digits only.");
                }
            }
        }
    }

    public void captureSeries() {
        System.out.println("\nCAPTURE A NEW SERIES");
        System.out.println("*************************");

        int id = readIntWithAgeValidation("Enter the series id: ", false);

        System.out.print("Enter the series name: ");
        String name = sc.nextLine();

        int age = readIntWithAgeValidation("Enter the series age restriction: ", true);

        int episodes = readIntWithAgeValidation("Enter the number of episodes for " + name + ": ", false);

        seriesList.add(new SeriesModel(id, name, age, episodes));
        System.out.println("Series processed successfully!!!");
    }

    public void searchSeries() {
        System.out.print("\nEnter the series id to search: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Series id must be numeric.");
            return;
        }

        for (SeriesModel s : seriesList) {
            if (s.getId() == id) {
                System.out.println("--------------------------------");
                System.out.print(s.toString());
                System.out.println("--------------------------------");
                return;
            }
        }
        System.out.println("Series with Series Id: " + id + " was not found!");
    }

    public void updateSeries() {
        System.out.print("\nEnter the series id to update: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Series id must be numeric.");
            return;
        }

        for (SeriesModel s : seriesList) {
            if (s.getId() == id) {
                System.out.print("Enter the series name: ");
                String name = sc.nextLine();
                int age = readIntWithAgeValidation("Enter the age restriction: ", true);
                int episodes = readIntWithAgeValidation("Enter the number of episodes: ", false);

                s.setName(name);
                s.setAgeRestriction(age);
                s.setEpisodes(episodes);

                System.out.println("Series updated successfully!!!");
                return;
            }
        }
        System.out.println("Series with Series Id: " + id + " was not found!");
    }

    public void deleteSeries() {
        System.out.print("\nEnter the series id to delete: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Series id must be numeric.");
            return;
        }

        SeriesModel toDelete = null;
        for (SeriesModel s : seriesList) {
            if (s.getId() == id) {
                toDelete = s;
                break;
            }
        }

        if (toDelete != null) {
            System.out.print("Are you sure you want to delete series " + id + " from the system? Yes (y) to delete: ");
            String confirm = sc.nextLine().trim();
            if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                seriesList.remove(toDelete);
                System.out.println("Series with Series Id: " + id + " WAS deleted!");
            } else {
                System.out.println("Delete cancelled.");
            }
        } else {
            System.out.println("Series with Series Id: " + id + " was not found!");
        }
    }

    public void seriesReport() {
        System.out.println();
        if (seriesList.isEmpty()) {
            System.out.println("No series available to display.");
            return;
        }

        int count = 1;
        for (SeriesModel s : seriesList) {
            System.out.println("Series " + count);
            System.out.println("------------------------------");
            System.out.print(s.toString());
            System.out.println("------------------------------");
            count++;
        }
    }

    public void exitSeriesApplication() {
        System.out.println("Exiting application... Goodbye!");
        System.exit(0);
    }
}
