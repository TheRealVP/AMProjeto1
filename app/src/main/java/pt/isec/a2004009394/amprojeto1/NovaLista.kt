package pt.isec.a2004009394.amprojeto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.room.Room
import db.ComprasDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class NovaLista : AppCompatActivity() {
    var lista_criada by Delegates.notNull<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_lista)
    }

    suspend fun addToList(view: View)
    {
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME)
            .build()

        if(lista_criada==false)
        {
            lista_criada=true
            db.comprasDBDao.newList()
            var id= findViewById<EditText>(R.id.item_id)
        }
        else
        {

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