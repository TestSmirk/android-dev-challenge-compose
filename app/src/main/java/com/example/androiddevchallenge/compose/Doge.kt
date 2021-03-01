package com.example.androiddevchallenge.compose

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.desc.Descactivity
import dev.chrisbanes.accompanist.glide.GlideImage

val dogeData = arrayListOf(
    "https://i2.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/words-describe-dogs-goldendoodle-at-laptop.jpg?w=840&ssl=1" to "Are you searching for the best words to describe dogs? There’s no need to chase your own tail. We’ve rounded up a mega list for you.Like a happy pack of doggos sniffing out a renegade treat under the sofa, Happy-Go-Doodle Chloe (my Goldendoodle bestie), Little Bear (the quintessential Lab I puppy sit), and I searched high and low to create this ultimate list of endearments, adjectives, and words for dogs. Plus, we organized them alphabetically to make it easy-breezy for you to scroll through them.",
    "https://i1.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/words-to-describe-dogs-lab-computer.jpg?w=840&ssl=1" to "Little Bear contemplates which words best describe dogs.",
    "https://i2.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/words-to-describe-dogs-golden.jpg?w=840&ssl=1" to "Descriptive words for dogs starting with the letter C",
    "https://i0.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/words-to-describe-dogs-laughing-lab.jpg?w=840&ssl=1" to "D words to describe dogs",
    "https://i1.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/words-describe-dogs-goldendoodle-snuggling-shoulder.jpg?w=840&ssl=1" to "Adjectives for dogs starting with the letter E",
    "https://i2.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/goldendoodle-running.jpg?w=840&ssl=1" to "Descriptive words for dogs starting with the letter G",
    "https://i0.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/words-describe-dogs-dog-sitting-beach.jpg?w=840&ssl=1" to "Words associated with dogs starting with the letter M",
    "https://i1.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/dog-with-shoe.jpg?w=840&ssl=1" to "Munchy Muncher",
    "https://i1.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/muddy-dog-word-desribes-dogs.jpg?w=840&ssl=1" to "Words for dogs starting with the letter N",
    "https://i0.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/goldendoodle-lying-on-yoga-mat.jpg?w=840&ssl=1" to "Happy Yoga Napper",
    "https://i0.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/labrador-retrieversun-dog-face.jpg?w=840&ssl=1" to "R words to describe dogs",
    "https://i0.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/words-to-describe-dogs-dachshund-darling.jpg?w=840&ssl=1" to "Undeniably Cute Doxie",
    "https://i2.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/words-describe-dog-playful.jpg?w=840&ssl=1" to "Wet ‘n’ Wacky!",
    "https://i2.wp.com/www.happygodoodle.com/wp-content/uploads/2020/06/dogs-playing-words-describe-dogs.jpg?w=840&ssl=1" to "Zoomie Zoomers",
)
private const val TAG = "Doge"
@Composable
fun DogeComponent() {
    LazyColumn(content = {
        itemsIndexed(dogeData, itemContent = { index, item ->
           Card(


               shape = RoundedCornerShape(4.dp),
               modifier = Modifier
                   .fillMaxWidth()
                   .background(androidx.compose.ui.graphics.Color.DarkGray)
                   .padding(10.dp)
                   .clickable {
                       Log.d(TAG, "DogeComponent: ")
                       Descactivity.open(item.first,item.second)
                   }
                   .height(200.dp),

           ) {


               Row(modifier = Modifier
                   .fillMaxWidth()
                   .fillMaxHeight(),verticalAlignment = Alignment.CenterVertically){
                   GlideImage(data = item.first,item.second,modifier = Modifier
                       .width(200.dp)
                       .fillMaxHeight()
                       .padding(15.dp))
                   Text(text = item.second,modifier = Modifier.padding(10.dp,15.dp))

               }


           }
        })

    })
}

@Composable
fun DogComponent() {

    LazyColumn(content = {
        itemsIndexed(arrayOf("www", "ee", "ewqe"), itemContent = { index, item ->
            Text(text = item)
        })

    })

}

@Composable
fun DogeNewsComponent() {
//    https://www.happygodoodle.com/words-describe-dogs/
    CreateWebView("https://www.happygodoodle.com/words-describe-dogs/")
}

@Composable
fun CreateWebView(initUrl: String) {
    return AndroidView({ context ->
        RelativeLayout(context).apply {
            val progressBar = ProgressBar(context)
            progressBar.isIndeterminate = true

            addView(WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                background = ColorDrawable(Color.BLACK)
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        return false
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        progressBar.visibility = View.VISIBLE
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        progressBar.visibility = View.GONE
                    }
                }

                loadUrl(initUrl)
            }
            )
            addView(
                progressBar,
                RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
                })
        }
    })
}