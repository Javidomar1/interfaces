package com.venanciob.dam.pmdm.ejemplosui

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    // Constantes de precios agrupadas en un companion object
    companion object {
        private const val PRECIO_PEQUENA = 8.0
        private const val PRECIO_MEDIANA = 10.5
        private const val PRECIO_FAMILIAR = 14.0
        private const val PRECIO_EXTRA = 1.5
        private const val PRECIO_ENVIO = 3.0
    }

    // Referencias a las vistas. "lateinit" porque se inicializan en onCreate,
    // después de setContentView (en el punto 2 las sustituiremos por View Binding)
    private lateinit var tilNombre: TextInputLayout
    private lateinit var etNombre: TextInputEditText
    private lateinit var rgTamano: RadioGroup
    private lateinit var spMasa: Spinner
    private lateinit var cbQueso: CheckBox
    private lateinit var cbChampinones: CheckBox
    private lateinit var cbJamon: CheckBox
    private lateinit var tvCantidad: TextView
    private lateinit var sbCantidad: SeekBar
    private lateinit var swEnvio: MaterialSwitch
    private lateinit var tvResumen: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarVistas()
        configurarListeners()
    }

    /** Obtiene las referencias a las vistas del layout. */
    private fun inicializarVistas() {
        tilNombre = findViewById(R.id.tilNombre)
        etNombre = findViewById(R.id.etNombre)
        rgTamano = findViewById(R.id.rgTamano)
        spMasa = findViewById(R.id.spMasa)
        cbQueso = findViewById(R.id.cbQueso)
        cbChampinones = findViewById(R.id.cbChampinones)
        cbJamon = findViewById(R.id.cbJamon)
        tvCantidad = findViewById(R.id.tvCantidad)
        sbCantidad = findViewById(R.id.sbCantidad)
        swEnvio = findViewById(R.id.swEnvio)
        tvResumen = findViewById(R.id.tvResumen)

        actualizarTextoCantidad(sbCantidad.progress)
    }

    /** Registra los escuchadores de eventos. */
    private fun configurarListeners() {
        // Quita el error en cuanto el usuario empieza a escribir
        etNombre.doAfterTextChanged { tilNombre.error = null }

        // Interfaz con tres métodos → objeto anónimo
        sbCantidad.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                actualizarTextoCantidad(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Interfaz con un único método → lambda
        findViewById<Button>(R.id.btnCalcular).setOnClickListener { calcularPedido() }
    }

    private fun actualizarTextoCantidad(progress: Int) {
        tvCantidad.text = getString(R.string.cantidad_valor, progress + 1)
    }

    /** Valida los datos, calcula el precio y muestra el resumen. */
    private fun calcularPedido() {
        // 1. Validación
        val nombre = etNombre.text.toString().trim()
        if (nombre.isEmpty()) {
            tilNombre.error = getString(R.string.error_nombre)
            etNombre.requestFocus()
            return
        }

        // 2. Lectura de los datos de la interfaz
        val precioBase = when (rgTamano.checkedRadioButtonId) {
            R.id.rbPequena -> PRECIO_PEQUENA
            R.id.rbFamiliar -> PRECIO_FAMILIAR
            else -> PRECIO_MEDIANA
        }
        val tamano = findViewById<RadioButton>(rgTamano.checkedRadioButtonId).text.toString()
        val masa = spMasa.selectedItem.toString()
        val cantidad = sbCantidad.progress + 1

        // Lista con los textos de los CheckBox marcados
        val extras = listOf(cbQueso, cbChampinones, cbJamon)
            .filter { it.isChecked }
            .map { it.text.toString() }

        // 3. Cálculo
        val precioUnidad = precioBase + extras.size * PRECIO_EXTRA
        var total = precioUnidad * cantidad
        if (swEnvio.isChecked) total += PRECIO_ENVIO

        // 4. Mostrar resultado
        val textoExtras = if (extras.isEmpty()) getString(R.string.sin_extras)
        else extras.joinToString(", ")

        tvResumen.text = getString(
            R.string.resumen_pedido,
            nombre, cantidad, tamano.lowercase(), masa.lowercase(), textoExtras, total
        )
    }
}