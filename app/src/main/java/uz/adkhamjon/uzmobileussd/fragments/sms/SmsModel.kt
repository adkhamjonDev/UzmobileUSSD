package uz.adkhamjon.uzmobileussd.fragments.sms

class SmsModel {
    var name:String?=null
    var cost:String?=null
    var number:String?=null
    var deadline:String?=null
    var enable:String?=null
    var disable:String?=null
    constructor()
    constructor(
        name: String?,
        cost: String?,
        number: String?,
        deadline: String?,
        enable: String?,
        disable: String?
    ) {
        this.name = name
        this.cost = cost
        this.number = number
        this.deadline = deadline
        this.enable = enable
        this.disable = disable
    }

}