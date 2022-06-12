package com.javacourse.se.week14;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class PizzaOrderManagement {

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int requirement = scanner.nextInt();
        String key = "";
        if (requirement == 2 || requirement == 3) {
            String temporary = scanner.next();
            key = temporary;
        }
        int tests = scanner.nextInt();
        scanner.nextLine();
        String[] orders = new String[tests];
        for (int i = 0; i < orders.length; i++)
            orders[i] = scanner.nextLine();


            switch (requirement) {
            case 1:
                getTotalprice(orders); break;
            case 2:
                searchByID(orders, key); break;
            case 3:
                searchByDate(orders, key); break;
            case 4:
                sortByID(orders); break;
            case 5:
                sortByDateAndTime(orders); break;
            case 6:
                mostPopularSize(orders); break;
            case 7:
                mostPopularPizzaType(orders); break;
            default:
                System.out.println("Write only integers from 1 to 7 inclusive");
        }
    }

    static void getTotalprice(String[] purchases) {
        int totalPrice = 0;

        for (int j = 0; j < purchases.length; j++){
            totalPrice = totalPrice + getElement(purchases[j], 3);
        }
        System.out.println("Total Price:");
        System.out.println(totalPrice);

    }

    static int getElement(String str, int n) {
        String[] words = str.split(" ");
        for (int w = 0; w < words.length; w++)
            if (w == n)
                return Integer.parseInt(words[w]);
        return 0;
    }

    static void searchByID(String[] purchases, String k) {
        int count = 0;
        System.out.println("Search by ID: " + k);
        for (int p = 0; p < purchases.length; p++) {
            if (Integer.parseInt(k) == getElement(purchases[p], 0)) {
                System.out.println(purchases[p]);
                count++;
            }
        }
        if (count > 0)
            System.exit(0);
        System.out.println("No result");

    }
    static void searchByDate(String[] purchases, String k) {
        int count = 0;
        System.out.println("Search by date: " + k);
        for (int p = 0; p < purchases.length; p++) {
            if (purchases[p].contains(k)) {
                System.out.println(purchases[p]);
                count++;
            }
        }
        if (count > 0)
            System.exit(0);
        System.out.println("No result");

    }
    static void sortByID(String[] purchases) {
        int[] sortedArray = new int[purchases.length];
        for (int h = 0; h < sortedArray.length; h++)
            sortedArray[h] = getElement(purchases[h], 0);
        Arrays.sort(sortedArray);
        String[] sorted = new String[sortedArray.length];
        for (int p = 0; p < sorted.length; p++) {
            for (int q = 0; q < sortedArray.length; q++) {

                if (sortedArray[p] < 1000 && sortedArray[p] > 99) {
                    if (purchases[q].contains("0" + String.valueOf(sortedArray[p])) &&
                            isDistinct(sorted, purchases[q])) {
                        sorted[p] = purchases[q];
                        break;
                    }
                }
                else if (sortedArray[p] < 100 && sortedArray[p] > 9) {
                    if (purchases[q].contains("00" + String.valueOf(sortedArray[p])) &&
                            isDistinct(sorted, purchases[q])) {
                        sorted[p] = purchases[q];
                        break;
                    }
                }
                else if (sortedArray[p] < 10) {
                    if (purchases[q].contains("000" + String.valueOf(sortedArray[p])) &&
                            isDistinct(sorted, purchases[q])) {
                        sorted[p] = purchases[q];
                        break;
                    }
                }
                else if (purchases[q].contains(String.valueOf(sortedArray[p])) &&
                isDistinct(sorted, purchases[q])) {
                    sorted[p] = purchases[q];
                    break;
                }
            }
        }

        System.out.println("Sort by ID:");
        for (int s = 0; s < sorted.length; s++)
            System.out.println(sorted[s]);

    }

    static boolean isDistinct(String[] arr, String str) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == str)
                return false;
        return true;
    }

    static String getElementString(String str, int n) {
        String[] words = str.split(" ");
        for (int w = 0; w < words.length; w++)
            if (w == n)
                return words[w];
        return "";
    }
    static void sortByDateAndTime(String[] purchases) throws ParseException{
        Date[] date = new Date[purchases.length];
        SimpleDateFormat object = new SimpleDateFormat("dd.MM.yyyy' 'HH:mm");
        for (int p = 0; p < date.length; p++)
            date[p] = object.parse(getElementString(purchases[p], 1) + " " +
                    getElementString(purchases[p], 2));
        Arrays.sort(date);
        String[] sorted = new String[date.length];
        for (int p = 0; p < date.length; p++) {
            for (int q = 0; q < sorted.length; q++) {
                if (purchases[q].contains(String.valueOf(object.format(date[p]))) &&
                        isDistinct(sorted, purchases[q])) {
                    sorted[p] = purchases[q];
                    break;
                }
            }
        }
        System.out.println("Sort by date and time:");

        for (int p = 0; p < sorted.length; p++)
            System.out.println(sorted[p]);

    }
    static void mostPopularSize(String[] purchases) {
        int count1 = 0, count2 = 0, count3 = 0;
        for (int p = 0; p < purchases.length; p++) {
            if (getElement(purchases[p], 5) == 20)
                count1++;
            else if (getElement(purchases[p], 5) == 30)
                count2++;
            else if (getElement(purchases[p], 5) == 40)
                count3++;
        }
        int max = count1;
        if (max < count2)
            max = count2;
        if (max < count3)
            max = count3;
        int[] popular = new int[3];
        if (count1 == max)
            popular[0] = 20;
        if (count2 == max)
            popular[1] = 30;
        if (count3 == max)
            popular[2] = 40;
        System.out.println("Most popular size(s):");
        for (int p = 0; p < popular.length; p++) {
            if (popular[p] == 0)
                continue;
            else
                System.out.println(popular[p]);
        }

    }

    static void mostPopularPizzaType(String[] purchases) {
        int count1 = 0, count2 = 0, count3 = 0, count4 = 0;

        for (int p = 0; p < purchases.length; p++) {
            if (getElementString(purchases[p], 6).equals("Yes"))
                count1++;
            if (getElementString(purchases[p], 7).equals("Yes"))
                count2++;
            if (getElementString(purchases[p], 8).equals("Yes"))
                count3++;
            if (getElementString(purchases[p], 9).equals("Yes"))
                count4++;

        }
        System.out.println(count1 + " " + count2 + " " +
                count3 +  " " + count4);
        int max = count1;
        if (max < count2)
            max = count2;
        if (max < count3)
            max = count3;
        if (max < count4)
            max = count4;

        String spices = "";
        if (count1 == max)
            spices = spices + "Cheese";
        if (count2 == max && spices == "")
            spices = spices + "Meat";
        else if (count2 == max)
            spices = spices + "+" + "Meat";

        if (count3 == max && spices == "")
            spices = spices + "Sausage";
        else if (count3 == max)
            spices = spices + "+" + "Sausage";

        if (count4 == max && spices == "")
            spices = spices + "Vegetables";
        else if (count4 == max)
            spices = spices + "+" + "Vegetables";


        System.out.println("Most popular pizza type(s):");
        System.out.println(spices);
    }
}
