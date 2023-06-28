package com.bphc.bluff

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _music = MutableStateFlow(false)
    val music = _music.asStateFlow()

    private val _sound = MutableStateFlow(false)
    val sound = _sound.asStateFlow()

    init {
        _music.value = sharedPreferences.getBoolean("music", false)
        _sound.value = sharedPreferences.getBoolean("sound", false)
    }

    fun getDisplayName(): String {
        return sharedPreferences.getString("display_name", "")!!
    }

    fun setDisplayName(name: String) {
        sharedPreferences.edit().putString("display_name", name).apply()
    }

    fun setMusic(value: Boolean) {
        _music.value = value
        sharedPreferences.edit().putBoolean("music", value).apply()
    }

    fun setSound(value: Boolean) {
        _sound.value = value
        sharedPreferences.edit().putBoolean("sound", value).apply()
    }


}