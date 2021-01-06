package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var activitySettingsBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySettingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(activitySettingsBinding.root)
    }

    fun onSave(view: View) {
        val dicesAmount = activitySettingsBinding.etDicesAmount.text.toString()?.toInt()
        val facesAmount = activitySettingsBinding.etFacesAmount.text.toString()?.toInt()

        val sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putInt(getString(R.string.saved_dices_amount), dicesAmount)
            putInt(getString(R.string.saved_faces_amount), facesAmount)
            apply()
        }

        setResult(Activity.RESULT_OK)
        finish()
    }

}