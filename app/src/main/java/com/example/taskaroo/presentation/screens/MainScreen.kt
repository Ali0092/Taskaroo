package com.example.taskaroo.presentation.screens

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.domain.model.Task
import com.example.taskaroo.presentation.nav_component.SimpleScreenNavigationItem
import com.example.taskaroo.presentation.viewmodel.TaskViewModel
import com.example.taskaroo.presentation.viewmodel.UserViewModel
import com.example.taskaroo.presentation.viewstates.UserViewState
import com.example.taskaroo.ui.theme.backgroundColor
import com.example.taskaroo.ui.theme.blue
import com.example.taskaroo.ui.theme.cardColor
import com.example.taskaroo.ui.theme.darKRed
import com.example.taskaroo.ui.theme.green
import com.example.taskaroo.ui.theme.orange
import com.example.taskaroo.ui.theme.red
import com.example.taskaroo.ui.theme.textColor
import org.koin.androidx.compose.get
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navController: NavController, userViewModel: UserViewModel = get(), taskViewModel: TaskViewModel = get()
) {
    val getUserData = userViewModel.userData.collectAsState()
    val taskList = taskViewModel.tasks.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopBar(navController,getUserData.value)
    }, bottomBar = {}, floatingActionButton = {},
        content = { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(backgroundColor)
        ) {
            //its loading
            if (taskList.value.isLoading) {
                items(10) {
                    ShimmerCard()
                }
            }else {

                if (taskList.value.error!= null) { //its error
                    item {
                        Column(
                            modifier = Modifier
                                .height(600.sdp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Image(
                                painter = painterResource(R.drawable.blank_list),
                                contentDescription = null,
                                modifier = Modifier.padding(horizontal = 50.sdp),
                            )
                            Text(
                                text = stringResource(R.string.labelEmptyScreen),
                                color = textColor,
                                fontSize = 14.textSdp
                            )
                        }
                    }

                } else { //its data
                    taskList.value.tasks?.let { list ->
                        items(list.size) { index ->
                            ItemTaskSection(
                                data = list[index],
                                getClicked = { data ->
                                    taskViewModel.setSelectedTask(data)
                                    navController.navigate("${SimpleScreenNavigationItem.AddTask.route}/1")
                                },
                                removeTheTask = { task->
                                    taskViewModel.deleteTask(task)
                                }
                            )
                        }
                    }
                }
            }
        }
    })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemTaskSection(
    data: Task, getClicked: (Task) -> Unit, removeTheTask: (Task) -> Unit
) {
    val dateFormater = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    var showRemoveDialogState by remember { mutableStateOf(false) }

    RemoveTaskDialog(
        showDialog = showRemoveDialogState,
        onDismiss = {
            showRemoveDialogState = false
        },
        removeTheTask = {
            removeTheTask(data)
        }
    )

    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 8.sdp, start = 24.sdp, end = 24.sdp)
            .combinedClickable(true, onClick = {
                getClicked(data)
            }, onLongClick = {
                showRemoveDialogState = !showRemoveDialogState
            }),
        shape = RoundedCornerShape(20.sdp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.sdp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    modifier = Modifier.weight(1f),
                    text = data.title,
                    color = textColor,
                    fontSize = 17.textSdp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true
                )

                Spacer(modifier = Modifier.width(10.sdp))

                Card(
                    modifier = Modifier.wrapContentSize(),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = if (data.priority == "Low") blue else if (data.priority == "Medium") orange else darKRed),
                    border = BorderStroke(width = 0.4.dp, color = textColor)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.sdp, vertical = 2.sdp),
                        text = data.priority,
                        color = textColor,
                        fontSize = 12.textSdp
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.sdp))

            Text(
                text = data.description,
                color = textColor,
                fontSize = 13.textSdp,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                modifier = Modifier.alpha(0.9f)
            )

            Spacer(modifier = Modifier.height(8.sdp))

            Row(modifier = Modifier.fillMaxWidth()) {

                Icon(
                    painter = painterResource(R.drawable.icon_category),
                    contentDescription = null,
                    tint = green,
                    modifier = Modifier.size(20.sdp)
                )

                Spacer(modifier = Modifier.width(5.sdp))

                Text(
                    text = data.category,
                    color = textColor,
                    fontSize = 12.textSdp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                )

                Spacer(modifier = Modifier.width(12.sdp))

                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = null,
                    tint = red,
                    modifier = Modifier.size(20.sdp)
                )

                Spacer(modifier = Modifier.width(5.sdp))

                Text(
                    text = dateFormater.format(data.startDate) + " - " + dateFormater.format(
                        data.dueDate
                    ),
                    color = textColor,
                    fontSize = 12.textSdp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                )
            }
        }
    }

}

@Composable
fun TopBar(
    navController: NavController,
    data: UserViewState,
    viewModel: TaskViewModel = get()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth()
            .background(backgroundColor)
            .padding(top = 44.sdp, start = 24.sdp, end = 24.sdp, bottom = 16.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = if (data.isLoading) "Loading...." else data.user?.firstName.toString(),
                color = textColor,
                fontSize = 21.textSdp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = stringResource(R.string.labelMotivation),
                modifier = Modifier.padding(top = 3.sdp),
                color = red,
                fontSize = 16.textSdp,
                fontWeight = FontWeight.Normal,
            )
        }

        Icon(
            modifier = Modifier
                .size(28.sdp)
                .clickable {
                    viewModel.setTaskToBeAdded(Task())
                    viewModel.setSelectedTask(Task())
                    navController.navigate("${SimpleScreenNavigationItem.AddTask.route}/0")
                },
            painter = painterResource(R.drawable.add), contentDescription = null, tint = textColor
        )
    }
}

@Composable
fun RemoveTaskDialog(
    showDialog: Boolean, onDismiss: () -> Unit, removeTheTask: () -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = {
            onDismiss()
        }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(cardColor, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 16.sdp, horizontal = 16.sdp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.removeTaskTitle),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = stringResource(R.string.removeTaskSubTitle),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(12.dp))

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
                            color = textColor,
                            fontWeight = FontWeight.Bold
                        )

                        Button(
                            onClick = {
                                removeTheTask()
                                onDismiss()
                            },
                            modifier = Modifier.padding(start = 14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = red)
                        ) {
                            Text(
                               text = stringResource(R.string.buttonRemove),
                                color = textColor,
                                fontSize = 16.textSdp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp)
            .shimmerEffect(),  // Apply shimmer
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {}
}

@Composable
fun Modifier.shimmerEffect(): Modifier {
    val transition = rememberInfiniteTransition()
    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val translateX by transition.animateFloat(
        initialValue = -1000f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    return this
        .graphicsLayer {
            clip = true
            alpha = 0.8f
        }
        .background(
            brush = Brush.linearGradient(
                colors = shimmerColor,
                start = Offset(translateX, 0f),
                end = Offset(translateX + 500f, 0f)
            )
        )
}