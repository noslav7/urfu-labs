package ru.urfu.labs.lab10.point2_2;

/**
 * Модель домашнего животного для хранения в XML-файле.
 */
public class Animal {
    private final String name;
    private final String species;
    private final String breed;
    private final int age;
    private final String owner;

    public Animal(String name, String species, String breed, int age, String owner) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "name='" + name + '\''
                + ", species='" + species + '\''
                + ", breed='" + breed + '\''
                + ", age=" + age
                + ", owner='" + owner + '\''
                + '}';
    }
}
