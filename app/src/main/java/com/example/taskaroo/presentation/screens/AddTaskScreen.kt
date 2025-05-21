package com.example.taskaroo.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.domain.model.Task
import com.example.taskaroo.presentation.components.IconSurface
import com.example.taskaroo.presentation.viewmodel.TaskViewModel
import com.example.taskaroo.ui.theme.Purple40
import com.example.taskaroo.ui.theme.TaskarooTheme
import com.example.taskaroo.ui.theme.background
import com.example.taskaroo.ui.theme.blue
import com.example.taskaroo.ui.theme.green
import com.example.taskaroo.ui.theme.onBackground
import com.example.taskaroo.ui.theme.orange
import com.example.taskaroo.ui.theme.primaryColor
import com.example.taskaroo.ui.theme.primaryColorVariant
import com.example.taskaroo.ui.theme.textColor
import org.koin.androidx.compose.get
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AddTaskScreen(
    navController: NavController,
    taskViewModel: TaskViewModel = get(),
    flag: Int = 0,
) {

    val selectedTask by taskViewModel.selectedTask.collectAsState()

    val dateFormater = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var category by remember { mutableStateOf(if (selectedTask.category=="") "-----" else selectedTask.category) }
    var priority by remember { mutableStateOf(if (selectedTask.priority=="") "-----" else selectedTask.priority) }
    var startDate by remember { mutableLongStateOf(selectedTask.startDate) }
    var endDate by remember { mutableLongStateOf(selectedTask.dueDate) }
    var taskTitle by remember { mutableStateOf(selectedTask.title) }
    var taskDetails by remember { mutableStateOf(selectedTask.description) }

    val context = LocalContext.current

    TaskarooTheme {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding()
                    .padding(top = 44.sdp)
            ) {
                //top row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.sdp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconSurface(icon = Icons.Rounded.KeyboardArrowLeft) {
                        navController.popBackStack()
                    }

                    Text(
                        text = "Add Task",
                        fontSize = 16.textSdp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 25.sdp),
                    )

                }

                Spacer(modifier = Modifier.height(16.sdp))

                //time and priority
                Text(
                    text = "Pick Priority & Time",
                    fontSize = 18.textSdp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 16.sdp)
                )

                Spacer(modifier = Modifier.height(16.sdp))

                //cat and priority
                Row(modifier = Modifier.padding(horizontal = 16.sdp)) {
                    ItemTimeAndPriority(
                        modifier = Modifier.weight(1f),
                        icon = R.drawable.icon_priority,
                        title = "Priority",
                        subTitle = priority,
                        tint = blue,
                        dialogTitle = "Select Task Priority",
                        dialogList = listOf("Low", "Medium", "High"),
                        type = 0
                    ) { p ->
                        priority = p
                        taskViewModel.setTaskToBeAdded(taskViewModel.taskToBeAdded.value.copy(priority = priority))

                    }
                    Spacer(modifier = Modifier.width(12.sdp))
                    ItemTimeAndPriority(
                        modifier = Modifier.weight(1f),
                        icon = R.drawable.icon_category,
                        title = "Category",
                        subTitle = category,
                        tint = orange,
                        dialogTitle = "Select Task Category",
                        dialogList = listOf(
                            "Default",
                            "Android",
                            "Web",
                            "Personal",
                            "Travel",
                            "Professional",
                            "Art"
                        ),
                        type = 1
                    ) { cat ->
                        //show in card
                        category = cat
                        taskViewModel.setTaskToBeAdded(taskViewModel.taskToBeAdded.value.copy(category = category))
                    }

                }
                Spacer(modifier = Modifier.height(12.sdp))
                Row(modifier = Modifier.padding(horizontal = 16.sdp)) {
                    ItemTimeAndPriority(
                        modifier = Modifier.weight(1f),
                        icon = R.drawable.icon_clock,
                        title = "Start Time",
                        subTitle = dateFormater.format(Date(startDate)),
                        tint = green,
                        dialogTitle = "Start Time",
                        dialogList = null,
                        type = 3
                    ) { date ->
                        startDate = date.toLong()
                        taskViewModel.setTaskToBeAdded(taskViewModel.taskToBeAdded.value.copy(startDate = startDate))
                    }
                    Spacer(modifier = Modifier.width(12.sdp))
                    //timeEnd
                    ItemTimeAndPriority(
                        modifier = Modifier.weight(1f),
                        icon = R.drawable.icon_clock,
                        title = "End Time",
                        subTitle = dateFormater.format(Date(endDate)),
                        tint = Purple40,
                        dialogTitle = "End Time",
                        dialogList = null,
                        type = 4
                    ) { date ->
                        endDate = date.toLong()
                        taskViewModel.setTaskToBeAdded(taskViewModel.taskToBeAdded.value.copy(dueDate = endDate))
                    }
                }
                Spacer(modifier = Modifier.height(24.sdp))

                Text(
                    text = "Task Details",
                    fontSize = 18.textSdp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 24.sdp, end = 24.sdp)
                )

                Spacer(modifier = Modifier.height(8.sdp))
                CustomTextField(placeHolderText = "Enter Title", text = taskTitle, onValueChange = {
                    taskTitle = it
                    taskViewModel.setTaskToBeAdded(taskViewModel.taskToBeAdded.value.copy(title = taskTitle))
                })
                CustomTextField(placeHolderText = "Description", text = taskDetails, onValueChange = {
                    taskDetails = it
                    taskViewModel.setTaskToBeAdded(taskViewModel.taskToBeAdded.value.copy(description = taskDetails))
                })
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 32.sdp, start = 32.sdp, end = 32.sdp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(50.sdp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable {
                        if (taskViewModel.taskToBeAdded.value == Task()) {
                            Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT)
                                .show()
                            return@clickable
                        }
                        if (flag == 0) {
                            taskViewModel.createTask()
                        } else {
                            taskViewModel.updateTask()
                        }
                        navController.popBackStack()
                    },
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 14.sdp)
                        .align(Alignment.Center),
                    text = "Add a task",
                    fontSize = 16.textSdp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Icon(
                    imageVector = Icons.Rounded.AddCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .size(46.sdp)
                        .offset(x = -4.sdp)
                        .align(Alignment.CenterEnd),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

        }

    }


}

