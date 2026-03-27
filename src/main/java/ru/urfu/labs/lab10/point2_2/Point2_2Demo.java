package ru.urfu.labs.lab10.point2_2;

import ru.urfu.labs.lab10.ConsoleEncodingUtil;

import java.util.List;

/**
 * Автоматическая демонстрация операций задания 2.2:
 * добавление, поиск и удаление записи в XML.
 */
public class Point2_2Demo {

    /**
     * Выполняет короткий сценарий проверки без ручного ввода.
     */
    public static void main(String[] args) {
        ConsoleEncodingUtil.ensureUtf8Console();

        AnimalXmlRepository repository = new AnimalXmlRepository();
        String demoName = "ДемоПитомец_" + System.currentTimeMillis();
        Animal demoAnimal = new Animal(
                demoName,
                "Кот",
                "Мейн-кун",
                2,
                "Тестовый владелец"
        );

        try {
            List<Animal> initial = repository.findAll();
            System.out.println("1) Изначально записей: " + initial.size());

            repository.addAnimal(demoAnimal);
            System.out.println("2) Добавлено животное: " + demoAnimal);

            List<Animal> byName = repository.findByName(demoName);
            System.out.println("3) Поиск по имени '" + demoName + "': найдено " + byName.size());

            List<Animal> bySpecies = repository.findBySpecies("кот");
            System.out.println("4) Поиск по виду 'кот': найдено " + bySpecies.size());

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
