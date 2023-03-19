package mati.dsd6_gdx.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.dsd6_gdx.Constants.THREAT_DIE_X
import mati.dsd6_gdx.Constants.THREAT_DIE_Y
import mati.dsd6_gdx.D6

class DieActor : Actor() {

    var num = 1

    init {
        setPosition(THREAT_DIE_X, THREAT_DIE_Y)
    }

    fun getCurrentRegionName() : String {
        return "d$num"
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        batch!!.draw(D6.getAtlas().findRegion(getCurrentRegionName()), x, y)
    }
}