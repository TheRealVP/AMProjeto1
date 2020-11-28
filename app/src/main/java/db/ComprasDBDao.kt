package db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

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
    @Query("DELETE FROM items")
    suspend fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM items ORDER BY itemId DESC")
    fun getAllNights(): LiveData<List<DBItem>>

    /**
     * Selects and returns the latest night.
     */
  //  @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
  //  suspend fun getTonight(): SleepNight?
}