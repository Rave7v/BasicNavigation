package fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicnavigation.database.DatabaseManager
import com.example.basicnavigation.database.MyAppDataSource
import com.example.basicnavigation.database.User
import kotlinx.coroutines.launch

class DestinationViewModel: ViewModel() {
    val savedUsers= MutableLiveData<List<User>>()
    fun getUsers(){
        viewModelScope.launch {
            val userDao = DatabaseManager.instance.database.userDao()
            savedUsers.value = MyAppDataSource(userDao).getUsers().value
        }
    }
    fun save(user: User){
        viewModelScope.launch {
            val userDao = DatabaseManager.instance.database.userDao()
            MyAppDataSource(userDao).save(user)
        }
    }
}