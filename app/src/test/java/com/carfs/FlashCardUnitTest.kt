package com.carfs

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FlashCardUnitTest {
    @Test
    fun flashCard_creation_isCorrect() {
        val card = FlashCard("Hello", "Hola")
        assertEquals("Hello", card.frontText)
        assertEquals("Hola", card.backText)
    }

    @Test
    fun flashCard_properties_areNotEmpty() {
        val card = FlashCard("Water", "Agua")
        assertTrue(card.frontText.isNotEmpty())
        assertTrue(card.backText.isNotEmpty())
    }
}