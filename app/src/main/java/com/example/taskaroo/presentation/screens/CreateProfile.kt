package com.example.taskaroo.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.taskaroo.common.sdp
import com.example.taskaroo.data.datastore.DataStoreManager
import com.example.taskaroo.domain.model.User
import com.example.taskaroo.presentation.components.DotIndicator
import com.example.taskaroo.presentation.nav_component.Screens
import com.example.taskaroo.presentation.viewmodel.PrefsViewModel
import com.example.taskaroo.presentation.viewmodel.UserViewModel
import com.example.taskaroo.ui.theme.backgroundColor
import com.example.taskaroo.ui.theme.textColor
import com.example.taskaroo.ui.theme.red
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun CreateProfile(
    navController: NavController, viewModel: PrefsViewModel, userViewModel: UserViewModel = get()) {

    val pagerState = rememberPagerState(pageCount = { 2 })
    val animationScope = rememberCoroutineScope()
    var buttonText = remember { mutableStateOf("Continue") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(start = 24.sdp, end = 24.sdp, top = 44.sdp)
    ) {

        HorizontalPager(pagerState, modifier = Modifier.weight(1f)) { page->
            if (page==0){
                SelectPreferences()
            }else {
                BasicInfoScreen()
            }
        }

        DotIndicator(2, pagerState.currentPage)

        Spacer(modifier = Modifier.height(8.sdp))

        Button(
            onClick = {
                animationScope.launch {
                    if (pagerState.currentPage==0){
                        buttonText.value = "Get Started !"
                        pagerState.animateScrollToPage(1)
                    }else {
                        checkoutCreateUserValidation(userViewModel.user.value){ canCreateProfile, error ->
                            //1.Store it in Room
                            if (error!=null) {
                                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                            }else {
                                userViewModel.setUser(userViewModel.user.value.copy(id = System.currentTimeMillis()))
                                userViewModel.createUserData()
                                //2.Update in dataStore
                                viewModel.saveBooleanPrefs(DataStoreManager.USER_PROFILE_DONE_KEY,true)
                                //3.navigate to next
                                navController.navigate(Screens.MAIN.name)
                            }
                        }
                    }
                }

            },
            colors = ButtonDefaults.buttonColors(containerColor = red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.sdp)
        ) {
            Text(buttonText.value, modifier = Modifier.padding(vertical = 8.sdp), color = textColor)
        }

    }


}
fun checkoutCreateUserValidation(user: User,canCreateUser:(Boolean,String?) -> Unit){
    user.let {
        if (it.firstName.isEmpty()){
            canCreateUser(false, "First Name cannot be empty")
        } else if(it.preferences.isEmpty()) {
            canCreateUser(false,"Please select at least one preference")
        } else if(it.lastName.isEmpty()) {
            canCreateUser(false,"Last Name is required")
        }else {
            canCreateUser(true,null)
        }
    }

}