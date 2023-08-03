package com.denizkabakcili.catchkenny

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var time : Int = 15
    var score : Int = 0
    val xPositions= arrayOf(-300,0,300)
    val yPositions= arrayOf(-300,0,300)
    val start=0
    val end=2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startButton=findViewById<Button>(R.id.button2)
        startButton.setOnClickListener { start() }

    }
    fun increaseScore(){
        val scoreText=findViewById<TextView>(R.id.scoreText)
        score++
        scoreText.text="Score: $score"

    }
    fun changePosition(){
        val kennyImage=findViewById<ImageView>(R.id.imageView)
        val randomX=xPositions.get(rand(start,end))
        val randomY=yPositions.get(rand(start,end))
        kennyImage.animate().apply { translationX(randomX.toFloat())
        translationY(randomY.toFloat())}

    }
    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(start, end + 1)
    }
    fun start(){
        val timeText=findViewById<TextView>(R.id.timeText)
        val kennyImage=findViewById<ImageView>(R.id.imageView)
        object: CountDownTimer(time.toLong()*1000,1000){
            override fun onTick(p0: Long) {
                changePosition()
                kennyImage.setOnClickListener { increaseScore() }
                timeText.text="Time: ${p0/1000}"
            }

            override fun onFinish() {
                timeText.text="Time: 0"
                alertDialog()
            }

        }.start()



    }
    fun alertDialog(){
        val alertDialog =AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Game is Over")
        alertDialog.setMessage("If you want to play the game again. Click 'Yes'")
        alertDialog.setPositiveButton("Yes",DialogInterface.OnClickListener{dialogInterface, i ->
            val intentFromMain=intent
            finish()
            startActivity(intentFromMain)

        })
        alertDialog.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
            System.exit(0)
        })
        alertDialog.show()
    }



}