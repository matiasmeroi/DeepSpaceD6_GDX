package mati.dsd6_gdx.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.dsd6_gdx.Constants.INDICATOR_SIZE
import mati.dsd6_gdx.D6

class Indicator36 : Actor() {

    companion object {
        const val REGION = "36_indicator"

        const val SIZE = INDICATOR_SIZE
    }

    init {
        setSize(SIZE, SIZE)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        batch!!.draw(D6.getAtlas().findRegion(REGION), x, y)
    }
}