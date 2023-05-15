package com.example.notesappjetpackcompose.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesappjetpackcompose.R
import com.example.notesappjetpackcompose.component.NoteButton
import com.example.notesappjetpackcompose.component.NoteInputText
import com.example.notesappjetpackcompose.data.NotesDataSource
import com.example.notesappjetpackcompose.model.Note
import com.example.notesappjetpackcompose.util.formatDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteScreen(modifier: Modifier= Modifier, notes: List<Note>, onAddNote: (Note)->Unit, onRemoveAdd: (Note)->Unit ){
    var title by remember {
        mutableStateOf("")
    }
    var description by remember{
        mutableStateOf("")
    }

    val context= LocalContext.current
    Column(modifier = modifier.padding(6.dp)) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name))}, actions = { Icon(
            imageVector = Icons.Rounded.Notifications,
            contentDescription = "Notification Icon"
        )}, backgroundColor = Color(0xFFDADFE3))
        Column(modifier = modifier
            .padding(6.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(text = title, label = "Title",
                onTextChanged ={
                if(it.all {
                    char->
                        char.isLetter()|| char.isWhitespace()
                    })
                    title=it
            } )
            NoteInputText(modifier= Modifier.padding(top = 9.dp, bottom = 8.dp),text = description, label = "Add a note",
                onTextChanged ={
                    if(it.all {
                        char->
                    char.isLetter()|| char.isWhitespace()
                })
                description=it
            } )
            NoteButton(text = "Save", onClick = {
                onAddNote(Note(title=title, description = description))
                title=""
                description=""
                Toast.makeText(context, "Note added",Toast.LENGTH_SHORT).show()
            })
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes){
                note->
                    NoteRow(modifier = Modifier, note = note, onNoteClicked ={
                        onRemoveAdd(note)
                    } )

            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(modifier: Modifier ,note: Note, onNoteClicked: (Note)-> Unit){
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
            color = Color(0xFFDFE6EB),
            elevation = 5.dp) {
        Column(modifier = modifier
            .clickable {
                onNoteClicked(note)
            }
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {

            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text =formatDate(note.entryDate.time),
            style = MaterialTheme.typography.caption)


        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(modifier = Modifier, notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveAdd = {})
}