//package com.mysteriouscoder.ultimatebrainbooster.ui.screens.faq
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.mysteriouscoder.ultimatebrainbooster.data.FAQDataClass
//import com.mysteriouscoder.ultimatebrainbooster.data.soothingmusiclist
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.combine
//import kotlinx.coroutines.flow.debounce
//import kotlinx.coroutines.flow.onEach
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.flow.update
//class FAQViewModel:ViewModel() {
//
//    private val _searchText = MutableStateFlow("")
//    val searchText = _searchText.asStateFlow()
//
//    private val _isSearching = MutableStateFlow(false)
//    val isSearching = _isSearching.asStateFlow()
//
//    private val _persons = MutableStateFlow(faqsothingmusiclist)
//    val persons = searchText
//        .debounce(1000L)
//        .onEach { _isSearching.update { true } }
//        .combine(_persons) { text, persons ->
//            if(text.isBlank()) {
//                persons
//            } else {
//                delay(2000L)
//                persons.filter {
//                    it.doesMatchSearchQuery(text)
//                }
//            }
//        }
//        .onEach { _isSearching.update { false } }
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(5000),
//            _persons.value
//        )
//
//    fun onSearchTextChange(text: String) {
//        _searchText.value = text
//    }
//}
//
//data class FAQDataClass(
//    val question: String,
//    val answer: String
//) {
//    fun doesMatchSearchQuery(query: String): Boolean {
//        val matchingCombinations = listOf(
//            "$question $answer",
//            "${question.first()} ${answer.first()}",
//        )
//
//        return matchingCombinations.any {
//            it.contains(query, ignoreCase = true)
//        }
//    }
//}
//
//val faqsothingmusiclist = listOf(
//    FAQDataClass(
//        "What is soothing music?",
//        "Soothing music refers to any type of music that has a calming and relaxing effect on the listener."
//    ),
//    FAQDataClass(
//        "What are the benefits of listening to soothing music?",
//        "Benefits include stress reduction, improved sleep quality, lowered blood pressure, and enhanced mood."
//    ),
//    FAQDataClass(
//        "What genres of music are typically considered soothing?",
//        "Ambient, classical, nature sounds, instrumental, and meditation music are often considered soothing."
//    ),
//    FAQDataClass(
//        "How does soothing music affect our emotions?",
//        "Soothing music can evoke feelings of peace, tranquility, and contentment, helping to alleviate anxiety and tension."
//    ),
//    FAQDataClass(
//        "Can soothing music help with sleep problems?",
//        "Yes, listening to soothing music before bedtime can help promote relaxation and improve sleep quality."
//    ),
//    FAQDataClass(
//        "Are there any specific instruments commonly used in soothing music?",
//        "Instruments such as piano, flute, guitar, harp, and violin are frequently used in soothing compositions."
//    ),
//    FAQDataClass(
//        "Is there scientific evidence supporting the effectiveness of soothing music?",
//        "Yes, numerous studies have shown that listening to soothing music can have measurable physiological and psychological benefits."
//    ),
//    FAQDataClass(
//        "Can soothing music be used as a form of therapy?",
//        "Yes, music therapy often incorporates soothing music to help individuals manage stress, pain, and emotional issues."
//    ),
//    FAQDataClass(
//        "How can I find the best soothing music for my needs?",
//        "Experiment with different genres and artists to find what resonates with you personally. Online streaming platforms often have curated playlists for relaxation and meditation."
//    ),
//    FAQDataClass(
//        "Does the tempo of the music matter for it to be soothing?",
//        "Generally, slower tempos and gentle rhythms are more conducive to creating a soothing atmosphere."
//    ),
//    FAQDataClass(
//        "Can soothing music be beneficial during meditation or yoga practices?",
//        "Yes, many people find that incorporating soothing music into their meditation or yoga routines enhances relaxation and focus."
//    ),
//    FAQDataClass(
//        "Are there any specific techniques for using soothing music to relieve stress?",
//        "Deep breathing exercises, progressive muscle relaxation, and visualization techniques can be combined with soothing music for maximum stress relief."
//    ),
//    FAQDataClass(
//        "How long should I listen to soothing music to experience its benefits?",
//        "Even just a few minutes of listening to soothing music can have a positive impact, but longer sessions, such as 30 minutes to an hour, are often recommended for deeper relaxation."
//    ),
//    FAQDataClass(
//        "Can soothing music help with concentration and productivity?",
//        "Yes, many people find that listening to soothing music while working or studying can improve focus and productivity by creating a calming environment."
//    ),
//    FAQDataClass(
//        "Are there any potential negative effects of listening to soothing music?",
//        "In general, soothing music is safe for most people, but individuals with certain conditions such as depression or PTSD should consult with a healthcare professional before using music therapy."
//    ),
//    FAQDataClass(
//        "Can soothing music help with pain management?",
//        "Yes, studies have shown that listening to soothing music can help reduce perceived pain levels and increase pain tolerance."
//    ),
//    FAQDataClass(
//        "How does soothing music affect the brain?",
//        "Soothing music can trigger the release of neurotransmitters such as dopamine and serotonin, which are associated with feelings of pleasure and relaxation."
//    ),
//    FAQDataClass(
//        "Is there a difference between relaxing music and soothing music?",
//        "While the terms are often used interchangeably, relaxing music typically refers to any music that promotes relaxation, whereas soothing music specifically focuses on calming and comforting the listener."
//    ),
//    FAQDataClass(
//        "Can soothing music be beneficial for children?",
//        "Yes, soothing music can help calm children, promote better sleep, and even aid in cognitive development."
//    ),
//    FAQDataClass(
//        "Are there any cultural variations in what is considered soothing music?",
//        "Yes, different cultures may have unique musical traditions and preferences for what is considered soothing, but certain elements such as gentle melodies and harmonies are often universally calming."
//    ),
//    FAQDataClass(
//        "Can soothing music be used in clinical settings, such as hospitals or therapy sessions?",
//        "Yes, music therapy programs often incorporate soothing music to help patients relax, manage pain, and improve emotional well-being."
//    ),
//    FAQDataClass(
//        "How does nature sound music contribute to relaxation?",
//        "Nature sound music incorporates sounds such as rain, ocean waves, and bird songs, which have been shown to evoke a sense of calmness and connection to the natural world."
//    ),
//    FAQDataClass(
//        "Can soothing music be used as an alternative to medication for anxiety or depression?",
//        "While soothing music can be a helpful complementary therapy, it is not typically used as a standalone treatment for clinical anxiety or depression."
//    ),
//    FAQDataClass(
//        "Can listening to soothing music while driving help reduce road rage?",
//        "Yes, listening to calming music while driving can help reduce stress and promote safer, more relaxed driving behavior."
//    ),
//    FAQDataClass(
//        "Is there a specific time of day when listening to soothing music is most beneficial?",
//        "Anytime you feel stressed or anxious is a good time to listen to soothing music, but many people find it particularly helpful in the evening to unwind before bed."
//    ),
//    FAQDataClass(
//        "Are there any apps or websites specifically dedicated to soothing music?",
//        "Yes, there are many apps and websites that offer curated playlists of soothing music, as well as customizable relaxation experiences."
//    ),
//    FAQDataClass(
//        "Can soothing music help with symptoms of PTSD?",
//        "Some studies suggest that soothing music may help reduce symptoms of PTSD by promoting relaxation and emotional regulation."
//    ),
//    FAQDataClass(
//        "How does classical music contribute to relaxation?",
//        "Classical music, with its complex harmonies and melodic patterns, can have a soothing effect on the nervous system, helping to reduce stress and anxiety."
//    ),
//    FAQDataClass(
//        "Can soothing music be used to enhance massage therapy sessions?",
//        "Yes, many massage therapists incorporate soothing music into their sessions to create a calming atmosphere and enhance the overall relaxation experience for clients."
//    ),
//    FAQDataClass(
//        "Is there a difference between listening to soothing music through headphones versus speakers?",
//        "While both can be effective, listening to soothing music through headphones can provide a more immersive and personal experience, blocking out external distractions and allowing for deeper relaxation."
//    )
//)
//
//
//val faqbinauralbeatslist = listOf(
//    FAQDataClass(
//        "What are binaural beats?",
//        "Binaural beats are auditory illusions perceived when two slightly different frequencies are presented separately to each ear, creating the perception of a third frequency."
//    ),
//    FAQDataClass(
//        "How do binaural beats work?",
//        "Binaural beats work by exploiting the brain's natural ability to interpret differences in frequencies between the ears, leading to the perception of a rhythmic beat."
//    ),
//    FAQDataClass(
//        "What frequencies are typically used in binaural beats?",
//        "Binaural beats often use frequencies ranging from 1 to 30 Hz, corresponding to different brainwave states such as delta, theta, alpha, beta, and gamma."
//    ),
//    FAQDataClass(
//        "What is the purpose of using binaural beats?",
//        "The purpose of using binaural beats varies, but common goals include relaxation, meditation, stress reduction, enhanced focus, and improved sleep."
//    ),
//    FAQDataClass(
//        "Are there any scientific studies supporting the effectiveness of binaural beats?",
//        "Yes, some studies suggest that binaural beats may have benefits for cognitive function, mood enhancement, and relaxation. However, more research is needed to fully understand their effects."
//    ),
//    FAQDataClass(
//        "Can binaural beats help with meditation?",
//        "Many people find that binaural beats can aid in meditation by helping to induce a state of relaxation and focus, though individual experiences may vary."
//    ),
//    FAQDataClass(
//        "Are there any potential side effects of listening to binaural beats?",
//        "Generally, binaural beats are safe for most people to listen to, but some individuals may experience dizziness, headaches, or other discomfort if the frequencies are too intense."
//    ),
//    FAQDataClass(
//        "How long should I listen to binaural beats for optimal effects?",
//        "The optimal duration for listening to binaural beats varies depending on individual preferences and goals. Some people find benefits from just a few minutes of listening, while others prefer longer sessions."
//    ),
//    FAQDataClass(
//        "Can binaural beats help with sleep?",
//        "Some people report that listening to binaural beats before bedtime can help promote relaxation and improve sleep quality, particularly when targeting frequencies associated with deep sleep."
//    ),
//    FAQDataClass(
//        "Are there specific binaural beats for anxiety relief?",
//        "Yes, there are binaural beats tracks specifically designed to promote relaxation and reduce anxiety by targeting frequencies associated with calmness and stress reduction."
//    ),
//    FAQDataClass(
//        "Do binaural beats require special equipment to listen to?",
//        "No, binaural beats can be experienced using any standard stereo headphones or earphones, as long as they can deliver different frequencies to each ear separately."
//    ),
//    FAQDataClass(
//        "Can binaural beats be used for pain management?",
//        "Some research suggests that binaural beats may help reduce perception of pain by altering brainwave activity and promoting relaxation, but more studies are needed to confirm their effectiveness for pain management."
//    ),
//    FAQDataClass(
//        "Are there any specific frequencies of binaural beats for focus and concentration?",
//        "Binaural beats in the alpha and beta frequency ranges (8-30 Hz) are often used to enhance focus, concentration, and mental alertness."
//    ),
//    FAQDataClass(
//        "Can binaural beats improve memory and learning?",
//        "While research on this topic is still limited, some studies suggest that certain frequencies of binaural beats may have potential to enhance memory retention and learning abilities."
//    ),
//    FAQDataClass(
//        "Are binaural beats effective for stress reduction?",
//        "Many people find that listening to binaural beats can help reduce stress levels by inducing a state of relaxation and promoting calmness."
//    ),
//    FAQDataClass(
//        "Can binaural beats be used to enhance creativity?",
//        "Some individuals report that listening to binaural beats can stimulate creative thinking and enhance problem-solving abilities, though scientific evidence supporting this claim is limited."
//    ),
//    FAQDataClass(
//        "Are there specific binaural beats for lucid dreaming?",
//        "Yes, some binaural beats tracks are designed to be listened to during sleep with the intention of inducing lucid dreams, though their effectiveness varies among individuals."
//    ),
//    FAQDataClass(
//        "Can binaural beats be harmful if used improperly?",
//        "Binaural beats are generally safe, but listening at very high volumes or frequencies outside the typical range may potentially cause discomfort or adverse effects in some individuals."
//    ),
//    FAQDataClass(
//        "How do I find reliable sources for binaural beats recordings?",
//        "Look for reputable websites, apps, or creators with positive reviews and a focus on quality audio production. It's also important to choose recordings that align with your specific goals and preferences."
//    ),
//    FAQDataClass(
//        "Can binaural beats be used to enhance athletic performance?",
//        "Some athletes use binaural beats as part of their pre-performance routine to promote focus, relaxation, and mental clarity, though individual results may vary."
//    ),
//    FAQDataClass(
//        "Are there any age restrictions for listening to binaural beats?",
//        "Binaural beats are generally safe for people of all ages, but parents should supervise young children and teenagers to ensure they use them responsibly and in moderation."
//    ),
//    FAQDataClass(
//        "Can binaural beats be combined with other relaxation techniques?",
//        "Yes, binaural beats can be combined with techniques such as deep breathing, progressive muscle relaxation, or guided imagery to enhance relaxation and stress reduction."
//    ),
//    FAQDataClass(
//        "Can I listen to binaural beats while working or studying?",
//        "Many people find that listening to binaural beats can help improve focus and concentration during work or study sessions, though it's important to find a balance and avoid distraction."
//    ),
//    FAQDataClass(
//        "Are there any binaural beats apps available for smartphones?",
//        "Yes, there are numerous apps available for both iOS and Android devices that offer a variety of binaural beats tracks for different purposes, including relaxation, meditation, and sleep."
//    ),
//    FAQDataClass(
//        "Can binaural beats be used to treat insomnia?",
//        "While binaural beats may help some individuals relax and fall asleep more easily, they should not be relied upon as a sole treatment for chronic insomnia, and consulting with a healthcare professional is recommended for persistent sleep issues."
//    ),
//    FAQDataClass(
//        "Are there any contraindications for using binaural beats?",
//        "Individuals with epilepsy, seizures, or other neurological conditions should consult with a healthcare professional before using binaural beats, as they may potentially trigger adverse reactions in some cases."
//    ),
//    FAQDataClass(
//        "Can binaural beats be used to alleviate symptoms of ADHD?",
//        "Some individuals with ADHD may find binaural beats helpful for promoting focus and concentration, but they should be used as part of a comprehensive treatment plan under the guidance of a healthcare professional."
//    ),
//
//    FAQDataClass(
//        "Can binaural beats be used for spiritual purposes?",
//        "Some people use binaural beats as part of spiritual practices such as meditation, mindfulness, or consciousness exploration, though their effectiveness for spiritual purposes varies among individuals."
//    ),
//    FAQDataClass(
//        "Are there any specific binaural beats frequencies for relaxation?",
//        "Binaural beats in the theta and delta frequency ranges (1-8 Hz) are often used to promote deep relaxation, stress relief, and meditation."
//    ),
//    FAQDataClass(
//        "How often should I listen to binaural beats for maximum benefits?",
//        "The frequency and duration of listening to binaural beats can vary depending on individual preferences and goals, but regular practice, such as daily or several times a week, may yield more consistent results.\" rewrite the code and implement all the question answer in the list "
//    ),
//)

package com.mysteriouscoder.ultimatebrainbooster.ui.screens.faq

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysteriouscoder.ultimatebrainbooster.data.binauralbeatsansoothingmusicfaqlist
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FAQViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _faqList = MutableStateFlow(binauralbeatsansoothingmusicfaqlist)

    @OptIn(FlowPreview::class)
    val faqList = searchText
        .debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_faqList) { text, faqList ->
            if (text.isBlank()) {
                faqList
            } else {
                delay(1000L)
                faqList.filter {
                    it.matches(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _faqList.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}
