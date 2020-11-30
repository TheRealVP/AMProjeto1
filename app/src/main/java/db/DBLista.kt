package db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "listas")
data class DBLista(
    @PrimaryKey(autoGenerate = true)
    var listaId: Long = 0L,

    @ColumnInfo(name = "preco_total")
    var preco_total: Int = 0
)
