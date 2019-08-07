package tests;

import org.testng.annotations.*;

import java.io.File;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;

public class ScenarioTest extends ParentTest
{
    private List<String> goodsTitlesList1;
    private List<String> goodsTitlesList2;

    @Override
    @BeforeMethod
    public void beforeMethod() throws Exception
    {
        super.beforeMethod();

        goodsTitlesList1
            = new ArrayList<>();
        goodsTitlesList2
            = new ArrayList<>();

        open(URL);
    }

    @Test
    (
        testName
            = "scenario1",
        description
            = "choose from the first three pages of "
            + "search results the name of all devices.\n"
            + "write the results to a text file.\n"
            + "send this file by mailing list."
    )
    public void scenario1() throws Exception
    {
        // go to the category
        generalPage
            .clickOnSidebarMenuItemByTitle("Смартфоны, ТВ и электроника");
        // go to the category
        categoryPage
            .clickOnSidebarMenuCategoryByTitle("Телефоны");
        // go to the category
        categoryPage
            .clickOnSidebarMenuCategoryByTitle("Смартфоны");

        for (int page = 1; page <= 3; page++)
        {
            // page selection
            catalogPage.selectPage(page);
            // selection of all goods names per page
            goodsTitlesList1.addAll(catalogPage
                .selectAllGoodsTitlesOnPage());
        }

        // write goods titles to txt file
        textWriter.stringListToTXT(
            goodsTitlesList1,
            "./data/goodsTitles.txt");

        // send emails
        emailWriter.sent(
            "scenario1",
            "text",
            new File("./data/goodsTitles.txt").getAbsolutePath());
    }

    @Test
    (
        testName
            = "scenario2",
        description
            = "choose from the first five pages of "
            + "search results the name and prices of "
            + "all products in the range from 100 to 300 UAH.\n"
            + "write the results to the database."
    )
    public void scenario2() throws Exception
    {
        // go to the category
        generalPage
            .clickOnSidebarMenuItemByTitle("Товары для дома");
        // go to the category
        categoryPage
            .clickOnSidebarMenuCategoryByTitle("Бытовая химия");
        // go to the category
        categoryPage
            .clickOnSidebarMenuCategoryByTitle("Средства для стирки");

        for (int page = 1; page <= 3; page++)
        {
            // page selection
            catalogPage.selectPage(page);
            // selection of all goods names on the page with the right price
            goodsTitlesList1.addAll(catalogPage
                .selectAllGoodsTitlesWithRightPriceOnPage(100, 300));
        }

        // insert goods titles to sqlite
        database.addGoodsTitleList(goodsTitlesList1);
    }

    @Test
    (
        testName
            = "scenario3",
        description
            = "choose from the first three pages of "
            + "search results the name and prices of "
            + "all devices designated as “Top Sales”.\n"
            + "choose from the first five pages of "
            + "search results the name and prices of "
            + "all products in the range from 3000 to 6000 UAH.\n"
            + "write the results in Excel file in descending order of "
            + "price on two different sheets.\n"
            + "send this file by mailing list."
    )
    public void scenario3() throws Exception
    {
        // go to the category
        generalPage
            .clickOnSidebarMenuItemByTitle("Смартфоны, ТВ и электроника");
        // go to the category
        categoryPage
            .clickOnSidebarMenuCategoryByTitle("Телефоны");
        // go to the category
        categoryPage
            .clickOnSidebarMenuCategoryByTitle("Смартфоны");

        for (int page = 1; page <= 5; page++)
        {
            // page selection
            catalogPage.selectPage(page);
            // if the page is less than third
            if (page <= 3)
            {
                // then we take the goods of top sales
                goodsTitlesList1.addAll(catalogPage
                    .selectAllTopSellGoodsTitlesOnPage());
            }
            // take ешеду of all goods with the right price
            goodsTitlesList2.addAll(catalogPage
                .selectAllGoodsTitlesWithRightPriceOnPage(3000, 6000));
        }

        // write goods titles to xlsx file
        excelWeiter.createTitleLists(
            List.of(goodsTitlesList1, goodsTitlesList2),
            "./data/goodsTitles.xlsx");

        // send emails
        emailWriter.sent(
            "scenario3",
            "text",
            new File("./data/goodsTitles.xlsx").getAbsolutePath());
    }
}
