package com.carfs

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.carfs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var cards = mutableListOf<FlashCard>()
    private var currentCardIndex = 0
    private var showingFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeDefaultCards()
        setupClickListeners()
        updateCardDisplay()
    }

    private fun initializeDefaultCards() {
        cards.add(FlashCard("Hello", "Hola"))
        cards.add(FlashCard("Goodbye", "Adiós"))
        cards.add(FlashCard("Thank you", "Gracias"))
        cards.add(FlashCard("Please", "Por favor"))
        cards.add(FlashCard("Water", "Agua"))
    }

    private fun setupClickListeners() {
        binding.flipButton.setOnClickListener {
            flipCard()
        }

        binding.nextButton.setOnClickListener {
            nextCard()
        }

        binding.prevButton.setOnClickListener {
            previousCard()
        }

        binding.addCardFab.setOnClickListener {
            showAddCardDialog()
        }

        // Allow tapping the card to flip it
        binding.cardContainer.setOnClickListener {
            flipCard()
        }
    }

    private fun flipCard() {
        showingFront = !showingFront
        updateCardDisplay()
    }

    private fun nextCard() {
        if (cards.isNotEmpty()) {
            currentCardIndex = (currentCardIndex + 1) % cards.size
            showingFront = true
            updateCardDisplay()
        }
    }

    private fun previousCard() {
        if (cards.isNotEmpty()) {
            currentCardIndex = if (currentCardIndex == 0) cards.size - 1 else currentCardIndex - 1
            showingFront = true
            updateCardDisplay()
        }
    }

    private fun updateCardDisplay() {
        if (cards.isEmpty()) {
            binding.cardText.text = "No cards available. Add some cards to get started!"
            updateButtonStates(false)
            return
        }

        val currentCard = cards[currentCardIndex]
        binding.cardText.text = if (showingFront) currentCard.frontText else currentCard.backText
        updateButtonStates(true)
    }

    private fun updateButtonStates(hasCards: Boolean) {
        binding.flipButton.isEnabled = hasCards
        binding.nextButton.isEnabled = hasCards && cards.size > 1
        binding.prevButton.isEnabled = hasCards && cards.size > 1
    }

    private fun showAddCardDialog() {
        val dialogLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 50, 50, 50)
        }

        val frontEditText = EditText(this).apply {
            hint = getString(R.string.front_text_hint)
        }

        val backEditText = EditText(this).apply {
            hint = getString(R.string.back_text_hint)
        }

        dialogLayout.addView(frontEditText)
        dialogLayout.addView(backEditText)

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.add_new_card))
            .setView(dialogLayout)
            .setPositiveButton(getString(R.string.save_card)) { _, _ ->
                val frontText = frontEditText.text.toString().trim()
                val backText = backEditText.text.toString().trim()

                if (frontText.isNotEmpty() && backText.isNotEmpty()) {
                    cards.add(FlashCard(frontText, backText))
                    Toast.makeText(this, "Card added successfully!", Toast.LENGTH_SHORT).show()
                    
                    // If this is the first card, update display
                    if (cards.size == 1) {
                        currentCardIndex = 0
                        showingFront = true
                        updateCardDisplay()
                    }
                } else {
                    Toast.makeText(this, "Please fill in both front and back text", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }
}

data class FlashCard(
    val frontText: String,
    val backText: String
)