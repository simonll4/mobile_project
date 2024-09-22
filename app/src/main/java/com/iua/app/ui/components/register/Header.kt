package com.iua.app.ui.components.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.iua.app.R
import com.iua.app.ui.components.app.NormalTextComponent
import com.iua.app.ui.components.app.TitleTextComponent


@Composable
fun Header() {
    NormalTextComponent(text = stringResource(R.string.welcome_to_register))

    TitleTextComponent(text = stringResource(R.string.create_account))
}
