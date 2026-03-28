package ru.urfu.labs.lab10.point2_3;

import ru.urfu.labs.lab10.ConsoleEncodingUtil;

import java.util.List;

/**
 * Автоматическая демонстрация операций задания 2.3:
 * добавление, поиск и удаление записи в JSON.
 */
public class Point2_3Demo {

    /**
     * Выполняет короткий сценарий проверки без ручного ввода.
     */
    public static void main(String[] args) {
        ConsoleEncodingUtil.ensureUtf8Console();

        AnimalJsonRepository repository = new AnimalJsonRepository();
        String demoName = "JsonДемо_" + System.currentTimeMillis();
        Animal demoAnimal = new Animal(
                demoName,
                "Собака",
                "Лабрадор",
                3,
                "Тестовый владелец"
        );

        try {
            List<Animal> initial = repository.findAll();
            System.out.println("1) Изначально записей: " + initial.size());

            repository.addAnimal(demoAnimal);
            System.out.println("2) Добавлено животное: " + demoAnimal);

            List<Animal> byName = repository.findByName(demoName);
            System.out.println("3) Поиск по имени '" + demoName + "': найдено " + byName.size());

            List<Animal> bySpecies = repository.findBySpecies("собака");
            System.out.println("4) Поиск по виду 'собака': найдено " + bySpecies.size());

            int removed = repository.deleteByName(demoName);
            System.out.println("5) Удаление по имени '" + demoName + "': удалено " + removed);

            List<Animal> afterDelete = repository.findByName(demoName);
            System.out.println("6) Повторный поиск после удаления: найдено " + afterDelete.size());

            System.out.println("Демо-сценарий завершен успешно.");
        } catch (Exception e) {
            System.out.println("Ошибка в демо-сценарии: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
