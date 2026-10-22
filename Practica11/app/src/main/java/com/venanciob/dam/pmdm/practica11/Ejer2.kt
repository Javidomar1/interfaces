package com.venanciob.dam.pmdm.practica11

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

class Ejer2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica11Theme {
                VistaMensajes()
            }
        }
    }
}
@Composable
fun PantallaMensajes() {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {
        Text(text = "MIS MENSAJES" , color = MaterialTheme.colorScheme.primary, modifier = Modifier.align(Alignment.Start).padding(start = 8.dp, top = 8.dp))
        LazyColumn(){
            item{TarjetaMensaje("Gibu","Haz esto")}
            item{TarjetaMensaje("Javi","Juanito el de los palotes")}
            item{TarjetaMensaje("Javichu","Juanito el de los ojos verdes")}
            item{TarjetaMensaje("Juan","Juanito el de los supermercados")}
            item{TarjetaMensaje("Dominik","Juanito el de los campos")}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaMensajes() {
    Practica11Theme {
        PantallaMensajes()
    }
}
@Composable
fun TarjetaMensaje(autor: String,mensaje: String,) {
    Spacer(modifier = Modifier.height(20.dp))
    Row(modifier = Modifier.fillMaxWidth()
        .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp)),
        horizontalArrangement = Arrangement.Start){
        var image = painterResource(R.drawable.ic_launcher_background)
        Image(
            painter = image,
            contentDescription = "Foto"
        )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp,20.dp)
        .border(2.dp, color = Color.Blue, shape = RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.Start

    ) {
        Text(text = autor , color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(0.dp,10.dp,0.dp))
        Spacer(modifier = Modifier.height(5.dp))
        Surface(modifier = Modifier.fillMaxSize()
            .padding(12.dp),
            shape = RoundedCornerShape(15.dp),
            border = BorderStroke(1.4.dp, color = Color.Blue)
        ){
            Text(
                text = mensaje,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
    }
}