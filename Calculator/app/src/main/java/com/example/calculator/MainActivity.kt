package com.example.calculator

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Setting up OnClickListener for AddButton
        binding.AddButton.setOnClickListener {
            val one = binding.Number1.text.toString()
            if (one.isEmpty()) {
                binding.Number1.error = "Please enter a number"
                return@setOnClickListener
            }

            val two = binding.Number2.text.toString()
            if (two.isEmpty()) {
                binding.Number2.error = "Please enter a number"
                return@setOnClickListener
            }



            // You can perform calculation here if needed
        }

        // Setting up spinner
        val spinner: Spinner = findViewById(R.id.planets_spinner)

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        }

        // Setting up spinner item selection listener
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                parent?.let { selectedSpinner ->
                    if (selectedSpinner.id == R.id.planets_spinner) {
                        val operation = selectedSpinner.getItemAtPosition(pos).toString()

                        // Perform calculations based on the selected operation
                        val number1 = binding.Number1.text.toString().toDoubleOrNull() ?: 0.0
                        val number2 = binding.Number2.text.toString().toDoubleOrNull() ?: 0.0
                        val result = when (operation) {
                            "+" -> number1 + number2
                            "-" -> number1 - number2
                            "*" -> number1 * number2
                            "/" -> {
                                if (number2 != 0.0) {
                                    number1 / number2
                                } else {
                                    // Handle division by zero error
                                    Double.NaN
                                }
                            }
                            else -> Double.NaN // Handle unknown operation
                        }

                        // Display the result
                        binding.Result.text = result.toString()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing if nothing is selected
            }
        }
    }
}
