# Automation Exercise – Selenium Test Automation Framework

A Selenium WebDriver + TestNG test automation framework for [automationexercise.com](https://automationexercise.com), an open practice site for QA automation. The framework follows the **Page Object Model (POM)**, generates **Allure reports**, and runs automatically on every push via **GitHub Actions**.

This project was built as a learning/portfolio project to practice real-world test automation framework design — including iterative refactoring, dead-code cleanup, and applying QA best practices.

---

## Tech Stack

| Tool | Purpose |
|---|---|
| Java 21 | Language |
| Selenium WebDriver 4 | Browser automation |
| TestNG | Test framework (annotations, assertions, suite execution) |
| WebDriverManager | Automatic ChromeDriver binary management |
| Allure Report | Test reporting (`@Epic`, `@Feature`, `@Story`, `@Step`, `@Severity`) |
| Maven | Build & dependency management |
| GitHub Actions | CI/CD pipeline (headless Chrome, Allure report published to GitHub Pages) |
| JSON (json-simple) | External test data files |

---

## Project Structure

```
src/test/java/com/automationexercise/
├── pages/      → Page Object classes (one per page, all extend BasePage)
├── tests/      → Test classes (TestCase1, TestCase2, ..., TestCaseN1-N6)
└── utils/      → Shared helpers (BrowserManager, JSONReader, SeleniumHelper, ...)

src/test/resources/
├── config.properties     → browser, base URL, search term
├── allure.properties     → Allure results directory
└── testData/*.json       → external test data (accounts, payment, brand products)

testing.xml   → TestNG suite definition (which test classes run)
pom.xml       → Maven dependencies & build config
```

### Framework design

```
TestBasic (TestNG @BeforeMethod/@AfterMethod, ThreadLocal<WebDriver>)
    └── TestCaseX  →  Page Objects (extend BasePage)  →  fluent chaining
```

- `TestBasic` handles driver setup/teardown for every test via `@BeforeMethod`/`@AfterMethod`.
- `BasePage` is a shared parent for all Page Objects — it only holds the `WebDriver` reference and runs `PageFactory.initElements()`. Page Objects never extend `TestBasic`, keeping test lifecycle logic separate from page logic.
- Page Object methods return the next page object (or `this`), enabling fluent chains like:
  ```java
  new HomePage(getDriver())
      .signupLoginClick()
      .fillCorrectSignup(name, email)
      .fillAccountDetails()
      .getAccountCreated()
      .getText();
  ```

---

## How to Run

### Prerequisites
- Java 21 (JDK)
- Maven
- Google Chrome installed

### Run the full suite
```bash
mvn clean test -Dbrowser.name=chrome
```

### Run a single test class
```bash
mvn clean test -Dbrowser.name=chrome -Dtest=TestCase1
```

### Generate and view the Allure report locally
```bash
mvn allure:serve
```

In CI (GitHub Actions), tests run **headless** automatically; locally, Chrome runs maximized by default so you can watch the test execute.

---

## Test Coverage

The suite currently implements **20 test cases** covering:

- **User account**: registration, login (valid/invalid), logout, duplicate-email registration, invalid email format
- **Products**: product listing, product detail view, search (valid and non-existent terms), category navigation, brand navigation, product reviews
- **Cart**: add to cart, quantity verification, price/total verification
- **Subscription**: newsletter subscribe (valid and invalid email)
- **Contact Us**: empty-field form submission

### Positive vs Negative

| Type | Test Cases |
|---|---|
| Positive | TC1, TC2, TC4, TC8, TC9, TC10, TC12, TC13, TC18, TC19, TC21 |
| Negative | TC3, TCN1, TCN2, TCN3, TCN4, TCN5, TCN6 |

Negative coverage spans **five different areas** (login, signup, search, contact form, newsletter subscription) — not just repeated login variations — to demonstrate genuine "how could this break?" thinking rather than copy-pasted negative cases.

---

## Test Data

All test data lives in `src/test/resources/testData/*.json` and is loaded via `JSONReader`, keeping data separate from test logic:

- `AccountDetails.json` – registration form data
- `ExistingUser.json` – credentials for the login tests
- `PaymentDetails.json` – payment form data
- `PoloBrandProducts.json` / `MadameBrandProducts.json` – expected product lists per brand

---

## Known Limitations & Next Steps

This project is a work in progress and these limitations are tracked deliberately rather than hidden:

- **`config.properties`** still contains a couple of leftover hardcoded local paths from earlier development that aren't referenced by any test — to be removed.
- **No retry-on-failure listener** or automatic failure screenshots yet — both are natural next additions for handling UI flakiness.
- **No `@DataProvider` usage yet** — test data is read via `JSONReader` static calls (a manual data-driven approach), but no test uses TestNG's native `@DataProvider` to run one method against multiple data sets. `TestCase19` (brand products: Polo, Madame) is a good candidate — it currently checks both brands sequentially inside one test method and could be split into two data-driven executions instead.

---

## CI/CD

Every push/PR triggers a GitHub Actions workflow that:
1. Sets up JDK 21 and Chrome
2. Runs `mvn clean test -Dbrowser.name=chrome` headlessly
3. Uploads Allure results and Surefire reports as artifacts
4. Publishes the Allure HTML report to GitHub Pages

---

## Author

Built by Shrouk Elkassas as a hands-on Selenium/TestNG framework practice project.
