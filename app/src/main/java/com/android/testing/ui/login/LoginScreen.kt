package com.android.testing.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.testing.R
import com.android.testing.ui.composables.CustomCheckbox
import com.android.testing.ui.composables.CustomEditText
import com.android.testing.ui.composables.CustomLoader
import com.android.testing.ui.theme.TestingCourseTheme

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onUserAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier
) {
    
    Column(modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer)) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(0.9f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {

            Text(
                text = stringResource(R.string.sign_in),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            CustomEditText(
                value = uiState.name.orEmpty(),
                onValueChange = {
                    onUserAction.invoke(LoginAction.OnUserNameChange(it))
                },
                label = stringResource(R.string.name),
                supportingText = stringResource(R.string.supporting_text_name)
            )
            CustomEditText(
                value = uiState.password.orEmpty(),
                onValueChange = {
                    onUserAction.invoke(LoginAction.OnUserPasswordChange(it))
                },
                label = stringResource(R.string.password),
                isPassword = true,
                supportingText = stringResource(R.string.supporting_text_password)
            )

            CustomEditText(
                value = uiState.email.orEmpty(),
                onValueChange = {
                    onUserAction.invoke(LoginAction.OnUserEmailChange(it))
                },
                label = stringResource(R.string.email),
                supportingText = stringResource(R.string.supporting_text_email)
            )

            CustomEditText(
                value = uiState.age?.toString() ?: "",
                onValueChange = { age ->
                    age.toIntOrNull()
                        ?.let { onUserAction.invoke(LoginAction.OnUserAgeChange(it)) }
                },
                label = stringResource(R.string.age),
                supportingText = stringResource(R.string.supporting_age_validator),
                isNumber = true
            )

            CustomCheckbox(
                text = stringResource(R.string.terms_and_conditions),
                checked = uiState.termsAndConditionsChecked,
                onCheckedChange = {
                    onUserAction.invoke(LoginAction.OnUserChangeTermsAndConditions(it))
                },
                modifier = Modifier.padding(top = 8.dp)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .navigationBarsPadding()
            ) {
                Button(
                    enabled = uiState.canLogin,
                    onClick = { onUserAction.invoke(LoginAction.Login) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors()
                        .copy(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                ) {
                    Text(
                        text = stringResource(R.string.create_account),
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }

    if (uiState.isLoading) {
        CustomLoader()
    }

}

@Composable
@Preview(device = Devices.PIXEL_7, showBackground = true)
private fun LoginScreenPreview() {
    TestingCourseTheme {
        val uiState = LoginUiState(isLoading = true)
        LoginScreen(uiState = uiState, onUserAction = { }, modifier = Modifier.fillMaxSize())
    }
}