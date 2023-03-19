package mati.dsd6_gdx.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import mati.dsd6.ThreatCard
import mati.dsd6_gdx.Constants.HELD_DIE_X
import mati.dsd6_gdx.Constants.HELD_DIE_Y
import mati.dsd6_gdx.D6

class ThreatCardActor(tc : ThreatCard) : ImageButton(getDrawable(tc)) {

    companion object {

        const val WIDTH = 240
        const val HEIGHT = 168

        fun getDrawable(card: ThreatCard) : TextureRegionDrawable {
            return TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion(card.info.imgFile.removeSuffix(".png"))))
        }

    }

    val card : ThreatCard = tc

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        if(card.shotByStasisBeam) {
            batch!!.draw(D6.getAtlas().findRegion("stasis_beam_cover"), x, y)
        } else if(card.activated) {
            batch!!.draw(D6.getAtlas().findRegion("activated_cover"), x, y)
        }

        if(card.assignedDice.isNotEmpty()) {
            var count = 0
            for(f in card.assignedDice) {
                CrewDieActor.getDrawable(f).draw(batch!!, x + count * 32 + count * 2, y, 32f, 32f)
                count++
            }
        }

        if(card.heldDie != null) {
            CrewDieActor.getDrawable(card.heldDie!!).draw(batch!!, x + HELD_DIE_X, y + HELD_DIE_Y, CrewDieActor.SIZE, CrewDieActor.SIZE)
        }
    }
}

