package dev.expositopablo.tonedeath.views.splashscreen

import android.app.ActivityOptions
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import dev.expositopablo.tonedeath.R
import dev.expositopablo.tonedeath.data.db.DataManager
import dev.expositopablo.tonedeath.views.main.MainActivity
import dev.expositopablo.tonedeath.views.main.MainActivityIntent
import org.koin.android.ext.android.inject
import kotlinx.android.synthetic.main.activity_splashscreen.michigan_logo as logo

class SplashScreenActivity : AppCompatActivity() {

    val dataManager: DataManager by inject()

    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        hideSystemUI()
        nextAnimation()


        val rowCount = dataManager.rowCount.value
        if (rowCount == null || rowCount == 0) {
            dataManager.initDB()
        }
    }

    private fun hideSystemUI() {
        val flags = window.decorView.systemUiVisibility
        window.decorView.systemUiVisibility = (flags or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
    }

    private fun nextAnimation() {
        i++
        when  {
            i > 6 -> rotateAndScaleUp()
            i % 2 != 0 -> scaleDown()
            else -> scaleUp()
        }

    }

    private fun scaleDown() {
        logo.animate()
                .scaleX(0.99f).scaleY(0.99f)//scale to quarter(half x,half y)
                .alpha(0.99f) // make it less visible
                .setDuration(1000) // all take 1 seconds
                .withEndAction {
                    nextAnimation()
                }
    }

    private fun scaleUp() {
        logo.animate()
                .scaleX(1f).scaleY(1f)//scale to quarter(half x,half y)
                .alpha(1f) // make it less visible
                .setDuration(1000) // all take 1 seconds
                .withEndAction {
                    nextAnimation()
                }
    }

    private fun rotateAndScaleUp() {
        logo.animate()
                .scaleX(30f).scaleY(30f)//scale to quarter(half x,half y)
                .rotationBy(360f)
                .setDuration(2000) // all take 1 seconds
                .withEndAction {
                    val bundleTransition = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                    startActivity(MainActivityIntent(), bundleTransition)
                }
    }
}