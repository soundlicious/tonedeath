package dev.expositopablo.tonedeath.views.practice

import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.NavUtils
import androidx.fragment.app.Fragment
import dev.expositopablo.tonedeath.R
import dev.expositopablo.tonedeath.data.commons.PinyinDTO
import dev.expositopablo.tonedeath.views.BaseActivity
import org.koin.core.context.unloadKoinModules

class PracticeActivity : BaseActivity(), PracticeCallback {


    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            backToGame()
        }
    }

    //endregion
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout_practice, fragment)
                .commit()
    }

    //region CallbackListeners
    override fun gameOver(currentPiyin: PinyinDTO, answer: PinyinDTO, score: Int) {
        changeFragment(gameOverFragmentInstance(currentPiyin, answer, score))
    }

    override fun backToGame() {
        changeFragment(PracticeFragment())
    } //endregion

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.frameLayout_practice) is GameOverFragment) {
            backToGame()
        } else {
            super.onBackPressed()
        }
    }
}