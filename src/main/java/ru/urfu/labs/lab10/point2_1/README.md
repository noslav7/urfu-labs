# Лабораторная работа 10

Папка с решениями: `src/main/java/ru/urfu/labs/lab10/point2_1`

## Что реализовано

- XML: создание и чтение
- JSON: создание и чтение
- HTML: парсинг ссылок и парсинг новостей
- Excel: создание и чтение `.xlsx`
- Общий запуск всех примеров: `Lab10Runner`

## Быстрый запуск всех примеров

Из корня проекта выполните:

```powershell
mvn -q -DskipTests "-Dexec.mainClass=ru.urfu.labs.lab10.point2_1.Lab10Runner" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

## Запуск примеров по отдельности

### XML

Создать XML:

```powershell
mvn -q -DskipTests "-Dexec.mainClass=ru.urfu.labs.lab10.point2_1.example1.CreateXmlFile" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

Прочитать XML:

```powershell
mvn -q -DskipTests "-Dexec.mainClass=ru.urfu.labs.lab10.point2_1.example1.XmlParser" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

### JSON

Создать JSON:

```powershell
mvn -q -DskipTests "-Dexec.mainClass=ru.urfu.labs.lab10.point2_1.example2.JsonCreator" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

Прочитать JSON:

```powershell
mvn -q -DskipTests "-Dexec.mainClass=ru.urfu.labs.lab10.point2_1.example2.JsonParser" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

### HTML

Ссылки со страницы `https://itlearn.ru/first-steps`:

```powershell
mvn -q -DskipTests "-Dexec.mainClass=ru.urfu.labs.lab10.point2_1.example5.LinkParser" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

Новости с `http://fat.urfu.ru/index.html`:

```powershell
mvn -q -DskipTests "-Dexec.mainClass=ru.urfu.labs.lab10.point2_1.example4.NewsParser" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

### Excel

Создать `.xlsx`:

```powershell
mvn -q -DskipTests "-Dexec.mainClass=ru.urfu.labs.lab10.point2_1.example3.WriteExcelFileExample" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

Прочитать `.xlsx`:

```powershell
mvn -q -DskipTests "-Dexec.mainClass=ru.urfu.labs.lab10.point2_1.example3.ReadExcelFileExample" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

## Файлы данных

- XML: `src/main/resources/lab10/point2_1/example1/example.xml`
- JSON: `src/main/resources/lab10/point2_1/example2/example-json.json`
- Excel: `src/main/resources/lab10/point2_1/example3/example3.xlsx`
