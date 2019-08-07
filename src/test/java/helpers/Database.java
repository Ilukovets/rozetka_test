package helpers;

import java.io.File;
import java.sql.*;
import java.util.List;

public class Database extends ParentHelper implements AutoCloseable
{
    private Connection connection;

    public Database() throws Exception
    {
        Class.forName("org.sqlite.JDBC");

        connection = DriverManager.getConnection("jdbc:sqlite:" + new File("./data/goodsTitles.db").getAbsolutePath());
        connection.setAutoCommit(false);
    }

    public void addGoodsTitleList(List<String> goodsTitleList) throws Exception
    {

        connection.createStatement()
            .execute("DROP TABLE IF EXISTS main.goodsList;");

        connection.createStatement()
            .execute("CREATE TABLE main.goodsList (title TEXT);");

        connection.commit();

        for (String title: goodsTitleList)
        {
            connection.createStatement()
                .execute("INSERT INTO main.goodsList (title) "
                    +   "VALUES ('" + title + "');");
        }

        connection.commit();
    }

    @Override
    public void close() throws Exception
    {
        if (!connection.isClosed())
            close();
    }
}
