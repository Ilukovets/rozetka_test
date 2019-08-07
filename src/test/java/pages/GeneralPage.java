package pages;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class GeneralPage extends ParentPage
{
    // Sidebar on general page

    private SelenideElement Sidebar
        = $("aside.sidebar.sidebar_type_main");

    private SelenideElement SidebarMenu
        = Sidebar.$("ul.menu-categories.menu-categories_type_main");

    private ElementsCollection SidebarMenuItem
        = SidebarMenu.$$("li.menu-categories__item > a");

    public void clickOnSidebarMenuItemByTitle(String title)
    {
        log.info("click on category...");

        SidebarMenuItem
            .find(text(title))
            .click();

        log.info("clicked on category: " + title);
    }

    // Sidebar on general page //
}
