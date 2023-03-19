package mati.dsd6_gdx.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.scenes.scene2d.Actor
import mati.dsd6_gdx.Constants.STAR_SPEED
import mati.dsd6_gdx.D6

import com.badlogic.gdx.utils.Array as GdxArray

class Background : Actor() {

    class Star(t : Type, xx : Float, yy : Float) {
        enum class Type(n: String) { SMALL("star_small"), MEDIUM("star_medium"), LARGE("star_large"); val region = n }
        var type = t
        var x = xx
        var y = yy
    }

    val INITIAL_STARS = 50
    val MIN_STARS = 50
    val MAX_STARS = 76

    val stars = GdxArray<Star>()

    init {
        this.setSize(Gdx.app.graphics.width + 0f, Gdx.app.graphics.height + 0f)

        for(i in 1..INITIAL_STARS) {
            val rnd = random(0, 100)
            var type =
                when(rnd) {
                    in 0..60 -> Star.Type.SMALL
                    in 61..90 -> Star.Type.MEDIUM
                    else -> Star.Type.LARGE
                }
            stars.add(Star(type, random(0, Gdx.graphics.width) + 0f, random(Gdx.graphics.height) + 20f))
        }
    }

    fun addNew() {
        val rnd = random(0, 100)
        var type =
            when(rnd) {
                in 0..60 -> Star.Type.SMALL
                in 61..90 -> Star.Type.MEDIUM
                else -> Star.Type.LARGE
            }
        stars.add(Star(type, random(0, Gdx.graphics.width) + 0f, Gdx.graphics.height + 20f))
    }

    override fun act(delta: Float) {
        super.act(delta)

        return

        if(stars.size < MIN_STARS) {
            addNew()
        }

        if(random(0, 100) <= 10 && stars.size < MAX_STARS) addNew()

        val iter = stars.iterator()
        while(iter.hasNext()) {
            val s = iter.next()
            if(s.y <= 10) {
                iter.remove()
            } else {
                s.y -= STAR_SPEED
            }
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        for(s in stars) {
            batch!!.draw(D6.getAtlas().findRegion(s.type.region), s.x, s.y)
        }
    }
}