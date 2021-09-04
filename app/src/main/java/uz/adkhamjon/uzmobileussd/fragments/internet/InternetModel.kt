package uz.adkhamjon.uzmobileussd.fragments.internet

import java.io.Serializable

class InternetModel:Comparator<InternetModel> {
    var name:Int?=null
    var name2:String?=null
    var cost:String?=null
    var number:String?=null
    var deadline:String?=null
    var activation:String?=null
    var disable:String?=null
    var type:String?=null
    constructor()
    constructor(
        name: Int?,
        name2: String?,
        cost: String?,
        number: String?,
        deadline: String?,
        activation: String?,
        disable: String?,
        type: String?
    ) {
        this.name = name
        this.name2 = name2
        this.cost = cost
        this.number = number
        this.deadline = deadline
        this.activation = activation
        this.disable = disable
        this.type = type
    }

    override fun compare(p0: InternetModel?, p1: InternetModel?): Int {
            return p0?.name!!.compareTo(p1?.name!!)
    }
}