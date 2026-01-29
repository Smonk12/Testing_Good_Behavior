<a id="readme-top"></a>

<!-- PROJECT SHIELDS -->
[![Contributors][contributors-shield]][contributors-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/Smonk12/Testing_Good_Behavior" alt="Test Automation" border="0">
    <img src="https://i.ibb.co/bR7w95gs/Test-Automation.png" alt="Test Automation" border="0" alt="Logo" width="200" height="200">
  </a>

  <h3 align="center">Testing Good Behavior</h3>

  <p align="center">
    Designed and implemented automated test scenarios using Gherkin syntax with Cucumber and Selenium WebDriver in JUnit5 to validate all key functionalities of a locally developed web project.
    <br />
    <a href="https://github.com/Smonk12/Testing_Good_Behavior"><strong>Explore the docs »</strong></a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

---

<!-- ABOUT THE PROJECT -->
## About The Project

Testing Good Behavior is a test automation project designed to validate the core functionalities of a local FastAPI web application.

Using Behavior-Driven Development (BDD) principles, it implements test scenarios in Gherkin syntax and executes them with Cucumber, Selenium WebDriver, and JUnit5.

The project allows developers to:
- Automatically verify that all key features of the FastAPI application work as expected.
- Run tests locally against the development server, ensuring changes don’t break existing functionality.
- Generate detailed test reports for better visibility of passed and failed scenarios.

By combining Java, Maven, and modern test automation tools, this project demonstrates a structured, maintainable approach to end-to-end testing of web applications.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Built With

#### Core
- [![Java][Java.com]][Java-url]
- [![Maven][Maven.apache]][Maven-url]

#### Test Automation
- [![Selenium][Selenium.dev]][Selenium-url]
- [![Cucumber][Cucumber.io]][Cucumber-url]
- [![JUnit][JUnit.org]][JUnit-url]

#### Supporting Tools
- WebDriverManager
- dotenv (environment configuration)


<p align="right">(<a href="#readme-top">back to top</a>)</p>

---
<!-- GETTING STARTED -->
## Getting Started

These instructions will help you set up and run the project locally on your machine.



### Prerequisites

Before you begin, make sure you have the following installed:

- **Java 11** (or later)  
  [Download Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Maven**  
  [Install Maven](https://maven.apache.org/install.html)
- A modern **web browser** (e.g., Chrome, Firefox)
- An IDE is optional but recommended (e.g., IntelliJ IDEA)

---

### Installation

Follow these steps to set up the project locally and run automated tests:

1.  **Clone the Repository**

    Open a terminal and run:

    ``` Bash
    git clone https://github.com/Smonk12/Testing_Good_Behavior.git 
    cd Testing_Good_Behavior
    ```
2.  **Set Up the FastAPI Local Website**

    Your tests run against a local FastAPI website. 

    Follow these steps:

    -   Make sure you have **Python 3.8+** installed.
    
    -   Clone the FastAPI repository (or use your local copy if already available):
    ``` Bash
    git clone https://github.com/fastapi/fastapi.git 
    cd fastapi
    ```
    -   (Optional) Create a virtual environment:

    ``` Bash
    python -m venv venv source venv/bin/activate  # Linux / Mac venv\Scripts\activate   # Windows
    ```
    -   Install required dependencies:
    ``` Bash
    pip install -r requirements.txt
    ```
    -   Start the FastAPI server locally (default runs on `http://127.0.0.1:8000`):
    ``` Bash
    uvicorn main:app --reload
    ```
    -   Keep this server running while executing your tests.
    
    
3.  **Set Up Java and Maven**
4. 
    Your test automation project requires **Java 11+** and **Maven**:

    -   Make sure **Java 11 or later** is installed:
        [Download Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

    -   Make sure **Maven** is installed:
        [Install Maven](https://maven.apache.org/install.html)


4.  **Configure Environment Variables (Optional)**

    Your project uses `dotenv` for environment configuration. 

    Create a `.env` file in the project root if needed, for example:
    
    ``` Bash
    BASE_URL=http://127.0.0.1:8000
    ```
5.  **Run the Tests**
    Back in your project root (`Testing_Good_Behavior`), execute:

    ``` Bash
    mvn clean test
    ```
    
    This will run all automated test scenarios using **JUnit5**, **Cucumber**, and **Selenium**.


6.  **View Test Results**

    Maven outputs results to the console. 

    You can also configure reports in `pom.xml` or use Cucumber reporting plugins for detailed HTML reports.

* * *

💡 **Tip:**

Always make sure the FastAPI server is running before executing the tests, or all browser-based tests will fail.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->

## Usage

This project is designed to automatically test a local FastAPI website using **BDD-style Cucumber scenarios** and **Selenium WebDriver**.  

### Running All Tests 

From the project root, execute: 

``` Bash
mvn clean test
```

This will run **all automated test scenarios** and display the results in the console.

### Running Specific Scenarios

You can run only scenarios with specific tags.

For example, to run tests related to login functionality:

``` Bash
mvn test -Dcucumber.filter.tags="@login"
```

### Test Reports

By default, Maven outputs results to the console.

For more detailed reports (HTML), you can configure the **Cucumber reporting plugin** in `pom.xml`. 

After running tests, open:

``` Bash
target/cucumber-reports/index.html
```

This report includes each scenario’s status, steps, and screenshots (if configured).

### Environment

The project uses `.env` for environment variables:

``` env
BASE_URL=http://127.0.0.1:8000
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP 
## Roadmap

- [x] Add Changelog

<p align="right">(<a href="#readme-top">back to top</a>)</p>
-->


### Contributing:

<a href="https://github.com/Smonk12/Testing_Good_Behavior/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Smonk12/Testing_Good_Behavior" alt="contrib.rocks image" />
</a>

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE 
## License

Blank License

<p align="right">(<a href="#readme-top">back to top</a>)</p>
-->


<!-- CONTACT -->
## Contact

Bácsik Szabolcs - bacsikszabi@gmail.com

Project Link: [https://github.com/Smonk12/Testing_Good_Behavior](https://github.com/Smonk12/Testing_Good_Behavior)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [FastAPI](https://fastapi.tiangolo.com/) – Web framework your tests run against
* [Selenium](https://www.selenium.dev/) – Browser automation
* [Cucumber](https://cucumber.io/docs/) – BDD and readable feature files
* [JUnit5](https://junit.org/junit5/) – Test execution
* [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) – Automatic driver management
* [Java dotenv](https://github.com/cdimascio/java-dotenv) – Environment configuration
* [Img Shields](https://shields.io) - Dynamic badges
* [GitHub Markdown Cheatsheet](https://guides.github.com/features/mastering-markdown/) - Easier Markdown editing

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/Smonk12/Testing_Good_Behavior.svg?style=for-the-badge
[contributors-url]: https://github.com/Smonk12/Testing_Good_Behavior/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Smonk12/Testing_Good_Behavior.svg?style=for-the-badge
[forks-url]: https://github.com/Smonk12/Testing_Good_Behavior/network/members
[stars-shield]: https://img.shields.io/github/stars/Smonk12/Testing_Good_Behavior.svg?style=for-the-badge
[stars-url]: https://github.com/Smonk12/Testing_Good_Behavior/stargazers
[issues-shield]: https://img.shields.io/github/issues/Smonk12/Testing_Good_Behavior.svg?style=for-the-badge
[issues-url]: https://github.com/Smonk12/Testing_Good_Behavior/issues
[license-shield]: https://img.shields.io/github/license/Smonk12/Testing_Good_Behavior.svg?style=for-the-badge
[license-url]: https://github.com/Smonk12/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/b%C3%A1csik-szabolcs-6150b735b/
[product-screenshot]: images/screenshot.png

[Selenium.dev]: https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white
[Selenium-url]: https://www.selenium.dev/documentation/

[Java.com]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white
[Java-url]: https://www.oracle.com/java/

[Maven.apache]: https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white
[Maven-url]: https://maven.apache.org/

[Cucumber.io]: https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white
[Cucumber-url]: https://cucumber.io/docs/

[JUnit.org]: https://img.shields.io/badge/JUnit%205-25A162?style=for-the-badge&logo=junit5&logoColor=white
[JUnit-url]: https://junit.org/junit5/
