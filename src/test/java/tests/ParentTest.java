package tests;

import com.codeborne.selenide.Configuration;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import helpers.*;
import pages.*;

public class ParentTest
{
    protected GeneralPage generalPage;
    protected CategoryPage categoryPage;
    protected CatalogPage catalogPage;

    protected TextWriter textWriter;
    protected ExcelWriter excelWeiter;
    protected EmailWriter emailWriter;
    protected Element element;
    protected Database database;

    protected Logger log
        = Logger.getLogger(getClass());;

    protected String URL
        = "https://rozetka.com.ua/";

    private String testStert
        = "------------------------------";
    private String testSuiteEnd
        = "******************************";

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("file.encoding", "UTF-8");

        Configuration.timeout
            = 15000;
//        Configuration.headless
//            = true;
        Configuration.browser
            = "firefox";
    }

    @BeforeMethod
    public void beforeMethod() throws Exception
    {
        generalPage
            = new GeneralPage();
        categoryPage
            = new CategoryPage();
        catalogPage
            = new CatalogPage();

        textWriter
            = new TextWriter();
        excelWeiter
            = new ExcelWriter();
        emailWriter
            = new EmailWriter("i_alex_i@yahoo.com", "honcwubdwvfldueh");
        element
            = new Element();
        database
            = new Database();

        log.info(testStert);
    }

    @AfterClass
    public void afterClass()
    {
        log.info(testSuiteEnd);
    }
}
