package uz.adkhamjon.uzmobileussd.fragments.tarif

class TarifModel {
   var name:String?=null
    var imgUrl:String?=null
    var payment:String?=null
    var minuteLimitMonth:String?=null
    var minuteLimitNetwork:String?=null
    var internetLimit:String?=null
    var smsLimit:String?=null

    constructor(
        name: String?,
        imgUrl: String,
        payment: String?,
        minuteLimitMonth: String?,
        minuteLimitNetwork: String?,
        internetLimit: String?,
        smsLimit: String?
    ) {
        this.name = name
        this.imgUrl = imgUrl
        this.payment = payment
        this.minuteLimitMonth = minuteLimitMonth
        this.minuteLimitNetwork = minuteLimitNetwork
        this.internetLimit = internetLimit
        this.smsLimit = smsLimit
    }
    constructor()
}