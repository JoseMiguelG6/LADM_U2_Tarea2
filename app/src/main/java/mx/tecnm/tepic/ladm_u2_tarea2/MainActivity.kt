package mx.tecnm.tepic.ladm_u2_tarea2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val hilo = Hilo(this)

        button.setOnClickListener {
            try {
                hilo.start()
            }catch (io: Exception){
                Toast.makeText(this, "ERROR HILO YA INICIADO", Toast.LENGTH_LONG)
                    .show()
            }
        }

        button2.setOnClickListener {
            hilo.pausar()
        }

        button3.setOnClickListener {
            finish()
        }
    }
}

class Hilo(p: MainActivity): Thread() {
    var pausado = false
    var puntero = p
    var indice = 0
    var contador = 0

    fun pausar(){
        pausado = !pausado
    }

    override fun run() {
        super.run()

        var cards= arrayOf(
            R.drawable.icono2,
            R.drawable.icono5,
            R.drawable.icono7);

        while (true) {
            if(!pausado){
                puntero.runOnUiThread {
                    if (indice==3){
                        indice = 0
                    }
                    puntero.imageView.setImageResource(cards[indice])
                    indice++
                }
                contador++
            }
            sleep(600)
        }
    }
}