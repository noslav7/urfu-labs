# Лабораторная работа 10 - пункт 2.4 (HTML парсер)

Папка с решением: `src/main/java/ru/urfu/labs/lab10/point2_4`  
Файлы данных: `src/main/resources/lab10/point2_4`

## Что реализовано

- `2.4.1` Добавлено сохранение полученных новостей в файл `news-log.txt`.
- `2.4.2` Добавлена обработка ошибок загрузки HTML:
  - вывод сообщения об ошибке;
  - повторные попытки подключения (`3` попытки, пауза `2` секунды).

## Запуск

```powershell
mvn -q "-Dexec.mainClass=ru.urfu.labs.lab10.point2_4.Point2_4NewsParser" org.codehaus.mojo:exec-maven-plugin:3.5.0:java
```

## Где смотреть сохранение

После запуска файл с результатом находится по пути:

`src/main/resources/lab10/point2_4/news-log.txt`
