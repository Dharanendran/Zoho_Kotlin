package sqlitePractise

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

object SqlConnection {

    lateinit var  statement: Statement
    lateinit var  connection: Connection

    fun sqlConnection()
    {
        Class.forName("org.sqlite.JDBC");

        val url = "jdbc:sqlite:/Users/dharan-pt6340/sqlite/contactApplication.db"

        try
        {
            connection = DriverManager.getConnection(url)
            var stmt =
                """CREATE TABLE IF NOT EXISTS CONTACT(
                |"contact_id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
                |"name" TEXT NOT NULL, 
                |"email" TEXT NOT NULL UNIQUE, 
                |"phone" TEXT NOT NULL UNIQUE,
                |"doorNo" TEXT ,
                |"Street" TEXT ,
                |"address" TEXT ,
                |"pinCode" TEXT)""".trimMargin()

            statement = connection.createStatement()
            statement.executeUpdate(stmt)

        }
        catch (e: SQLException)
        {
            e.printStackTrace()
        }
    }
}