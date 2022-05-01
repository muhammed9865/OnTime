package com.muhammed.ontime.activity

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.muhammed.ontime.R
import com.muhammed.ontime.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    private val binding: ActivityAddNoteBinding by lazy {
        ActivityAddNoteBinding.inflate(
            LayoutInflater.from(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = getColor(R.color.buttonsColor)
        setSupportActionBar(binding.noteAdditionToolbar)
        binding.noteAdditionToolbar.setNavigationOnClickListener {
            onBackPressed()
            finish()
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.note_addition_topbar, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.note_addition_topbar, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.pin_note -> Snackbar.make(binding.root, "Implement Note pin", Snackbar.LENGTH_LONG)
                .show()
            R.id.note_addition_settings -> Snackbar.make(
                binding.root,
                "Implement Note settings",
                Snackbar.LENGTH_LONG
            ).show()
        }

        return true
    }
}