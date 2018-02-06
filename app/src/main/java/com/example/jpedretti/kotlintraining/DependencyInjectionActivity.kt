package com.example.jpedretti.kotlintraining

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.res.Resources
import android.graphics.drawable.Icon
import android.os.Bundle
import android.os.storage.StorageManager
import android.support.v4.app.NotificationCompat
import android.view.View
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.github.salomonbrys.kodein.instance
import kotlinx.android.synthetic.main.activity_dependency_injection.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.*

class DependencyInjectionActivity : KodeinAppCompatActivity() {

    val testService: TestService by instance()
    val diViewModel: DIViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dependency_injection)
        val notificationManager : NotificationManager by instance()
        val clipboardManager : ClipboardManager by instance()
        clipboardManager.primaryClip.addItem(ClipData.newPlainText("teste DI", "DI rules").getItemAt(0))
        val resouces : Resources by instance()
        app_name.text = resouces.getText(R.string.app_name)
        val notification = NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("DI")
                .setContentText("hello DI")

        notificationManager.notify(Random().nextInt(), notification.build())
    }

    fun doServiceStuffClick(view: View) {
        launch (UI){
            service_result.text = testService.doServiceStuffAsync().await()
        }
    }

    fun doServiceStuffByViewModelClick(view: View) {
        launch (UI){
            view_model_result.text = diViewModel.callDoTestServiceStuff().await()
        }
    }
}
