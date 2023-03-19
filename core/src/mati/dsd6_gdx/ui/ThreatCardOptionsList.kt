package mati.dsd6_gdx.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.List
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import mati.dsd6_gdx.D6
import mati.dsd6_gdx.actors.ThreatCardActor
import mati.dsd6_gdx.screens.PlayScreen


import com.badlogic.gdx.utils.Array as GdxArray

class ThreatCardOptionsList(play: PlayScreen) : List<String>(D6.uiSkin) {

    enum class Options(n: String) {
        FIRE_1("Fire 1 die"),
        FIRE_2("Fire 2 dice"),
        FIRE_3("Fire 3 dice"),
        STASIS("Stasis Beam"),
        ASS_SCIENCE("Assign Science"),
        ASS_COMMANDER("Assign Commander"),
        ASS_MEDICAL("Assign Medical"),
        ASS_TACTICAL("Assign Tactical"),
        ASS_ENGINEERING("Assign Engineering");

        val text = n
    }

    val playScreen = play

    var threatCard : ThreatCardActor? = null

    init {
        val arr = GdxArray<String>()
        for(i in Options.values())
            arr.add(i.toString())
        this.setItems(arr)

        this.pack()

        this.isVisible = false

        this.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                play.threatCardOptionClicked()
            }
        })
    }

    fun makeVisible(tc : ThreatCardActor) {
        threatCard = tc

        val pos : Vector2 = D6.mouse2stage(playScreen.mainStage)
        setPosition(pos.x, pos.y)
        isVisible = true
        toFront()
        this.isVisible = true
    }

    fun makeInvisible() {
        threatCard = null
        this.isVisible = false
    }

    override fun drawBackground(batch: Batch?, parentAlpha: Float) {
        super.drawBackground(batch, parentAlpha)
        batch!!.end()

        // draw background
        val sr = ShapeRenderer()
        sr.begin(ShapeType.Filled)
        sr.color = Color.LIGHT_GRAY
        val pos = this.stage.stageToScreenCoordinates(Vector2(x, y))
        val zoom = (stage.camera as OrthographicCamera).zoom
        sr.rect(pos.x, Gdx.graphics.height - pos.y, width / zoom, height / zoom)
        sr.end()

        batch!!.begin()
    }


}