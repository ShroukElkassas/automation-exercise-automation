# Automation Exercise – Selenium Test Automation Framework

A Selenium WebDriver + TestNG test automation framework for
[automationexercise.com](https://automationexercise.com), an open practice
site for QA automation. The framework follows the **Page Object Model (POM)**,
generates **Allure reports**, and runs automatically on every push via
**GitHub Actions**.

This project was built as a hands-on portfolio project to practice real-world
test automation framework design — including iterative refactoring,
dead-code cleanup, CI/CD integration, and applying QA best practices.

---

## 🎬 Demo

▶️ [Watch the project demo on Google Drive](https://drive.google.com/file/d/1u4ewqdTiCPgcxFfQ4g8vGhJEJDi2E4Qb/view?usp=sharing)

---

## 📊 Test Cases & Bug Report

The full test case sheet (positive, negative, edge cases, and bug report) is
available here:

📋 [Test Cases & Bug Report – Google Sheets](https://docs.google.com/spreadsheets/d/1MVj3FSOVC2Qc64TWUHI3gRkkJ1GSqlIS2ee_m8LsalI/edit?gid=0#gid=0)

The sheet covers:
- ✅ Positive test cases
- ❌ Negative test cases
- ⚠️ Edge cases
- 🐛 Bug report

---

## 🛠️ Tech Stack

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

## 📁 Project Structure

```
src/test/java/com/automationexercise/
├── pages/      → Page Object classes (one per page, all extend BasePage)
├── tests/      → Test classes (TestCase1, TestCase2, ..., TestCaseN1–N6, TestCaseE1–E4)
└── utils/      → Shared helpers (BrowserManager, JSONReader, SeleniumHelper, Constants, ...)

src/test/resources/
├── config.properties     → browser, base URL, search term
├── allure.properties     → Allure results directory
└── testData/*.json       → external test data (accounts, payment, brand products)

testing.xml   → TestNG suite definition (which test classes run)
pom.xml       → Maven dependencies & build config
```

### Framework Design

```
TestBasic (TestNG @BeforeMethod/@AfterMethod, ThreadLocal<WebDriver>)
    └── TestCaseX  →  Page Objects (extend BasePage)  →  fluent chaining
```

- **`TestBasic`** handles driver setup/teardown for every test via
  `@BeforeMethod`/`@AfterMethod` with a `ThreadLocal<WebDriver>` for
  thread-safe parallel execution.
- **`BasePage`** is the shared parent for all 13 Page Objects — holds the
  `WebDriver` reference and runs `PageFactory.initElements()` once, eliminating
  duplicated constructor boilerplate.
- **`JSONReader`** loads test data from classpath JSON files with per-file
  caching — each file is parsed once and reused, not re-read on every call.
- Page Object methods return the next page object (or `this`), enabling
  fluent chains like:
  ```java
  new HomePage(getDriver())
      .signupLoginClick()
      .fillCorrectSignup(name, email)
      .fillAccountDetails()
      .getAccountCreated()
      .getText();
  ```

---

## ▶️ How to Run

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

In CI (GitHub Actions), tests run **headless** automatically.
Locally, Chrome runs maximized by default so you can watch the test execute.

---

## ✅ Test Coverage

The suite implements **21 test cases** across positive, negative, and edge
categories:

### Positive Test Cases

| Test Case | Description |
|---|---|
| TC1 | Register new user |
| TC2 | Login with valid credentials |
| TC4 | Logout user |
| TC8 | View all products and product detail page |
| TC9 | Search for a product |
| TC12 | Add products to cart and verify price/quantity/total |
| TC13 | Verify product quantity in cart |
| TC18 | View category products |
| TC19 | View brand products (Polo & Madame) |
| TC21 | Add review on product |

### Negative Test Cases

| Test Case | Description |
|---|---|
| TC3 | Login with incorrect email and password |
| TC5 | Register with already registered email |
| TCN1 | Login with empty fields |
| TCN2 | Search for a non-existent product |
| TCN3 | Register with invalid email format |
| TCN4 | Login with correct email but wrong password |
| TCN5 | Submit contact form with empty fields |
| TCN6 | Subscribe with invalid email format |

### Edge Cases

| Test Case | Description |
|---|---|
| TCE1 | Add product with quantity = 0 to cart |
| TCE2 | Add product with quantity = -1 to cart |
| TCE4 | Verify cart behavior with boundary quantity values |

### Coverage Areas

Negative coverage spans **five different areas** — login, signup, search,
contact form, and newsletter subscription — to demonstrate genuine
"how could this break?" thinking rather than repeated login variations.

---

## 🗂️ Test Data

All test data lives in `src/test/resources/testData/*.json` and is loaded
via `JSONReader`, keeping data completely separate from test logic:

| File | Used For |
|---|---|
| `AccountDetails.json` | Registration form data |
| `ExistingUser.json` | Credentials for login tests |
| `PaymentDetails.json` | Payment form data |
| `PoloBrandProducts.json` | Expected product list for Polo brand |
| `MadameBrandProducts.json` | Expected product list for Madame brand |

---



## ⚙️ CI/CD

Every push/PR to `main` triggers a GitHub Actions workflow that:

1. Sets up JDK 21 and Chrome on Ubuntu
2. Runs `mvn clean test -Dbrowser.name=chrome` in headless mode
3. Uploads Allure results and Surefire reports as downloadable artifacts
4. Generates and publishes the Allure HTML report to GitHub Pages

---

## 👩‍💻 Author

Built by **Shrouk Elkassas** as a hands-on Selenium/TestNG framework
practice project — ITI Graduation Project.
