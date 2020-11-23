package pt.isec.a2004009394.amprojeto1

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

    fun onGerirItems(view: View) {}
    fun onNovaLista(view: View) {}
    fun onEscolherLista(view: View) {}
}