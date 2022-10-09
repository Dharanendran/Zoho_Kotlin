package practise

import java.sql.DriverManager
import java.sql.SQLException

object SqlConnection {

    fun sqlConnection() {
        Class.forName("org.sqlite.JDBC");
        val url = "jdbc:sqlite:/Users/dharan-pt6340/sqlite/test.db"
        try {
            val connection = DriverManager.getConnection(url)

            var first_name = "mahaveer"
            var last_name = "raj"
            var email = "mahaveer.com"
            var phone = "952617752"
            val sql =//"select * from contacts"
                "insert into contacts(contact_id,first_name,last_name,email,phone) values(10,\"dharan\",\"Appandairaj\",\"dhar@gmail.com\",\"63747751\");"

            val sql1="select * from contacts"
            val statement = connection.createStatement()
            val result = statement.executeQuery(sql1)
            while (result.next()) {
                val name = result.getString("contact_id")
                println(name)
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }
}