package com.example.laboratorio4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
class ResultActivity : AppCompatActivity() {


    private  var nameText:String? = ""
    private var emailText:String? = ""
    private lateinit var shareButton:Button
    private var phoneNum: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        if (intent != null){

            intent.extras?.apply {
                nameText = getString(MainActivity.NAME_TEXT)
                emailText= getString(MainActivity.EMAIL_TEXT)
                phoneNum= getInt(MainActivity.CELL_NUMBER)
                findViewById<TextView>(R.id.name_text_view).apply{
                    text = getString(R.string.name_txt, nameText)
                }
                findViewById<TextView>(R.id.email_text_view).apply {
                    text = getString(R.string.email_txt, emailText)
                }
                findViewById<TextView>(R.id.cellphone_text_view).apply {
                    text= getString(R.string.phone_txt, phoneNum)
                }
            }
        }
        bind()
        onClickListener()
    }
    private fun bind(){

        shareButton = findViewById(R.id.share_button)
    }
    private fun onClickListener(){


        shareButton.setOnClickListener {

            val sendIntent: Intent =Intent().apply{
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Name: $nameText" +
                        "\nEmail: $emailText" +
                        "\nPhone: $phoneNum")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}