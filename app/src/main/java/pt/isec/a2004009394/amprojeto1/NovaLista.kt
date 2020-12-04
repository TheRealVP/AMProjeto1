package pt.isec.a2004009394.amprojeto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.room.Room
import db.ComprasDatabase
import db.DBItem
import db.DBLista
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class NovaLista : AppCompatActivity() {
    var lista_criada : Boolean = false
    var n_lista : Long = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_lista)
        lista_criada=false
    }

    suspend fun addToList(id: Long, quant: Int)
    {
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME)
            .build()
        Log.i("id", "$id")
        Log.i("quant", "$quant")
        var item: DBItem? = db.comprasDBDao.get(id)
        if(!lista_criada)
        {
            Log.i("listacriada", "not yet!")
            db.comprasDBDao.insertLista(DBLista(0,0))
            if(item != null)
            {
                Log.i("item", "not Null")
                lista_criada=true
                var listaid : Long = db.comprasDBDao.getMaxLista()
                n_lista= listaid
                Log.i("n_lista", "$n_lista")
                db.comprasDBDao.atualizaListaPreco(n_lista, item.preco*quant)
                db.comprasDBDao.addToLista(item.itemId,n_lista,quant)

            }
        }
        else
        {
            if(item != null) {
                db.comprasDBDao.atualizaListaPreco(n_lista, item.preco * quant)
                db.comprasDBDao.addToLista(item.itemId,n_lista,quant)
            }
        }
    }

    fun onNovaLista(view: View) {
            var id= findViewById<EditText>(R.id.item_id)
            var quant= findViewById<EditText>(R.id.item_quant)
            GlobalScope.launch {
                addToList(id.text.toString().toLong(),quant.text.toString().toInt())
            }
        }
    }
