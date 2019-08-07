package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.Element;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$;

class ParentPage
{
    protected SelenideElement currentElement;

    protected String currentTitle;
    protected int currentPrice;

    protected Logger log
        = Logger.getLogger(getClass());

    protected Element element
        = new Element();

    protected SelenideElement progressBar
        = $("progress-b progress-b-fixed");
}
