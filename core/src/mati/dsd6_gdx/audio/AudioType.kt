package mati.dsd6_gdx.audio

enum class AudioType(val filePath: String, val isMusic: Boolean, val volume: Float) {

    MAIN_1_MUSIC("audio/music/main_1.mp3", true, 0.3f),
    MAIN_2_MUSIC("audio/music/main_2.mp3", true, 0.3f),
    FIX_STH_SOUND("audio/sounds/fix_16.wav", false, 1f),
    WEAPONS_FIRED_SOUND("audio/sounds/weapons_16.wav", false, 1f),
    DICE_ROLL_SOUND("audio/sounds/roll_16.wav", false, 1.3f),
    STASIS_BEAM_SOUND("audio/sounds/stasis_16.wav", false, 1f),
    IMPACT_1_SOUND("audio/sounds/impact_1.mp3", false, 1f),
    IMPACT_2_SOUND("audio/sounds/impact_2.mp3", false, 1f),
    IMPACT_3_SOUND("audio/sounds/impact_3.mp3", false, 1f),
    CARD_DRAWN_SOUND("audio/sounds/card_draw.mp3", false, 1.1f),
    BUTTON_CLICKED_SOUND("audio/sounds/button_click.wav", false, 1f),
    BUTTON_CLICKED_IN_GAME_SOUND("audio/sounds/button_click.wav", false, 0.3f),
    ASSIGN_SOUND("audio/sounds/assign.wav", false, 1.3f),
    THREAT_SCANNED_SOUND("audio/sounds/threat_scanned.mp3", false, 0.6f),
    THREAT_DETECTED_SOUND("audio/sounds/threat_detected.mp3", false, 1.4f),
}