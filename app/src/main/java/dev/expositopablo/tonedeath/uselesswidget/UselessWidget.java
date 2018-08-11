package dev.expositopablo.tonedeath.uselesswidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.widget.RemoteViews;

import javax.inject.Inject;

import dev.expositopablo.tonedeath.R;
import dev.expositopablo.tonedeath.data.commons.Constants;
import dev.expositopablo.tonedeath.data.db.AppDataManager;
import dev.expositopablo.tonedeath.data.db.DataManager;
import dev.expositopablo.tonedeath.data.db.PinyinDatabase;
import dev.expositopablo.tonedeath.data.db.dao.PinyinDao;
import dev.expositopablo.tonedeath.di.DaggerAppComponent;

/**
 * Implementation of App Widget functionality.
 */
public class UselessWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        CharSequence widgetText = "Best Score : " + pref.getInt(Constants.SCORE, 0);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.useless_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

