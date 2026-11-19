package com.venanciob.dam.pmdm.practica11

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venanciob.dam.pmdm.practica11.ui.theme.Practica11Theme

data class MensajeE3(
    val autor: String,
    val texto: String
)

class Ejer3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica11Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaMensajes3(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun PantallaMensajes3(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "MIS MENSAJES",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp, top = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { TarjetaMensaje2("Gibu", "Haz esto antes de mañana, que si no luego vamos con prisas y se nos acumula todo el trabajo. Haz esto antes de mañana, que si no luego vamos con prisas y se nos acumula todo el trabajo.") }
            item { TarjetaMensaje2("Javi", "Juanito el de los palotes") }
            item { TarjetaMensaje2("Javichu", "Juanito el de los ojos verdes, que siempre llega tarde pero siempre trae algo de comer para todosuanito el de los ojos verdes, que siempre llega tarde pero siempre truanito el de los ojos verdes, que siempre llega tarde pero siempre truanito el de los ojos verdes, que siempre llega tarde pero siempre truanito el de los ojos verdes, que siempre llega tarde pero siempre tr.") }
            item { TarjetaMensaje2("Juan", "Juanito el de los supermercados") }
            item { TarjetaMensaje2("Dominik", "Juanito el de los campos, el que se levanta a las seis de la mañana para ir a trabajar la tierra.") }
            item { TarjetaMensaje2("Gibu", "Haz esto") }
            item { TarjetaMensaje2("Javi", "Juanito el de los palotes y las ganas de aprender Jetpack Compose cada día un poquito más.") }
        }
    }
}
@Composable
fun TarjetaMensaje2(autor: String,mensaje: String,) {
    var expandido by remember { mutableStateOf(false) }
    Spacer(modifier = Modifier.height(20.dp))
    Row(modifier = Modifier.fillMaxWidth()
        .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp)),
        horizontalArrangement = Arrangement.Start){
        var image = painterResource(R.drawable.ic_launcher_background)
        Image(
            painter = image,
            contentDescription = "Foto",
            modifier = Modifier.size(50.dp)
                .clip(shape = CircleShape)
        )

        Column(modifier = Modifier
            .weight(1f)
            .padding(10.dp,20.dp)
            .border(2.dp, color = Color.Blue, shape = RoundedCornerShape(16.dp))
            .clickable { expandido = !expandido },
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = autor , color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(0.dp,10.dp,0.dp))
            Spacer(modifier = Modifier.height(5.dp))
                Surface(modifier = Modifier.fillMaxWidth()
                    .padding(12.dp)
                    .animateContentSize(),
                    shape = RoundedCornerShape(15.dp),
                    border = BorderStroke(1.4.dp, color = Color.Blue)
                ){
                    Text(
                        text = mensaje,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(12.dp),
                        maxLines = if (expandido) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewEj3() {
    Practica11Theme {
        PantallaMensajes3()
    }
}