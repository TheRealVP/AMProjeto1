package db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "lista_items")
data class DBLista_Items(
        @ColumnInfo(name = "listaId")
        var listaId: Long = 0L,

        @ColumnInfo(name ="itemId")
        var itemId: Long = 0L,

        @ColumnInfo(name = "quantidade")
        var quantidade: Int = 0
)
