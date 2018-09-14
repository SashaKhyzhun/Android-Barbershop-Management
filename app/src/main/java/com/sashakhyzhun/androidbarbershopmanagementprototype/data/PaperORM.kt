package com.sashakhyzhun.androidbarbershopmanagementprototype.data

import com.sashakhyzhun.androidbarbershopmanagementprototype.model.UserRequest
import io.paperdb.Paper
import java.util.*

/**
 * @author SashaKhyzhun
 * Created on 9/14/18.
 */
object PaperORM {

    /**
     * path to storage
     */
    private const val storage: String = "/storage/emulated/0"

    /**
     * book name
     */
    private const val book: String = "UserBook"
    /**
     * request name
     */
    private const val request: String = "UserRequest"



    fun storeUserRequest(name: String, regDay: Calendar, startHour: Int, endHour: Int) {
        Paper.book(book)
                .write(System.currentTimeMillis().toString(),
                       UserRequest(name, regDay, startHour, endHour))
    }

    fun getUserRequest(name: String): UserRequest = getBooksById().first { it.name == name }

    fun getUserRequest(regDate: Date): UserRequest = getBooksById().first { it.regDay == regDate }

    fun getBooksById(): List<UserRequest> {
        val requests = mutableListOf<UserRequest>()
        getAllRequests().forEach {
            requests.add(Paper.book(book).read(it, UserRequest("",
                    Calendar.getInstance(), 0, 0)))
        }
        return requests
    }

    fun deleteAll() = Paper.book(book).destroy()

    private fun getAllRequests(): List<String> = Paper.book(book).allKeys



}