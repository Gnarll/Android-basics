package com.example.androidbasics

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidbasics.data.InventoryDatabase
import com.example.androidbasics.data.Item
import com.example.androidbasics.data.ItemDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ItemDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase

    private val item1 = Item(1, "Apple", 10.0, 21)
    private val item2 = Item(2, "Banana", 15.0, 42)

    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }

    private suspend fun addTwoItemsToDb() {
        itemDao.insert(item1)
        itemDao.insert(item2)
    }

    @Before
    fun initializeDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        inventoryDatabase =
            Room.inMemoryDatabaseBuilder(context = context, klass = InventoryDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        itemDao = inventoryDatabase.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun destroyDb() {
        inventoryDatabase.close()

    }


    @Test
    @Throws(Exception::class)
    fun itemDao_InsertOneItemIntoDb() = runBlocking {
        addOneItemToDb()
        val items = itemDao.getAll().first()
        assertEquals(item1, items[0])
    }

    @Test
    @Throws(Exception::class)
    fun itemDao_GetAllItemsFromDb() = runBlocking {
        addTwoItemsToDb()
        val items = itemDao.getAll().first()
        assertEquals(item1, items[0])
        assertEquals(item2, items[1])
    }

    @Test
    @Throws(Exception::class)
    fun itemDao_UpdateItemsInDb() = runBlocking {
        addTwoItemsToDb()
        val item1Update = item1.copy(name = "Big apple")
        val item2Update = item2.copy(name = "Big banana")

        itemDao.update(item1Update)
        itemDao.update(item2Update)

        val allItems = itemDao.getAll().first()
        assertEquals(allItems[0], item1Update)
        assertEquals(allItems[1], item2Update)
    }
}