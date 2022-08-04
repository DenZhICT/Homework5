package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitSoftTest {
    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition = "0x0";
    }
    //- Откройте страницу Selenide в Github
    @Test //усложнил себе задачу, чтобу чуть лучше разобраться со всем. Хоть по факту это было в лекции, но я решил написать это с нуля.
    void selenideOpen() {
        open("/");
        // вбить selenide
        $("[data-test-selector=nav-search-input]").setValue("Selenide").pressEnter();
        // выбрать первый из списка
        $("ul.repo-list li a").click();
        //убедиться что это selenide/selenide
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
    }

    @Test
    void findJUnit5() {
        //- Откройте страницу Selenide в Github
        open("/selenide/selenide");
        //- Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();
        //- Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $(".js-wiki-more-pages-link").click();
        //- Откройте страницу SoftAssertions,
        $("#wiki-pages-box").find(new ByText("SoftAssertions")).click();
        // проверьте что внутри есть пример кода для JUnit5
        $(".markdown-body").shouldHave(text("3. Using JUnit5 extend test class:"));
    }
}
