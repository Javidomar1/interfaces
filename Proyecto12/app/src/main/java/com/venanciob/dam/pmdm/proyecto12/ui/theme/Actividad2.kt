package com.venanciob.dam.pmdm.proyecto12.ui.theme

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venanciob.dam.pmdm.proyecto12.ui.theme.ui.theme.Proyecto12Theme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import com.venanciob.dam.pmdm.proyecto12.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.lazy.items

data class Tarea(
    val titulo: String,
    val descripcion: String
)
class Actividad2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto12Theme {
                PantallaMiAula()
            }
        }
    }
}
@Composable
fun PantallaMiAula() {
    var nuevasTareas by remember { mutableStateOf(0) }

    val tareas = listOf(
        Tarea("Práctica 12", "Listas, Material y animaciones"),
        Tarea("Proyecto Figma", "Prototipo de interfaz"),
        Tarea("Repasar Compose", "Text, Row, Column y Box"),
        Tarea("Preparar entrega", "Capturas y documentación")
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { nuevasTareas++ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir tarea")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                CabeceraPerfil()
            }
            item {
                ImagenDestacada()
            }
            item {
                ZonaAcciones(nuevasTareas) { nuevasTareas = 0 }
            }
            items(tareas) { tarea ->
                TarjetaTarea(tarea = tarea)
            }
        }
    }
}
@Composable
fun ImagenDestacada(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Imagen destacada",
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = "DESARROLLO DE INTERFACES",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}
@Composable
fun CabeceraPerfil() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(12.dp))
        Column {
            Text("Laura García")
            Text("DAM · Desarrollo de Interfaces")
        }
    }
}
@Composable
fun TarjetaTarea(tarea: Tarea) {
    // --- Código de la imagen de la práctica ---
    var expandida by remember {
        mutableStateOf(false)
    }

    val color by animateColorAsState(
        targetValue = if (expandida)
            MaterialTheme.colorScheme.primaryContainer
        else
            MaterialTheme.colorScheme.surface,
        label = "colorTarea"
    )

    Surface(
        color = color,
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable { expandida = !expandida }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = tarea.titulo,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = tarea.descripcion,
                maxLines = if (expandida) Int.MAX_VALUE else 1
            )
        }
    }
}
@Composable
fun ZonaAcciones(contador: Int, onReiniciar: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

        Row(Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) { Text("CANCELAR") }
            Button(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) { Text("GUARDAR") }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Proyecto12Theme {
            PantallaMiAula()
        }
    }
