package com.example.androiddevchallenge.desc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.App
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.glide.GlideImage

class Descactivity : AppCompatActivity() {

    private val image by lazy { intent.getStringExtra("image") }
    private val desc by lazy { intent.getStringExtra("desc") }
    private  val TAG = "DescActivity"
    companion object{
        fun open(image:String,desc:String){
            App.app?.apply {
                startActivity(Intent(this,Descactivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    putExtra("image",image)
                    putExtra("desc",desc)
                })

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: image $image ")
        setContent {
            MyTheme {
                Desc(image,desc)
            }
        }
    }

}

@Composable
fun Desc(image:String?,desc: String?) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(data = image?:return,desc,modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = desc?:return)
    }
}
