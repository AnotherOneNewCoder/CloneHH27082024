# Тестовое задание Clone HeadHunter

## Краткое описание приложения.
Приложение (клон hh) для поиска работы.

### Инструменты.
- Архитиктура MVVM (Многомодульность)
- Библиотеки:
    - Jetpack Compose
    - Material Design
    - Ktor
    - Dagger
    - GSON
    - Navigation
    - Flow
    - Coroutines
    - Room

### Функционал.

Пользователь открывает приложение. Вход по емаилу и паролю.

<img src ="https://github.com/AnotherOneNewCoder/CloneHH27082024/blob/main/art/entrance1.jpg" width=25% height=25%>
<img src ="https://github.com/AnotherOneNewCoder/CloneHH27082024/blob/main/art/entrance2.jpg" width=25% height=25%>

Открывается главный экран со списком вакансий. Внизу кнопка "Еще ХХХ вакансий".

При клике на нее исчезает фильтр и отображается лейнта всех вакансий.

Можно добавить вакансию в избранное, после перехода в раздел "Избранное" эта вакансия там отобразится.

Выбор вакансии (клик на карточку). Отображается описание вакансии.

Клик на "Откликнуться" - отображается форма с подгруженным резюме и поле для ввода сообщения. Отправляется отклик.