package com.example.calcintentweb

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class IntentActivity : AppCompatActivity() {
    lateinit var Btn_call:Button
    lateinit var Btn_sms:Button
    lateinit var Btn_dial:Button
    lateinit var Btn_camera:Button
    lateinit var Btn_stk:Button
    lateinit var Btn_share:Button
    lateinit var Btn_email:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        Btn_call=findViewById(R.id.Btn_call)
        Btn_camera=findViewById(R.id.Btn_camera)
        Btn_sms=findViewById(R.id.Btn_sms)
        Btn_stk=findViewById(R.id.Btn_stk)
        Btn_share=findViewById(R.id.Btn_share)
        Btn_dial=findViewById(R.id.Btn_dial)
        Btn_email=findViewById(R.id.Btn_email)

        Btn_sms.setOnClickListener {
            val uri = Uri.parse("Smsto:0793712961")
            val intent = Intent(Intent.ACTION_SENDTO,uri)
            intent.putExtra("Hello","Good morning")
            startActivity(intent)

        }
        Btn_camera.setOnClickListener {
            val takepic = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takepic,1)
        }
        Btn_dial.setOnClickListener {
            val nambari="0793712961"
            val intent = Intent(Intent.ACTION_DIAL,Uri.fromParts("telephone",nambari,null))
            startActivity(intent)
        }
        Btn_email.setOnClickListener {
            val emailintent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","lizsheila182@gmail.com",null))
            emailintent.putExtra(Intent.EXTRA_SUBJECT,"Subject")
            emailintent.putExtra(Intent.EXTRA_TEXT,"Body")
            startActivity(Intent.createChooser(emailintent,"send email...."))
        }
        Btn_stk.setOnClickListener {
            val simToolKitLaunchIntent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }
        }
        Btn_share.setOnClickListener {
            val shareIntent=Intent(Intent.ACTION_SENDTO)
            shareIntent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT,"Hello, click to download")
            startActivity(shareIntent)
        }
        Btn_call.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0793712961"))

            if (ContextCompat.checkSelfPermission(this@IntentActivity,
                    android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                this@IntentActivity,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                1
            )
            } else {
                startActivity(intent)
            }


        }


    }


}