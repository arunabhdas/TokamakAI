package pro.tokamak.mobile.android.tokamakai.model

/*
 * A fake repository for model data.
 */
object ReflectRepo {
    val model = createInitialModel()
}

private fun createInitialModel(): ReflectModel {
    val model = ReflectModel()

    val callMom = Tracker(
        emoji = "📞",
        name = "Call Mom",
        type = TrackerType.BOOLEAN,
    )

    val sleepQuality = Tracker(
        emoji = "😴",
        name = "Sleep quality",
        type = TrackerType.RANGE,
        minValue = 0,
        maxValue = 10,
    )

    val mood = Tracker(
        emoji = "😊",
        name = "Mood",
        type = TrackerType.RANGE,
        minValue = 0,
        maxValue = 5,
    )

    val drankWater = Tracker(
        emoji = "💧",
        name = "Drank water",
        type = TrackerType.COUNT,
    )

    model.apply {
        addTracker(callMom)
        addTracker(sleepQuality)
        addTracker(mood)
        addTracker(drankWater)
    }

    model.today().apply {
        dataFor(callMom)?.value = 1
        dataFor(sleepQuality)?.value = 7
        dataFor(mood)?.value = 7
        dataFor(drankWater)?.increment()
        dataFor(drankWater)?.increment()
        dataFor(drankWater)?.increment()
    }

    return model
}
