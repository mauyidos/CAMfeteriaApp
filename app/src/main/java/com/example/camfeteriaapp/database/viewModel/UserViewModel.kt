package com.example.camfeteriaapp.database.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.camfeteriaapp.database.model.User
import com.example.camfeteriaapp.database.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userlist = _userList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllUser().collect() {
                    item ->
                if(item.isNullOrEmpty()) {
                    _userList.value = emptyList()
                } else {
                    _userList.value = item
                }
            }
        }
    }

    suspend fun getUser(email: String): User? {
        return repository.getUserByEmail(email)
    }

    fun addUser(user: User) = viewModelScope.launch {repository.addUser(user)}
    fun updateUser(user: User) = viewModelScope.launch {repository.updateUser(user)}
    fun deleteUser(user: User) = viewModelScope.launch {repository.deleteUser(user)}
}