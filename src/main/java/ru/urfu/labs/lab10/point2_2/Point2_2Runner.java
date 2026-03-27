package ru.urfu.labs.lab10.point2_2;

import ru.urfu.labs.lab10.ConsoleEncodingUtil;

import java.util.List;
import java.util.Scanner;

/**
 * Консольное меню для задания 2.2 по работе с XML-файлом домашних животных.
 */
public class Point2_2Runner {

    private static final AnimalXmlRepository REPOSITORY = new AnimalXmlRepository();

    /**
     * Запускает интерактивное меню: просмотр, добавление, поиск и удаление животных.
     */
    public static void main(String[] args) {
        ConsoleEncodingUtil.ensureUtf8Console();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMenu();
                String action = scanner.nextLine().trim();

                if ("0".equals(action)) {
                    System.out.println("Выход из программы.");
                    return;
                }

                try {
                    switch (action) {
                        case "1" -> printAnimals(REPOSITORY.findAll());
                        case "2" -> addAnimal(scanner);
                        case "3" -> searchByName(scanner);
                        case "4" -> searchBySpecies(scanner);
                        case "5" -> deleteByName(scanner);
                        default -> System.out.println("Неизвестный пункт меню. Выберите 0-5.");
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка при выполнении операции: " + e.getMessage());
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n========== XML Animals (point2_2) ==========");
        System.out.println("1 - Показать всех животных");
        System.out.println("2 - Добавить животное");
        System.out.println("3 - Найти животных по имени");
        System.out.println("4 - Найти животных по виду");
        System.out.println("5 - Удалить животных по имени");
        System.out.println("0 - Выход");
        System.out.print("Выбор: ");
    }

    private static void addAnimal(Scanner scanner) throws Exception {
        System.out.print("Имя животного: ");
        String name = scanner.nextLine().trim();
        System.out.print("Вид (например, Кот/Собака): ");
        String species = scanner.nextLine().trim();
        System.out.print("Порода: ");
        String breed = scanner.nextLine().trim();
        int age = readAge(scanner);
        System.out.print("Владелец: ");
        String owner = scanner.nextLine().trim();

        Animal animal = new Animal(name, species, breed, age, owner);
        REPOSITORY.addAnimal(animal);
        System.out.println("Животное добавлено в XML.");
    }

    private static void searchByName(Scanner scanner) throws Exception {
        System.out.print("Введите имя (или его часть): ");
        String query = scanner.nextLine();
        List<Animal> found = REPOSITORY.findByName(query);
        printAnimals(found);
    }

    private static void searchBySpecies(Scanner scanner) throws Exception {
        System.out.print("Введите вид (или его часть): ");
        String query = scanner.nextLine();
        List<Animal> found = REPOSITORY.findBySpecies(query);
        printAnimals(found);
    }

    private static void deleteByName(Scanner scanner) throws Exception {
        System.out.print("Введите точное имя животного для удаления: ");
        String name = scanner.nextLine();
        int removed = REPOSITORY.deleteByName(name);
        System.out.println("Удалено записей: " + removed);
    }

    private static int readAge(Scanner scanner) {
        while (true) {
            System.out.print("Возраст (целое число): ");
            String raw = scanner.nextLine().trim();
            try {
                int age = Integer.parseInt(raw);
                if (age < 0) {
                    System.out.println("Возраст не может быть отрицательным.");
                    continue;
                }
                return age;
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное число.");
            }
        }
    }

    private static void printAnimals(List<Animal> animals) {
        if (animals.isEmpty()) {
            System.out.println("Ничего не найдено.");
            return;
        }

        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            System.out.printf(
                    "%d) Имя: %s, Вид: %s, Порода: %s, Возраст: %d, Владелец: %s%n",
                    i + 1,
                    animal.getName(),
                    animal.getSpecies(),
                    animal.getBreed(),
                    animal.getAge(),
                    animal.getOwner()
            );
        }
    }
}
