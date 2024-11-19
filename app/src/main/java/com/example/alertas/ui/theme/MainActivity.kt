package com.example.alertas.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
        AlertasTheme {
            MainScreen()
        }
    }
}
}

@Composable
fun MainScreen() {
    var message by remember { mutableStateOf("Estado Inicial") }
    var showDialog by remember { mutableStateOf<DialogType?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = message)

                // Botón 1
                Button(onClick = { showDialog = DialogType.Confirmacion }) {
                    Text("Botón 1: Confirmación de Acción")
                }

                // Botón 2
                Button(onClick = { showDialog = DialogType.Eliminacion }) {
                    Text("Botón 2: Eliminación Permanente")
                }

                // Botón 3
                Button(onClick = { showDialog = DialogType.Informacion }) {
                    Text("Botón 3: Información Importante")
                }

                // Botón 4
                Button(onClick = { showDialog = DialogType.Autenticacion }) {
                    Text("Botón 4: Autenticación Requerida")
                }

                // Botón 5
                Button(onClick = { showDialog = DialogType.Error }) {
                    Text("Botón 5: Error Crítico")
                }

                // Mostrar el diálogo correspondiente según el botón presionado
                showDialog?.let {
                    AlertDialogContent(
                        dialogType = it,
                        onDismiss = { showDialog = null },
                        onConfirmAction = { newMessage ->
                            message = newMessage
                            showDialog = null
                        }
                    )
                }
            }
        }
    )
}

// Enum para los tipos de diálogo
enum class DialogType {
    Confirmacion, Eliminacion, Informacion, Autenticacion, Error
}

// Composable que muestra el contenido del diálogo
@Composable
fun AlertDialogContent(
    dialogType: DialogType,
    onDismiss: () -> Unit,
    onConfirmAction: (String) -> Unit
) {
    when (dialogType) {
        DialogType.Confirmacion -> {
            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text("Confirmación de Acción") },
                text = { Text("¿Estás seguro de que deseas continuar con esta acción?") },
                confirmButton = {
                    TextButton(onClick = { onConfirmAction("Acción Confirmada") }) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text("No")
                    }
                }
            )
        }
        DialogType.Eliminacion -> {
            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text("Eliminar Elemento") },
                text = { Text("Esta acción es irreversible. ¿Deseas eliminar este elemento?") },
                confirmButton = {
                    TextButton(onClick = { onConfirmAction("Elemento Eliminado") }) {
                        Text("Eliminar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                }
            )
        }
        DialogType.Informacion -> {
            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text("Aviso Importante") },
                text = { Text("Recuerda que los cambios realizados no se pueden deshacer.") },
                confirmButton = {
                    TextButton(onClick = onDismiss) {
                        Text("Entendido")
                    }
                }
            )
        }
        DialogType.Autenticacion -> {
            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text("Requiere Autenticación") },
                text = { Text("Para continuar, necesitas autenticarte de nuevo.") },
                confirmButton = {
                    TextButton(onClick = { onConfirmAction("Usuario Autenticado") }) {
                        Text("Autenticar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                }
            )
        }
        DialogType.Error -> {
            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text("Error Crítico") },
                text = { Text("Se ha producido un error crítico. ¿Deseas intentar nuevamente?") },
                confirmButton = {
                    TextButton(onClick = { onConfirmAction("Intento de Reintento") }) {
                        Text("Reintentar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AlertasTheme {
        MainScreen()
    }
}