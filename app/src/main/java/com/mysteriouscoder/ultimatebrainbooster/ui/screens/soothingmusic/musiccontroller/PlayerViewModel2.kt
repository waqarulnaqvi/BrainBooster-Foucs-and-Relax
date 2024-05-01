package com.mysteriouscoder.ultimatebrainbooster.ui.screens.soothingmusic.musiccontroller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mysteriouscoder.ultimatebrainbooster.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Collections


/***
 * Data class to represent a music in the list
 */
data class Music2(
    val name: String = "",
    val artist: String = "",
    val music: Int = 0,
    val cover: Int = 0,
)

/***
 * Sealed class to represent the player events
 */
sealed class PlayerEvent2 {
    data object Play : PlayerEvent2()
    data object Pause : PlayerEvent2()
    data object Stop : PlayerEvent2()
    data object Resume : PlayerEvent2()
    data object Next : PlayerEvent2()
    data object Previous : PlayerEvent2()
    data class UpdateCurrentSong(val song: Music2) : PlayerEvent2()
}

/***
 * Data class to represent the player state
 */
data class PlayerState2(
    var currentSong: Music2 = Music2(),
    var playList: List<Music2> = emptyList(),
    var currentPosition: Long = 0,
    var isPlaying: Boolean = false,
) {
    fun playNext() {
        val currentIndex = playList.indexOf(currentSong)
        val nextIndex = (currentIndex + 1) % playList.size
        currentSong = playList[nextIndex]
    }

    fun playPrevious() {
        val currentIndex = playList.indexOf(currentSong)
        val previousIndex = (currentIndex - 1) % playList.size
        currentSong = playList[previousIndex]
    }

    fun pause() {
        isPlaying = false
    }

    fun resume() {
        isPlaying = true
    }

    fun stop() {
        isPlaying = false
        currentPosition = 0
    }
}

/***
 * ViewModel to handle the player state and events
 */
@Suppress("UNCHECKED_CAST")
class MusicPlayerViewModel2(
    private val startIdx: Int
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerState2())
    val playerState: StateFlow<PlayerState2> = _state.asStateFlow()

    init {
        val playList = getPlayList()
        Collections.swap(playList, 0, startIdx)
        _state.value = PlayerState2(playList = playList)
    }

    fun handlePlayerEvent(event: PlayerEvent2) {
        when (event) {
            is PlayerEvent2.Play -> _state.value.resume()
            is PlayerEvent2.Pause -> _state.value.pause()
            is PlayerEvent2.Stop -> _state.value.stop()
            is PlayerEvent2.Resume -> _state.value.resume()
            is PlayerEvent2.UpdateCurrentSong -> _state.value.currentSong = event.song
            PlayerEvent2.Next -> _state.value.playNext()
            PlayerEvent2.Previous -> _state.value.playPrevious()
        }
    }

    /***
     * Return a play list of type Music data class
     */


    private fun getPlayList(): List<Music2> {
        return listOf(
            Music2(
                name = "Indian Classical Music",
                artist = "Brain Booster",
                cover = R.drawable.indianclassicalmusicsquareicon,
                music = R.raw.indianclassicalmusic
            ),
            Music2(
                name = "Meditative & Healing Music",
                artist = "Brain Booster",
                cover = R.drawable.meditationandhealingmusicsquareicon,
                music = R.raw.meditativeandhealingmusic
            ),
            Music2(
                name = "Nature Sounds",
                artist = "Brain Booster",
                cover = R.drawable.naturesoundssquareicon,
                music = R.raw.naturesounds
            ),
//            Music2(
//                name = "Downtempo Music",
//                artist = "Brain Booster",
//                cover = R.drawable.downtempomusicsquareicon,
//                music = R.raw.meditativeandhealingmusic
//
//            ),
            Music2(
                name = "Chill-out Music",
                artist = "Brain Booster",
                cover = R.drawable.chilloutmusicsquareicon,
                music = R.raw.chilloutmusic
            ),
            Music2(
                name = "Ambient Music",
                artist = "Brain Booster",
                cover = R.drawable.ambientmusicsquareicon,
                music = R.raw.ambientmusic

            ),
            Music2(
                name = "New Age Music",
                artist = "Brain Booster",
                cover = R.drawable.newagemusicsquareicon,
                music = R.raw.newagemusic

            ),
           Music2(
                name = "Instrumental Music",
                artist = "Brain Booster",
                cover = R.drawable.instrumentalmusicsquareicon,
                music = R.raw.instrumentalmusic

            ),
            Music2(
                name = "Piano Instrumentals Music",
                artist = "Brain Booster",
                cover = R.drawable.pianoinstrumentalssquareicon,
                music = R.raw.pianoinstrumentals
            ),
            Music2(
                name = "Minimalism Music",
                artist = "Brain Booster",
                cover = R.drawable.minimalismmusicsquareicon,
                music = R.raw.minimalismmusic

            ),
            Music2(
                name = "Sound Baths",
                artist = "Brain Booster",
                cover = R.drawable.soundbathsquareicon,
                music = R.raw.soundbaths

            ),
            Music2(
                name = "Jazz Music",
                artist = "Brain Booster",
                cover = R.drawable.jazzmusicsquareicon,
                music = R.raw.jazzmusic

            ),
            Music2(
                name = "Spa Music",
                artist = "Brain Booster",
                cover = R.drawable.spamusicsquareicon,
                music = R.raw.spamusic

            ),
            Music2(
                name = "LoFi Hip Hop Music ",
                artist = "Brain Booster",
                cover = R.drawable.lofihiphopsquareicon,
                music = R.raw.lofihiphopmusic

            ),
            Music2(
                name = "Acoustic Music",
                artist = "Brain Booster",
                cover = R.drawable.acousticmusicsquareicon,
                music = R.raw.acouticmusic

            ),
            Music2(
                name = "Soundtracks & Film Scores",
                artist = "Brain Booster",
                cover = R.drawable.soundtrackandfilmscoresquareicon,
                music = R.raw.soundtrackandfilmscores
            ),
        )
    }

    fun updateIndex(selectedIdx: Int) {
        // make the selectedIndex first
        _state.value = _state.value.apply {
            playList.toMutableList().swap(0, selectedIdx)
        }
    }

    private fun <T> MutableList<T>.swap(idx1: Int, idx2: Int): MutableList<T> = apply {
        val t = this[idx1]
        this[idx1] = this[idx2]
        this[idx2] = t
    }

    class Factory(private val startIdx: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MusicPlayerViewModel2(startIdx) as T
        }
    }
}