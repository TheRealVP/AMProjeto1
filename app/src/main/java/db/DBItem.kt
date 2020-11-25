package db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
data class Item(
        @PrimaryKey(autoGenerate = true)
        var itemId: Long = 0L,

        @ColumnInfo(name = "nome")
        val nome: String = "",

        @ColumnInfo(name = "marca")
        var marca: String = "",

        @ColumnInfo(name = "unidade")
        var unidade: String = "",

        @ColumnInfo(name = "preco")
        var preco: Int = 0
)