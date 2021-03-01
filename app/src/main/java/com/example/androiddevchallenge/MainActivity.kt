/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.compose.DogComponent
import com.example.androiddevchallenge.compose.DogeComponent
import com.example.androiddevchallenge.compose.DogeNewsComponent
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlin.coroutines.coroutineContext

private const val TAG = "MainActivity"

val listItems by lazy { listOf("Doge" to  R.mipmap.doge, "DogeNews" to  R.mipmap.doge_news,  "Dog" to R.mipmap.dog) }

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {

        Column  {


           var selectedIndex by remember { mutableStateOf(0) }
            Surface(modifier = Modifier.weight(1f)) {
                when (selectedIndex) {
                    0 -> DogeComponent()
                    1 -> DogeNewsComponent()
                    2-> DogComponent()
                }
            }
           Log.d(TAG, "MyApp() called selectedIndex $selectedIndex")
           // BottomNavigation is a component placed at the bottom of the screen that represents primary
           // destinations in your application.
            val resources = App.app?.resources

           BottomNavigation {
               listItems.forEachIndexed { index, label ->
                   // A composable typically used as part of BottomNavigation. Since BottomNavigation
                   // is usually used to represent primary destinations in your application,
                   // BottomNavigationItem represents a singular primary destination in your application.
                   BottomNavigationItem(
                       icon = {
                           // Simple composable that allows you to draw an icon on the screen. It
                           // accepts a vector asset as the icon.
                              Image(painter = painterResource(id = label.second), contentDescription = label.first,modifier = Modifier.height(
                                  25.dp
                              ).width(25.dp))
                       },
                       label = {
                           // Text is a predefined composable that does exactly what you'd expect it to -
                           // display text on the screen. It allows you to customize its appearance using the
                           // style property.
                           Text(text = label.first)

                       },
                       // Update the selected index when the BottomNavigationItem is clicked
                       selected = selectedIndex == index,
                       onClick = { selectedIndex = index }
                   )
               }
           }
       }
    }
}
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
