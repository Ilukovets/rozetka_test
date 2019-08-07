package pages;

import com.codeborne.selenide.*;

import static org.testng.Assert.*;
import static com.codeborne.selenide.Selenide.*;

public class CategoryPage extends ParentPage
{
    // Sidebar on category page

    private SelenideElement Sidebar
        = $(".m-cat");

    private SelenideElement SidebarMenu
        = Sidebar.$("ul.m-cat-l");

    private ElementsCollection SidebarMenuCategory
        = SidebarMenu.$$("li.m-cat-l-i");

    private String CategoryTitle
        = "a.m-cat-l-i-title-link";

    public void clickOnSidebarMenuCategoryByTitle(String title)
    {
        log.info("click on category...");

        int countCategories = SidebarMenuCategory.size();
        for (int numberCategory = 0; numberCategory < countCategories; numberCategory++)
        {
            currentElement = SidebarMenuCategory.get(numberCategory);
            currentElement = currentElement.find(CategoryTitle);
            currentTitle = currentElement.getText();
            if (currentTitle.equals(title))
            {
                currentElement.click();

                log.info("clicked on category: " + title);

                return;
            }
        }

        fail("category not found");
    }

    // Sidebar on category page //
}
