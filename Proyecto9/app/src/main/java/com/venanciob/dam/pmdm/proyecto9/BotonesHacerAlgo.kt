package com.venanciob.dam.pmdm.proyecto9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.venanciob.dam.pmdm.proyecto9.ui.theme.Proyecto9Theme

class BotonesHacerAlgo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto9Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(30.dp,0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text="Contador", textAlign = TextAlign.Center, fontSize = 30.sp,modifier= modifier.padding(0.dp,50.dp))
        Button({}, shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(Color.Blue))
        {
            Text("GUARDAR CAMBIOS", textAlign = TextAlign.Center, modifier = modifier. fillMaxWidth())
        }
        Row(
            Modifier.fillMaxWidth()
        ) {
            OutlinedButton({},modifier = Modifier.weight(1f)

            ){
                Text("CANCELAR")
            }
            TextButton ({},modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(Color.Red ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Borrar",textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }

        }
        Button({}, enabled = false, modifier = Modifier.fillMaxWidth()) {
            Text("NO DISPONIBLE")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Proyecto9Theme {
        Greeting2("Android")
    }
}