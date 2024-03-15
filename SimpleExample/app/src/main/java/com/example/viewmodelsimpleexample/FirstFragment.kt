package com.example.viewmodelsimpleexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.viewmodelsimpleexample.Person
import com.example.viewmodelsimpleexample.PersonViewModel
import com.example.viewmodelsimpleexample.R
import com.example.viewmodelsimpleexample.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGreet.setOnClickListener {
            // Get input values from EditText fields
            val name = binding.editTextName.text.trim().toString()
            if (name.isEmpty()) {
                binding.editTextName.error = "No name"
                return@setOnClickListener
            }
            val ageStr = binding.editTextAge.text.trim().toString()
            if (ageStr.isEmpty()) {
                binding.editTextAge.error = "No age"
                return@setOnClickListener
            }
            val age = ageStr.toInt()
            val address = binding.editTextAdress.text.trim().toString()
            val email = binding.editTextEmail.text.trim().toString()

            // Create a new Person object with input values
            val newPerson = Person(name, age, address, email)

            // Add the new Person to the ViewModel's personsList
            viewModel.addPerson(newPerson)

            println("Person added to the list" + Person(name, age, address, email))
        }

        // Observe changes to the personsList in ViewModel
        viewModel.personsList.observe(viewLifecycleOwner) { persons ->
            // Display the details of the first person in the list
            if (persons.isNotEmpty()) {
                val person = persons.first()
                binding.textviewName.text = "Hello ${person.name}"
                binding.textviewAge.text = "${person.age} years old"
                binding.textviewAdress.text = "Your address equals ${person.address}"
                binding.textviewEmail.text = "Your email equals ${person.email}"
            }
        }

        binding.buttonShowlist.setOnClickListener {
            // Display the list of persons in the TextView
            val personsList = viewModel.personsList.value.orEmpty()
            binding.textviewPersonlist.text = buildPersonListString(personsList)
        }

        binding.buttonNext.setOnClickListener {
            // Navigate to the SecondFragment when the Next button is clicked
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun buildPersonListString(personsList: List<Person>): String {
        val stringBuilder = StringBuilder()
        for ((index, person) in personsList.withIndex()) {
            stringBuilder.append("${index + 1}. Name: ${person.name}, Age: ${person.age}, Address: ${person.address}, Email: ${person.email}\n")
        }
        return stringBuilder.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
