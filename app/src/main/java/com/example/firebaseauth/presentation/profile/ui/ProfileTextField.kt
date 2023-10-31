package com.example.firebaseauth.presentation.profile.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.firebaseauth.R
import com.example.firebaseauth.ui.theme.FocusEmptyColor
import com.example.firebaseauth.ui.theme.ProfileInputTextColor
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ShowProfileTextField(
    modifier: Modifier,
    label: String,
    text: StateFlow<String>,
    saveClicked: Boolean,
    onChangeValue: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val content = text.collectAsState()
    OutlinedTextField(
        modifier = setModifier(
            modifier = modifier,
            btnClicked = saveClicked,
            fieldEmpty = content.value.isBlank(),
            focusRequester
        ),
        maxLines = 2,
        value = content.value,
        onValueChange = {
            onChangeValue(it)
        },
        label = {
            Text(
                modifier = Modifier.wrapContentSize(),
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
            focusedBorderColor = if (saveClicked && content.value.isBlank()) FocusEmptyColor else Color.Black,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Gray,
            focusedTextColor = ProfileInputTextColor,
            unfocusedTextColor = Color.Gray
        ),
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.Black,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_medium
                )
            )
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = if (saveClicked && content.value.isBlank()) ImeAction.Done else ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (saveClicked && content.value.isBlank()) {
                    focusRequester.requestFocus()
                }
            }
        ),
        interactionSource = remember { MutableInteractionSource() },
    )
    if (saveClicked && content.value.isBlank()) {
        DisposableEffect(Unit) {
            focusRequester.requestFocus()
            onDispose { /* cleanup if necessary */ }
        }
    }

}

private fun setModifier(
    modifier: Modifier,
    btnClicked: Boolean,
    fieldEmpty: Boolean,
    focusRequester: FocusRequester
): Modifier {
    if (btnClicked && fieldEmpty) {
        return modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
    }
    return modifier
        .fillMaxWidth()
}

@Composable
@Preview
fun PreviewShowProfileTextField() {

}