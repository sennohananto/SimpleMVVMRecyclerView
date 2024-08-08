package com.latihan.simplemvvmrecyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private val _biodata = MutableLiveData<List<Biodata>>()
    val biodata: LiveData<List<Biodata>>
        get() = _biodata

    init {
        loadItems()
    }

    private fun loadItems() {
        // Simulating loading data
        _biodata.value = listOf(
            Biodata("Alfa",21,"Aceh"),
            Biodata("Beta",21,"Bireun"),
            Biodata("Charlie",21,"Cikampek"),
            Biodata("Delta",21,"Depok"),
            Biodata("Eko",21,"Wamena")
        )
    }
}