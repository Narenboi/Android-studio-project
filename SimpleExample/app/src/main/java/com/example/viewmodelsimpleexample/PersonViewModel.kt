package com.example.viewmodelsimpleexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Person(
    var name: String = "No name",
    var age: Int = 0,
    var address: String = "No address",
    var email: String = "No email"
)

class PersonViewModel : ViewModel() {
    val personsList: MutableLiveData<List<Person>> = MutableLiveData()

    init {
        // Initialize personsList with an empty list
        personsList.value = emptyList()
    }

    // Function to add a new person to the list
    fun addPerson(person: Person) {
        val currentList = personsList.value.orEmpty().toMutableList()
        currentList.add(person)
        personsList.value = currentList
    }

    // Function to remove a person from the list
    fun removePerson(person: Person) {
        val currentList = personsList.value.orEmpty().toMutableList()
        currentList.remove(person)
        personsList.value = currentList
    }
}
