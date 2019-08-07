package helpers;

import com.codeborne.selenide.*;

import java.util.NoSuchElementException;

public class Element extends ParentHelper
{
    public int priceToInt(SelenideElement element)
    {
        return Integer.parseInt(
            element.getText()
                .replaceAll(" ", "")
                .replaceAll(" грн", ""));
    }

    public boolean isExists(SelenideElement element)
    {
        try
        {
            element.exists();
            return true;
        }
        catch (NoSuchElementException ex)
        {
            return false;
        }
    }
}
