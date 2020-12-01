package pt.isec.a2004009394.amprojeto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import db.ComprasDatabase
import db.DBItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EscolherLista : AppCompatActivity() {
    var orderby: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escolher_lista)

        selectByOrder()

    }

    suspend fun orderByNameAsc()
    {
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME)
                .build()
        val listAsc = db.comprasDBDao.getAllItemsByAscName()

    }

    suspend fun orderByNameDesc(lista_id : Long)
    {
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME)
                .build()
        val listAsc = mutableListOf<List<DBItem>>(db.comprasDBDao.getAllItemsByDescName())
        val item_ids= db.comprasDBDao.getLists(lista_id)

    }

    fun selectByOrder()
    {
        GlobalScope.launch {
            if(orderby==0)
                orderByNameAsc()
            if(orderby==1)
                orderByNameDesc(0)
        }
    }

    fun onNewOrder()
    {

    }
}