package com.mysteriouscoder.brainbooster.ui.screens.binauralbeats.musiccontroller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mysteriouscoder.brainbooster.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Collections


/***
 * Data class to represent a music in the list
 */
data class Music(
    val name: String = "",
    val artist: String = "",
    val music: Int = 0,
    val cover: Int = 0,
)

/***
 * Sealed class to represent the player events
 */
sealed class PlayerEvent {
    data object Play : PlayerEvent()
    data object Pause : PlayerEvent()
    data object Stop : PlayerEvent()
    data object Resume : PlayerEvent()
    data object Next : PlayerEvent()
    data object Previous : PlayerEvent()
    data class UpdateCurrentSong(val song: Music) : PlayerEvent()
}

/***
 * Data class to represent the player state
 */
data class PlayerState(
    var currentSong: Music = Music(),
    var playList: List<Music> = emptyList(),
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
class MusicPlayerViewModel(
    private val startIdx: Int
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerState())
    val playerState: StateFlow<PlayerState> = _state.asStateFlow()

    init {
        val playList = getPlayList()
        Collections.swap(playList, 0, startIdx)
        _state.value = PlayerState(playList = playList)
    }



    // Function to handle player events
    fun handlePlayerEvent(event: PlayerEvent) {
        when (event) {
            is PlayerEvent.Play -> _state.value.resume()
            is PlayerEvent.Pause -> _state.value.pause()
            is PlayerEvent.Stop -> _state.value.stop()
            is PlayerEvent.Resume -> _state.value.resume()
            is PlayerEvent.UpdateCurrentSong -> _state.value.currentSong = event.song
            PlayerEvent.Next -> _state.value.playNext()
            PlayerEvent.Previous -> _state.value.playPrevious()
        }
    }

    /***
     * Return a play list of type Music data class
     */
    private fun getPlayList(): List<Music> {
        return listOf(

            Music(
                name = "Deep Sleep",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_deepsleepsquareicon,
                music = R.raw.deepsleep

            ),
            Music(
                name = "Creativity Boost",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_creativityboostsquareicon,
                music = R.raw.creativityboost

            ),
            Music(
                name = "Memory Boost",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_memoryboostsquareicon,
                music = R.raw.memoryboost

            ),
            Music(
                name = "Unlock Hidden Genius",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_unlockhiddengeniussquareicon,
                music = R.raw.unlockhiddengenius

            ),
            Music(
                name = "Nirvana Meditation Aid",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_nirvanameditationaidsquareicon,
                music = R.raw.nirvanameditationaid

            ),
            Music(
                name = "Enhanced Focus",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_enhancedfocussquareicon,
                music = R.raw.enhancedfocus

            ),
            Music(
                name = "Mood Enhancement",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_moodenhancementsquareicon,
                music = R.raw.moodenhancement

            ),
         /*   Music(
                name = "Open Your Third Eye",
                artist = "(Headphones recommended)",
                cover = R.drawable.openyourthirdeyesquareicon,
                music = R.raw.meditativeandhealingmusic

            ),*/
            Music(
                name = "Relaxation & Stress Relief",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_relaxationandstressreliefsquareicon,
                music = R.raw.relaxationandstressrelief

            ),
            Music(
                name = "Improved Brain Sync",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_improvedbrainsyncsquareicon,
                music = R.raw.improvedbrainsync

            ),
            Music(
                name = "Deep Concentration (Gamma)",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_deepconcentrationgammasquareicon,
                music = R.raw.deepconcentrationgamma

            ),
            Music(
                name = "Alertness & Energy (Beta)",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_alertnessandenergybetasquareicon,
                music = R.raw.alertnessandenergybeta

            ),
            Music(
                name = "Relaxed & Awake (Alpha)",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_relaxedandawakealphasquareicon,
                music = R.raw.relaxedandawakealpha

            ),

            Music(
                name = "Deep Relaxation (Theta)",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_deeprelaxationthetasquareicon,
                music = R.raw.deeprelaxationtheta

            ),
            Music(
                name = "Lucid Dreaming (Alpha & Theta)",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_luciddreamingalphaandthetasquareicon,
                music = R.raw.luciddreamingalphaandtheta

            ),
            Music(
                name = "Astral Projection (Delta)",
                artist = "(Headphones recommended)",
                cover = R.drawable.ic_astralprojectiondeltasquareicon,
                music = R.raw.astralprojectiondelta

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
            return MusicPlayerViewModel(startIdx) as T
        }
    }
}
