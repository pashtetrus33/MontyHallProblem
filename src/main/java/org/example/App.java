package org.example;

import org.example.presents.Car;
import org.example.presents.Goat;

import java.util.*;

/**
 * Создать свой Java Maven/Gradle проект;
 * Реализовать прикладную задачу - приложение для демонстрации парадокса Монти Холла;
 * Можно добавить любые библиотеки которые считают необходимыми
 * Результаты должны быть сохранены в HashMap (шаг цикла -> результат)
 * Необходимо вывести статистику по результату - количество позитивных и негативных результатов, процент от общего количества шагов цикла.
 */
public class App {
    private static int doorsAmount;
    private static int choice;
    private static int originalChoice;
    private static int hostChoice = 1;
    private static List<Integer> listOfHostChoices;
    private static int timesToPlay;
    private static Map<Integer, String> gameResults;


    public static void main(String[] args) {
        gameResults = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        while (doorsAmount < 3) {
            System.out.print("Введите количество дверей (>=3): ");
            doorsAmount = scanner.nextInt();
        }
        while (timesToPlay < 1) {
            System.out.print("Введите количество попыток (>=1): ");
            timesToPlay = scanner.nextInt();
        }

        System.out.println("-----------------------------------------INFO--------------------------------------------------------------");
        System.out.println("Дверей: " + doorsAmount);
        System.out.println("Попыток: " + timesToPlay);
        System.out.println();

        while (choice < 1 || choice > doorsAmount) {
            System.out.print("Выберите дверь (от 1 до " + doorsAmount + "): ");
            choice = scanner.nextInt();
            originalChoice = choice;
        }
        int gameStrategy = -1;
        while (gameStrategy < 0 || gameStrategy > 1) {
            System.out.print("Вы будете менять свой выбор? (Да: 1 Нет: 0): ");
            gameStrategy = scanner.nextInt();
        }
        if (gameStrategy == 1) {
            game(true);
        } else {
            game(false);
        }
    }

    private static void game(boolean isChoiceChanging) {

        for (int i = 1; i <= timesToPlay; i++) {
            List<Door> doorsList = fillDoors();
            if (isChoiceChanging) {
                listOfHostChoices = new ArrayList<>();
                System.out.println("-------------------------------------------------------------------------------------------------------------");
                choice = originalChoice;

                System.out.println("Выбор возращен на начальный: " + choice);
                while (listOfHostChoices.size() < doorsAmount - 2) {
                    hostChoice = new Random().nextInt(1, doorsAmount + 1);
                    if (!listOfHostChoices.contains(hostChoice) && hostChoice != choice && !doorsList.get(hostChoice - 1).getPrize().showPresent().equals("Car")) {
                        listOfHostChoices.add(hostChoice);
                    }
                }

                System.out.println("Ведущий открыл дверь(и): " + listOfHostChoices + " там: КОЗА(Ы)");

                for (int j = 1; j <= doorsList.size(); j++) {
                    if (j != choice && !listOfHostChoices.contains(j)) {
                        choice = j;
                        break;
                    }
                }
                System.out.println("Выбор изменен на дверь: " + choice);
            } else {
                System.out.println("Выбор сделан: " + choice);
            }


            System.out.println("Игра№" + i + ": " + doorsList);
            if (doorsList.get(choice - 1).getPrize().showPresent().equals("Car")) {
                gameResults.put(i, "WINNER");
                System.out.println("---------------------------------------------------------------------------------------------------------");
                System.out.println("Игра№" + i + ": Автомобиль!");
            } else {
                gameResults.put(i, "LOSER");
            }
        }
        printStatistics(gameResults);
    }

    private static List<Door> fillDoors() {
        List<Door> doorList = new ArrayList<>();
        // Генерируем рандомно индекс двери с машиной
        int carIndex = new Random().nextInt(1, doorsAmount + 1);
        for (int j = 1; j <= doorsAmount; j++) {
            if (j == carIndex) {
                doorList.add(new Door(j, new Car()));
            } else {
                doorList.add(new Door(j, new Goat()));
            }
        }
        return doorList;
    }

    private static void printStatistics(Map<Integer, String> result) {
        long totalAttempts = result.size();
        long winCount = result.entrySet().stream().filter(e -> e.getValue().equals("WINNER")).count();
        System.out.println("----------------------------------STATISTICS____________________________________________________________");
        System.out.println("Количество побед: " + winCount + " из " + totalAttempts);
        System.out.println("Количество проигрышей: " + (totalAttempts - winCount) + " из " + totalAttempts);
        System.out.println("Процент побед: " + ((float) winCount / totalAttempts) * 100 + "%");
        //System.out.println(result);
    }
}

