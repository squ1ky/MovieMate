# MovieMate 🎬 [In Development]

**MovieMate** — сервис для подбора фильмов и сериалов, созданный на микросервисной архитектуре. Проект позволяет пользователям находить фильмы и сериалы, добавлять их в избранное или в список «Буду смотреть», а также получать рекомендации на основе предпочтений.

## ⚙️ Технологический стек

- **Backend**: Java 21, Spring Boot, Spring Data JPA, Spring Cloud, Redis, Hibernate, Maven
- **Брокер сообщений**: RabbitMQ
- **Внешний API**: Кинопоиск API для получения информации о фильмах и сериалах
- **Тестирование**: Postman
- **Управление версиями и репозиторием**: Git & GitHub

## 📜 Функциональность
  
- **Работа с фильмами и сериалами**:
  - Получение фильмов и сериалов из внешнего Kinopoisk API.
  - Добавление фильмов в раздел «Избранное».
  - Добавление фильмов в список «Буду смотреть».

- **Рекомендации**:
  - Автоматическое формирование рекомендаций на основе оценок пользователя.
  
## 📐 Архитектура

Проект построен с использованием микросервисной архитектуры. Основные микросервисы включают:
- **User Service**: отвечает за управление пользовательскими данными.
- **Movie Service**: взаимодействует с Kinopoisk API для получения информации о фильмах и сериалах, рекомендует пользователям похожие фильмы.

## 💡 Дальнейшее развитие

- **Добавление Spring Security**.
- **Реализация CI/CD** для автоматического деплоя и тестирования.
- **Улучшение алгоритма рекомендаций** на основе истории просмотров.
