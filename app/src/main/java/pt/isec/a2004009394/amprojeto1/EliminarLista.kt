package pt.isec.a2004009394.amprojeto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.room.Room
import db.ComprasDatabase
import db.DBLista
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EliminarLista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_lista)
        var nlistas : List<DBLista> = emptyList()
        GlobalScope.launch {

            nlistas = chooseList()
        }
        Thread.sleep(1000)
        val ids = getIDs(nlistas)
        val spinner = findViewById<Spinner>(R.id.spinnerListas3)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, ids)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

        }
    }

    suspend fun chooseList() : List<DBLista>
    {
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME).build()
        val x = db.comprasDBDao.getAllLists()
        for( toshi in x)
            Log.i("list", "${toshi.listaId}")
        return x
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

    fun onEliminar(view: View) {

        GlobalScope.launch {
            val spinner = findViewById<Spinner>(R.id.spinnerListas3)

            val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME).build()
            db.comprasDBDao.clearListaItem(spinner.selectedItem.toString().toLong())
            db.comprasDBDao.clearLista(spinner.selectedItem.toString().toLong())
        }
    }

}