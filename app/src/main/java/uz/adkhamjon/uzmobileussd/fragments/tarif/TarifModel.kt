package uz.adkhamjon.uzmobileussd.fragments.tarif

import android.text.BoringLayout
import java.io.Serializable

class TarifModel:Serializable {
   var name:String?=null
   var information:String?=null
    var imgUrl:String?=null
    var payment:String?=null
    var code:String?=null
    var minuteLimitMonth:String?=null
    var minuteLimitNetwork:String?=null
    var internetLimit:String?=null
    var smsLimit:String?=null
    var recommended:Boolean=false

    constructor()
    constructor(
        name: String?,
        information: String?,
        imgUrl: String?,
        payment: String?,
        code: String?,
        minuteLimitMonth: String?,
        minuteLimitNetwork: String?,
        internetLimit: String?,
        smsLimit: String?,
        recommended: Boolean
    ) {
        this.name = name
        this.information = information
        this.imgUrl = imgUrl
        this.payment = payment
        this.code = code
        this.minuteLimitMonth = minuteLimitMonth
        this.minuteLimitNetwork = minuteLimitNetwork
        this.internetLimit = internetLimit
        this.smsLimit = smsLimit
        this.recommended = recommended
    }


}