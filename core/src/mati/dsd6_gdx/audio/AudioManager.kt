package mati.dsd6_gdx.audio

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import mati.dsd6_gdx.D6

object AudioManager {

    private var currentMusic: AudioType? = null

    fun load() {
        for (audioType in AudioType.values()) {
            D6.Companion.assets.load(
                audioType.filePath,
                if (audioType.isMusic) Music::class.java else Sound::class.java
            )
        }
    }

    fun play(audioType: AudioType) {
        if (audioType.isMusic && D6.musicOn) {
            if (currentMusic === audioType) {
                return
            } else if (currentMusic != null) {
                D6.assets.get(
                    currentMusic!!.filePath,
                    Music::class.java
                ).stop()
            }
            val music: Music = D6.assets.get(
                audioType.filePath,
                Music::class.java
            )
            music.volume = audioType.volume
            music.isLooping = true
            music.play()
            currentMusic = audioType
        } else if(!audioType.isMusic && D6.soundOn) {
            D6.assets.get(audioType.filePath, Sound::class.java)
                .play(audioType.volume)
        }
    }

    fun stopMusic() {
        if(currentMusic != null) {
            D6.assets.get(currentMusic!!.filePath, Music::class.java).stop()
            currentMusic = null
        }
    }

}