package com.example.notes.mynotes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.notes.mynotes.database.firebase.AppFirebaseRepository
import com.example.notes.mynotes.database.room.AppRoomDatabase
import com.example.notes.mynotes.database.room.AppRoomRepository
import com.example.notes.mynotes.utilits.REPOSITORY
import com.example.notes.mynotes.utilits.TYPE_FIREBASE
import com.example.notes.mynotes.utilits.TYPE_ROOM
import com.example.notes.mynotes.utilits.showToast

class StartFragmentViewModel(application: Application):AndroidViewModel(application) {
    private val mContext = application

    fun initDatabase(type:String,onSuccess:()->Unit) {
        when(type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }

            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase({onSuccess()},{ showToast(it)})
            }
        }
    }
}