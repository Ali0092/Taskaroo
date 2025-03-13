package com.example.taskaroo.presentation.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.domain.model.CategoryModel
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
    navController: NavController,
    userViewModel: UserViewModel,
    taskViewModel: TaskViewModel = get()
) {

    val getUserData = userViewModel.userData.collectAsState()
    val taskList = taskViewModel.tasks.collectAsStateWithLifecycle()

    Log.d("checkingTaskAddingIssue", "getUserData: ${getUserData.value}")
    Log.d("checkingTaskAddingIssue", "taskList: ${taskList.value}")

    Scaffold(topBar = {}, bottomBar = {}, floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier.padding(bottom = 24.sdp, end = 12.sdp),
            onClick = {
                taskViewModel.setTaskToBeAdded(Task())
                taskViewModel.setSelectedTask(Task())
                navController.navigate("${SimpleScreenNavigationItem.AddTask.route}/0")
        }, containerColor = red) {
            Icon(
                imageVector = Icons.Rounded.Add, contentDescription = null, tint = Color.White
            )
        }
    }, content = { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(backgroundColor)
        ) {
            stickyHeader {
                TopBar(getUserData.value)
            }
            item {
//                    SearchBar()
            }
            //do this in the end
//                item{
//                    SectionCategories()
//                    Spacer(Modifier.height(24.sdp))
//                }
            item {
//                Text(
//                    text = "Tasks",
//                    color = textColor,
//                    fontSize = 18.textSdp,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(start = 24.sdp)
//                )
//                Spacer(modifier = Modifier.height(8.sdp))
            }
            if (taskList.value.tasks == null) {
                item {
                    Column(
                        modifier = Modifier
                            .height(600.sdp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No tasks available",
                            color = textColor,
                            fontSize = 14.textSdp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            } else {
                taskList.value.tasks?.let { list ->
                    items(list.size) { index ->
                        ItemTaskSection(list[index]) { data->

                            taskViewModel.setSelectedTask(data)
                            navController.navigate("${SimpleScreenNavigationItem.AddTask.route}/1")

                        }
                    }
                }

            }

        }
    })
}

@Composable
fun ItemTaskSection(
    data: Task,
    getClicked: (Task) -> Unit
) {

    val dateFormater = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 8.sdp, start = 24.sdp, end = 24.sdp)
            .clickable {
                getClicked(data)
            },
        shape = RoundedCornerShape(20.sdp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.sdp).fillMaxSize()
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
                    colors = CardDefaults.cardColors(containerColor = if (data.priority=="Low")blue else if(data.priority=="Medium") orange else darKRed),
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
                fontSize = 12.textSdp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                modifier = Modifier.alpha(0.7f)
            )

            Spacer(modifier = Modifier.height(8.sdp))

            Row(modifier = Modifier.fillMaxWidth()) {

                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = null,
                    tint = red,
                    modifier = Modifier.size(20.sdp)
                )

                Spacer(modifier = Modifier.width(5.sdp))

                Text(
                    text =  dateFormater.format(data.startDate)+" - "+dateFormater.format(data.dueDate),
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
fun SectionCategories() {

    val categoriesList = listOf(
        CategoryModel(title = "Mobile App", totalTasks = 10, icon = R.drawable.cat_android),
        CategoryModel(title = "Web App", totalTasks = 10, icon = R.drawable.cat_web),
        CategoryModel(title = "Personal ", totalTasks = 10, icon = R.drawable.cat_personal),
        CategoryModel(title = "Travel", totalTasks = 10, icon = R.drawable.cat_travel),
        CategoryModel(title = "Professional", totalTasks = 10, icon = R.drawable.cat_work),
        CategoryModel(title = "Art", totalTasks = 10, icon = R.drawable.car_art),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.sdp)
    ) {
        Text(
            text = "Categories",
            color = textColor,
            fontSize = 18.textSdp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.sdp))

        LazyRow {
            items(categoriesList) { category ->
                ItemCategoriesSection(
                    title = category.title, totalTasks = category.totalTasks, icon = category.icon
                )
            }
        }

    }

}

@Composable
fun ItemCategoriesSection(title: String, totalTasks: Int, icon: Int) {

    Box(
        modifier = Modifier
            .width(150.sdp)
            .height(160.sdp)
            .padding(end = 16.sdp)
            .clip(RoundedCornerShape(20.sdp))
            .background(cardColor)
    ) {
        Column(
            modifier = Modifier.padding(start = 16.sdp, top = 16.sdp)
        ) {

            Text(
                text = title,
                color = textColor,
                fontSize = 17.textSdp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = "${totalTasks} Pending Tasks",
                modifier = Modifier
                    .padding(top = 1.sdp)
                    .alpha(0.7f),
                color = textColor,
                fontSize = 13.textSdp,
                fontWeight = FontWeight.Normal,
            )

            Spacer(modifier = Modifier.height(8.sdp))

        }

        Image(
            painter = painterResource(icon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.sdp)
                .offset(x = 1.sdp, y = 5.sdp)
                .align(Alignment.BottomEnd)
        )

    }
}

@Composable
fun SearchBar() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.sdp, horizontal = 24.sdp)
            .background(Color.Transparent)
            .clickable {},
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = cardColor),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 12.sdp, horizontal = 24.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            ) {
            Image(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                modifier = Modifier
                    .size(25.sdp)
                    .alpha(0.6f),
                colorFilter = ColorFilter.tint(textColor)
            )

            Text(
                text = "search ",
                modifier = Modifier
                    .padding(start = 3.sdp)
                    .alpha(0.6f),
                color = textColor,
                fontSize = 16.textSdp,
                fontWeight = FontWeight.Normal,
            )
        }
    }

}

@Composable
fun TopBar(data: UserViewState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth()
            .background(backgroundColor)
            .padding(top = 24.sdp, start = 24.sdp, end = 24.sdp, bottom = 16.sdp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = data.user?.name.toString(),
                color = textColor,
                fontSize = 21.textSdp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = "Plan it, track it, crush it!",
                modifier = Modifier.padding(top = 3.sdp),
                color = red,
                fontSize = 14.textSdp,
                fontWeight = FontWeight.Normal,
            )
        }


        Card(
            modifier = Modifier.size(45.sdp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            border = BorderStroke(width = 0.4.dp, color = textColor)
        ) {
            if (data.user != null) {
                Log.d("getUserDataLogsd", "TopBar: ${data.user.image}")
//                AsyncImage(
//                    model = data.user.image.toString(),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.fillMaxSize()
//                )
                Image(
                    painter = painterResource(R.drawable.onboarding_1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.onboarding_1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
    }
}