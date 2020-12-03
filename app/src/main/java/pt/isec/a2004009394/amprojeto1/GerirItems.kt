package pt.isec.a2004009394.amprojeto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class GerirItems : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerir_items)
    }

    fun onAdicionarItem(view: View) {
        val intent = Intent(this,AdicionarItem::class.java)
        startActivity(intent)
    }
    fun onEditarItem(view: View) {
        val intent = Intent(this,EditarItem::class.java)
        startActivity(intent)
    }
    fun onApagarItem(view: View) {}
}