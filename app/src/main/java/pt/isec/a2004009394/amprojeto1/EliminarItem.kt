package pt.isec.a2004009394.amprojeto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.room.Room
import db.ComprasDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EliminarItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_item)
    }

suspend fun eliminar (view : View)
{
    if(applicationContext!=null) {

        // val dbh= MyDBHelper(applicationContext)
        //val db = ComprasDatabase.getInstance(applicationContext)
        val db = Room.databaseBuilder(applicationContext!!, ComprasDatabase::class.java, DB_NOME)
                .build()
        var n  = findViewById<EditText>(R.id.item_id).text

        db.comprasDBDao.clear(n.toString().toLong())

    }
}
    fun onEliminar(view : View)
    {
        GlobalScope.launch {
            eliminar(view)
        }
    }
}