package com.example.taskaroo.koin

import androidx.room.Room
import com.example.taskaroo.data.datastore.DataStoreManager
import com.example.taskaroo.data.repositoryImp.TaskRepositoryImp
import com.example.taskaroo.data.repositoryImp.UserRepositoryImp
import com.example.taskaroo.data.room.TaskDao
import com.example.taskaroo.data.room.TaskarooDatabase
import com.example.taskaroo.data.room.UserDao
import com.example.taskaroo.domain.repository.TaskRepository
import com.example.taskaroo.domain.repository.UserRepository
import com.example.taskaroo.domain.usercases.task.CreateTaskUseCase
import com.example.taskaroo.domain.usercases.task.DeleteTaskUseCase
import com.example.taskaroo.domain.usercases.task.GetTasksListUseCase
import com.example.taskaroo.domain.usercases.task.UpdateTaskUseCase
import com.example.taskaroo.domain.usercases.user.CreateUserUserCase
import com.example.taskaroo.domain.usercases.user.GetUserUserCase
import com.example.taskaroo.domain.usercases.user.UpdateUserUserCase
import com.example.taskaroo.presentation.viewmodel.TaskViewModel
import com.example.taskaroo.presentation.viewmodel.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //data sources
    single { DataStoreManager(androidContext()) }
    single<TaskarooDatabase> {
        Room.databaseBuilder(androidContext(), TaskarooDatabase::class.java, "app_database").build()
    }

    //daos
    factory<UserDao> { get<TaskarooDatabase>().getUserDao()}
    factory<TaskDao> { get<TaskarooDatabase>().getTaaskDao() }

    //repos
    factory<UserRepository>{ UserRepositoryImp(get<UserDao>()) }
    factory<TaskRepository>{ TaskRepositoryImp(get<TaskDao>()) }

    //UseCases (User)
    factory { CreateUserUserCase(get<UserRepository>()) }
    factory { UpdateUserUserCase(get<UserRepository>()) }
    factory { GetUserUserCase(get<UserRepository>()) }

    //UseCases (Task)
    factory { CreateTaskUseCase(get<TaskRepository>()) }
    factory { UpdateTaskUseCase(get<TaskRepository>()) }
    factory { DeleteTaskUseCase(get<TaskRepository>()) }
    factory { GetTasksListUseCase(get<TaskRepository>()) }

}

val viewModelModules = module {
    viewModel{ UserViewModel(get(),get(),get())  }
    single { TaskViewModel(get(),get(),get(),get()) }
}