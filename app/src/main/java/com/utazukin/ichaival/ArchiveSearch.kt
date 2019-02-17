/*
 * Ichaival - Android client for LANraragi https://github.com/Utazukin/Ichaival/
 * Copyright (C) 2019 Utazukin
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.utazukin.ichaival

import android.app.Activity
import android.os.Bundle
import com.utazukin.ichaival.ArchiveListFragment.OnListFragmentInteractionListener

class ArchiveSearch : BaseActivity(), OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(archive: Archive?) {
        if (archive != null) {
            setResult(Activity.RESULT_OK)
            startDetailsActivity(archive.id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive_search)
        setSupportActionBar(findViewById(R.id.toolbar))
        intent.run {
            val listFragment: ArchiveListFragment =
                supportFragmentManager.findFragmentById(R.id.list_fragment) as ArchiveListFragment
            val tag = getStringExtra(TAG_SEARCH)

            listFragment.showOnlySearch(true)
            listFragment.searchView.setQuery(tag, true)
        }
    }

    override fun onTabInteraction(tab: ReaderTab, longPress: Boolean) {
        super.onTabInteraction(tab, longPress)
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onStart() {
        super.onStart()
        ReaderTabHolder.registerAddListener(this)
    }

    override fun onStop() {
        super.onStop()
        ReaderTabHolder.unregisterAddListener(this)
    }
}
