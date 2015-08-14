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

    public void pending(View view) {
        Intent intent = new Intent(this,Second.class);
        Intent intent3 = new Intent(this, Thrid.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 0, intent3, 0);

        Notification.Builder notification = new Notification.Builder(MainActivity.this);
//        notification.setContentTitle("hello");
//        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
//                R.drawable.ic_launcher);
//        notification.setLargeIcon(icon);
//        notification.setContentText("here is the details");
        notification.setSmallIcon(android.R.drawable.btn_star_big_off);
        notification.setContentIntent(pendingIntent);

//        Button button1 = (Button) findViewById(R.id.button1);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget);
        remoteViews.setOnClickPendingIntent(R.id.textView1,pendingIntent3);
        notification.setContent(remoteViews);

//        remoteViews.setTextViewText(R.id.textView1,"customized text");
//        remoteViews.setTextViewText(R.id.button1,"customized button");

//        notification.setProgress(10, 5, true);

//        notification.addAction(0, "Reply", pendingIntent3);
//        notification.addAction(0, "Delete", pendingIntent);

        notification.setAutoCancel(true);

        Notification notification1 = notification.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification1);
//        notificationManager.cancel(0);

    }

}
