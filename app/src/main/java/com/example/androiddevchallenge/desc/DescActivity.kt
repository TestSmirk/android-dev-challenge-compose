package com.example.androiddevchallenge.desc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.bumptech.glide.request.RequestOptions
import com.example.androiddevchallenge.App
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.glide.GlideImage

const val AUTH_AVATAR ="https://secure.gravatar.com/avatar/272dfa49948a90c4ff39c2d33cb4f56d"
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

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)

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
            .background(Color.Transparent)
            .fillMaxWidth(),
            requestBuilder = {
                val options = RequestOptions()
                options.centerCrop()

                apply(options)
            }
            )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = desc?:return,modifier = Modifier.padding(10.dp,0.dp))
        Row (modifier = Modifier.weight(1f).padding(10.dp),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center){
            Card(
                modifier = Modifier.padding(10.dp).shadow(10.dp),backgroundColor = Color.DarkGray,shape = RoundedCornerShape(20.dp),) {
                Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center){
                    GlideImage(data = AUTH_AVATAR,"TextSmirk",modifier = Modifier.height(100.dp).width(100.dp).background(Color.Transparent,shape = RoundedCornerShape(200.dp)),)
                    Text(text = "TextSmirk",Modifier.padding(10.dp,0.dp))
                }

            }

        }
        Button(onClick = {
                         Toast.makeText(App.app,"👀",Toast.LENGTH_LONG).show()
        },modifier = Modifier.padding(0.dp,100.dp)) {
            Text(text = "Who is The Dog")
        }
    }
}
