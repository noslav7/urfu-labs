# Лабораторная работа 10 - пункт 2.3 (вариант Animals)

Папка с решением: `src/main/java/ru/urfu/labs/lab10/point2_3`  
Файлы данных: `src/main/resources/lab10/point2_3`

## Кодировка (Windows PowerShell)

Перед запуском Java-примера выполните:

```powershell
chcp 65001
```

## Что реализовано

- `2.3.1` Создан собственный JSON-файл `animals.json` по варианту "Домашние животные".
- `2.3.2` Поиск животных по имени и по виду через `JSONArray.stream()` и `filter()`.
- `2.3.3` Добавление нового животного в массив через `JSONArray.add()`.
- `2.3.4` Удаление животного из массива по имени через `Iterator.remove()`.

## Почему логика не "по книгам"

В методичке пример дан для книг (автор/год), но ваш вариант — **Animals**.  
Поэтому поиск реализован по полям предметной области:

- по имени животного;
- по виду животного.

## Запуск интерактивного меню

```powershell
mvn -q "-Dexec.mainClass=ru.urfu.labs.lab10.point2_3.Point2_3Runner" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

## Быстрая автоматическая проверка

```powershell
mvn -q "-Dexec.mainClass=ru.urfu.labs.lab10.point2_3.Point2_3Demo" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

## Меню программы

- `1` - показать всех животных
- `2` - добавить животное
- `3` - найти по имени
- `4` - найти по виду
- `5` - удалить по имени
- `0` - выход
