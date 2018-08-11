package dev.expositopablo.tonedeath.uselesswidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import dev.expositopablo.tonedeath.R;
import dev.expositopablo.tonedeath.data.commons.Constants;

/**
 * Implementation of App Widget functionality.
 */
public class UselessWidget extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        CharSequence widgetText = context.getString(R.string.display_score) + pref.getInt(Constants.SCORE, 0);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.useless_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }
}

