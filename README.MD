# saucedemo-automation

Automation tests for https://www.saucedemo.com (Inventory: sort by price, add to cart)

## Tech stack
- Java 21
- Maven
- Selenium 4
- TestNG
- WebDriverManager

## Prerequisites
- Java 21+ installed
- Maven installed
- Chrome browser installed (if running locally)
- Internet access

## How to run locally
1. Clone repo:
   git clone <your-repo-url>
2. Run tests:
   mvn clean test

By default tests use Chrome. To run headless (CI), set environment variable `CI=true`.

## Tests included
- testSortByPriceLowToHigh — logs in with `standard_user` / `secret_sauce`, selects sort "Price (low to high)", asserts ascending order.
- testAddToCartAndVerifyCartContents — logs in, adds "Sauce Labs Backpack", verifies cart contains it.

## Credentials
Use standard saucedemo credentials:
- Username: standard_user
- Password: secret_sauce

## CI
A GitHub Actions workflow is included: `.github/workflows/ci.yml`.
It will run `mvn test` on push to `main`.

## How to publish to GitHub (quick)
1. Create repo on GitHub (skip README on creation)
2. Locally:
   git init
   git add .
   git commit -m "Initial commit - saucedemo tests"
   git branch -M main
   git remote add origin git@github.com:<your-username>/<repo>.git
   git push -u origin main

## Notes & improvements
- Replace Thread.sleep with explicit waits.
- Add PageFactory waits and better locators.
- Add Allure reporting for nicer reports.
- Add BrowserStack/Sauce Labs integration for cross-browser cloud execution.
