package mati.dsd6_gdx.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import mati.dsd6.CrewDie
import mati.dsd6.DSD6
import mati.dsd6_gdx.D6

class CrewDieActor(gameDie : CrewDie) : Actor() {

    companion object {
        const val TEXTURE_MED = "die_medical"
        const val TEXTURE_COM = "die_commander"
        const val TEXTURE_TAC = "die_tactical"
        const val TEXTURE_ENG = "die_engineering"
        const val TEXTURE_SCI = "die_science"
        const val TEXTURE_THR = "die_threat"

        const val SIZE = 64f

        lateinit var drawableMed : Drawable
        lateinit var drawableCom : Drawable
        lateinit var drawableTac : Drawable
        lateinit var drawableSci : Drawable
        lateinit var drawableEng : Drawable
        lateinit var drawableThr : Drawable

        fun loadDrawable() {
            drawableMed = TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion(TEXTURE_MED)))
            drawableCom = TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion(TEXTURE_COM)))
            drawableTac = TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion(TEXTURE_TAC)))
            drawableSci = TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion(TEXTURE_SCI)))
            drawableEng = TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion(TEXTURE_ENG)))
            drawableThr = TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion(TEXTURE_THR)))
        }

        fun getDrawable(die : CrewDie) : Drawable {
            return when(die.value) {
                CrewDie.Value.COMMANDER -> drawableCom
                CrewDie.Value.TACTICAL -> drawableTac
                CrewDie.Value.MEDICAL -> drawableMed
                CrewDie.Value.SCIENCE -> drawableSci
                CrewDie.Value.ENGINEERING -> drawableEng
                CrewDie.Value.THREAT_DETECTED -> drawableThr
            }
        }
    }

    val die = gameDie
    var isAssigned : Boolean = false
    var isUsed : Boolean = false

    fun changePosition(x: Float, y : Float) {
        this.addAction(Actions.moveTo(x, y, 0.4f))
    }

    fun update(game : DSD6) {
        checkIfAssigned(game)
        checkIfUssed(game)
    }

    private fun checkIfAssigned(game : DSD6) {
        if(die.value != CrewDie.Value.THREAT_DETECTED) {
            isAssigned = die in game.getAssignedList(die.value)
        }
    }

    private fun checkIfUssed(game : DSD6) {
        if(die.value != CrewDie.Value.THREAT_DETECTED) {
            isUsed = die.actionResolved
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        getDrawable(die).draw(batch, x, y, SIZE + 0f, SIZE + 0f)

        if(isUsed) batch!!.draw(D6.getAtlas().findRegion("used_symbol"), x, y)
        else if(isAssigned) batch!!.draw(D6.getAtlas().findRegion("assigned_symbol"), x, y)

    }
}