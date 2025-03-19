package com.android.testing.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.testing.R

@Composable
fun CustomLoader() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_loader))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { Unit }
            )
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Preview(device = Devices.PIXEL_7, showBackground = true)
@Composable
private fun LoaderPreview() {
    MaterialTheme {
        CustomLoader()
    }
}
