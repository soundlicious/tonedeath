package dev.expositopablo.tonedeath.views.practice

import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.NavUtils
import androidx.fragment.app.Fragment
import dev.expositopablo.tonedeath.R
import dev.expositopablo.tonedeath.views.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.unloadKoinModules

class PracticeActivity : BaseActivity(), PracticeCallback {
    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(practiceModule)
        setContentView(R.layout.activity_practice)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            changeFragment(PRACTICE)
        }
    }

    override fun onStop() {
        super.onStop()
        unloadKoinModules(practiceModule)
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

    private fun changeFragment(tag: String) {
        var fragment: Fragment = supportFragmentManager.findFragmentByTag(tag).let {
            it ?: when (tag) {
//                GAMEOVER -> GameOverFragment()
                else -> PracticeFragment()
            }
        }
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout_practice, fragment, tag)
                .commit()
    }

    //region CallbackListeners
    override fun gameOver() {
        changeFragment(GAMEOVER)
    }

    override fun backToGame() {
        changeFragment(PRACTICE)
    } //endregion

    companion object {
        private const val GAMEOVER = "gameOverFragment"
        private const val PRACTICE = "PracticeFragment"
    }
}