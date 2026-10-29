package com.venanciob.dam.pmdm.proyecto12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venanciob.dam.pmdm.proyecto12.ui.theme.Proyecto12Theme

data class Mensaje(
    val autor: String,
    val texto: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto12Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PantallaMensajes()
                }
            }
        }
    }
}
@Composable
fun PantallaMensajes(){
    val mensajes = listOf(
        Mensaje(
            "Laura",
            "¿Qué tal ha ido la clase? Espero que hayas podido terminar la práctica."
        ),
        Mensaje(
            "Mario",
            "Ya terminé la práctica de Jetpack Compose y he probado todos los ejercicios."
        ),
        Mensaje(
            "Ana",
            "Mañana llevo el proyecto para que podamos revisarlo durante la clase."
        ),
        Mensaje(
            "Pedro",
            "Nos vemos mañana. Recuerda que tenemos que terminar la interfaz."
        ),
        Mensaje(
            "Sara",
            "He subido el ejercicio al aula virtual y también he añadido las capturas."
        )
    )
    Text("MIS MENSAJES")
    LazyColumn() {
        items(mensajes){ mensaje ->
            TarjetaMensaje(
                mensaje = mensaje
            )
        }
    }
}
@Composable
fun TarjetaMensaje(mensaje: Mensaje) {
    var expandido by remember { mutableStateOf(false) }
    val colorFondo by animateColorAsState(
        targetValue =
            if (expandido)
            MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface,
        label = "colorMensaje"
    )
    Row(modifier = Modifier.fillMaxSize()
        .padding(20.dp)
        .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))) {
        Surface(
            color = colorFondo,
            modifier = Modifier.animateContentSize(),
            shape = RoundedCornerShape(16.dp)){
            Column(modifier = Modifier.fillMaxSize().padding(20.dp).border(1.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
                .clickable { expandido = !expandido }) {
                Text(text = mensaje.autor,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                Text(
                    text = mensaje.texto,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    maxLines = if (expandido) Int.MAX_VALUE else 1
                )
            }
        }
    }
    Modifier.clickable {
        expandido = !expandido
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Proyecto12Theme {
        PantallaMensajes()
    }
}