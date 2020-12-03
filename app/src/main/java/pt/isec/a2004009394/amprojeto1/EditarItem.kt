package pt.isec.a2004009394.amprojeto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.room.Room
import db.ComprasDatabase
import db.DBItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditarItem : AppCompatActivity() {
    lateinit var editNome:EditText
    lateinit var editUnidade:EditText
    lateinit var editMarca:EditText
    lateinit var editPreco:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_item)
        editNome = findViewById(R.id.item_nome2)
        editUnidade = findViewById(R.id.item_unidade2)
        editMarca = findViewById(R.id.item_marca2)
        editPreco = findViewById(R.id.item_preco2)
        var items : List<DBItem> = emptyList()


            GlobalScope.launch {
                items= printItems()
            }
        Thread.sleep(1000)
        if(items.isNotEmpty()) {
            val ids = getIDs(items)
            val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, ids)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                    editNome.setText(items.get(position).nome)
                    editMarca.setText(items.get(position).marca)
                    editUnidade.setText(items.get(position).unidade)
                    editPreco.setText(items.get(position).preco.toString())
                    //remakeItem(ids.get(position).toLong())
                    Toast.makeText(this@EditarItem,
                            ids.get(position), Toast.LENGTH_SHORT).show()

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

        }
        }
    }

    suspend fun printItems() : List<DBItem>
    {
        var items : List<DBItem> = emptyList()
        if(applicationContext!=null) {

            // val dbh= MyDBHelper(applicationContext)
            //val db = ComprasDatabase.getInstance(applicationContext)
            val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME)
                    .build()
            items=db.comprasDBDao.getAllItems()
            var string = mutableListOf<Long>()
            for(i in items)
            {
                Log.i("item","${i.itemId} ${i.nome} ${i.marca} ${i.preco} ${i.unidade} ")
                string.add(i.itemId)
            }

            }
        return items
    }

    fun getIDs(items: List<DBItem>) :Array<String>
    {

        val ret : MutableList<String> = MutableList(items.size,{ i-> "A" + i })
        var counter = 0
        for(i in items)
        {
            ret.set(counter, i.itemId.toString())
            counter= counter +1
            Log.i("setting","${counter}")
        }
        return ret.toTypedArray()
    }

    fun remakeItem(view: View)
    {
        GlobalScope.launch {
            val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME)
                    .build()
            val spinner = findViewById<Spinner>(R.id.spinner)

            db.comprasDBDao.updateItem(spinner.selectedItem.toString().toLong(),editNome.text.toString(),editMarca.text.toString(),editUnidade.text.toString(), editPreco.text.toString().toInt())
        }
    }
}