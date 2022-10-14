package contactApplication

import contactApplication.dataBase.SQLiteConnector
import contactApplication.main.InitialView

fun main()
{
    SQLiteConnector.sqlConnection()
    InitialView.viewOption()
}

