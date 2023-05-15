package com.example.notesappjetpackcompose.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(modifier: Modifier= Modifier,
            text: String,
            label: String,
            maxLines: Int=1,
            onTextChanged: (String)->Unit,
            onImeAction: ()->Unit ={}){

    val keyboardController= LocalSoftwareKeyboardController.current
    TextField(modifier = modifier,value = text, onValueChange = onTextChanged, label = { Text(text = label)} ,maxLines = maxLines,
    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    keyboardActions = KeyboardActions(onDone = {
        onImeAction()
        keyboardController?.hide()
    })
    )
}

@Composable
fun NoteButton(modifier: Modifier = Modifier, text: String, onClick: ()-> Unit, enabled: Boolean=true){
    Button(onClick = {onClick.invoke()}, shape = CircleShape, enabled = enabled, modifier = modifier) {
        Text(text = text)
    }
}