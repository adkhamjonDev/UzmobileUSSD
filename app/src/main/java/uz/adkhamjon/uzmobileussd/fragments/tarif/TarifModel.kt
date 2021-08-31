package uz.adkhamjon.uzmobileussd.fragments.tarif

class TarifModel {
    var imageUrl:String?=null
    var name:String?=null
    var monthlyPayment:Int?=null
    var Minute:Int?=null
    var networkMinute:Int?=null
    var internet:Int?=null
    var Sms:Int?=null
    var oneMinute:Int?=null
    var oneMb:Int?=null
    var oneSms:Int?=null
    var code:String?=null
    var mode:String?=null

    constructor()
        constructor(
        imageUrl: String?,
        name: String?,
        monthlyPayment: Int?,
        Minute: Int?,
        networkMinute: Int?,
        internet: Int?,
        Sms: Int?,
        oneMinute: Int?,
        oneMb: Int?,
        oneSms: Int?,
        code: String?,
        mode: String?
    ) {
        this.imageUrl = imageUrl
        this.name = name
        this.monthlyPayment = monthlyPayment
        this.Minute = Minute
        this.networkMinute = networkMinute
        this.internet = internet
        this.Sms = Sms
        this.oneMinute = oneMinute
        this.oneMb = oneMb
        this.oneSms = oneSms
        this.code = code
        this.mode = mode
    }


}