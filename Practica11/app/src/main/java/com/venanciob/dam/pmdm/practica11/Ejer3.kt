package com.venanciob.dam.pmdm.practica11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venanciob.dam.pmdm.practica11.ui.theme.Practica11Theme

class Ejer3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica11Theme {
                VistaMensajes2()
            }
        }
    }
}
@Composable
fun PantallaMensajes2() {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {
        Text(text = "MIS MENSAJES" , color = MaterialTheme.colorScheme.primary, modifier = Modifier.align(Alignment.Start).padding(start = 8.dp, top = 8.dp))
        LazyColumn(){
            item{TarjetaMensaje2("Gibu","Haz esto")}
            item{TarjetaMensaje2("Javi","Juanito el de los palotes")}
            item{TarjetaMensaje2("Javichu","Juanito el de los ojos verdes")}
            item{TarjetaMensaje2("Juan","Juanito el de los supermercados")}
            item{TarjetaMensaje2("Dominik","Juanito el de los campos")}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaMensajes2() {
    Practica11Theme {
        PantallaMensajes()
    }
}
@Composable
fun TarjetaMensaje2(autor: String,mensaje: String,) {
    var expandido by remember {
        mutableStateOf(false)
    }
    val colorFondo by animateColorAsState(
        targetValue = if (expandido)
            MaterialTheme.colorScheme.primaryContainer
        else
            MaterialTheme.colorScheme.surface,
        label = "colorMensaje"
    )
    Row(modifier = Modifier.fillMaxSize()) {
    
        Image()
        Column() {
            Text()
            Surface() {
                Text()
            }
        }
    }
}




//Row(modifier = Modifier.fillMaxWidth(),
//horizontalArrangement = Arrangement.Start) {
//    var image = painterResource(R.drawable.ic_launcher_background)
//    Image(
//        painter = image,
//        contentDescription = "Foto"
//    )
//    Spacer(modifier = Modifier.height(20.dp))
//}