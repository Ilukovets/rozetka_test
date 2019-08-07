package pages;

import com.codeborne.selenide.*;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage extends ParentPage
{
    private List<String> titleList;

    // Block with goods

    private SelenideElement blockWithGoods
        = $("#block_with_goods");

    private ElementsCollection goodsBlocks
        = blockWithGoods.$$("div.g-i-tile > div.over-wraper");

    private String goodsTitle
        = "div.g-i-tile-i-title > a";
    private String goodsPrice
        = "div.g-price-uah";
    private String goodsTopSeles
        = "i.g-tag-icon-small-popularity";

    public List<String> selectAllGoodsTitlesOnPage()
    {
        titleList
            = new ArrayList<>();

        log.info("select goods titles...");

        // the end of loading of goods through ajax
        progressBar.shouldBe(hidden);
        // takes the names of all goods and checks that there are 32 of them.
        int countGoods = goodsBlocks.shouldHaveSize(32).size();
        for (int numberGoods = 0; numberGoods < countGoods; numberGoods++)
        {
            // takes a goods block
            currentElement = goodsBlocks.get(numberGoods);
            // takes the goods name
            titleList.add(currentElement.find(goodsTitle).getText());
        }

        log.info("selected goods titles: " + titleList.toString());

        return titleList;
    }

    public List<String> selectAllTopSellGoodsTitlesOnPage()
    {
        titleList
            = new ArrayList<>();

        log.info("select top sell goods titles...");

        // the end of loading of goods through ajax
        progressBar.shouldBe(hidden);
        //takes the names of all products with a top sales label
        int countGoods = goodsBlocks.shouldHaveSize(32).size();
        for (int numberGoods = 0; numberGoods < countGoods; numberGoods++)
        {
            // takes a goods block
            currentElement = goodsBlocks.get(numberGoods);
            // check that the top goods
            if (element.isExists(currentElement.find(goodsTopSeles)))
            {
                currentElement.scrollTo();
                // takes a goods name
                titleList.add(currentElement.find(goodsTitle).getText());
            }
        }

        log.info("selected top sell goods titles: " + titleList.toString());

        return titleList;
    }

    public List<String> selectAllGoodsTitlesWithRightPriceOnPage(int from, int to)
    {
        titleList
            = new ArrayList<>();

        log.info("select goods titles with right price...");

        // the end of loading of goods through ajax
        progressBar.shouldBe(hidden);
        //takes the names of all products with a top sales label
        int countGoods = goodsBlocks.shouldHaveSize(32).size();
        for (int numberGoods = 0; numberGoods < countGoods; numberGoods++)
        {
            // takes a goods block
            currentElement = goodsBlocks.get(numberGoods);
            // check that the right price goods
            currentPrice = element.priceToInt(currentElement.find(goodsPrice));
            if (currentPrice >= from && currentPrice < to)
            {
                currentElement.scrollTo();
                // takes a goods name
                titleList.add(currentElement.find(goodsTitle).getText());
            }
        }

        log.info("selected goods titles with right price: " + titleList.toString());

        return titleList;
    }

    // Block with goods on catalog page //


    // Pagination on catalog page

    private SelenideElement paginator
        = $("ul[name=\"paginator\"]");

    private ElementsCollection paginatorPages
        = paginator.$$("li.paginator-catalog-l-i");

    public void selectPage(int page)
    {
        log.info("select page...");

        // finds the page number on the pagination and clicks on it
        paginatorPages.find(text("" + page)).click();

        log.info("selected page: " + page);
    }

    // Pagination on catalog page //
}
