package uz.adkhamjon.uzmobileussd.fragments.xizmat

class XizmatModel {

    var name:String?=null
    var description:String?=null
    var urlLink:String?=null

    constructor()
    constructor(name: String?, description: String?, urlLink: String?) {
        this.name = name
        this.description = description
        this.urlLink = urlLink
    }
}