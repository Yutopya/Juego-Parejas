package com.example.trabajo_parejas
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    //<editor-fold desc="IMAGENES">
    lateinit var card1_1:ImageView
    lateinit var card1_2:ImageView
    lateinit var card1_3:ImageView
    lateinit var card1_4:ImageView

    lateinit var card2_1:ImageView
    lateinit var card2_2:ImageView
    lateinit var card2_3:ImageView
    lateinit var card2_4:ImageView

    lateinit var card3_1:ImageView
    lateinit var card3_2:ImageView
    lateinit var card3_3:ImageView
    lateinit var card3_4:ImageView
    //</editor-fold>

    //<editor-fold desc="OTROS">
    lateinit var tv_Jugador1:TextView
    lateinit var tv_Jugador2:TextView

    lateinit var btnSonidoON:ImageButton

    lateinit var mediaP:MediaPlayer
    lateinit var mediaPBG:MediaPlayer

    lateinit var img1:ImageView
    lateinit var img2:ImageView
    //</editor-fold>

    //<editor-fold desc="VARIABLES">
    var imagenesConjunto = arrayOf(1_1,1_2,1_3,1_4,1_5,1_6,2_1,2_2,2_3,2_4,2_5,2_6)
    var idImagen1 = 0
    var idImagen2 = 0
    var idImagen3 = 0
    var idImagen4 = 0
    var idImagen5 = 0
    var idImagen6 = 0

    var turn=1
    var pointsJ1=0
    var pointsJ2=0
    var numImagen=1
    var listen=true

    //</editor-fold>

    lateinit var cardsArray:Array<ImageView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        joinGUI()
    }

    private fun joinGUI() {

        card1_1 = findViewById(R.id.card1_1)
        card1_2 = findViewById(R.id.card1_2)
        card1_3 = findViewById(R.id.card1_3)
        card1_4 = findViewById(R.id.card1_4)
        card2_1 = findViewById(R.id.card2_1)
        card2_2 = findViewById(R.id.card2_2)
        card2_3 = findViewById(R.id.card2_3)
        card2_4 = findViewById(R.id.card2_4)
        card3_1 = findViewById(R.id.card3_1)
        card3_2 = findViewById(R.id.card3_2)
        card3_3 = findViewById(R.id.card3_3)
        card3_4 = findViewById(R.id.card3_4)

        card1_1.tag = "0"
        card1_2.tag = "1"
        card1_3.tag = "2"
        card1_4.tag = "3"
        card2_1.tag = "4"
        card2_2.tag = "5"
        card2_3.tag = "6"
        card2_4.tag = "7"
        card3_1.tag = "8"
        card3_2.tag = "9"
        card3_3.tag = "10"
        card3_4.tag = "11"

        cardsArray = arrayOf(card1_1,card1_2,card1_3,card1_4,card2_1,card2_2,card2_3,card2_4,card3_1,card3_2,card3_3,card3_4)

        idImagen1 = R.drawable.card1
        idImagen2 = R.drawable.card2
        idImagen3 = R.drawable.card3
        idImagen4 = R.drawable.card4
        idImagen5 = R.drawable.card5
        idImagen6 = R.drawable.card6

        imagenesConjunto.shuffle()

        tv_Jugador1 = findViewById(R.id.tv_Jugador1)
        tv_Jugador2 = findViewById(R.id.tv_Jugador2)

        tv_Jugador1.setBackgroundColor(Color.GRAY)
        tv_Jugador2.setBackgroundColor(Color.GREEN)

        btnSonidoON = findViewById(R.id.btnSonidoON)
        btnSonidoON.setColorFilter(Color.GREEN)

        sound("bgmusic",true)

    }

    private fun sound(nombreSonido: String, loop: Boolean=false) {
        var resID = resources.getIdentifier(nombreSonido,"raw",packageName)
        if(nombreSonido == "bgmusic"){
            mediaPBG = MediaPlayer.create(this,resID)
            mediaPBG.isLooping = loop
            mediaPBG.setVolume(0.04F,0.04F)
            if(!mediaPBG.isPlaying){
                mediaPBG.start()
            }
        }else{
            mediaP = MediaPlayer.create(this,resID)
            mediaP.setVolume(0.04F,0.04F)
            mediaP.setOnCompletionListener (MediaPlayer.OnCompletionListener { mediaPlayer ->
                mediaPlayer.pause()
                mediaPlayer.release()
            } )
            if(!mediaP.isPlaying){
                mediaP.start()
            }
        }
    }

    fun bGMusic(view: View){
        if (listen){
            mediaPBG.pause()
            btnSonidoON.setImageResource(R.drawable.volume_off)
            btnSonidoON.setColorFilter(Color.GRAY)
        }else {
            mediaPBG.start()
            btnSonidoON.setImageResource(R.drawable.volumen_on)
            btnSonidoON.setColorFilter(Color.GREEN)
        }
        listen = !listen
    }

    fun select(image: View){
        sound("touch")
        check(image)
    }

    private fun check(imagen:View){
        var imageV = (imagen as ImageView)
        var tag = imagen.tag.toString().toInt()

        when (imagenesConjunto[tag]) {
            1_1 -> imageV.setImageResource(idImagen1)
            1_2 -> imageV.setImageResource(idImagen2)
            1_3 -> imageV.setImageResource(idImagen3)
            1_4 -> imageV.setImageResource(idImagen4)
            1_5 -> imageV.setImageResource(idImagen5)
            1_6 -> imageV.setImageResource(idImagen6)

            2_1 -> imageV.setImageResource(idImagen1)
            2_2 -> imageV.setImageResource(idImagen2)
            2_3 -> imageV.setImageResource(idImagen3)
            2_4 -> imageV.setImageResource(idImagen4)
            2_5 -> imageV.setImageResource(idImagen5)
            2_6 -> imageV.setImageResource(idImagen6)
        }

        if(numImagen==1){
            img1 = imageV
            numImagen = 2
            imageV.isEnabled=false
        }else if (numImagen==2){
            img2 = imageV
            numImagen = 1
            imageV.isEnabled=false
            disableImage()
            val handl = Handler(Looper.getMainLooper())
            handl.postDelayed({areEqual()},1000)
        }
    }

    private fun areEqual() {
        if(img1.drawable.constantState == img2.drawable.constantState){
            sound("good")
            if(turn == 1){
                pointsJ1++
                tv_Jugador1.text = "J1: $pointsJ1"
            }else{
                pointsJ2++
                tv_Jugador2.text = "J2: $pointsJ2"
            }
            img1.isEnabled = false
            img2.isEnabled = false
            img1.tag = ""
            img2.tag = ""
        }else{
            sound("wrong")
            img1.setImageResource(R.drawable.cover)
            img2.setImageResource(R.drawable.cover)
            if(turn == 1){
                turn = 2
                tv_Jugador1.setBackgroundColor(Color.GRAY)
                tv_Jugador2.setBackgroundColor(Color.GREEN)
            }else{
                turn = 1
                tv_Jugador1.setBackgroundColor(Color.GREEN)
                tv_Jugador2.setBackgroundColor(Color.GRAY)
            }
        }
        resetNonTagged()
        checkEndOfGame()
    }

    private fun checkEndOfGame() {
        var check = true
        var cont = 0
        while (cont < cardsArray.size && check){
            if (cardsArray[cont].tag.toString().isNotEmpty()){ check=false}
            cont++
        }
        if (check){
            mediaPBG.stop()
            mediaPBG.release()
            sound("win")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("")
                .setMessage("Puntuación: \n J1: $pointsJ1\n J2: $pointsJ2")
                .setCancelable(false)
                .setPositiveButton("New Game",
                    DialogInterface.OnClickListener { _, _ ->
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    })
                .setNegativeButton("Exit",
                    DialogInterface.OnClickListener { _, _ ->
                        finish()
                    })
            val ad = builder.create()
            ad.show()
        }
    }

    private fun resetNonTagged(){
        for (item in cardsArray) item.isEnabled = item.tag.toString().isNotEmpty()
    }

    private fun disableImage() {
        for (item in cardsArray) item.isEnabled = false
    }
}