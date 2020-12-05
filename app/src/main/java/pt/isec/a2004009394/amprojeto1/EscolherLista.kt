package pt.isec.a2004009394.amprojeto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.room.Room
import db.ComprasDatabase
import db.DBItem
import db.DBLista
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class EscolherLista : AppCompatActivity() {

    class ItemQuant(x : DBItem, q: Int)
    {
        var item : DBItem = x
        var quant : Int = q

        fun print() : String
        {
            return "${item.nome} ${item.marca} ${item.preco}€ $quant ${item.unidade}"
        }
    }
    var orderby: Int = 0
    lateinit var listas: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escolher_lista)
        listas = findViewById<TextView>(R.id.textListas)
        selectByOrder()

    }
    suspend fun chooseList() : List<DBLista>
    {
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME).build()
        val x = db.comprasDBDao.getAllLists()
        for( toshi in x)
            Log.i("list", "${toshi.listaId}")
        return x
    }
    suspend fun orderByNameAsc(lista_id : Long) : String
    {
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME)
                .build()
        
        val listAsc = db.comprasDBDao.getAllItemsByAscName()
        val item_ids= db.comprasDBDao.getLists(lista_id)
        val itemsInList=  ArrayList<ItemQuant>()
        val total= db.comprasDBDao.getPrecoTotal(lista_id)
        var p= listAsc.iterator()
        while(p.hasNext())
        {
            var x= p.next()
            for(it in item_ids) {
                if (x.itemId == it.itemId) {
                    itemsInList.add(ItemQuant(x, it.quantidade))
                }
            }

        }
        return printList(itemsInList, total)

    }
    suspend fun orderByNameDesc(lista_id : Long) :String
    {
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME)
                .build()
        val listAsc = db.comprasDBDao.getAllItemsByDescName()
        val item_ids= db.comprasDBDao.getLists(lista_id)
        val total= db.comprasDBDao.getPrecoTotal(lista_id)
        val itemsInList=   ArrayList<ItemQuant>()
        var p= listAsc.iterator()
        while(p.hasNext())
            {
                var x= p.next()
                for(it in item_ids) {
                    if (x.itemId == it.itemId) {
                        itemsInList.add(ItemQuant(x, it.quantidade))
                    }
                }
            }
        return printList(itemsInList, total)

    }

    fun printList(a : ArrayList<ItemQuant>, total :Int) :String
    {
        var ret : String =""
        for( i in a)
        {
            Log.i("item",i.print())
            ret = ret + i.print() + "\n"
        }
        Log.i("total",total.toString())
        ret = ret + "Total: €" + total.toString()
        Log.i("ret",ret)
        return ret
    }
    fun selectByOrder()
    {
        var nlistas : List<DBLista> = emptyList()
        GlobalScope.launch {

            nlistas = chooseList()
        }
        Thread.sleep(1000)
        val ids = getIDs(nlistas)
        val spinner = findViewById<Spinner>(R.id.spinnerListas)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, ids)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    var listtext : String = ""
                    listas.setText("loading...")
                    GlobalScope.launch {
               if(orderby==0)
                   listtext  = orderByNameAsc(ids.get(position).toLong())
            if(orderby==1)
                listtext  =orderByNameDesc(ids.get(position).toLong())
                    Log.i("listtext", listtext)}
                    Thread.sleep(1000)
                    listas.setText(listtext)
                    //remakeItem(ids.get(position).toLong()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

        }
    }

    fun getIDs(items: List<DBLista>) :Array<String>
    {

        val ret : MutableList<String> = MutableList(items.size,{ i-> "A" + i })
        var counter = 0
        for(i in items)
        {
            ret.set(counter, i.listaId.toString())
            counter= counter +1
        }
        return ret.toTypedArray()
    }
}


