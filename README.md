<div align="center">
<img src="https://x-lines.ru/letters/i/cyrillicscript/1533/b4b4b6/38/0/j7zgktdbxf8sh3kzcfho.png">
<p align="center"></p>
</div>

<details><summary><b>Содержание</b></summary>
  
    1. Краткое описание
    2. Стек-технологий
    3. Функциональные особенности проекта
    4. Системные требования
    5. Инструкция по сборке
</details>

## Краткое описание

OneDayOneWay - это приложение для сборки прогулочного маршрута на один день. Пользователь может создавать маршрут, добавлять в него места с фотографиями, комментировать добавленные ранее места других пользователей, искать маршруты доступные в необходимом ему городе и др. 
* API сервиса разделен на три части:
  - Первая — публичная, доступна без регистрации любому пользователю сети.
  - Вторая — закрытая, доступна только авторизованным пользователям.
  - Третья — административная, для администраторов сервиса.
* Авторизация реализована с помощью JWT (JSON Web Token).

## Стек-технологий

* JWT (JSON Web Token)
* Spring Boot
* JPA+API
* Spring Security
* Mapstruct
* Swagger
* Maven

## Функциональные особенности проекта

Задумка проекта - создание прогулочного маршрута на 1 день (one day one way), поэтому процесс добавления пользователем мест в маршрут реализован с особенностями:
   - Пользователь может добавить места в радиусе только 2км друг от друга. Ведь прогулочный маршрут должен быть комфортным.
   - Расстояние между местами вычисляется через координаты (долгота и широта) двух точек на карте с учетом кривизны Земли.
   - Пользователь может добавлять фотографии к местам маршрута.

Приложение отвечает требованиям [спецификации](./oneDayOneWay-spec.json).
С полным списком функциональностей проекта можно ознакомиться при помощи [swagger](https://editor-next.swagger.io).

## Системные требования

В данном репозитории представлен бэкенд приложения.

Приложение работает корректно в текущем виде при наличии:

- установленный [JDK версии 11](https://docs.aws.amazon.com/corretto/),
- сборка с использованием [Maven](https://maven.apache.org/).

## Инструкция по сборке:

- Необходимо скачать проект в zip архиве нажав на главной странице проекта зеленую кнопку code -> download zip
- Откройте проект с помощью своей среды разработки и дождитесь, пока загрузятся все необходимые данные.
- Для проверки работоспособности приложение было протестировано по WEB API, можно запустить Postman тесты, они расположены в папке [postman](./postman/).
