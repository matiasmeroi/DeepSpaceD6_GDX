package mati.dsd6_gdx.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import mati.dsd6_gdx.D6
import mati.dsd6_gdx.audio.AudioManager
import org.w3c.dom.Text

class GameOverScreen(val d6 : D6) : Screen {

    companion object {
        const val WIN_TEXTURE = "win.png"
        const val GAME_OVER_TEXTURE = "loser.png"
    }

    var regWidth  = 0
    var regHeight = 0

    lateinit var tex : Texture

    override fun show() {
        if(D6.gameWon) {
            tex = D6.assets.get(WIN_TEXTURE, Texture::class.java)
        } else {
            tex = D6.assets.get(GAME_OVER_TEXTURE, Texture::class.java)
        }
        regWidth = tex.width
        regHeight = tex.height

        AudioManager.stopMusic()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        D6.batch.begin()
        D6.batch.draw(tex, Gdx.graphics.width / 2 - regWidth / 2 + 0f, Gdx.graphics.height / 2 - regHeight / 2 + 0f)
        D6.batch.end()

        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) d6.gotoMenu()
    }

    override fun resize(width: Int, height: Int) {}
    override fun pause() {}

    override fun resume() {
    }

    override fun hide() {}

    override fun dispose() {}
}