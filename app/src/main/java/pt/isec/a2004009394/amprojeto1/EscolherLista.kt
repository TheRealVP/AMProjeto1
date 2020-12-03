package pt.isec.a2004009394.amprojeto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    suspend fun chooseList()
    {
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME).build()
        val x = db.comprasDBDao.getAllLists()
        for( toshi in x)
            Log.i("list", "${toshi.listaId}")
    }
    suspend fun orderByNameAsc(lista_id : Long)
    {
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME)
                .build()
        
        val listAsc = db.comprasDBDao.getAllItemsByAscName()
        val item_ids= db.comprasDBDao.getLists(lista_id)
        val itemsInList=  ArrayList<DBItem>()
        var p= listAsc.iterator()
        while(p.hasNext())
        {
            var x= p.next()
            item_ids.forEach {
                if (x.itemId == item_ids.toLong())
                    itemsInList.add(x)
            }

        }
        printList(itemsInList)

    }

    suspend fun orderByNameDesc(lista_id : Long)
    {
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME)
                .build()
        val listAsc = db.comprasDBDao.getAllItemsByDescName()
        val item_ids= db.comprasDBDao.getLists(lista_id)
        val itemsInList=  ArrayList<DBItem>()
        var p= listAsc.iterator()
        while(p.hasNext())
            {
                var x= p.next()
                item_ids.forEach {
                    if (x.itemId == item_ids.toLong())
                        itemsInList.add(x)
                }

            }
        printList(itemsInList)
    }
    fun printList(a : ArrayList<DBItem>)
    {
        for( i in a)
        {
            Log.i("item","${i.itemId} ${i.nome} ${i.marca} ${i.unidade}")
        }
    }
    fun selectByOrder()
    {
        GlobalScope.launch {
/*            if(orderby==0)
                orderByNameAsc(1)
            if(orderby==1)
                orderByNameDesc(1)*/
            chooseList()
        }
    }

    fun onNewOrder()
    {

    }
}

private fun Long.forEach(any: Any) {

}
