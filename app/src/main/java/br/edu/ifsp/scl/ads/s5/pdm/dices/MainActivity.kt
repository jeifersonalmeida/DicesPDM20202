package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    companion object {
        const val LAUNCH_SETTINGS_ACTIVITY = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    fun onClick(view: View) {
        val sharedRef = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val dicesAmount = sharedRef.getInt(getString(R.string.saved_dices_amount), 1)
        val facesAmount = sharedRef.getInt(getString(R.string.saved_faces_amount), 6)

        activityMainBinding.resultadoTv.text = ""

        if (view == activityMainBinding.jogarBt) {
            activityMainBinding.llDices.removeAllViews()
            for (i in 1..dicesAmount) {
                val resultado: Int = Random(System.currentTimeMillis()).nextInt(facesAmount) + 1;

                with (activityMainBinding.resultadoTv) {
                    text = "${text} ${resultado}"
                }
                // Gerando nome da imagem
                val resultadoImagem = "dice_$resultado"

                val iv = ImageView(this)
                iv.setImageResource(
                    resources.getIdentifier(resultadoImagem, "drawable", packageName)
                )

                activityMainBinding.llDices.addView(iv)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_settings -> {
                openSettingsActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun openSettingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

}