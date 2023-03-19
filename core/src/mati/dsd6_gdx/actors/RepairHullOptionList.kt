package mati.dsd6_gdx.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.List
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Array
import mati.dsd6_gdx.D6
import mati.dsd6_gdx.screens.PlayScreen

class RepairHullOptionList(play: PlayScreen) : List<String>(D6.uiSkin) {

    enum class Options(s : String) {
        REPAIR_HULL_1("Repair Hull: 1d"),
        REPAIR_HULL_2("Repair Hull: 2d"),
        REPAIR_HULL_3("Repair Hull: 3d"),
        REPAIR_HULL_4("Repair Hull: 4d");

        var text = s
    }

    val playScreen = play

    init {
        val arr = Array<String>()
        for(i in Options.values())
            arr.add(i.toString())
        this.setItems(arr)

        this.pack()

        this.isVisible = false

        this.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                play.repairHullOptionClicked()
            }
        })
    }

    fun makeVisible() {
        val pos : Vector2 = D6.mouse2stage(playScreen.mainStage)
        setPosition(pos.x, pos.y)
        isVisible = true
        toFront()
        this.isVisible = true
    }

    fun makeInvisible() {
        this.isVisible = false
    }

    override fun drawBackground(batch: Batch?, parentAlpha: Float) {
        super.drawBackground(batch, parentAlpha)
        batch!!.end()

        // draw background
        val sr = ShapeRenderer()
        sr.begin(ShapeRenderer.ShapeType.Filled)
        sr.color = Color.LIGHT_GRAY
        val pos = this.stage.stageToScreenCoordinates(Vector2(x, y))
        val zoom = (stage.camera as OrthographicCamera).zoom
        sr.rect(pos.x, Gdx.graphics.height - pos.y, width / zoom, height / zoom)
        sr.end()

        batch!!.begin()
    }
}