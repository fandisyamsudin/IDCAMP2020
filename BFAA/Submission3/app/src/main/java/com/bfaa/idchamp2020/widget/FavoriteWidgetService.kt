package com.bfaa.idchamp2020.widget

import android.content.Intent
import android.widget.RemoteViewsService

class FavoriteWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(p0: Intent?): RemoteViewsFactory =
        FavoriteWidgetRemoteViewsFactory(this.applicationContext)
}