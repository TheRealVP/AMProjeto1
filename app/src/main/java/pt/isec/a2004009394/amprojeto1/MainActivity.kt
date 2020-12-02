package pt.isec.a2004009394.amprojeto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class Item(id: Int, nome:String, marca:String, unidade:String)
{
    val id= id
    val nome=nome
    val marca= marca
    val unidade= unidade
    val notas= null

}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onGerirItems(view: View) {
        val intent = Intent(this,GerirItems::class.java)
        startActivity(intent)
    }
    fun onNovaLista(view: View) {
        val intent = Intent(this,NovaLista::class.java)
        startActivity(intent)
    }
    fun onEscolherLista(view: View) {
        val intent = Intent(this,EscolherLista::class.java)
        startActivity(intent)
    }
}