package com.bfaa.idchamp2020.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.net.toUri
import com.bfaa.idchamp2020.R
import com.bfaa.idchamp2020.ui.main.MainActivity


class FavoriteWidget : AppWidgetProvider() {
    companion object {
        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val intent = Intent(context, FavoriteWidgetService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                data = toUri(Intent.URI_INTENT_SCHEME).toUri()
            }
            val tittleIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val tittlePendingIntent = PendingIntent.getActivity(context, 0, tittleIntent, 0)
            val views = RemoteViews(context.packageName, R.layout.favorite_widget).apply {
                setRemoteAdapter(R.id.stack_view, intent)
                setEmptyView(R.id.stack_view, R.id.empty_view)
                setTextViewText(
                    R.id.tv_title_widget,
                    context.getString(R.string.favorite)
                )
                setTextViewText(
                    R.id.tv_tittle,
                    context.getString(R.string.sorry)
                )
                setTextViewText(
                    R.id.tv_message,
                    context.getString(R.string.not_found_favorite)
                )
                setOnClickPendingIntent(
                    R.id.tv_title_widget,
                    tittlePendingIntent
                )
            }
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        fun updateWidget(context: Context) {
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE).apply {
                component = ComponentName(context, FavoriteWidget::class.java)
            }
            context.sendBroadcast(intent)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (it.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
                val component = context?.let { context ->
                    ComponentName(
                        context,
                        FavoriteWidget::class.java
                    )
                }
                AppWidgetManager.getInstance(context).apply {
                    notifyAppWidgetViewDataChanged(
                        getAppWidgetIds(component),
                        R.id.stack_view
                    )
                }
            }
        }
        super.onReceive(context, intent)
    }
}