@Composable
fun CustomTextField(placeHolderText: String, text: String, onValueChange: (String) -> Unit) {

    TextField(
        value = text,
        placeholder = { Text(placeHolderText) },
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.sdp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onBackground,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = MaterialTheme.colorScheme.onBackground,
            unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
        ),
        shape = RectangleShape,
        textStyle = TextStyle(fontSize = 20.textSdp),
    )
}

@Composable
fun ItemTimeAndPriority(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    subTitle: String,
    dialogTitle: String,
    dialogList: List<String>? = null,
    tint: Color,
    type: Int = 0,
    onDone: (String) -> Unit,
) {

    var canShowDialog by remember { mutableStateOf(false) }
    var canShowDatePickerDialog by remember { mutableStateOf(false) }

    if (type == 0 || type == 1) {
        TaskPriorityDialog(
            title = dialogTitle,
            dropDownList = dialogList,
            showDialog = canShowDialog,
            onDismiss = {
                canShowDialog = false
            },
            onAdd = { selected ->
                canShowDialog = false
                onDone(selected)
            })
    } else {
        DatePickerDialogSample(showDatePicker = canShowDatePickerDialog, onDismiss = {
            canShowDatePickerDialog = false
        }, onDateSelected = {
            canShowDatePickerDialog = false
            onDone(it.toString())
        })
    }

    Card(
        modifier = modifier
            .height(70.sdp)
            .clickable {
                if (type == 0 || type == 1) {
                    canShowDialog = true
                } else {
                    canShowDatePickerDialog = true
                }

            },
        shape = RoundedCornerShape(20.sdp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(25.sdp),
                colorFilter = ColorFilter.tint(tint)
            )
            Spacer(modifier = Modifier.width(6.sdp))
            Column {
                Text(
                    text = title,
                    fontSize = 12.textSdp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.alpha(0.7f)
                )
                Spacer(modifier = Modifier.width(2.sdp))
                Text(
                    text = subTitle, fontSize = 14.textSdp, color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun TaskPriorityDialog(
    title: String,
    dropDownList: List<String>? = null,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onAdd: (String) -> Unit,
) {
    if (showDialog) {
        Dialog(onDismissRequest = {
            onDismiss()
        }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(background, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 24.sdp, horizontal = 16.sdp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    // Title
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Priority Dropdown
                    var selectedPriority by remember {
                        mutableStateOf(
                            dropDownList?.get(0).toString()
                        )
                    }
                    var expanded by remember { mutableStateOf(false) }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                            .clickable { expanded = true }
                            .padding(12.dp),
                        contentAlignment = Alignment.CenterStart) {

                        Text(selectedPriority, color = primaryColorVariant)

                        DropdownMenu(
                            expanded = expanded,
                            containerColor = onBackground,
                            onDismissRequest = { expanded = false }) {
                            dropDownList?.forEach { priority ->
                                DropdownMenuItem(
                                    colors = MenuDefaults.itemColors(
                                    textColor = primaryColor,
                                ),
                                    text = { Text(priority, color = primaryColorVariant) },
                                    onClick = {
                                        selectedPriority = priority
                                        expanded = false
                                    })
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            modifier = Modifier.clickable {
                                onDismiss()
                            },
                            text = stringResource(R.string.buttonCancel),
                            fontSize = 14.textSdp,
                            color = primaryColor,
                            fontWeight = FontWeight.Bold
                        )

                        Button(
                            onClick = {
                                onAdd(selectedPriority)
                                onDismiss()
                            },
                            modifier = Modifier.padding(start = 14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                        ) {
                            Text(
                                text = stringResource(R.string.buttonAdd),
                                color = textColor,
                                fontSize = 14.textSdp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogSample(
    showDatePicker: Boolean,
    onDismiss: () -> Unit,
    onDateSelected: (Long) -> Unit,
) {
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()

        DatePickerDialog(onDismissRequest = onDismiss, confirmButton = {
            TextButton(onClick = {
                onDateSelected(
                    datePickerState.selectedDateMillis ?: System.currentTimeMillis()
                )
                onDismiss()
            }) {
                Text("OK")
            }
        }, dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }) {
            DatePicker(state = datePickerState)
        }
    }
}