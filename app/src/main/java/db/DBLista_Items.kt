package db

import androidx.room.ColumnInfo
import androidx.room.Entity



@Entity(tableName = "lista_items",primaryKeys = ["listaId", "itemId"])
data class DBLista_Items(

        var listaId: Long = 0L,
        var itemId: Long = 0L,

        @ColumnInfo(name = "quantidade")
        var quantidade: Int = 0
)
