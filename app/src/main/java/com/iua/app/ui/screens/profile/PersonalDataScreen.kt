package com.iua.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.iua.app.R
import com.iua.app.ui.components.TopAppBarComponent
import com.iua.app.ui.navigation.AppScreens
import com.iua.app.ui.view_models.PersonalDataViewModel

@Composable
fun PersonalDataScreen(
    personalDataViewModel: PersonalDataViewModel = hiltViewModel(), navController: NavHostController
) {

    val user by personalDataViewModel.user.observeAsState()
    val isLoading by personalDataViewModel.isLoading.observeAsState(false)
    val deleteState by personalDataViewModel.deleteAccountState.collectAsState()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBarComponent(
                navController = navController,
                stringResource(id = R.string.personal_data_top_bar),
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(vertical = 5.dp, horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            user?.let { currentUser ->
                ProfileDataSection(label = stringResource(id = R.string.personal_data_name),
                    value = currentUser.firstName,
                    onEditConfirm = { newValue ->
                        personalDataViewModel.updateField(field = "firstName", value = newValue)
                    })
                ProfileDataSection(label = stringResource(id = R.string.personal_data_last_name),
                    value = currentUser.lastName,
                    onEditConfirm = { newValue ->
                        personalDataViewModel.updateField(field = "lastName", value = newValue)
                    })
                ProfileDataSection(label = stringResource(id = R.string.personal_data_email),
                    value = currentUser.email,
                    onEditConfirm = { newValue ->
                        personalDataViewModel.updateField(field = "email", value = newValue)
                    })
            }

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            Spacer(modifier = Modifier.weight(1f))

            // Estado de eliminación de cuenta
            when (deleteState) {
                is PersonalDataViewModel.DeleteAccountState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                is PersonalDataViewModel.DeleteAccountState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(AppScreens.LoginScreen.routes) {
                            popUpTo(0)
                        }
                    }
                }

                is PersonalDataViewModel.DeleteAccountState.Error -> {
                    // mensaje de errro basico
                    Text(
                        text = (deleteState as PersonalDataViewModel.DeleteAccountState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                else -> {}
            }

            TextButton(
                onClick = {
                    personalDataViewModel.deleteAccount(user!!.id)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent, contentColor = Color.Red
                )
            ) {
                Text(
                    text = stringResource(id = R.string.personal_data_delete_account_button),
                    color = Color.Red,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDataSection(
    label: String, value: String, onEditConfirm: (String) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var editableValue by remember { mutableStateOf(value) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleMedium
                )
                if (isEditing) {
                    OutlinedTextField(
                        value = editableValue,
                        onValueChange = { editableValue = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                } else {
                    Text(
                        text = value,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                    )
                }
            }
            IconButton(onClick = {
                isEditing = if (isEditing) {
                    onEditConfirm(editableValue)
                    false
                } else true
            }) {
                Icon(
                    imageVector = if (isEditing) Icons.Default.Check else Icons.Default.Edit,
                    contentDescription = if (isEditing) "Confirm Edit" else "Edit",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
    }
}