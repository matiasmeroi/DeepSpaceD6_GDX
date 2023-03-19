package mati.dsd6_gdx.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Scaling
import com.badlogic.gdx.utils.viewport.ScalingViewport
import mati.dsd6.*
import mati.dsd6_gdx.*
import mati.dsd6_gdx.Constants.ASSIGN_COMMANDER_X
import mati.dsd6_gdx.Constants.ASSIGN_COMMANDER_Y
import mati.dsd6_gdx.Constants.ASSIGN_ENGINEERING_X
import mati.dsd6_gdx.Constants.ASSIGN_ENGINEERING_Y
import mati.dsd6_gdx.Constants.ASSIGN_MEDICAL_X
import mati.dsd6_gdx.Constants.ASSIGN_MEDICAL_Y
import mati.dsd6_gdx.Constants.ASSIGN_SCIENCE_X
import mati.dsd6_gdx.Constants.ASSIGN_SCIENCE_Y
import mati.dsd6_gdx.Constants.ASSIGN_TACTICAL_X
import mati.dsd6_gdx.Constants.ASSIGN_TACTICAL_Y
import mati.dsd6_gdx.Constants.CHANGE_DIE_BUTTON_X
import mati.dsd6_gdx.Constants.CHANGE_DIE_BUTTON_Y
import mati.dsd6_gdx.Constants.EXTERNAL_THREATS_BARS_X
import mati.dsd6_gdx.Constants.EXTERNAL_THREATS_BAR_0_Y
import mati.dsd6_gdx.Constants.EXTERNAL_THREATS_BAR_1_Y
import mati.dsd6_gdx.Constants.EXTERNAL_THREATS_BAR_2_Y
import mati.dsd6_gdx.Constants.EXTERNAL_THREATS_BAR_3_Y
import mati.dsd6_gdx.Constants.EXTERNAL_THREATS_BAR_4_Y
import mati.dsd6_gdx.Constants.EXTERNAL_THREATS_CARDS_SEP
import mati.dsd6_gdx.Constants.EXTERNAL_THREATS_CARDS_X_OFFSET
import mati.dsd6_gdx.Constants.EXTERNAL_THREATS_CARDS_Y_OFFSET
import mati.dsd6_gdx.Constants.HULL_INDICATOR_START_Y
import mati.dsd6_gdx.Constants.HULL_INDICATOR_X
import mati.dsd6_gdx.Constants.INDICATOR_HULL_SEP
import mati.dsd6_gdx.Constants.INDICATOR_SHIELD_SEP
import mati.dsd6_gdx.Constants.INDICATOR_SIZE
import mati.dsd6_gdx.Constants.INTERNAL_THREATS_SEP
import mati.dsd6_gdx.Constants.INTERNAL_THREATS_START_Y
import mati.dsd6_gdx.Constants.INTERNAL_THREATS_X
import mati.dsd6_gdx.Constants.LOCKED_DICE_PAD
import mati.dsd6_gdx.Constants.LOCKED_DICE_START_X
import mati.dsd6_gdx.Constants.LOCKED_DICE_Y
import mati.dsd6_gdx.Constants.LOCKED_DIE_SIZE
import mati.dsd6_gdx.Constants.LOCKED_THREATS_SEP
import mati.dsd6_gdx.Constants.LOCKED_THREATS_X
import mati.dsd6_gdx.Constants.LOCKED_THREATS_Y
import mati.dsd6_gdx.Constants.RECHARGE_SHIELDS_BUTTON_X
import mati.dsd6_gdx.Constants.RECHARGE_SHIELDS_BUTTON_Y
import mati.dsd6_gdx.Constants.REPAIR_HULL_BUTTON_X
import mati.dsd6_gdx.Constants.REPAIR_HULL_BUTTON_Y
import mati.dsd6_gdx.Constants.REROLL_CREW_BUTTON_X
import mati.dsd6_gdx.Constants.REROLL_CREW_BUTTON_Y
import mati.dsd6_gdx.Constants.RETURN_INFIRMARY_BUTTON_X
import mati.dsd6_gdx.Constants.RETURN_INFIRMARY_BUTTON_Y
import mati.dsd6_gdx.Constants.RETURN_THREAT_BUTTON_X
import mati.dsd6_gdx.Constants.RETURN_THREAT_BUTTON_Y
import mati.dsd6_gdx.Constants.ROLLED_DIE_SEP
import mati.dsd6_gdx.Constants.ROLLED_DIE_Y
import mati.dsd6_gdx.Constants.ROLLED_MIDDLE_X
import mati.dsd6_gdx.Constants.SCROLL_SPEED
import mati.dsd6_gdx.Constants.SHIELD_INDICATOR_START_Y
import mati.dsd6_gdx.Constants.SHIELD_INDICATOR_X
import mati.dsd6_gdx.Constants.ZOOM_SPEED
import mati.dsd6_gdx.actors.*
import mati.dsd6_gdx.audio.AudioManager
import mati.dsd6_gdx.audio.AudioType
import mati.dsd6_gdx.ui.ButtonsUI
import mati.dsd6_gdx.ui.DieChangeSelector
import mati.dsd6_gdx.ui.ThreatCardOptionsList
import com.badlogic.gdx.utils.Array as GdxArray


