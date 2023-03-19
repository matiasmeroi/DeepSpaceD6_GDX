package mati.dsd6_gdx

import mati.dsd6_gdx.actors.Board
import mati.dsd6_gdx.actors.ThreatCardActor

object Constants {

    const val WINDOW_WIDTH = 1280f
    const val WINDOW_HEIGHT = 720f

    const val STATUS_UI_SKIN = "ui/statusui.json"
    const val STATUS_UI_ATLAS = "ui/statusui.atlas"

    const val DEFAULT_UI_SKIN = "ui/default/skin/uiskin.json"
    const val DEFAULT_UI_ATLAS = "ui/default/skin/uiskin.atlas"

    const val UI_SKIN = DEFAULT_UI_SKIN
    const val UI_ATLAS = DEFAULT_UI_ATLAS


    const val SCROLL_SPEED = 32
    const val ZOOM_SPEED = 0.04f


    // BOARD
    const val HULL_INDICATOR_X = 267f
    const val HULL_INDICATOR_START_Y = Board.HEIGHT - 470f

    const val SHIELD_INDICATOR_X = 315f
    const val SHIELD_INDICATOR_START_Y = Board.HEIGHT - 320f

    const val INDICATOR_HULL_SEP = 0.5f
    const val INDICATOR_SHIELD_SEP = 0f
    const val INDICATOR_SIZE = 36f

    const val CHANGE_DIE_BUTTON_X = 416f
    const val CHANGE_DIE_BUTTON_Y = Board.HEIGHT - 327f

    const val REROLL_CREW_BUTTON_X = 413f
    const val REROLL_CREW_BUTTON_Y = Board.HEIGHT - 345f

    const val RECHARGE_SHIELDS_BUTTON_X = 532f
    const val RECHARGE_SHIELDS_BUTTON_Y = Board.HEIGHT - 557f

    const val RETURN_INFIRMARY_BUTTON_X = 378f
    const val RETURN_INFIRMARY_BUTTON_Y = Board.HEIGHT - 558f

    const val RETURN_THREAT_BUTTON_X = 412f
    const val RETURN_THREAT_BUTTON_Y = Board.HEIGHT - 591f

    const val REPAIR_HULL_BUTTON_X = 471F
    const val REPAIR_HULL_BUTTON_Y = Board.HEIGHT - 649f

    const val RETURNED_X = 30
    const val RETURNED_Y = Board.HEIGHT - 762

    const val INFIRMARY_X = 30f
    const val INFIRMARY_Y = Board.HEIGHT - 912f

    const val ROLLED_X = 300
    const val ROLLED_Y = Board.HEIGHT - 916

    const val ROLLED_DIE_X = 351
    const val ROLLED_DIE_Y = Board.HEIGHT - 901f
    const val ROLLED_MIDDLE_X = ROLLED_X + 431 / 2
    const val ROLLED_DIE_SEP = 2

    const val LOCKED_THREATS_X = 380f
    const val LOCKED_THREATS_Y = Board.HEIGHT - 160f
    const val LOCKED_THREATS_SEP = 1

    const val ASSIGN_SQUARE_SIZE = 37

    const val ASSIGN_ENGINEERING_X = 433f
    const val ASSIGN_ENGINEERING_Y = Board.HEIGHT - 656f

    const val ASSIGN_MEDICAL_X = 479f
    const val ASSIGN_MEDICAL_Y = Board.HEIGHT - 520f

    const val ASSIGN_SCIENCE_X = 533f
    const val ASSIGN_SCIENCE_Y = Board.HEIGHT - 520f

    const val ASSIGN_TACTICAL_X = 426f
    const val ASSIGN_TACTICAL_Y = Board.HEIGHT - 405f

    const val ASSIGN_COMMANDER_X = 482f
    const val ASSIGN_COMMANDER_Y = Board.HEIGHT - 274f

    const val THREAT_DIE_X = 742f
    const val THREAT_DIE_Y = ROLLED_DIE_Y

    const val EXTERNAL_THREATS_BARS_X = 753f
    const val EXTERNAL_THREATS_BAR_0_Y = Board.HEIGHT - 960f
    const val EXTERNAL_THREATS_BAR_1_Y = Board.HEIGHT - 783f
    const val EXTERNAL_THREATS_BAR_2_Y = Board.HEIGHT - 606f
    const val EXTERNAL_THREATS_BAR_3_Y = Board.HEIGHT - 430f
    const val EXTERNAL_THREATS_BAR_4_Y = Board.HEIGHT - 253f
    const val EXTERNAL_THREATS_CARDS_X_OFFSET = 100f
    const val EXTERNAL_THREATS_CARDS_Y_OFFSET = 2f
    const val EXTERNAL_THREATS_CARDS_SEP = 4f

    const val INTERNAL_THREATS_X = -ThreatCardActor.WIDTH - 20f
    const val INTERNAL_THREATS_START_Y = 20F
    const val INTERNAL_THREATS_SEP = 4f

    const val HELD_DIE_X = 164f
    const val HELD_DIE_Y = ThreatCardActor.HEIGHT - 75f

    const val LOCKED_DIE_SIZE = 32f
    const val LOCKED_DICE_PAD = 5f
    const val LOCKED_DICE_START_X = LOCKED_DICE_PAD
    const val LOCKED_DICE_Y = WINDOW_HEIGHT - LOCKED_DIE_SIZE - LOCKED_DICE_PAD

    const val STAR_SPEED = 0.01f
}