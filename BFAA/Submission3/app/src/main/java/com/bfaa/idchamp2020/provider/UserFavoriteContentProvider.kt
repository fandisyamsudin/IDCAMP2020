package com.bfaa.idchamp2020.provider

import android.app.Application
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.core.net.toUri
import com.bfaa.idchamp2020.database.AppDatabase
import com.bfaa.idchamp2020.util.DATABASE_AUTHORITY
import com.bfaa.idchamp2020.util.USER_CONTENT_URI
import com.bfaa.idchamp2020.util.USER_TABLE_NAME
import com.bfaa.idchamp2020.util.cursorToUserGithub
import com.bfaa.idchamp2020.widget.FavoriteWidget
import dagger.android.DaggerContentProvider
import javax.inject.Inject

class UserFavoriteContentProvider : DaggerContentProvider() {
    @Inject
    lateinit var db: AppDatabase

    @Inject
    lateinit var application: Application

    companion object {
        private const val USER = 1
        private const val USER_ID = 2


        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(DATABASE_AUTHORITY, USER_TABLE_NAME, USER)
            uriMatcher.addURI(DATABASE_AUTHORITY, "$USER_TABLE_NAME/#", USER_ID)
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val deleted: Int = when (USER_ID) {
            uriMatcher.match(uri) -> uri.lastPathSegment?.toInt()?.let {
                db.getUserDao().deleteUser(
                    it
                )
            } ?: 0
            else -> 0
        }

        context?.contentResolver?.notifyChange(USER_CONTENT_URI.toUri(), null)
        FavoriteWidget.updateWidget(application)
        return deleted
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val added: Long = when (USER) {
            uriMatcher.match(uri) -> values?.cursorToUserGithub()?.let {
                db.getUserDao().insertUser(
                    it
                )
            } ?: 0
            else -> 0
        }
        context?.contentResolver?.notifyChange(USER_CONTENT_URI.toUri(), null)
        FavoriteWidget.updateWidget(application)
        return Uri.parse("$USER_CONTENT_URI/$added")
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            USER -> db.getUserDao().getUsers()
            USER_ID -> uri.lastPathSegment?.toInt()?.let { db.getUserDao().getUserById(it) }
            else -> null
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}
