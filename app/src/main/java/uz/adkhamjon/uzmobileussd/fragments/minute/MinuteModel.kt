package uz.adkhamjon.uzmobileussd.fragments.minute

class MinuteModel {
    var name:String?=null
    var name2:String?=null
    var cost:String?=null
    var number:String?=null
    var deadline:String?=null
    var activation:String?=null
    var yechibOlsih:String?=null
    var takdimEtish:String?=null
    var type:String?=null
    constructor()
    constructor(
        name: String?,
        name2: String?,
        cost: String?,
        number: String?,
        deadline: String?,
        activation: String?,
        yechibOlsih: String?,
        takdimEtish: String?,
        type: String?
    ) {
        this.name = name
        this.name2 = name2
        this.cost = cost
        this.number = number
        this.deadline = deadline
        this.activation = activation
        this.yechibOlsih = yechibOlsih
        this.takdimEtish = takdimEtish
        this.type = type
    }


}