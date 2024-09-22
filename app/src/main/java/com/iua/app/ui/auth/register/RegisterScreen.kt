package com.iua.app.ui.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iua.app.R
import com.iua.app.ui.components.app.CheckBoxComponent
import androidx.navigation.NavController
import com.iua.app.ui.components.app.ButtonComponent
import com.iua.app.ui.components.app.ClickableTextComponent
import com.iua.app.ui.components.app.FieldTextComponent
import com.iua.app.ui.components.app.FieldType
import com.iua.app.ui.components.app.NormalTextComponent
import com.iua.app.ui.components.app.TitleTextComponent
import com.iua.app.ui.navigation.AppScreens

@Composable
fun RegisterScreen(navController: NavController) {
    var textField: String = "" // TODO aca van los textos para cada campo sacado del viewmodel

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(text = stringResource(R.string.welcome_to_register))
            TitleTextComponent(text = stringResource(R.string.create_account))

            Spacer(modifier = Modifier.padding(20.dp))
            FieldTextComponent(
                labelValue = stringResource(
                    R.string.first_name,
                ), icon = Icons.Default.Person, textField, FieldType.TEXT
            ) { }
            FieldTextComponent(
                labelValue = stringResource(
                    R.string.last_name,
                ), icon = Icons.Default.Person, textField, fieldType = FieldType.TEXT
            ) { }
            FieldTextComponent(
                labelValue = stringResource(
                    R.string.email,
                ), icon = Icons.Default.Email, textField, fieldType = FieldType.EMAIL
            ) { }
            FieldTextComponent(
                labelValue = stringResource(
                    R.string.password,
                ), icon = Icons.Default.Lock, textField, fieldType = FieldType.PASSWORD
            ) { }
            FieldTextComponent(
                labelValue = stringResource(
                    R.string.repeat_password,
                ), icon = Icons.Default.Lock, textField, fieldType = FieldType.PASSWORD
            ) { }

            CheckBoxComponent(
                text = stringResource(R.string.terms_and_conditions),
                clickableWords = listOf("Privacy", "Policy", "Terms", "and", "of", "Use"),
                onClick = { clickedWord ->
                    navController.navigate(AppScreens.TermsAndConditionsScreen.routes)
                }
            )

            ButtonComponent(text = stringResource(R.string.register_button))

            //TODO pasar a componente
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),  // Espacio superior e inferior
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Primera línea horizontal
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp),
                    color = Color.LightGray
                )

                // Texto "or" con padding
                Text(
                    text = "or",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                // Segunda línea horizontal
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp),
                    color = Color.LightGray
                )
            }

            ClickableTextComponent(
                text = stringResource(R.string.already_have_an_account),
                clickableWords = listOf("Login"),
                onClick = {
                    navController.navigate(AppScreens.LoginScreen.routes)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

        }
    }
}
