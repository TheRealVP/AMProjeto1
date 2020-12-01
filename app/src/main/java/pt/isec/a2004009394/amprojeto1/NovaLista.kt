package pt.isec.a2004009394.amprojeto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.room.Room
import db.ComprasDatabase
import db.DBItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class NovaLista : AppCompatActivity() {
    var lista_criada by Delegates.notNull<Boolean>()
    var n_lista by Delegates.notNull<Long>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_lista)
    }

    suspend fun addToList(view: View)
    {
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME)
            .build()
        var id= findViewById<EditText>(R.id.item_id)
        var quant= findViewById<EditText>(R.id.item_quant)
        var item: DBItem? = db.comprasDBDao.get(id.text.toString().toLong())
        if(lista_criada==false)
        {

            db.comprasDBDao.newList()
            if(item != null)
            {
                lista_criada=true
                var listaid : Long = db.comprasDBDao.getMaxLista()
                n_lista= listaid
                db.comprasDBDao.atualizaListaPreco(n_lista, item.preco*quant.text.toString().toInt())
                db.comprasDBDao.addToLista(item.itemId,n_lista,quant.text.toString().toInt())
            }
        }
        else
        {
            if(item != null) {
                db.comprasDBDao.atualizaListaPreco(n_lista, item.preco * quant.text.toString().toInt())
                db.comprasDBDao.addToLista(item.itemId,n_lista,quant.text.toString().toInt())
            }
        }
    }

    fun onNovaLista(view: View) {
        fun gravarItem(view: View) {
            GlobalScope.launch {
                addToList(view)
            }
        }
    }
}