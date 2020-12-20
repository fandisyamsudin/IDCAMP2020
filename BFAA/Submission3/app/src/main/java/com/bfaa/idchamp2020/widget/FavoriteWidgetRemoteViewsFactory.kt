package com.bfaa.idchamp2020.widget

import android.content.Context
import android.database.Cursor
import android.os.Binder
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.net.toUri
import com.bfaa.idchamp2020.R
import com.bfaa.idchamp2020.model.UserGithub
import com.bfaa.idchamp2020.util.USER_CONTENT_URI
import com.bfaa.idchamp2020.util.toBitmap
import com.bfaa.idchamp2020.util.toListUserEntity

internal class FavoriteWidgetRemoteViewsFactory(private val mContext: Context) :
    RemoteViewsService.RemoteViewsFactory {
    private var list: List<UserGithub> = listOf()
    private var cursor: Cursor? = null
    override fun onCreate() {}
    override fun onDataSetChanged() {
        cursor?.close()
        val identityToken = Binder.clearCallingIdentity()
        cursor = mContext.contentResolver?.query(USER_CONTENT_URI.toUri(), null, null, null, null)
        cursor?.let {
            list = it.toListUserEntity()
        }
        Binder.restoreCallingIdentity(identityToken)
    }
    override fun onDestroy() {
        cursor?.close()
        list = listOf()
    }
    override fun getCount(): Int = list.size
    override fun getViewAt(position: Int): RemoteViews? {
        val views = RemoteViews(mContext.packageName, R.layout.favorite_widget_item)
        if (!list.isNullOrEmpty()) {
            views.apply {
                list[position].apply {
                    setImageViewBitmap(
                        R.id.iv_avatar_favorite, avatar_url?.toBitmap(mContext)
                    )
                    setTextViewText(
                        R.id.tv_name_favorite, name ?: login
                    )
                }
            }
        }

        return views
    }

    override fun getLoadingView(): RemoteViews? = null
    override fun getViewTypeCount(): Int = 1
    override fun getItemId(position: Int): Long = 0
    override fun hasStableIds(): Boolean = true
}