package com.venanciob.dam.pmdm.practica14

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venanciob.dam.pmdm.practica14.ui.theme.Practica14Theme

class Ejer2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica14Theme {
                PantallaEntrenamiento()
            }
        }
    }
}
// declaramos en una clase los dos tipos de booleans normal o Activa
enum class EstadoEntrenamiento {
    normal, Activa
}

@Composable
fun PantallaEntrenamiento() {
    //Guarda el estado entiendo
    var estado by remember { mutableStateOf(EstadoEntrenamiento.normal) }

    val transition = updateTransition(
        targetState = estado,
        label = "entrenamiento"
    )

    val color by transition.animateColor(label = "color") { s ->
        if (s == EstadoEntrenamiento.normal)
            MaterialTheme.colorScheme.surface
        else
            MaterialTheme.colorScheme.primaryContainer
    }

    val ancho by transition.animateDp(label = "ancho") { s ->
        if (s == EstadoEntrenamiento.normal) 220.dp else 320.dp
    }

    val texto = if (estado == EstadoEntrenamiento.normal)
        "DESCANSO" else "ENTRENANDO"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Surface(
            color = color,
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 4.dp
        ) {
            Box(
                modifier = Modifier
                    .width(ancho)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = texto,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                estado = if (estado == EstadoEntrenamiento.normal)
                    EstadoEntrenamiento.Activa
                else
                    EstadoEntrenamiento.normal
            }
        ) {
            Text(
                if (estado == EstadoEntrenamiento.normal)
                    "INICIAR ENTRENAMIENTO" else "FINALIZAR"
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    Practica14Theme {
        PantallaEntrenamiento()
    }
}