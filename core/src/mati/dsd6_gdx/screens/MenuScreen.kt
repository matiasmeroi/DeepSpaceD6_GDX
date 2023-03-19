package mati.dsd6_gdx.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.ui.List
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Scaling
import com.badlogic.gdx.utils.viewport.ScalingViewport
import mati.dsd6.DSD6
import com.badlogic.gdx.utils.Array as GdxArray
import mati.dsd6_gdx.D6
import mati.dsd6_gdx.actors.Background
import mati.dsd6_gdx.audio.AudioManager
import mati.dsd6_gdx.audio.AudioType

class MenuScreen(val d6 : D6) : Screen {

    private val stage = Stage(
            ScalingViewport(
            Scaling.stretch,
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat(),
            OrthographicCamera()
        ), D6.batch)


    private val backgound = Background()

    private val playButton = TextButton("Play", D6.uiSkin)

    private val difficultyList = List<String>(D6.uiSkin)

    private val musicBox = CheckBox("Music", D6.uiSkin)
    private val soundBox = CheckBox("Sound", D6.uiSkin)

    private val exitButton = TextButton("Exit", D6.uiSkin)

    private val table = Table()

    init {

        val difficultyItems = GdxArray<String>()
        difficultyItems.add("Easy", "Normal", "Hard")
        difficultyList.setItems(difficultyItems)
        difficultyList.pack()

        musicBox.isChecked = D6.musicOn
        soundBox.isChecked = D6.soundOn

        playButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                AudioManager.play(AudioType.BUTTON_CLICKED_SOUND)
                playButtonClicked()
            }
        })

        musicBox.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                D6.musicOn = musicBox.isChecked
                AudioManager.play(AudioType.BUTTON_CLICKED_SOUND)
            }
        })

        soundBox.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                D6.soundOn = soundBox.isChecked
                AudioManager.play(AudioType.BUTTON_CLICKED_SOUND)
            }
        })

        exitButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                AudioManager.play(AudioType.BUTTON_CLICKED_SOUND)
                Gdx.app.exit()
            }
        })

        difficultyList.addListener(object:ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                AudioManager.play(AudioType.BUTTON_CLICKED_SOUND)
            }
        })

        table.setFillParent(true)
        table.defaults().center().pad(20f)

        table.add(playButton)

        table.row()
        table.add(difficultyList).center()

        table.row()
        val st = Table()
        st.defaults().pad(10f)
        st.add(musicBox)
        st.add(soundBox)
        table.add(st)


        table.row()
        table.add(exitButton).center()

        stage.addActor(backgound)
        stage.addActor(table)
    }


    override fun show() { D6.multiplexer.addProcessor(stage) }

    fun playButtonClicked() {
        when(difficultyList.selected) {
            "Easy" -> D6.selectedDifficulty = DSD6.Difficulty.EASY
            "Normal" -> D6.selectedDifficulty = DSD6.Difficulty.NORMAL
            "Hard" -> D6.selectedDifficulty = DSD6.Difficulty.HARD
            else -> DSD6.Difficulty.NORMAL
        }
        d6.startGame()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act(delta)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {}

    override fun pause() {}

    override fun resume() {}

    override fun hide() { D6.multiplexer.removeProcessor(stage)}

    override fun dispose() { stage.dispose()}
}