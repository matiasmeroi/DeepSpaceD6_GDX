package mati.dsd6_gdx.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.dsd6_gdx.D6

class Board : Actor() {

    companion object {
        const val BOARD_TEXTURE = "board.png"

        const val WIDTH = 818f
        const val HEIGHT = 1058f
    }

    val region : TextureRegion = TextureRegion(D6.assets.get(BOARD_TEXTURE, Texture::class.java))

    init {
        setPosition(0f, 0f)
        setSize(region.regionWidth + 0f, region.regionHeight + 0f)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        batch!!.draw(region, x, y)
    }
}