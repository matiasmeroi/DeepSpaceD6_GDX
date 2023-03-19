package mati.dsd6_gdx.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.Window
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import mati.dsd6.DSD6
import mati.dsd6_gdx.D6
import mati.dsd6_gdx.audio.AudioManager
import mati.dsd6_gdx.audio.AudioType

class ButtonsUI(g : DSD6) : Window("Actions", D6.uiSkin) {

    val game = g

    val table = Table()
    val gameStateLabel = Label("", D6.uiSkin)

    val rollDiceButton = TextButton("Roll", D6.uiSkin)
    val endTurnButton = TextButton("End Turn", D6.uiSkin)
    val drawCardButton = TextButton("Draw (${game.state.threatStack.size})", D6.uiSkin)
    val continueButton = TextButton("Continue", D6.uiSkin)

    init {
        setSize(Gdx.graphics.width + 0f, 100f)
        isMovable = false

        table.defaults().pad(20f)

        rollDiceButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    if (game.state.gameState == DSD6.GameState.DICE_ROLL) game.rollDice()
                    else if (game.state.gameState == DSD6.GameState.THREAT_DIE_ROLL) game.rollThreatDie()
                }
                AudioManager.play(AudioType.BUTTON_CLICKED_IN_GAME_SOUND)
            }
        })

        endTurnButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    game.finishResolvingCrewActions()
                }
                AudioManager.play(AudioType.BUTTON_CLICKED_IN_GAME_SOUND)
            }
        })

        drawCardButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    game.discoverNextThreat()
                }
                AudioManager.play(AudioType.BUTTON_CLICKED_IN_GAME_SOUND)
            }
        })

        continueButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game){
                    game.nextState()
                }
                AudioManager.play(AudioType.BUTTON_CLICKED_IN_GAME_SOUND)
            }
        })

        table.row().center()
        table.add(gameStateLabel)
        table.add(rollDiceButton)
        table.add(endTurnButton)
        table.add(drawCardButton)
        table.add(continueButton)

        this.add(table)
    }

    fun updateDrawText(n : Int) {
        drawCardButton.setText("Draw ($n)")
    }
}
