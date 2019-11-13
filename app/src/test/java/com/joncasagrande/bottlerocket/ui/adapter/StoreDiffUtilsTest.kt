package com.joncasagrande.bottlerocket.ui.adapter

import android.os.Bundle
import com.joncasagrande.bottlerocket.CITY
import com.joncasagrande.bottlerocket.NAME
import com.joncasagrande.bottlerocket.PHONE
import com.joncasagrande.bottlerocket.PICTURE
import com.joncasagrande.bottlerocket.model.Store
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class StoreDiffUtilsTest {

    lateinit var storeDiffUtils: StoreDiffUtils
    val store = Store(1,"address","city","Store","10101","logo","123456789","10/12/1111",0.0,0.0)
    val store1 = Store(1,"address1","city1","Store1","101011","logo1","1234567891","10/12/11111",1.0,1.0)
    val storeClone = Store(1,"address","city","Store","10101","logo","123456789","10/12/1111",0.0,0.0)
    val store1Name = Store(1,"address","city","Store","10101","logo","123456789","10/12/1111",0.0,0.0)
    val store1City = Store(1,"address","city","Store","10101","logo","123456789","10/12/1111",0.0,0.0)
    val store1Logo = Store(1,"address","city","Store","10101","logo","123456789","10/12/1111",0.0,0.0)

    val list = listOf(store, store1, storeClone)
    val contactList= listOf(store, store1,store1Name,store1City,store1Logo)
    @Before
    fun setUp() {
        storeDiffUtils = StoreDiffUtils(list,contactList)
    }

    @Test
    fun areItemsTheSame() {
        assertTrue(storeDiffUtils.areItemsTheSame(2,0))
    }
    @Test
    fun areDifferentItems() {
        assertFalse(storeDiffUtils.areItemsTheSame(2,1))
    }

    @Test
    fun getOldListSize() {
        assertEquals(list.size,storeDiffUtils.oldList.size)
    }

    @Test
    fun getNewListSize() {
        assertEquals(contactList.size,storeDiffUtils.newList.size)
    }

    @Test
    fun areContentsTheSame() {
        assertTrue(storeDiffUtils.areContentsTheSame(2,0))
    }
    @Test
    fun areContentsNotTheSame() {
        assertFalse(storeDiffUtils.areContentsTheSame(2,1))
    }

    @Test
    fun getChangePayloadName() {
        val bundle = storeDiffUtils.getChangePayload(1,2) as Bundle
        Assert.assertNotNull(bundle.getString(NAME))
    }
    @Test
    fun getChangePayloadPhone() {

        val bundle = storeDiffUtils.getChangePayload(1,3)as Bundle
        Assert.assertNotNull(bundle.getString(PHONE))
    }

    @Test
    fun getChangePayloadCity() {

        val bundle = storeDiffUtils.getChangePayload(1,3)as Bundle
        Assert.assertNotNull(bundle.getString(CITY))
    }

    @Test
    fun getChangePayloadPic() {

        val bundle = storeDiffUtils.getChangePayload(1,4) as Bundle
        Assert.assertNotNull(bundle.getBoolean(PICTURE))
    }
}