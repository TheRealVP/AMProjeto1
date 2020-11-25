package pt.isec.a2004009394.amprojeto1


import android.content.ClipData
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATABASE_NAME = "listadb.db"
const val DATABASE_VERSION = 1

class MyDBHelper(context : Context) :
    SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    val createItems = "CREATE TABLE items ( id NUMBER, nome TEXT, marca TEXT, unidade TEXT);"
    val createListas = "CREATE TABLE listas(id NUMBER, data DATE);"
    val createItemLista = "CREATE TABLE items_listas(id_lista NUMBER, id_item NUMBER, num_item NUMBER);"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createItems)
        db?.execSQL(createListas)
        db?.execSQL(createItemLista)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

    fun insereItem(item : Item)
    {

    }
}


