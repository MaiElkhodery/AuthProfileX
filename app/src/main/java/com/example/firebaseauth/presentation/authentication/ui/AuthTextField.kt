package com.example.firebaseauth.presentation.authentication.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebaseauth.R
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ShowTextField(
    label: String,
    text: StateFlow<String>,
    onChangeValue: (String) -> Unit
) {

    val content = text.collectAsState()
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        maxLines = 1,
        value = content.value,
        onValueChange = {
            onChangeValue(it)
        },
        label = {
            Text(
                text = label,
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_medium
                    )
                )
            )
        },
        shape = RoundedCornerShape(10),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.Gray,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        )
    )
}

@Composable
fun ShowPasswordTextField(
    text: StateFlow<String>,
    onChangeValue: (String) -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }
    val content = text.collectAsState()
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        maxLines = 1,
        value = content.value,
        onValueChange = {
            onChangeValue(it)
        },
        label = {
            Text(
                text = stringResource(id = R.string.password),
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_medium
                    )
                )
            )
        },
        shape = RoundedCornerShape(10),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.Gray,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // Localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            // Toggle button to hide or display password
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        }
    )
}

@Composable
@Preview
fun PreviewI() {
    //ShowInputField("email", state) {}
}