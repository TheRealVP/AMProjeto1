package pt.isec.a2004009394.amprojeto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class GerirItems : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerir_items)
    }

    fun onAdicionarItem(view: View) {}
    fun onEditarItem(view: View) {}
    fun onApagarItem(view: View) {}
}