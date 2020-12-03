package db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ComprasDBDao {

    @Insert
    suspend fun insertItem(item: DBItem)

    @Insert
    suspend fun insertLista(lista: DBLista)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param night new value to write
     */
    @Update
    suspend  fun update(night: DBItem)

    @Query("INSERT INTO lista_items values(:listaId , :itemId, :quant)")
    suspend fun addToLista(itemId : Long, listaId: Long, quant:Int)
    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key startTimeMilli to match
     */
    @Query("UPDATE items SET nome=:nome, marca=:marca, unidade=:unidade, preco=:preco where itemId=:id")
    suspend fun updateItem(id : Long, nome: String, marca:String, unidade:String, preco:Int)

    @Query("UPDATE listas SET preco_total=preco_total+:preco WHERE listaId=:id ")
    suspend fun atualizaListaPreco(id:Long, preco:Int)

    @Query("SELECT * from items WHERE itemId = :key")
    suspend fun get(key: Long): DBItem?
    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("INSERT INTO listas(listaId,preco_total) VALUES(0,0)")
    suspend fun newList()

    @Query("SELECT MAX(listaId) FROM listas")
    suspend fun getMaxLista() : Long

    @Query("DELETE FROM items where itemID = :key")
    suspend fun clear(key: Long)

    @Query("SELECT itemId from LISTA_ITEMS where listaId=:lid")
    suspend fun getLists(lid: Long) : Long
    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM items ORDER BY itemId ASC")
    suspend fun getAllItems(): List<DBItem>


    @Query("SELECT * FROM listas ORDER BY listaId ASC")
    suspend fun getAllLists(): List<DBLista>

    @Query("SELECT * FROM items ORDER BY nome ASC")
    suspend fun getAllItemsByAscName(): List<DBItem>

    @Query("SELECT * FROM items ORDER BY nome DESC")
    suspend fun getAllItemsByDescName(): List<DBItem>
    /**
     * Selects and returns the latest night.
     */
  //  @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
  //  suspend fun getTonight(): SleepNight?
}