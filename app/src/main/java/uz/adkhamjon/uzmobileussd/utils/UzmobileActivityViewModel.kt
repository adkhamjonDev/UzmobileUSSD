package uz.adkhamjon.uzmobileussd.utils

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UzmobileActivityViewModel(val simCards: SimCardRepository): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Uzmobile Activity"
    }
    val text: LiveData<String> = _text
}