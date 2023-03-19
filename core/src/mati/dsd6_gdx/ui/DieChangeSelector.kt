package mati.dsd6_gdx.ui

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Window
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import mati.dsd6.CrewDie
import mati.dsd6_gdx.D6
import mati.dsd6_gdx.screens.PlayScreen
import com.badlogic.gdx.utils.Array as GdxArray

class DieChangeSelector(p : PlayScreen) : Window("Select change", D6.uiSkin) {

    val playScreen = p

    private val options = GdxArray<String>()

    val table = Table()

    var fromList : SelectBox<String>
    var toList : SelectBox<String>

    val okButton = TextButton("Ok", D6.uiSkin)

    init {
        for(v in CrewDie.Value.values()) {
            if(v != CrewDie.Value.THREAT_DETECTED) {
                options.add(v.toString())
            }
        }

        fromList = SelectBox(D6.uiSkin)
        fromList.items = options

        toList = SelectBox(D6.uiSkin)
        toList.items = options

        okButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                playScreen.dieChangeOptionListClicked()
            }
        })

        table.setFillParent(true)

        table.add(fromList).padBottom(5f).padTop(5f)
        table.row()
        table.add(toList)

        table.row()
        table.add(okButton).padTop(10f)

        table.pack()

        this.addActor(table)
    }

    fun makeVisible() {
        val pos : Vector2 = D6.mouse2stage(playScreen.mainStage)
        setPosition(pos.x, pos.y)
        isVisible = true
        toFront()
        this.isVisible = true
    }

}