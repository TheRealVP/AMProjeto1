package db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ComprasDBDao {

    @Insert
    suspend fun insertItem(item: DBItem)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param night new value to write
     */
    @Update
    suspend  fun update(night: DBItem)
    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key startTimeMilli to match
     */
    @Query("SELECT * from items WHERE itemId = :key")
    suspend fun get(key: Long): DBItem?
    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("INSERT INTO listas(preco_total) VALUES(0)")
    suspend fun newList()

    @Query("DELETE FROM items where itemID = :key")
    suspend fun clear(key: Long)

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM items ORDER BY itemId DESC")
    fun getAllItems(): List<DBItem>

    /**
     * Selects and returns the latest night.
     */
  //  @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
  //  suspend fun getTonight(): SleepNight?
}