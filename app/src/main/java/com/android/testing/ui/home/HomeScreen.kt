package com.android.testing.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(uiState: HomeUiState, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Bienvenido ${uiState.userName}",
            modifier = modifier
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .testTag("name"),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}