package com.example.calculadorapropinas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadorapropinas.ui.theme.CalculadoraPropinasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraPropinasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculadoraPropinas(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculadoraPropinas(modifier: Modifier = Modifier) {
    // 1. Definición de estados
    var montoCuenta by remember { mutableStateOf("") }
    var porcentajePropina by remember { mutableStateOf("") }

    val monto = montoCuenta.toDoubleOrNull() ?: 0.0
    val porcentaje = porcentajePropina.toDoubleOrNull() ?: 0.0

    val propina = monto * (porcentaje / 100)
    val total = monto + propina

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de Propinas",
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = montoCuenta,
            onValueChange = { montoCuenta = it },
            label = { Text("Ingrese el monto de la cuenta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Seleccione la propina"
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Fila para los botones de porcentaje
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { porcentajePropina = "10" }) {
                Text(text = "10%")
            }

            Button(onClick = { porcentajePropina = "15" }) {
                Text("15%")
            }

            Button(onClick = { porcentajePropina = "20" }) {
                Text("20%")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Mostrar el total formateado a 2 decimales
        Text(
            text = "Total: $${String.format("%.2f", total)}",
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    CalculadoraPropinasTheme {
        CalculadoraPropinas()
    }
}