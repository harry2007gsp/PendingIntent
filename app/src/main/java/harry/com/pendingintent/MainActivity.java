package harry.com.pendingintent;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonForPendingIntent(View view) {
        Intent intent2 = new Intent(this,Second.class);
        Intent intent3 = new Intent(this, Thrid.class);
        Intent intent4 = new Intent(this, Fourth.class);

        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, 0);
        PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 0, intent3, 0);
        PendingIntent pendingIntent4 = PendingIntent.getActivity(this, 0, intent4, 0);

        Notification.Builder notificationBuilder = new Notification.Builder(this);

        notificationBuilder.setSmallIcon(android.R.drawable.btn_star_big_off); //small icon takes int which is from drawable
                                                                              // THIS IS MUST TO BE DEFINED WITHOUT WHICH NOTIFICATION WILL NOT COME
        notificationBuilder.setContentTitle("Hello");
        notificationBuilder.setContentText("here is the details");
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), //to convert from int in drawable to Bitmap
                R.drawable.ic_launcher);
        notificationBuilder.setLargeIcon(icon); //large icon takes Bitmap which we have to make or take

        //this is for addtional clickables to take us to different things
        //VERY IMPORTANT, THIS WILL TAKE OVER CUSTOMIZED LAYOUT IF DEFINED. SO TO SHOW CUSTOM LAYOUT, DONT USE addAction()
        notificationBuilder.addAction(0, "Reply", pendingIntent3);//setAutoCancel(true) will work for these too as they are in bydefault notification
        notificationBuilder.addAction(0, "Delete", pendingIntent4);

        //BEFORE THIS NOTIFICATION IS NOT HAVING CLICK AND TAKING TO SOMEWHERE ACTION
        notificationBuilder.setContentIntent(pendingIntent2); // to take us to the destination on the click
        //upto here everything is bydefault UI without any customized layout

        //This is for customized layout of notification
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget);
        remoteViews.setOnClickPendingIntent(R.id.textView1,pendingIntent3);
        remoteViews.setOnClickPendingIntent(R.id.button1,pendingIntent4);

        notificationBuilder.setContent(remoteViews); //this will set and replace the bydefault layout of the notification

//        remoteViews.setTextViewText(R.id.textView1,"customized text");
        remoteViews.setTextViewText(R.id.button1,"customized button");
//        notificationBuilder.setProgress(10, 5, true);

        //this will take effect and remove the notification only if notification or any of its addAction() has clickable pending intent
        notificationBuilder.setAutoCancel(true); // this will auto remove the notification when clicking action with pending intent is done

        Notification notification = notificationBuilder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }

}
