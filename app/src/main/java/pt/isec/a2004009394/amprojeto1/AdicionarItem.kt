package pt.isec.a2004009394.amprojeto1

import android.content.ContentValues
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import db.*

class AdicionarItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_item)

        val value: Item = Item (2,"Teste", "Testex","Testometro")

    }

    fun gravarItem(view: View) {
        if(applicationContext!=null)
        {
            val dbh= MyDBHelper(applicationContext)
            val db= dbh.writableDatabase
            val nome= R.id.item_nome
            val marca = R.id.item_marca
            val unidade = R.id.item_unidade
            val values = ContentValues()
            var item : DBItem
            insertItem(item)
            values.put("id",1)
            values.put("nome",nome)
            values.put("marca",marca)
            values.put("unidade",unidade)
            db.insert("Items",null, values)

        }
        checkResult()
    }
    fun checkResult() {
        var sb: String
        sb = ""
        if (applicationContext != null) {
            val dbHelper = MyDBHelper(applicationContext!!)

            val db = dbHelper.readableDatabase

            val cursor: Cursor = db.query("items",
                    null, null, null,
                    null, null, null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                sb += ("Id: ${cursor.getInt(0)}; " +
                        "nome: ${cursor.getString(1)}; " +
                        "marca: ${cursor.getString(2)}" +
                        "unidade: ${cursor.getString(3)}\n")
                cursor.moveToNext()
            }
            Log.i("result", "$sb")
        }
    }
    fun getID()
    {

    }
}