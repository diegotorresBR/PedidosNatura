package diegotorres.pedidos.natura.rede.pedidoson_line;

/**
 * Created by diego on 28/08/15.
 */
import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

public class Analytics_Exemplo extends Application {
    public  static GoogleAnalytics analytics ;
    public  static Tracker tracker;
    public enum TrackerName {
        APP_TRACKER, // Tracker used only in this app.
        GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
        ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
    }

    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = analytics.newTracker(R.xml.global_tracker);
            mTrackers.put(trackerId, t);

        }
        return mTrackers.get(trackerId);
    }

    @Override
    public void onCreate ()  {
        super.onCreate();
        analytics =  GoogleAnalytics. getInstance(this);
        analytics . setLocalDispatchPeriod ( 1 );

        tracker = analytics . newTracker ( "UA-66702427-3" );  // Substitua com rastreador real id
        tracker . enableExceptionReporting ( true );
        tracker . enableAdvertisingIdCollection ( true );
        tracker . enableAutoActivityTracking ( true );


    }



}