class PlayScreen(val d6 : D6) : Screen, DSD6.DSD6Observer {

    val game = DSD6()

    val backStage = Stage(
        ScalingViewport(
            Scaling.stretch,
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat(),
            OrthographicCamera()
        ), D6.batch
    )

    val mainStage = Stage(
        ScalingViewport(
            Scaling.stretch,
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat(),
            OrthographicCamera()
        ), D6.batch
    )

    private val uiStage = Stage(
        ScalingViewport(
            Scaling.stretch,
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat(),
            OrthographicCamera()
        ), D6.batch
    )

    private val background = Background()

    private val changeDieButton = ImageButton(TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("change_die_button"))),
        TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("change_die_button_over"))))

    private val rerollCrewButton = ImageButton(TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("reroll_crew_button"))),
        TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("reroll_crew_button_over"))))

    private val rechargeShieldsButton = ImageButton(TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("recharge_shields_button"))),
        TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("recharge_shields_button_over"))))

    private val returnInfirmaryButton = ImageButton(TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("return_infirmary_button"))),
        TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("return_infirmary_button_over"))))

    private val returnThreatButton = ImageButton(TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("return_threat_button"))),
        TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("return_threat_button_over"))))

    private val repairHullButton = ImageButton(TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("repair_hull_button"))),
        TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("repair_hull_button_over"))))
    private val medButton = ImageButton(TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("ass_med"))))
    private val comButton = ImageButton(TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("ass_com"))))
    private val tacButton = ImageButton(TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("ass_tac"))))
    private val sciButton = ImageButton(TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("ass_sci"))))

    private val engButton = ImageButton(TextureRegionDrawable(TextureRegion(D6.getAtlas().findRegion("ass_eng"))))
    private val buttonsUi = ButtonsUI(game)

    private val board = Board()
    private val diceList = GdxArray<CrewDieActor>()
    private val threatCardList = GdxArray<ThreatCardActor>()

    private val dieChangeOptionList = DieChangeSelector(this)
    val repairHullOptionList = RepairHullOptionList(this)
    private val threatCardOptions = ThreatCardOptionsList(this)

    private val hullIndicator = Indicator36()
    private val shieldIndicator = Indicator36()

    private val threatDieActor = DieActor()

    val commanderLocked = Sprite(TextureRegion(D6.getAtlas().findRegion("commander_locked")))
    val scienceLocked = Sprite(TextureRegion(D6.getAtlas().findRegion("science_locked")))
    val engineeringLocked = Sprite(TextureRegion(D6.getAtlas().findRegion("engineering_locked")))

    init {
        game.dsd6Listener = this

        changeDieButton.setPosition(CHANGE_DIE_BUTTON_X, CHANGE_DIE_BUTTON_Y)
        rerollCrewButton.setPosition(REROLL_CREW_BUTTON_X, REROLL_CREW_BUTTON_Y)
        rechargeShieldsButton.setPosition(RECHARGE_SHIELDS_BUTTON_X, RECHARGE_SHIELDS_BUTTON_Y)
        returnInfirmaryButton.setPosition(RETURN_INFIRMARY_BUTTON_X, RETURN_INFIRMARY_BUTTON_Y)
        returnThreatButton.setPosition(RETURN_THREAT_BUTTON_X, RETURN_THREAT_BUTTON_Y)
        repairHullButton.setPosition(REPAIR_HULL_BUTTON_X, REPAIR_HULL_BUTTON_Y)

        dieChangeOptionList.isVisible = false
        repairHullOptionList.makeInvisible()

        comButton.setPosition(ASSIGN_COMMANDER_X, ASSIGN_COMMANDER_Y)
        tacButton.setPosition(ASSIGN_TACTICAL_X, ASSIGN_TACTICAL_Y)
        sciButton.setPosition(ASSIGN_SCIENCE_X, ASSIGN_SCIENCE_Y)
        medButton.setPosition(ASSIGN_MEDICAL_X, ASSIGN_MEDICAL_Y)
        engButton.setPosition(ASSIGN_ENGINEERING_X, ASSIGN_ENGINEERING_Y)

        commanderLocked.setPosition(LOCKED_DICE_START_X, LOCKED_DICE_Y)
        engineeringLocked.setPosition(LOCKED_DICE_START_X + LOCKED_DICE_PAD + LOCKED_DIE_SIZE, LOCKED_DICE_Y)
        scienceLocked.setPosition(LOCKED_DICE_START_X + 2 * LOCKED_DICE_PAD + LOCKED_DIE_SIZE, LOCKED_DICE_Y)

        background.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                makeListsInvisible()
            }
        })

        board.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                makeListsInvisible()
            }
        })

        changeDieButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    dieChangeOptionList.makeVisible()
                }
            }
        })

        rerollCrewButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    game.commanderRerollAction()
                }
            }
        })

        rechargeShieldsButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    game.rechargeShields()
                }
            }
        })

        returnInfirmaryButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    game.returnAllUnitsFromInfirmary()
                }
            }
        })

        returnThreatButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    game.returnLockedThreat()
                }
            }
        })

        repairHullButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    repairHullOptionList.makeVisible()
                }
            }
        })

        comButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    comPressed()
                }
            }
        })

        tacButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    tacPressed()
                }
            }
        })

        sciButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    sciPressed()
                }
            }
        })

        medButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    medPressed()
                }
            }
        })

        engButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                synchronized(game) {
                    engPressed()
                }
            }
        })

        backStage.addActor(background)

        mainStage.addActor(board)

        mainStage.addActor(changeDieButton)
        mainStage.addActor(rerollCrewButton)
        mainStage.addActor(rechargeShieldsButton)
        mainStage.addActor(returnInfirmaryButton)
        mainStage.addActor(returnThreatButton)
        mainStage.addActor(repairHullButton)

        mainStage.addActor(comButton)
        mainStage.addActor(tacButton)
        mainStage.addActor(sciButton)
        mainStage.addActor(medButton)
        mainStage.addActor(engButton)

        mainStage.addActor(hullIndicator)
        mainStage.addActor(shieldIndicator)

        mainStage.addActor(dieChangeOptionList)
        mainStage.addActor(repairHullOptionList)
        mainStage.addActor(threatCardOptions)

        mainStage.addActor(threatDieActor)

        uiStage.addActor(buttonsUi)

        mainStage.camera.translate(
            Board.WIDTH / 2 - mainStage.camera.viewportWidth / 2,
            Board.HEIGHT / 2 - mainStage.camera.viewportHeight / 2, 0f)

        AudioManager.play(AudioType.MAIN_2_MUSIC)

        createDice()

        arrangeReturnedList()
        arrangeIndicators()
    }

    private fun makeListsInvisible() {
        if(threatCardOptions.isVisible) threatCardOptions.makeInvisible()
        if(repairHullOptionList.isVisible) repairHullOptionList.makeInvisible()
        if(dieChangeOptionList.isVisible) dieChangeOptionList.isVisible = false
    }

    private fun arrangeIndicators() {
        synchronized(game) {
            val h = game.state.hull
            hullIndicator.setPosition(HULL_INDICATOR_X, HULL_INDICATOR_START_Y + h * INDICATOR_SIZE + h * INDICATOR_HULL_SEP)

            val s = game.state.shield
            val sx = if(game.state.shieldsOnline) SHIELD_INDICATOR_X else SHIELD_INDICATOR_X + INDICATOR_SIZE + 2
            shieldIndicator.setPosition(sx, SHIELD_INDICATOR_START_Y + s * INDICATOR_SIZE + s * INDICATOR_SHIELD_SEP)
        }
    }

    private fun createDice() {
        synchronized(game) {
            for(i in game.state.returnedList) {
                val die = CrewDieActor(i)
                diceList.add(die)
                mainStage.addActor(die)
            }
        }
    }





    private fun comPressed() {
        if(game.state.gameState == DSD6.GameState.USE_CREW) {
            for(d in game.state.rolledDiceList) {
                if(d.value == CrewDie.Value.COMMANDER) {
                    game.assignCommander(d.id)
                    break
                }
            }
        }
    }

    private fun engPressed() {
        synchronized(game) {
            if(game.state.gameState == DSD6.GameState.USE_CREW) {
                for(d in game.state.rolledDiceList) {
                    if(d.value == CrewDie.Value.ENGINEERING) {
                        game.assignEngineering(d.id)
                        break
                    }
                }
            }
        }
    }

    private fun tacPressed() {
        if(game.state.gameState == DSD6.GameState.USE_CREW) {
            for(d in game.state.rolledDiceList) {
                if(d.value == CrewDie.Value.TACTICAL) {
                    game.assignTactical(d.id)
                    break
                }
            }
        }
    }

    private fun medPressed() {
        if(game.state.gameState == DSD6.GameState.USE_CREW) {
            for(d in game.state.rolledDiceList) {
                if(d.value == CrewDie.Value.MEDICAL) {
                    game.assignMedical(d.id)
                    break
                }
            }
        }
    }


    private fun sciPressed() {
        if(game.state.gameState == DSD6.GameState.USE_CREW) {
            for(d in game.state.rolledDiceList) {
                if(d.value == CrewDie.Value.SCIENCE) {
                    game.assignScience(d.id)
                    break
                }
            }
        }
    }

    private fun cardClicked(tca: ThreatCardActor) {
        threatCardOptions.makeVisible(tca)
    }

    fun dieChangeOptionListClicked() {
        val dieID = this.getSomeRolledUnusedDie(str2die(dieChangeOptionList.fromList.selected))
        val newValue = str2die(dieChangeOptionList.toList.selected)

        println("${dieChangeOptionList.fromList.selected}, ${dieChangeOptionList.toList.selected}")
        println("$newValue")
        game.changeDieValue(dieID, newValue)

        dieChangeOptionList.isVisible = false
    }

    private fun str2die(str : String) : CrewDie.Value{
        return when(str) {
            CrewDie.Value.COMMANDER.toString() -> CrewDie.Value.COMMANDER
            CrewDie.Value.MEDICAL.toString() -> CrewDie.Value.MEDICAL
            CrewDie.Value.ENGINEERING.toString() -> CrewDie.Value.ENGINEERING
            CrewDie.Value.SCIENCE.toString() -> CrewDie.Value.SCIENCE
            CrewDie.Value.TACTICAL.toString() -> CrewDie.Value.TACTICAL
            CrewDie.Value.THREAT_DETECTED.toString() -> CrewDie.Value.THREAT_DETECTED
            else -> {
                println("str2die INVALID TYPE")
                CrewDie.Value.COMMANDER
            }
        }
    }

    fun repairHullOptionClicked() {
        when(repairHullOptionList.selected) {
            RepairHullOptionList.Options.REPAIR_HULL_1.toString() -> game.repairHull(1)
            RepairHullOptionList.Options.REPAIR_HULL_2.toString() -> game.repairHull(2)
            RepairHullOptionList.Options.REPAIR_HULL_3.toString() -> game.repairHull(3)
            RepairHullOptionList.Options.REPAIR_HULL_4.toString() -> game.repairHull(4)
        }
        repairHullOptionList.makeInvisible()
    }

    fun threatCardOptionClicked() {
        val cid = threatCardOptions.threatCard!!.card.id
        when(threatCardOptions.selected) {
            ThreatCardOptionsList.Options.FIRE_1.toString() -> game.fireWeapons(1, cid)
            ThreatCardOptionsList.Options.FIRE_2.toString() -> game.fireWeapons(2, cid)
            ThreatCardOptionsList.Options.FIRE_3.toString() -> game.fireWeapons(3, cid)
            ThreatCardOptionsList.Options.STASIS.toString() -> {
                if(threatCardOptions.threatCard!!.card.info.health > 0)
                    game.shootStasisBeam(threatCardOptions.threatCard!!.card.id)
            }
            ThreatCardOptionsList.Options.ASS_COMMANDER.toString() ->
                game.assignCommander2Threat(getSomeRolledUnusedDie(CrewDie.Value.COMMANDER), cid)
            ThreatCardOptionsList.Options.ASS_MEDICAL.toString() ->
                game.assignMedical2Threat(getSomeRolledUnusedDie(CrewDie.Value.MEDICAL), cid)
            ThreatCardOptionsList.Options.ASS_ENGINEERING.toString() ->
                game.assignEngineering2Threat(getSomeRolledUnusedDie(CrewDie.Value.ENGINEERING), cid)
            ThreatCardOptionsList.Options.ASS_TACTICAL.toString() ->
                game.assignTactical2Threat(getSomeRolledUnusedDie(CrewDie.Value.TACTICAL), cid)
            ThreatCardOptionsList.Options.ASS_SCIENCE.toString() ->
                game.assignScience2Threat(getSomeRolledUnusedDie(CrewDie.Value.SCIENCE), cid)
        }

        threatCardOptions.makeInvisible()
    }







    private fun getSomeRolledUnusedDie(v : CrewDie.Value) : ID{
        for(d in game.state.rolledDiceList) {
            if(d.value == v && !d.actionResolved) return d.id
        }
        return -1
    }

    private fun arrangeReturnedList() {
        var count = 0
        val sep = 8
        for(die in game.state.returnedList) {
            val actor = getDieActor(die)
            actor!!.changePosition(
                Constants.RETURNED_X + 8 + (count % 2) * CrewDieActor.SIZE + 0f + (count % 2 + 1) * sep ,
                Constants.RETURNED_Y +  (count / 2) * CrewDieActor.SIZE + 0f + (count / 2 + 1) * sep )
            count++
        }
    }

    private fun arrangeRolledList() {
        var count = 0

        val dicePool = GdxArray<CrewDie>()
        for(i in game.state.rolledDiceList) dicePool.add(i)
        for(i in game.state.assignedCommander) dicePool.add(i)
        for(i in game.state.assignedEngineering) dicePool.add(i)
        for(i in game.state.assignedMedical) dicePool.add(i)
        for(i in game.state.assignedTactical) dicePool.add(i)
        for(i in game.state.assignedScience) dicePool.add(i)

        var amt = dicePool.size

        if(amt == 0) return

        var startX = ROLLED_MIDDLE_X - (amt - 1) * CrewDieActor.SIZE / 2 - CrewDieActor.SIZE / 2 - (amt - 1) * ROLLED_DIE_SEP


        for(die in dicePool) {
            val actor = getDieActor(die)
            actor!!.changePosition(startX + ROLLED_DIE_SEP * count + 0f + count * CrewDieActor.SIZE, ROLLED_DIE_Y + 0f)
            count++
        }
    }

    private fun arrangeInfirmaryList() {
        var count = 0
        val sep = 4
        for(die in game.state.infirmaryList) {
            val actor = getDieActor(die)
            actor!!.changePosition(
                Constants.INFIRMARY_X + 8 + (count % 2) * CrewDieActor.SIZE + 0f + (count % 2 + 1) * sep ,
                Constants.INFIRMARY_Y +  (count / 2) * CrewDieActor.SIZE + 0f + (count / 2 + 1) * sep )
            count++
        }
    }


    private fun arrangeLockedThreatsList() {
        var count = 0
        for(die in game.state.lockedThreats) {
            val actor = getDieActor(die)
            actor!!.changePosition(LOCKED_THREATS_X + LOCKED_THREATS_SEP * count + 0f + count * CrewDieActor.SIZE, LOCKED_THREATS_Y + 0f)
            count++
        }
    }

    private fun arrangeThreatCards() {
        var count = 0
        for(i in game.state.externalThreats[4]!!) {
            val tca = getThreatCardActor(i)
            tca.setPosition(EXTERNAL_THREATS_BARS_X + EXTERNAL_THREATS_CARDS_X_OFFSET + count * EXTERNAL_THREATS_CARDS_SEP + ThreatCardActor.WIDTH * count,
                EXTERNAL_THREATS_BAR_4_Y + EXTERNAL_THREATS_CARDS_Y_OFFSET)
            count++
        }

        count = 0
        for(i in game.state.externalThreats[3]!!) {
            val tca = getThreatCardActor(i)
            tca.setPosition(EXTERNAL_THREATS_BARS_X + EXTERNAL_THREATS_CARDS_X_OFFSET + count * EXTERNAL_THREATS_CARDS_SEP + ThreatCardActor.WIDTH * count,
                EXTERNAL_THREATS_BAR_3_Y + EXTERNAL_THREATS_CARDS_Y_OFFSET)
            count++
        }

        count = 0
        for(i in game.state.externalThreats[2]!!) {
            val tca = getThreatCardActor(i)
            tca.setPosition(EXTERNAL_THREATS_BARS_X + EXTERNAL_THREATS_CARDS_X_OFFSET + count * EXTERNAL_THREATS_CARDS_SEP + ThreatCardActor.WIDTH * count,
                EXTERNAL_THREATS_BAR_2_Y + EXTERNAL_THREATS_CARDS_Y_OFFSET)
            count++
        }

        count = 0
        for(i in game.state.externalThreats[1]!!) {
            val tca = getThreatCardActor(i)
            tca.setPosition(EXTERNAL_THREATS_BARS_X + EXTERNAL_THREATS_CARDS_X_OFFSET + count * EXTERNAL_THREATS_CARDS_SEP + ThreatCardActor.WIDTH * count,
                EXTERNAL_THREATS_BAR_1_Y + EXTERNAL_THREATS_CARDS_Y_OFFSET)
            count++
        }

        count = 0
        for(i in game.state.externalThreats[0]!!) {
            val tca = getThreatCardActor(i)
            tca.setPosition(EXTERNAL_THREATS_BARS_X + EXTERNAL_THREATS_CARDS_X_OFFSET + count * EXTERNAL_THREATS_CARDS_SEP + ThreatCardActor.WIDTH * count,
                EXTERNAL_THREATS_BAR_0_Y + EXTERNAL_THREATS_CARDS_Y_OFFSET)
            count++
        }

        count = 0
        for(i in game.state.internalThreats) {
            val tca = getThreatCardActor(i)
            tca.setPosition(INTERNAL_THREATS_X, INTERNAL_THREATS_START_Y + count * ThreatCardActor.HEIGHT + count * INTERNAL_THREATS_SEP)
            count++
        }


    }


    fun lookForDeletedThreats() {
        val iter = threatCardList.iterator()
        while(iter.hasNext()) {
            val actor = iter.next()
            if(!game.threatInPlay(actor.card.id)) {
                iter.remove()
                actor.remove()
            }

        }
    }

    /**
     * Si un dado está asignado a una amenaza no pertenece a ningunalista y se encarga de dibujarlo threatCardActor
     * por lo que se pone como invisible
     * */
    fun updateDiceVisibility() {
        val visiblePool = getVisibleDiceListsPool()
        for(dieActor in this.diceList) {
            if(dieActor.die in visiblePool) dieActor.isVisible = true
            else dieActor.isVisible = false
        }
    }


    private fun getVisibleDiceListsPool() : GdxArray<CrewDie> {
        val pool = GdxArray<CrewDie>()

        for(d in game.state.rolledDiceList) pool.add(d)
        for(d in game.state.returnedList) pool.add(d)
        for(d in game.state.lockedThreats) pool.add(d)
        for(d in game.state.infirmaryList) pool.add(d)
        for(d in game.state.assignedScience) pool.add(d)
        for(d in game.state.assignedMedical) pool.add(d)
        for(d in game.state.assignedTactical) pool.add(d)
        for(d in game.state.assignedCommander) pool.add(d)
        for(d in game.state.assignedEngineering) pool.add(d)

        return pool
    }

    private fun getDieActor(gameDie : CrewDie) : CrewDieActor? {
        for(i in diceList) {
            if(i.die.id == gameDie.id) return i
        }
        return null
    }

    private fun threatCardActorExists(card : ThreatCard) : Boolean {
        for(i in threatCardList) {
            if(i.card.id == card.id) return true
        }
        return false
    }

    private fun getThreatCardActor(card: ThreatCard) : ThreatCardActor {
        if(threatCardActorExists(card)) {
            for(i in threatCardList) if(i.card.id == card.id) return i
        } else {
            return createThreatCardActor(card)
        }
        println("<>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ERROR>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        return ThreatCardActor(card)
    }


    private fun createThreatCardActor(card: ThreatCard) : ThreatCardActor {
        val newActor = ThreatCardActor(card)

        newActor.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                cardClicked(newActor)
            }
        }.apply { button = Input.Buttons.RIGHT })

        threatCardList.add(newActor)
        mainStage.addActor(newActor)

        return newActor
    }

    private fun arrangeAllLists() {
        arrangeReturnedList()
        arrangeRolledList()
        arrangeInfirmaryList()
        arrangeLockedThreatsList()
        arrangeRolledList()
    }

    override fun show() {
        D6.multiplexer.addProcessor(uiStage)
        D6.multiplexer.addProcessor(mainStage)
        D6.multiplexer.addProcessor(backStage)
    }

    override fun onDSD6Event(gameEvent: DSD6.GameEvent, state: DSD6.DSD6State) {
        when(gameEvent) {
            DSD6.GameEvent.DIFFICULTY_CHANGE -> {}
            DSD6.GameEvent.GAME_STATE_CHANGE -> buttonsUi.gameStateLabel.setText(game.state.gameState.toString())

            DSD6.GameEvent.THREAT_SCANNED -> AudioManager.play(AudioType.THREAT_SCANNED_SOUND)
            DSD6.GameEvent.THREAT_DETECTED -> AudioManager.play(AudioType.THREAT_DETECTED_SOUND)

            DSD6.GameEvent.LIST_RETURNED_CHANGE -> arrangeReturnedList()
            DSD6.GameEvent.LIST_ROLLED_CHANGE -> arrangeRolledList()
            DSD6.GameEvent.LIST_INFIRMARY_CHANGE -> arrangeInfirmaryList()
            DSD6.GameEvent.LIST_LOCKED_THREATS_CHANGE -> arrangeLockedThreatsList()
            DSD6.GameEvent.LIST_ASSIGNED_COMMANDER_CHANGE -> arrangeRolledList()
            DSD6.GameEvent.LIST_ASSIGNED_TACTICAL_CHANGE -> arrangeRolledList()
            DSD6.GameEvent.LIST_ASSIGNED_MEDICAL_CHANGE -> arrangeRolledList()
            DSD6.GameEvent.LIST_ASSIGNED_SCIENCE_CHANGE -> arrangeRolledList()
            DSD6.GameEvent.LIST_ASSIGNED_ENGINEERING_CHANGE -> arrangeRolledList()
            DSD6.GameEvent.LIST_ASSIGNED_COMMANDER_CHANGE -> arrangeRolledList()
            DSD6.GameEvent.LIST_ASSIGNED_TACTICAL_CHANGE -> arrangeRolledList()
            DSD6.GameEvent.LIST_ASSIGNED_MEDICAL_CHANGE -> arrangeRolledList()
            DSD6.GameEvent.LIST_ASSIGNED_SCIENCE_CHANGE -> arrangeRolledList()
            DSD6.GameEvent.LIST_ASSIGNED_ENGINEERING_CHANGE -> arrangeRolledList()

            DSD6.GameEvent.LIST_THREAT_STACK_CHANGE -> buttonsUi.updateDrawText(game.state.threatStack.size)

            DSD6.GameEvent.ASSIGNED_DIE_USED,
            DSD6.GameEvent.LISTS_CHANGE -> {
                arrangeReturnedList()
                arrangeRolledList()
                arrangeLockedThreatsList()
                arrangeInfirmaryList()
            }


            DSD6.GameEvent.CHANGED_ALLOWED_ASSIGNATIONS -> { }

            DSD6.GameEvent.LIST_INTERNAL_THREATS_CHANGE,
            DSD6.GameEvent.LIST_EXTENAL_THREATS_CHANCE,
            DSD6.GameEvent.THREATS_UPDATED,
            DSD6.GameEvent.THREATS_ACTIVATED -> {
                lookForDeletedThreats()
                arrangeThreatCards()
                threatCardOptions.makeInvisible()
            }
            DSD6.GameEvent.HULL_CHANGE,
            DSD6.GameEvent.SHIELD_CHANGE -> arrangeIndicators()

            DSD6.GameEvent.DIE_RETURNED_FROM_THREAT,
            DSD6.GameEvent.DIE_ASSIGNED_TO_THREAT -> {
                updateDiceVisibility()
            }

            DSD6.GameEvent.THREAT_DIE_ROLLED -> {
                threatDieActor.num = game.state.threatDie
            }

            DSD6.GameEvent.STASIS_BEAM_SHOT -> AudioManager.play(AudioType.STASIS_BEAM_SOUND)
            DSD6.GameEvent.WEAPONS_FIRED -> AudioManager.play(AudioType.WEAPONS_FIRED_SOUND)
            DSD6.GameEvent.FIXED_STH -> AudioManager.play(AudioType.FIX_STH_SOUND)
            DSD6.GameEvent.THREAT_CARD_DRAWN -> AudioManager.play(AudioType.CARD_DRAWN_SOUND)
            DSD6.GameEvent.CREW_ASSIGNED -> AudioManager.play(AudioType.ASSIGN_SOUND)
            DSD6.GameEvent.DICE_ROLLED -> AudioManager.play(AudioType.DICE_ROLL_SOUND)
            DSD6.GameEvent.TAKEN_DMG -> AudioManager.play(AudioType.IMPACT_1_SOUND)
            DSD6.GameEvent.GAME_WON -> {
                D6.gameWon = true
                d6.gotoGameOverScreen()
            }
            DSD6.GameEvent.GAME_LOST -> {
                D6.gameWon = false
                d6.gotoGameOverScreen()
            }
        }

        updateDiceVisibility()
    }

    override fun render(delta: Float) {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))  {
            makeListsInvisible()
        }
        diceList.forEach { d -> d.update(game) }

        backStage.act(delta)
        mainStage.act(delta)

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        val cam = mainStage.camera


        if(!Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && !Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if (!Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && !Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && D6.scrolled)
                cam.translate(0f, -D6.scrollY * SCROLL_SPEED, 0f)
            else if (!Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && D6.scrolled)
                cam.translate(D6.scrollY * SCROLL_SPEED, 0f, 0f)
            else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                cam.translate(-Gdx.input.deltaX + 0f, Gdx.input.deltaY + 0f, 0f)
            }
        }

        if((Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            && !Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && D6.scrolled
        ) {
            val camera = mainStage.camera as OrthographicCamera
            camera.zoom = camera.zoom + ZOOM_SPEED * D6.scrollY
        }


        backStage.draw()
        mainStage.draw()
        uiStage.draw()

        if(!(game.state.commanderAssignationAllowed && game.state.engineeringAssignationAllowed && game.state.scienceAssignationAllowed)) {
            D6.batch.begin()
            if(!game.state.commanderAssignationAllowed) commanderLocked.draw(uiStage.batch)
            if(!game.state.engineeringAssignationAllowed) engineeringLocked.draw(uiStage.batch)
            if(!game.state.scienceAssignationAllowed) scienceLocked.draw(uiStage.batch)
            D6.batch.end()
        }
    }
    override fun dispose() {
        backStage.dispose()
        mainStage.dispose()
        uiStage.dispose()
    }
    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}

    override fun hide() {
        D6.multiplexer.removeProcessor(backStage)
        D6.multiplexer.removeProcessor(mainStage)
        D6.multiplexer.removeProcessor(uiStage)
    }
}
