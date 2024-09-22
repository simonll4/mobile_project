package com.iua.app.ui.components.app

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
import androidx.compose.ui.unit.dp

@Composable
fun FieldTextComponent(
    labelValue: String,
    icon: ImageVector,
    text: String,
    fieldType: FieldType,
    onTextChanged: (String) -> Unit
) {

    val keyboardOptions = when (fieldType) {
        FieldType.PASSWORD -> KeyboardOptions(keyboardType = KeyboardType.Password)
        FieldType.EMAIL -> KeyboardOptions(keyboardType = KeyboardType.Email)
        FieldType.TEXT -> KeyboardOptions(keyboardType = KeyboardType.Text)
    }

    TextField(
        value = text,
        onValueChange = { onTextChanged(it) },
        label = { Text(text = labelValue) },
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp)),
        keyboardOptions = keyboardOptions, // TODO ver como manejar esto para todos los tipos de inputs
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        ),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "$labelValue Icon"
            )
        },  // TODO tunear este componente para que quede mas entendible
        trailingIcon = {
            var passwordVisible by remember { mutableStateOf(false) }
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

enum class FieldType {
    PASSWORD,
    EMAIL,
    TEXT
}


//TextField(
//value = text,
//onValueChange = { onEmailChanged(it) },
//label = { Text(text = labelValue) },
//keyboardOptions = KeyboardOptions.Default,
//singleLine = true,
//value = text,
//onValueChange = { text = it })
//onValueChange = TextFieldDefaults.outlinedTextFieldColors(
//focusedBorderColor = Color(0xED17DC09),
//focusedLabelColor = Color(0xFFFF0000),
//cursorColor = Color(0xFF006AFF),
//)