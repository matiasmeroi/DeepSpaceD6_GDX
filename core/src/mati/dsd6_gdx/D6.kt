package mati.dsd6_gdx

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import mati.dsd6.DSD6
import mati.dsd6_gdx.Constants.UI_ATLAS
import mati.dsd6_gdx.actors.Board
import mati.dsd6_gdx.actors.CrewDieActor
import mati.dsd6_gdx.audio.AudioManager
import mati.dsd6_gdx.screens.GameOverScreen
import mati.dsd6_gdx.screens.MenuScreen
import mati.dsd6_gdx.screens.PlayScreen

class D6 : Game() {

    companion object {
        const val ATLAS_PATH = "atlas/d6pack.atlas"

        var assets = AssetManager()
        lateinit var batch : SpriteBatch
        lateinit var uiSkin : Skin

        val multiplexer = InputMultiplexer()

        fun getAtlas() : TextureAtlas{
            return assets.get(ATLAS_PATH, TextureAtlas::class.java)
        }

        var selectedDifficulty = DSD6.Difficulty.NORMAL
        var musicOn = true
        var soundOn = true
        var gameWon = false

        var scrolled = false
        var scrollY = 0f

        fun mouse2stage(st : Stage) : Vector2{
            return st.screenToStageCoordinates(Vector2(Gdx.input.getX()+ 0f, Gdx.input.getY() + 0f))
        }
    }

    private lateinit var menuScreen : MenuScreen
    private var playScreen : PlayScreen? = null
    private lateinit var gameOverScreen : GameOverScreen

    override fun create() {
        batch = SpriteBatch()
        multiplexer.addProcessor(MyScrollProcessor())
        Gdx.input.inputProcessor = multiplexer
        loadAssets()
        menuScreen = MenuScreen(this)
        gameOverScreen = GameOverScreen(this)
        gotoMenu()
    }

    fun gotoGameOverScreen() {
        setScreen(gameOverScreen)
    }

    fun startGame() {
        if(playScreen != null) {
            playScreen!!.dispose()
            playScreen = null
        }
        playScreen = PlayScreen(this)
        playScreen!!.game.setup(selectedDifficulty)
        this.setScreen(playScreen)
    }

    fun gotoMenu() {
        setScreen(menuScreen)
    }

    override fun render() {
        super.render()
        scrolled = false
    }

    private fun loadAssets() {
        assets.load(UI_ATLAS, TextureAtlas::class.java)

        assets.load(Board.BOARD_TEXTURE, Texture::class.java)
        assets.load(ATLAS_PATH, TextureAtlas::class.java)

        assets.load(GameOverScreen.WIN_TEXTURE, Texture::class.java)
        assets.load(GameOverScreen.GAME_OVER_TEXTURE, Texture::class.java)

        AudioManager.load()

        assets.finishLoading()

        uiSkin = Skin(Gdx.files.internal(Constants.UI_SKIN), assets.get(UI_ATLAS, TextureAtlas::class.java))

        CrewDieActor.loadDrawable()
    }


    override fun dispose() {
        uiSkin.dispose()
        assets.dispose()
        batch.dispose()
        menuScreen.dispose()
        gameOverScreen.dispose()
        if(playScreen != null) playScreen!!.dispose()
    }
}