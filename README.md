# Застосунок "Вікторина"

При запуску додатку "Вікторина" вас вітає екран вибору категорії. Кожне правильне відповідь на питання приносить два бали. Ваші бали можна переглянути на екрані вашого профілю.

## Запуск проекту

Для запуску проекту вам знадобиться Android studio.Крім цього вам знадобиться завантажити на налаштувати емулятор телефону або ж налаштувати фізичний телефон.


## Використані принципи програмування

- **DRY (Don't Repeat Yourself)**: У коді немає повторень. Вся логіка розбита на окремі методи та класи, щоб забезпечити їхню одноразову реалізацію та використання(без врахування ui компонентів).
- **KISS (Keep It Simple, Stupid)**: Код дуже простий та зрозумілий. Немає громіздких методів або класів, що робить його легким у розумінні та роботі з ним.
- **SR (Single Responsibility)**: Кожен клас виконує своє конкретне завдання. Кожен клас відповідає за один аспект функціоналу та не переплутується з іншими.
- **OCP (Open/Closed Principle)**: Класи закриті для модифікацій, але відкриті для розширення. Це означає, що зміни в поведінці програми здійснюються через створення нових класів або інтерфейсів, а не шляхом зміни вже існуючого коду.
- **ISP (Interface Segregation Principle)**: Класи мають лише ті методи, які їм необхідні. Кожен клас має свій власний набір методів, не перевантажуючи його зайвими функціями.
- **YAGNI(You Aren't Gonna Need It)**: уникнув додавання зайвого функціоналу до програми, якого насправді не потрібно.
## Використанні патерни проектування

- **Фабричний метод (Factory Method)** клас QuestionRepositoryFactory використовується як фабричний метод для створення екземплярів QuestionRepository.
Конкретна реалізація цього фабричного методу надається класом DefaultQuestionRepositoryFactory. [DefaultQuestionRepositoryFactory](https://github.com/mvyu222/quize/blob/master/app/src/main/java/com/chunmaru/quizland/data/db/repositories/DefaultQuestionRepositoryFactory.kt)

- **Декоратор (Decorator)** у проекті застосовується патерн декоратора для розширення функціональності класу DataStorageManager.Загалом він потрібний для тесту та логування даних [LoggingDataStorageManager](https://github.com/mvyu222/quize/blob/master/app/src/main/java/com/chunmaru/quizland/data/storage/LoggingDataStorageManager.kt)

- **Singleton(Одинак)** у проекті бібліотека hilt створює всі залежності лише в одному екземплярі, що дає нам змогу вживляти одні і ті самі залежності в різні конструктори. [Module](https://github.com/mvyu222/quize/blob/master/app/src/main/java/com/chunmaru/quizland/domain/hilt/Module.kt).
  Крім цього у Kotlin створення об'єкту з використанням ключового слова object фактично створює синглтон-об'єкт. [CategoryConst](https://github.com/mvyu222/quize/blob/master/app/src/main/java/com/chunmaru/quizland/data/models/CategoryConst.kt).

- **Observer(Спостерігач)** в проекті для зв'язку між View та ViewModel застосовується технологія LiveData, яка виступає в ролі спостерігача (Observer). LiveData дозволяє підписатись на об'єкт та автоматично відслідковувати його зміни. [ViewModel](https://github.com/mvyu222/quize/blob/master/app/src/main/java/com/chunmaru/quizland/presentation/screens/home/HomeViewModel.kt)   [View](https://github.com/mvyu222/quize/blob/master/app/src/main/java/com/chunmaru/quizland/presentation/screens/home/HomeFragment.kt)

## Використані Техніки рефакторингу
- **Inline Method**: Переміщення вмісту методу безпосередньо в місце його виклику для зменшення зайвої абстракції та полегшення зрозуміння коду.Було застосовано під час написання програмного коду.
- **Inline Temp**: Заміна локальної змінної викликом її значення для полегшення читання та зменшення зайвих проміжних кроків у коді.Було застосовано під час написання програмного коду.
- **Extract Method**: Виділення фрагменту коду в окремий метод для зменшення дублювання коду та покращення читабельності та підтримки.Було застосовано під час написання програмного коду.
