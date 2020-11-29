package pt.isec.a2004009394.amprojeto1

import ComprasDatabase
import android.content.ContentValues
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.room.Room
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX.Companion.init
import db.*
import kotlinx.android.synthetic.main.activity_adicionar_item.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdicionarItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_item)


    }

    suspend fun saveItem() {
        if(applicationContext!=null)
        {

           // val dbh= MyDBHelper(applicationContext)
           val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, "myroomdb.db")
                    .build()


           // val dbdao: ComprasDBDao
           // dbdao= ComprasDatabase.getInstance(application).comprasDBDao
            val n= R.id.item_nome
            val m = R.id.item_marca
            val u = R.id.item_unidade
            val values = ContentValues()
            val item : DBItem
            item = DBItem (0, n.toString(),m.toString(),u.toString(),0)

        //    item.nome = nome.toString()
            db.comprasDBDao.insertItem(item)/*
            values.put("id",1)
            values.put("nome",nome)
            values.put("marca",marca)
            values.put("unidade",unidade)
            db.insert("Items",null, values)*/

        }
        checkResult()
    }
    fun checkResult() {
        var sb: String
        sb = ""
        if (applicationContext != null) {

            val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, "myroomdb.db")
                    .build()
       //     val dbdao: ComprasDBDao
       //     dbdao= ComprasDatabase.getInstance(application).comprasDBDao

            var p= db.comprasDBDao.getAllItems()

            for (x in p) {
                sb += ("Id: ${x.itemId}; " +
                        "nome: ${x.nome}; " +
                        "marca: ${x.marca}" +
                        "preco: ${x.preco}" +
                        "unidade: ${x.unidade}\n")

            }
            Log.i("result", "$sb")
        }
    }
    fun getID()
    {

    }

    fun gravarItem(view: View) {
        GlobalScope.launch {
            saveItem()
        }

    }
}