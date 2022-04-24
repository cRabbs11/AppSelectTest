package com.ekochkov.appselecttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ekochkov.appselecttest.databinding.ActivityMainBinding

private const val TAG_FILM_LIST_FRAGMENT = "film_list_fragment"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AppSelectTest)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launchFragment(FilmListFragment(), TAG_FILM_LIST_FRAGMENT)
    }

    private fun launchFragment(fragment: Fragment, tag: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.host_fragment, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        }
        super.onBackPressed()
    }
}