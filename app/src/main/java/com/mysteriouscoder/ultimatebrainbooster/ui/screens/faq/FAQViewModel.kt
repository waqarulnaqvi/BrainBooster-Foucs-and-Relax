package com.mysteriouscoder.ultimatebrainbooster.ui.screens.faq

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysteriouscoder.ultimatebrainbooster.data.FAQDataClass
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FAQViewModel:ViewModel()
{

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _persons = MutableStateFlow(allPersons)
    val persons = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_persons) { text, persons ->
            if(text.isBlank()) {
                persons
            } else {
                delay(2000L)
                persons.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _persons.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}

data class Person(
    val firstName: String,
    val lastName: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "${firstName.first()} ${lastName.first()}",
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

private val allPersons = listOf(
    Person(
        "What is soothing music?",
        "Soothing music refers to any type of music that has a calming and relaxing effect on the listener."
    ),
    Person(
        "What are the benefits of listening to soothing music?",
        "Benefits include stress reduction, improved sleep quality, lowered blood pressure, and enhanced mood."
    ),
    Person(
        "What genres of music are typically considered soothing?",
        "Ambient, classical, nature sounds, instrumental, and meditation music are often considered soothing."
    ),
    Person(
        "How does soothing music affect our emotions?",
        "Soothing music can evoke feelings of peace, tranquility, and contentment, helping to alleviate anxiety and tension."
    ),
    Person(
        "Can soothing music help with sleep problems?",
        "Yes, listening to soothing music before bedtime can help promote relaxation and improve sleep quality."
    ),
    Person(
        "Are there any specific instruments commonly used in soothing music?",
        "Instruments such as piano, flute, guitar, harp, and violin are frequently used in soothing compositions."
    ),
    Person(
        "Is there scientific evidence supporting the effectiveness of soothing music?",
        "Yes, numerous studies have shown that listening to soothing music can have measurable physiological and psychological benefits."
    ),
    Person(
        "Can soothing music be used as a form of therapy?",
        "Yes, music therapy often incorporates soothing music to help individuals manage stress, pain, and emotional issues."
    ),
    Person(
        "How can I find the best soothing music for my needs?",
        "Experiment with different genres and artists to find what resonates with you personally. Online streaming platforms often have curated playlists for relaxation and meditation."
    ),
    Person(
        "Does the tempo of the music matter for it to be soothing?",
        "Generally, slower tempos and gentle rhythms are more conducive to creating a soothing atmosphere."
    ),
    Person(
        "Can soothing music be beneficial during meditation or yoga practices?",
        "Yes, many people find that incorporating soothing music into their meditation or yoga routines enhances relaxation and focus."
    ),
    Person(
        "Are there any specific techniques for using soothing music to relieve stress?",
        "Deep breathing exercises, progressive muscle relaxation, and visualization techniques can be combined with soothing music for maximum stress relief."
    ),
    Person(
        "How long should I listen to soothing music to experience its benefits?",
        "Even just a few minutes of listening to soothing music can have a positive impact, but longer sessions, such as 30 minutes to an hour, are often recommended for deeper relaxation."
    ),
    Person(
        "Can soothing music help with concentration and productivity?",
        "Yes, many people find that listening to soothing music while working or studying can improve focus and productivity by creating a calming environment."
    ),
    Person(
        "Are there any potential negative effects of listening to soothing music?",
        "In general, soothing music is safe for most people, but individuals with certain conditions such as depression or PTSD should consult with a healthcare professional before using music therapy."
    ),
    Person(
        "Can soothing music help with pain management?",
        "Yes, studies have shown that listening to soothing music can help reduce perceived pain levels and increase pain tolerance."
    ),
    Person(
        "How does soothing music affect the brain?",
        "Soothing music can trigger the release of neurotransmitters such as dopamine and serotonin, which are associated with feelings of pleasure and relaxation."
    ),
    Person(
        "Is there a difference between relaxing music and soothing music?",
        "While the terms are often used interchangeably, relaxing music typically refers to any music that promotes relaxation, whereas soothing music specifically focuses on calming and comforting the listener."
    ),
    Person(
        "Can soothing music be beneficial for children?",
        "Yes, soothing music can help calm children, promote better sleep, and even aid in cognitive development."
    ),
    Person(
        "Are there any cultural variations in what is considered soothing music?",
        "Yes, different cultures may have unique musical traditions and preferences for what is considered soothing, but certain elements such as gentle melodies and harmonies are often universally calming."
    ),
    Person(
        "Can soothing music be used in clinical settings, such as hospitals or therapy sessions?",
        "Yes, music therapy programs often incorporate soothing music to help patients relax, manage pain, and improve emotional well-being."
    ),
    Person(
        "How does nature sound music contribute to relaxation?",
        "Nature sound music incorporates sounds such as rain, ocean waves, and bird songs, which have been shown to evoke a sense of calmness and connection to the natural world."
    ),
    Person(
        "Can soothing music be used as an alternative to medication for anxiety or depression?",
        "While soothing music can be a helpful complementary therapy, it is not typically used as a standalone treatment for clinical anxiety or depression."
    ),
    Person(
        "Can listening to soothing music while driving help reduce road rage?",
        "Yes, listening to calming music while driving can help reduce stress and promote safer, more relaxed driving behavior."
    ),
    Person(
        "Is there a specific time of day when listening to soothing music is most beneficial?",
        "Anytime you feel stressed or anxious is a good time to listen to soothing music, but many people find it particularly helpful in the evening to unwind before bed."
    ),
    Person(
        "Are there any apps or websites specifically dedicated to soothing music?",
        "Yes, there are many apps and websites that offer curated playlists of soothing music, as well as customizable relaxation experiences."
    ),
    Person(
        "Can soothing music help with symptoms of PTSD?",
        "Some studies suggest that soothing music may help reduce symptoms of PTSD by promoting relaxation and emotional regulation."
    ),
    Person(
        "How does classical music contribute to relaxation?",
        "Classical music, with its complex harmonies and melodic patterns, can have a soothing effect on the nervous system, helping to reduce stress and anxiety."
    ),
    Person(
        "Can soothing music be used to enhance massage therapy sessions?",
        "Yes, many massage therapists incorporate soothing music into their sessions to create a calming atmosphere and enhance the overall relaxation experience for clients."
    ),
    Person(
        "Is there a difference between listening to soothing music through headphones versus speakers?",
        "While both can be effective, listening to soothing music through headphones can provide a more immersive and personal experience, blocking out external distractions and allowing for deeper relaxation."
    )
)