package com.android.testing.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.testing.R

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Bienvenido ${uiState.userName}",
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .testTag("name"),
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = stringResource(R.string.test),
            modifier = modifier
                .statusBarsPadding()
                .padding(horizontal = 16.dp)
                .testTag("name"),
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = onClick,
            modifier = modifier
                .padding(16.dp)
        ) { Text("Logout") }
    }
}