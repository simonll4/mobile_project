package com.iua.app.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

enum class FieldType {
    TEXT,
    PASSWORD,
    EMAIL
}

@Composable
fun FieldTextComponent(
    labelValue: String,
    icon: ImageVector,
    text: String,
    fieldType: FieldType = FieldType.TEXT,
    onTextChanged: (String) -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }
    val visualTransformation = if (fieldType == FieldType.PASSWORD && !passwordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    val keyboardOptions = when (fieldType) {
        FieldType.PASSWORD -> KeyboardOptions(keyboardType = KeyboardType.Password)
        FieldType.EMAIL -> KeyboardOptions(keyboardType = KeyboardType.Email)
        FieldType.TEXT -> KeyboardOptions(keyboardType = KeyboardType.Text)
    }

    TextField(
        value = text,
        onValueChange = { onTextChanged(it) },
        label = { Text(text = labelValue, style = MaterialTheme.typography.bodyMedium) },
        modifier = Modifier
            .padding(7.dp)
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp)),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            focusedContainerColor = MaterialTheme.colorScheme.surface, // Color de fondo cuando está enfocado
            unfocusedContainerColor = MaterialTheme.colorScheme.background, // Color de fondo cuando no está enfocado
        ),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "$labelValue Icon"
            )
        },  // TODO tunear este componente para que quede mas entendible
        trailingIcon = {
            if (fieldType == FieldType.PASSWORD) {
                val image =
                    if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = image,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        },
    )
}