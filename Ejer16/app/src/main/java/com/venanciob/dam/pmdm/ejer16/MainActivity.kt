package com.venanciob.dam.pmdm.ejer16

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venanciob.dam.pmdm.ejer16.ui.theme.Ejer16Theme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class Tarea(
    val id: String,
    val titulo: String,
    val descripcion: String,
    val activo: Boolean = false
)

//La lista de las ciudades a Buscar
val tareas = listOf(
    Tarea("13", "Salamanca","Ciudad de Cultura"),
    Tarea("14", "Zamora","Ciudad del Queso"),
    Tarea("15", "Santander","Ciudad de la Musica")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejer16Theme {
                Greeting()
            }
        }
    }
}
class SearchState(initialText: String = "") {
    // Esto permite modificar el texto de la barra de búsqueda
    var text by mutableStateOf(initialText)
        private set

    fun onSearch(newText: String) {
        text = newText
    }

    val isEmpty: Boolean
        get() = text.isBlank()

    // Esto guarda el texto de la barra de búsqueda y lo restaura
    companion object {
        val Saver: Saver<SearchState, String> = Saver(
            save = { it.text },
            restore = { SearchState(it) }
        )
    }
}
@Composable
fun rememberSearchState(): SearchState {
    return rememberSaveable(saver = SearchState.Saver) {
        SearchState()
    }
}
@Composable
fun SearchBuscador(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = rememberSearchState()
    LaunchedEffect(state) {
        snapshotFlow { state.text }
            .filter { !it.isEmpty() }
            .distinctUntilChanged()
            .collect { onSearch(it) }
    }
    val mostrarLimpiar by remember {
        derivedStateOf {
            !state.isEmpty
        }
    }
    Column(modifier = modifier.fillMaxWidth()) {
        SearchField(state = state)

        if (mostrarLimpiar) {
            Button(
                onClick = { state.onSearch("") }
            ) {
                Text("LIMPIAR")
            }
        }
    }
}
@Composable
fun SearchField(state: SearchState) {
    TextField(
        value = state.text,
        onValueChange = { state.onSearch(it) },
        label = { Text("Buscar...") }
    )
}
@Composable
fun TarjetaTarea(
    tarea: Tarea,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = tarea.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = tarea.descripcion,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Image(
                imageVector = drawable.
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    var textoBuscado by remember { mutableStateOf("") }

    // 2. Filtramos tu lista 'tareas' según lo que se haya escrito
    val tareasFiltradas = if (textoBuscado.isBlank()) {
        emptyList()
    } else {
        tareas.filter { it.titulo.contains(textoBuscado, ignoreCase = true) }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Mi Aula") }
            )
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            // 3. Llamamos a tu buscador y guardamos lo que escribe el usuario
            SearchBuscador(
                onSearch = { texto ->
                    textoBuscado = texto
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Pintamos el texto inicial o la lista con tus TarjetaTarea
            if (tareasFiltradas.isEmpty()) {
                Text(text = "Escribe un término para buscar")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(tareasFiltradas) { tarea ->
                        TarjetaTarea(
                            tarea = tarea,
                            onClick = { }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ejer16Theme {
        Greeting()
    }
}