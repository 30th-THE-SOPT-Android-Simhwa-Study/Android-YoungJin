package org.sopt.anshim.presentation.friend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.anshim.domain.repositories.FriendRepository

class FriendDetailViewModelFactory(private val repository: FriendRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendDetailViewModel::class.java)) {
            return FriendDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}