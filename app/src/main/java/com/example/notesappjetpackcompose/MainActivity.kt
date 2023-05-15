package com.example.notesappjetpackcompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesappjetpackcompose.data.NotesDataSource
import com.example.notesappjetpackcompose.model.Note
import com.example.notesappjetpackcompose.screen.NoteScreen
import com.example.notesappjetpackcompose.screen.NotesViewModel
import com.example.notesappjetpackcompose.ui.theme.NotesAppJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            NotesAppJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel= viewModel<NotesViewModel>()
                    Notes(viewModel)
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Notes(viewModel: NotesViewModel){
    val notes= viewModel.notes.collectAsState().value
    NoteScreen(modifier = Modifier, notes = notes,
        onAddNote = {
                note ->
            viewModel.addNote(note)
        }, onRemoveAdd = {
                note ->
            viewModel.deleteNote(note)
        })
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesAppJetpackComposeTheme {
        Greeting("Android")
    }
}