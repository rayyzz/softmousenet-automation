# SoftMouse.NET Automation Framework

This is an automated test framework for SoftMouse.NET using:
- Selenium WebDriver
- TestNG
- Page Object Model (POM)
- Maven

## Features

- Login, create mouse, edit genotype, export data, mark mouse as dead
- Uses config.properties for environment setup
- Downloads and verifies .xlsx export files

## Setup

1. Clone the repo
2. Import into IDE of choice (VSCode, IntelliJ)
3. Run `mvn test` or use TestNG suite

## Config

Update `src/main/resources/config.properties` for:
- `baseUrl`
- `username`
- `password`
- `downloadPath`

## Author
Raiyan Islam
