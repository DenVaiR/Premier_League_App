package com.example.premierleagueapp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.premierleagueapp.data.model.Match
import com.example.premierleagueapp.data.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDetailsViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {
    private val _match = MutableStateFlow<Match?>(null)
    val match: StateFlow<Match?> = _match.asStateFlow()

    fun loadMatch(matchId: Int) {
        viewModelScope.launch {
            repository.getMatchById(matchId)?.let { _match.value = it }
        }
    }
}