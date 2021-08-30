package uz.adkhamjon.uzmobileussd.utils

class SimCardRepository(private val simCardsDS: SimCardsDS) {
    fun getSimCards() = simCardsDS.getSimCards()

    fun isHave(simName: String): Boolean {
        var isHave = false
        for (sim in getSimCards()) {
            if (sim.length > simName.length) {
                if (sim.toLowerCase().startsWith(simName.toLowerCase())) {
                    isHave = true
                }
            } else {
                if (simName.toLowerCase().startsWith(sim.toLowerCase())) {
                    isHave = true
                }
            }
        }
        return isHave
    }

    fun getSlotNumber(simName: String): Int {
        getSimCards().forEachIndexed { index, s ->
            if (s.length > simName.length) {
                if (s.toLowerCase().startsWith(simName.toLowerCase())) {
                    return index
                }
            } else {
                if (simName.toLowerCase().startsWith(s.toLowerCase())) {
                    return index
                }
            }
        }
        return 0
    }
}